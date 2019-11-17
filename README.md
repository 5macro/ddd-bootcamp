# 项目简介
本项目是订单子系统，用于向用户展示产品并接受用户订单。

# 本地构建
|功能|命令|备注|
| --- | --- | --- |
|生成IntelliJ工程|`./idea.sh`|自动打开IntelliJ|
|本地运行|`./run.sh`|监听5005调试端口|
|本地构建|`./local-build.sh`|运行所有类型的自动化测试|
|停止MySQL|`./gradlew composeDown`|将清空所有数据|
|手动启动MySQL|`./gradlew composeUp`||
