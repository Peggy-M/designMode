# DesingMode
## FactoryPattern



### 没有抽象工厂 noFactory

~~~ java
/**
 * @Author Peggy
 * @Date 2023-05-17 16:09
 **/
public class DeliverController {
    /**
     * 按照类型的不同发放商品
     * 奖品类型: 1 打折券 ,2 优酷会员,3 小礼品
     */
    public void awardToUser(AwardInfo awardInfo){
        
        if(awardInfo.getAwardTypes() == 1){ //打折券
            DiscountService discountService = new DiscountService();
            DiscountResult result = discountService.sendDiscount(awardInfo.getUid(),
                            awardInfo.getAwardNumber());
            System.out.println("打折券发放成功!"+ JSON.toJSON(result));
        }else if(awardInfo.getAwardTypes() == 2){ //优酷会员
            //获取用户手机号
            String bindMobile = awardInfo.getExtMap().get("phone");
            //调用service
            YouKuMemberService youKuMemberService = new YouKuMemberService();
            youKuMemberService.openMember(bindMobile,awardInfo.getAwardNumber());
            System.out.println("优酷会员发放成功!");
        }else if(awardInfo.getAwardTypes() == 3){
            /*小礼品 封装收货用户信息 */
            SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
            smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
                    smallGiftInfo.setOrderId(UUID.randomUUID().toString());
            smallGiftInfo.setRelAddress(awardInfo.getExtMap().get("adderss"));
                    SmallGiftService smallGiftService = new SmallGiftService();
            Boolean isSuccess = smallGiftService.giveSmallGift(smallGiftInfo);
            System.out.println("小礼品发放成功!" + isSuccess);
        }
        
    }
}

~~~

可以看到之前的这种架构，DeliverController的代码违反了开闭原则的规范，而且如果说有抽奖的接口出现了问题，那么进行重构的成本就会非常高。

除此之外代码中有一组if分支判断逻辑,现在看起来还可以,但是如果经历几次迭代和拓展,后续ifelse肯定还会增加，到时候接手这段代码的研发将会十分痛苦。

### 简单工厂 simpleFactory

![image-20230517200641556](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230517200641556.png)

~~~ java
/**
 * @Author Peggy
 * @Date 2023-05-17 17:46
 * 生成免费商品
 **/
public class FreeGoodsFactory {
    private static IFreeGoods iFreeGoods = null;

    //通过商品请求类型返回指定商品
    public static IFreeGoods getInstance(Integer awardType) {

        if (awardType == 1) { //打折券
            iFreeGoods = new DiscountFreeGoods();
        }
        if (awardType == 2) { //优酷会员
            iFreeGoods = new SmallGiftFreeGoods();
        }
        if (awardType == 3) { //小礼品
            iFreeGoods = new YouKuMemberFreeGoods();
        }
        return iFreeGoods;
    }
}
~~~

优点：封装了创建对象的过程，可以通过参数直接获取对象。把对象的创建和业务逻辑层分开，这样以后就避免了修改客户代码，如果要实现新产品直接修改工厂类，而不需要在原代码中修改，这样就降低了客户代码修改的可能性，更加容易扩展。  

缺点：增加新产品时还是需要修改工厂类的代码，违背了“开闭原则”。

### 工厂模式 factoryMethod

![image-20230517210526636](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230517210526636.png)

DeliverController_1

~~~ java
	/**
     * 按照类型的不同发放商品
     */
    public ResponseResult awardToUser(AwardInfo awardInfo) {

        /*************************工厂类对象的创建**************************/
        //从上面的代码实现来看，工厂类对象的创建逻辑又耦合进了 awardToUser() 方
        //法中，跟我们最初的代码版本非常相似,引入工厂方法非但没有解决问题，反倒
        //让设计变得更加复杂了。

        if (awardInfo.getAwardTypes() == 1) {
            freeGoodsFactory = new DiscoutFreeGoodsFactory();
        } else if (awardInfo.getAwardTypes() == 2) {
            freeGoodsFactory = new SamllGiftFreeGoodsFactory();
        }

        /***************************************************************/
        //为了解决这样的问题,我们可以创建一个工厂的工厂,去包装我们的工厂模式

        IFreeGoods freeGoods = freeGoodsFactory.getInstance();
        System.out.println("===== 工厂方法模式 ========");
        ResponseResult result = freeGoods.sendFreeGoods(awardInfo);
        return result;
    }
~~~

DeliverController_2

~~~ java
	
	/**
     * 按照类型的不同发放商品
     */
    public ResponseResult awardToUser(AwardInfo awardInfo) {

        //按照不同的类型获取对应工厂
        FfreeGoodsFactory = FreeGoodsFactoryMap.getPareserFactory(awardInfo.getAwardTypes());

        IFreeGoods freeGoods = freeGoodsFactory.getInstance();
        System.out.println("===== 工厂方法模式 ========");
        ResponseResult result = freeGoods.sendFreeGoods(awardInfo);
        return result;
    }
~~~

现在我们的代码已经基本上符合了开闭原则,当有新增的产品时,我们需要做的事情包括:

1. 创建新的产品类,并且让该产品实现抽象产品接口
2. 创建产品类对应的具体工厂,并让具体工厂实现抽象工厂
3. 将新的具体工厂对象,添加到FreeGoodsFactoryMap的cachedFactories中即可,需要改动的代码改动的非常少.  

工厂方法优缺点
优点：用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程；在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；
缺点：每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度。  

### 抽象工厂 

![image-20230517211308109](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230517211308109.png)

​	在上图中,每一个具体工厂可以生产属于一个产品族的所有产品,例如海尔工厂生产海尔电视机、海尔空调和海尔冰箱,所生产的产品又位于不同的产品等级结构中. 如果使用工厂方法模式,上图所示的结构需要提供9个具体工厂,而使用抽象工厂模式只需要提供3个具体工厂,极大减少了系统中类的个数。

抽象工厂模式(Abstract Factory Pattern) 原始定义：

***提供一个创建一系列相关或相互依赖对象的接口，而无须指定它们具体的类。***
抽象工厂模式为创建一组对象提供了解决方案.与工厂方法模式相比,***抽象工厂模式中的具体工厂不只是创建一种产品,而是负责创建一个产品族.***如下图  

![image-20230517211659116](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230517211659116.png)

**抽象工厂模式原理**  

在抽象工厂模式中,每一个具体工厂都提供了多个工厂方法,用于产生多种不同类型的产品。

这些产品构成了一个产品族。

![image-20230517211907682](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230517211907682.png)

抽象工厂模式的主要角色如下：

- 抽象工厂（Abstract Factory）：它声明了一种用于创建一族产品的方法,每一个方法对应一种产品。
- 具体工厂（Concrete Factory）：主要是实现抽象工厂中的多个抽象方法，完成具体产品的创建。
- 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能，抽象工厂模式有多个抽象产品。
- 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它 同具体工厂之间是多对一的关系。  
