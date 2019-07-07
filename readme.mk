SpringCload框架简单示例：
* gateway：网关路由服务
* client1: 微服务1
* client2: 微服务2
* server: eureka服务端
启动顺序：
1. 启动gateway项目
2. 启动server服务
3. 启动client1
4. 启动client2

访问：http://localhost:8761 进入eureka管理界面，可看到已经注册的服务
访问：http://localhost:8099/logService1/hello，(8099是gateway的端口)，请求经过gateway的路由，到达client1，由client1提供服务返回相关信息
访问：http://localhost:8099/logService2/hello，请求经过gateway的路由，到达client2，由client2提供服务返回相关信息