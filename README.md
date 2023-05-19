# DesingMode
## 工厂模式 FactoryPattern

[工厂模式UML关联类图](https://www.processon.com/view/64658eed5356ee4ac310691b?fromnew=1#pc)

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

抽象工厂模式优点

1. 对于不同产品系列有比较多共性特征时，可以使用抽象工厂模式，有助于提升组件的复用性.
2. 当需要提升代码的扩展性并降低维护成本时，把对象的创建和使用过程分开，能有效地将代码统一到一个级别上
3. 解决跨平台带来的兼容性问题抽象工厂模式缺点增加新的产品等级结构麻烦,需要对原有结构进行较大的修改,甚至需要修改抽象层代码,这显然会带来较大不变,违背了开闭原则.  

## 建造者模式 builderPattern
建造者模式可以将部件和其组装过程分开，一步一步创建一个复杂的对象。用户只需要指定复杂对象的类型就可以得到该对象，而无须知道其内部的具体构造细节。
比如一辆汽车的制造，用户不用关心汽车制造的过程，用户不需要关心汽车的方向盘，车轮，发动机这些部件是如何拼接出一辆汽车的，建造者模式就是将这些部件进行内部的组装然后返给用户一辆汽车即可

关于建造者模式主要包含以下几个角色：
- 抽修建造者（Builder）: 这个对象主要实现的复杂的对象是哪些部分创建的，并不涉及到具体部件的创建
- 具体建造者 (ConcreteBuilder): 实现 Builder 的接口，完成复杂产品的各个部件的具体创建方法。在建造完成之后，提供一个方法，返回创建好的产品对象。
- 产品类(Product): 需要创建的复杂的对象（包含多个组成部分）
- 指挥类(Director):调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建(客户端一般只需要与指挥者进行交互)

![image-20230518094808910](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230518094808910.png)

### 生成案例：自行车的生产

#### 建造者模式-实现方式1

生产自行车是一个复杂的过程，它包含了车架，车座等组件的生产。而车架又有碳纤维，铝合金等材质的，车座有橡胶，真皮等材质。对于自行车的生产就可以使用建造者模式。这里Bike是产品，包含车架，车座等组件；
Builder是抽象建造者， MobikeBuilder和HelloBuilder是具体的建造者；Director是指挥者。类图如下：

实现过程:

客户端调用其建造者 Director 进行建造, Director 通过 Builder 抽象建造者的多肽形式指向具体的 Builder 实现（碳纤维自行车/铝合金自行车）。
其中关于具体的实现过程（车架的选型，坐垫的选型，轮毂的选型。。。） Client 客户端与 Director 指挥者不用去关心，交于 Builder 抽象建造者的具体实现去完成

![image-20230518110653431](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230518110653431.png)

#### 建造者模式-实现方式2

除了以上这种，客户端不用关心具体的内部实现细节的建造者模式，还有一种关于建造者模式的实现。

当一个类的需要传入的参数很多的时候，如果创建该类的实例，代码的可读性就会变的非常的差，而且很容易引入错误，此时就可以利用建造者模式进行重构。  

1. 构造方法创建复杂对象的问题  

> 构造方法如果参数过多,代码的可读性和易用性都会变差. 在使用构造函数时,很容易搞错参数的顺序,传递进去错误的参数值,导致很有隐蔽的BUG出现。

2. set 方法出现的中间结果的无状态的问题

比如以下的代码：

> 比如下面的代码, 创建对象后使用set 的方式，那就会导致在第一个 set
> 之后，对象处于无效状态
> Rectangle r = new Rectangle (); //无效状态
> r.setWidth(2); //无效状态
> r.setHeight(3); //有效状态  

3. set方法还破坏了"不可变对象"的密闭性  

> 不可变对象: 对象创建好了,就不能再修改内部的属性值,下面的client类
> 就是典型的不可变对象,创建好的连接对象不能再改动  

建造者方式实现

建造者使用步骤如下:

1. 目标类的构造方法要传入Builder对象
2. Builder建造者类位于目标类内部,并且使用static修饰
3. Builder建造者对象提供内置的各种set方法,注意set方法返回的是builder对象本身  
4. Builder建造者类提供build()方法实现目标对象的创建  

~~~ java
public class 目标类{
    //目标类的构造方法需要传入Builder对象
    public 目标类(Builder builder){
        
    } 
    public 返回值 业务方法(参数列表){
        
    } 
    //Builder建造者类位于目标类内部,并且使用static修
    public static class Builder(){
        //Builder建造者对象提供内置的各种set方法,注意set方法返回的是builder对象本身
        private String xxx;
        public Builder setXxx(String xxx){
            this.xxx = xxx;
            return this;
        } 
        //Builder建造者类提供build()方法实现目标对象的创建
        public 目标类 build(){
            //校验
            return new 目标类(this)；
        }
    }
}
~~~



~~~ JAVA
/**
 * @Author Peggy
 * @Date 2023-05-18 15:25
 * 建造者模式
 **/
public class RabbitMQClient {
    //私有构造方法
    private RabbitMQClient(Builder builder) {}

    @Data
    @Accessors(chain = true)
    public static class Builder {
        //属性密闭性,保证对象不可变
        private String host = "127.0.0.1";
        private int port = 5672;
        private int mode;
        private String exchange;
        private String queue;
        private boolean isDurable = true;
        int connectionTimeout = 1000;

        //返回建造好的复杂对象
        public RabbitMQClient build() {
            //首先进行校验
            if (mode == 1) { //工作队列模式不需要设计交换机,但队列名一定要有
                if (exchange != null) {
                    throw new RuntimeException("工作队列模式不需要交换机");
                }
                if (queue == null || queue.trim().equals("")) {
                    throw new RuntimeException("工作队列模式名不能为空");
                }
                if (isDurable == false) {
                    throw new RuntimeException("工作队列模式必须开启持久化");
                }
            } else if (mode == 2) { //路由模式必须设计交换机,但是不能设计队列
                if (exchange == null) {
                    throw new RuntimeException("路由模式下必须设置交换机");
                }
                if (queue != null) {
                    throw new RuntimeException("路由模式无须设计队列名称");
                }
            }
            return new RabbitMQClient(this);
        }
    }
    public void sendMessage(String msg) {
        System.out.println("发送消息......");
    }
}
~~~

所以总的来说，建造者模式其实是解决了两方面的主要问题

1. 由于如果对外不开放其 set 方法，在通过有构造创建对象的时候，参数过多就可能会出现参数的混乱
2. 当对外开放其 set 方法就有可能出现一些对象当前的空状态无效的情况，无法在进行传参的时候进行拦截

但是如果使用期建造者模式-将其 set 方法封装在静态内部类当中，内部类的 build 方法其传入的状态值进行校验，返回其目标对象的创建。

### 建造者模式于工厂模式之间的区别

前的关于建造者模式的两个案例感觉于工厂模式好像其大同小异的，但其实他们具体的区别在于实现上的细度不同。

工厂模式-抽奖案例 ：

- 用户只需要调用其按照不同的种类进行抽奖，返回其对应的奖品（优惠价/实体奖品/奖金）

建造者模式-自行车案例：

- 用户需要调用其生产不同的自行车（碳纤维/铝合金）

建造者模式-目标对象案例：

- 用户通过传入不同参数获取具体的目标对象

两者对比：

- 工厂模式是用来创建不同但是相关类型的对象（继承同一父类或者接口的一组子类），由给定的参数来决定创建哪种类型的对象。
- 建造者模式是用来创建一种类型的复杂对象，通过设置不同的可选参数，“定制化”地创建不同的对象。

> 举例: 
>
> 顾客走进一家餐馆点餐，我们利用工厂模式，根据用户不同的选择，来制作不同的食物，比 如披萨、汉堡、沙拉。对于披萨来说，用户又有各种配料可以定制，比如奶酪、西红柿、起 司，我们通过建造者模式根据用户选择的不同配料来制作披萨。  

应用场景：

- 建造者（Builder）模式创建的是复杂对象，其产品的各个部分经常面临着剧烈的变化，但将它们组合在一起的算法却相对稳定，所以它通常在以下场合使用。  
  - 创建的对象较复杂，由多个部件构成，各部件面临着复杂的变化，但构件间的建造顺序是稳定的。
  - 创建复杂对象的算法独立于该对象的组成部分以及它们的装配方式，即产品的构建过程和最终的表示是独立的。  

## 原型模式 prototypePattern

### 原型模式原理与介绍

原型模式其实就是用一个***已经创建的实例作为原型***，通过***复制该原型对象***来创建一个***和原型对象相同的新对象***。

> 比如在《西游记》当中的猴哥用自己的猴毛克隆了多个与自己一模一样的本体，这就是对象原型，而猴哥（克隆本体）就是原型，变出千千万万个克隆的对象。

### 原型模式主要解决的问题

如果说一个对象内部是足够复杂，对象足够大，内部的数据是要通过大量的精密计算才能得到的，或者说需要通过 RPC 的接口或者数据库等比较慢的 IO 中获取，这种情况我们就需要用到原型模式。

直接从原有的现成对象模型当中克隆一份，就不需要每一次都需要创建一个新的对象，进行一些费事的操作，可以节省大量的时间与资源。

### 原型模式的角色

抽象原型类(Prototype)：它是声明克隆方法的接口,是所有具体原型类的公共父类,它可以是抽象类也可以是接口

具体原型类(ConcretePrototype)：实现在抽象原型类中声明的克隆方法,在克隆方法中返回自己的一个克隆对象

客户类(Client)：在客户类中,让一个原型对象克隆自身从而创建一个新的对象，由于客户类针对抽象原型类Prototype编程.因此用户可以根据需要选择具体原型类,系统具有较好的扩展性,增加或者替换具体原型类都比较方便 。

![image-20230519095441702](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230519095441702.png)

### 深克隆与浅克隆

- 浅克隆

  ![image-20230519104633381](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230519104633381.png)

- 深克隆

  ![image-20230519104640551](https://peggy-note.oss-cn-hangzhou.aliyuncs.com/images/image-20230519104640551.png)



在 Java 当中 Object 类提供了 clone() 方法实现了 **浅克隆** 。

​	需要注意的一点就是，如果要使得一个克隆对象的 Java 类支持被克隆复制，那么改克隆的对象类就必须实现一个表示接口 Cloenable ，用来表示该 Java 类支持被复制。

​	Cloenable 接口就是上面原型模式角色当中的抽象接口原型 (Prototype) ，而其被克隆的对象类就是具体原型类 (ConcretePrototype) ，而用户端（Client）调用需要克隆的原型类中的方法 clone() 生成其原型对象。 

~~~ java
/**
 * @Author Peggy
 * @Date 2023-05-19 10:54
 * 克隆对象 A
 **/
public class ConcretePrototypeA implements Cloneable {

    public User user = new User();

    public ConcretePrototypeA() {
        System.out.println("具体原型对象创建完成");
        user.setName("卡卡罗特");
        user.setAge(18);
    }

    @Override
    public ConcretePrototypeA clone() throws CloneNotSupportedException {
        System.out.println("具体原型对象复制成功");
        return (ConcretePrototypeA) super.clone();
    }
}
~~~



~~~ java
    @Test
    public void testCloen1() throws CloneNotSupportedException {
        ConcretePrototypeA c1 = new ConcretePrototypeA();
        c1.setUser(new User("卡卡罗特", 18));

        ConcretePrototypeA c2 = c1.clone();
        c2.user.setName("贝吉塔");
        c2.user.setAge(19);

        System.out.println("对象 c1 和 c2 是用一个对象？" + (c1 == c2));

        System.out.println("对象 c1 和 c2 内部的 user 是同一个对象？" + (c1.user == c2.user));
        System.out.println("对象 c1.user "+c1.getUser());
        System.out.println("对象 c2.user "+c2.getUser());
    }
~~~

> 具体原型对象创建完成
> 具体原型对象复制成功
> 对象 c1 和 c2 是用一个对象？false
> 对象 c1 和 c2 内部的 user 是同一个对象？true
> 对象 c1.user User(name=贝吉塔, age=19)
> 对象 c2.user User(name=贝吉塔, age=19)

可以看到浅拷贝中的对象是指向的同一个引用。

如果有需求场景中不允许共享同一对象，那么就需要使用深拷贝，如果想要进行深拷贝需要使用到对象序列化流 (对象序列化之后,再进行反序列化获取到的是不同对象)。

~~~ java
@Test
    public void testCloen2() throws Exception {

        ConcretePrototypeA c1 = new ConcretePrototypeA();

        c1.setUser(new User("卡卡罗特", 18));

        //创建序列化输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c.txt"));
        //将 c1 写入到文件当中
        oos.writeObject(c1);
        oos.close();

        //创建对象序列化输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c.txt"));
        //读取对象
        ConcretePrototypeA c2 = (ConcretePrototypeA) ois.readObject();
        c2.user.setName("贝吉塔");
        c2.user.setAge(19);

        System.out.println("对象 c1 和 c2 是用一个对象？" + (c1 == c2));
        System.out.println("对象 c1 和 c2 内部的 user 是同一个对象？" + (c1.user == c2.user));
        System.out.println("对象 c1.user "+c1.getUser());
        System.out.println("对象 c2.user "+c2.getUser());
    }
~~~

> 具体原型对象创建完成
> 具体原型对象复制成功
> 对象 c1 和 c2 是用一个对象？false
> 对象 c1 和 c2 内部的 user 是同一个对象？true
> 对象 c1.user User(name=贝吉塔, age=19)
> 对象 c2.user User(name=贝吉塔, age=19)

利用 Cloenable 接口实现进行克隆有些许的麻烦，所以一般推荐使用 ApacheCommons与springframework 来实现对象原型的克隆

- 浅克隆： BeanUtils.cloneBean(Objectobj);BeanUtils.copyProperties(S,T);
- 深克隆： SerializationUtils.clone(T object);  

BeanUtils 是利用反射原理获得所有类可见的属性和方法，然后复制到 target 类。 SerializationUtils.clone() 就是使用我们的前面讲的序列化实现深克隆，当然你要把要克隆的类实现Serialization 接口。  

### 原型模式应用实例 
