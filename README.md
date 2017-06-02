#hj-server

#webApp接口处理系统
###Spring+SpringMVC+MyBatis，也是本平台的核心框架组成，搭建本平台主要做学习研究之用，期望打造成一个通用的IT能力开放平台。
基于maven构建
功能说明：
1、 
2、  数据库：mysql 5.5、c3p0数据库连接池; 
3、 持久层：MyBatis进行数据持久化;【Mybatis3.4.1】
4、 MVC:基于spring mvc注解;【SpringMVC4.3.5、Spring4.3.5】
5、  缓存：Redis进行数据缓存;
6、  文件流：apache fileupload进行通用的文件上传下载处理;
7、 日志：集成了Apache Log4j进行日志管理；
8、 定时任务：基于spring task，支持定时任务配置;
9、工具类：常用日期时间处理、通用异常处理机制、Jackson进行json数据处理、redis缓存、参数检查、加密、线程池等;
10、 服务治理：dubbo2.5.3
11、消息JMS：activemq 5.11.1

项目配置说明

    项目配置：application.properties，主要包含数据库配置、文件上传目录配置和redis服务器配置等；
 redis缓存:ApplicationContext_Cache.xml
 dubbo配置：ApplicationContext_Dubbo.xml
 mybatis: mybatis-config.xml
   消息配置：ApplicationContext_Jms.xml【activemq】
    日志配置：log4j.properties
    数据初始化：hjf.sql，创建数据库表及初始数据；

