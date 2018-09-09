# 简介
该项目仅是springboot2 + jms(activeMq实现)的demo项目。在具体项目中请自行进行进一步封装。该demo中包含如下示例：
1. 简单消息（生产者发送消息，消费者接受消息）；
2. 模拟同步请求，测试中包含多线程压力测试；
3. 双向消息（生产者向消费者发送消息，消费者接受到消息后向生产者发送回复请求）。

目前demo中仅有quene的示例，topic示例请自行探索，方法大致相同。

# 启动步骤
1. 安装 activemq。具体安装方法可以百度。如果不需要进行特定设置，下载activemq后，解压运行其中的`\bin\win64\activemq.bat`即可。
2. 启动 consumer, 运行`ConsumerApplication`即可
3. 运行 provider 项目中 `HelloServiceTest` 的测试用例即可。