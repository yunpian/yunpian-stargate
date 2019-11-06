# yunpianmvn-stargate-sg1

+ 该项目用作扩展stargate组件功能，如果你还不了解stargate请前往[Stargate](../readme.md)
+ 如果你想为stargate扩展功能请新建自己的分支后提交merge
+ 请这样命名你的分支名
  + feature/20190530-zhoukaifan-XXXXX
  + hotfix/20190530-zhoukaifan-XXXXX
+ 规范
  + 请遵守云片开发规范
  + 请保证与yunpian-stargate-core中的原生功能的兼容性
  + 请在com.yunpian.extend包下新建一个包开发你自己的模块
  + 请记得更新《更新日志》
+ 如何开发一个stargate扩展功能模块
  + [快速开始](./quickStart.md)
  + Demo
    + 参考com.yunpian.extend.demo包下
+ 你也可以建立自己的仓库维护自己的扩展包，但是请遵守以下三点
  + 在这里写上你自己维护扩展包的地址，让所有人都知道他的存在
  + 请保证与yunpian-stargate-core中的原生功能的兼容性
  + 请提供使用文档

#### 更新日志

+ [查看更新日志](./updateLog.md)

#### 模块列表
+ **StargateMsgBus消息总线**
  + ```java
    stargateConfig.addProcessAnnotationConsume(new StargateMsgBusAnnotationHandel(appName));
    stargateConfig.addProcessAnnotationProducer(new StargateMsgBusAnnotationHandel(appName));
    stargateConfig.addProcessMessageProducer(new StargateMsgBusMessageHandel());
    ```
  + ```java
    @StargateMsgBus("testBus",pip="a")
    public void testBus(@StargateBody TestVO testVO) {
      // TODO
    }
    ```
  + ```java
    @StargateMsgBus("testBus")
    void testBus(@StargateBody TestVO testVO,@StargateMsgBusPip String pip);
    ```
  + StargateMsgBusPip可以不要，这样的话总线不分管道，pip也不能加
+ **StargateTopicFormat生产者动态Topic**
  + ```java
    stargateConfig.addProcessAnnotationProducer(new StargateTopicFormatAnnotationHandel());
    stargateConfig.addProcessMessageProducer(new StargateTopicFormatProcessMessageProducer());
    ```
  + ```java
    //StargateTopicFormat前缀固定，#{code}将被替换成参数code的值，如果不是基本类型调用toString()方法
    @StargateMapper(group = "StargateTopicFormat_code", topic = "StargateTopicFormat#{code}")
    void stargateTopicFormat(@StargateBody TestVO testVO, @StargateTopicFormat("code") int code);
    ```
+ **StargateTagParam动态TAG**
  + ```java
    stargateConfig.addProcessAnnotationProducer(new TagParamAnnotationHandel());
    stargateConfig.addProcessMessageProducer(new TagParamMessage());
    ```
  + ```java
    @StargateMapper(group = "baseConsumer-tagparam-BBS", topic = "baseConsumer-tagparam", tag = "BBS")
      public void tagparamB(@StargateBody TestVO testVO) {
        //TODO
      }
    ```
  + ```java
    @StargateMapper("baseConsumer-tagparam")
    void tagparam(@StargateBody TestVO testVO, @StargateTagParam String tag);
    ```