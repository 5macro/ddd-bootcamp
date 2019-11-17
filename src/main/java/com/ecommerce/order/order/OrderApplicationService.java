package com.ecommerce.order.order;

import com.ecommerce.order.common.ddd.ApplicationService;
import com.ecommerce.order.order.command.ChangeProductCountCommand;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderFactory;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.order.order.representation.OrderRepresentation;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecommerce.order.order.model.OrderId.orderId;

@Component
public class OrderApplicationService implements ApplicationService {
    private final OrderRepresentationService orderRepresentationService;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final OrderPaymentService orderPaymentService;

    public OrderApplicationService(OrderRepresentationService orderRepresentationService,
                                   OrderRepository orderRepository,
                                   OrderFactory orderFactory,
                                   OrderPaymentService orderPaymentService) {
        this.orderRepresentationService = orderRepresentationService;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderPaymentService = orderPaymentService;
    }

    @Transactional
    public OrderId createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(item.getProductId(),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        Order order = orderFactory.create(items, command.getAddress());
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    public OrderRepresentation byId(String id) {
        Order order = orderRepository.byId(orderId(id));
        return orderRepresentationService.toRepresentation(order);
    }

    @Transactional
    public void changeProductCount(String id, ChangeProductCountCommand command) {
        Order order = orderRepository.byId(orderId(id));
        order.changeProductCount(UUID.fromString(command.getProductId()), command.getCount());
        orderRepository.save(order);
    }

    @Transactional
    public void pay(String id, PayOrderCommand command) {
        Order order = orderRepository.byId(orderId(id));
        orderPaymentService.pay(order, command.getPaidPrice());
        orderRepository.save(order);
    }

    @Transactional
    public void changeAddressDetail(String id, String detail) {
        Order order = orderRepository.byId(orderId(id));
        order.changeAddressDetail(detail);
        orderRepository.save(order);
    }
}
