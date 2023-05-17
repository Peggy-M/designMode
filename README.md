# DesingMode
## FactoryPattern
### noFactory

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

### simpleFactory

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

### factoryMethod



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
        FreeGoodsFactoryMap.getPareserFactory(awardInfo.getAwardTypes());

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
