#设计模式
## 1.分类
-  创建型模式：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式
-  结构型模式：适配者模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式
-  行为型模式：策略模式、模版方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
-  其他模式：并发模式、线程池模式

![设计模式之间的关系](https://github.com/YinglishZhi/struggle-object/blob/master/img/1_1设计模式之间的关系.jpg)

## 2.设计模式六大原则

**总原则：开闭原则(Open Close Principle)**

开闭原则就是说对**扩展开放，对修改关闭**。在程序需要进行拓展的时候，不能去修改原有的代码，而是要扩展原有代码，实现一个热插拔的效果。所以一句话概括就是：为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类等，后面的具体设计中我们会提到这点。

**1. 单一职责原则**

不要存在多于一个导致类变更的原因，也就是说每个类应该实现单一的职责，如若不然，就应该把类拆分。

**2. 里氏替换原则（Liskov Substitution Principle）**

里氏代换原则(Liskov Substitution Principle LSP)面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。 LSP是继承复用的基石，只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。里氏代换原则是对“开-闭”原则的补充。实现“开-闭”原则的关键步骤就是抽象化。而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。<br>
历史替换原则中，子类对父类的方法尽量不要重写和重载。因为父类代表了定义好的结构，通过这个规范的接口与外界交互，子类不应该随便破坏它。

**3. 依赖倒转原则（Dependence Inversion Principle）**

这个是开闭原则的基础，具体内容：面向接口编程，依赖于抽象而不依赖于具体。写代码时用到具体类时，不与具体类交互，而与具体类的上层接口交互。

**4. 接口隔离原则（Interface Segregation Principle）**

这个原则的意思是：每个接口中不存在子类用不到却必须实现的方法，如果不然，就要将接口拆分。使用多个隔离的接口，比使用单个接口（多个接口方法集合到一个的接口）要好。

**5. 迪米特法则（最少知道原则）（Demeter Principle）**

就是说：一个类对自己依赖的类知道的越少越好。也就是说无论被依赖的类多么复杂，都应该将逻辑封装在方法的内部，通过public方法提供给外部。这样当被依赖的类变化时，才能最小的影响该类。

最少知道原则的另一个表达方式是：只与直接的朋友通信。类之间只要有耦合关系，就叫朋友关系。耦合分为依赖、关联、聚合、组合等。我们称出现为成员变量、方法参数、方法返回值中的类为直接朋友。局部变量、临时变量则不是直接的朋友。我们要求陌生的类不要作为局部变量出现在类中。

**6. 合成复用原则（Composite Reuse Principle）**

原则是尽量首先使用合成/聚合的方式，而不是使用继承。

## 3.二十三种设计模式
-  **创建型模式**

首先，简单工厂模式<font color="red">不属于</font>23中涉及模式，简单工厂一般分为：普通简单工厂、多方法简单工厂、静态方法简单工厂。

0. 简单工厂模式

简单工厂模式分为三种：

(1). 普通

就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。

(2). 多方法

(3). 静态方法

1. 工厂方法模式(Factory Method)

在工厂模式中，创建对象时不会对客户端暴露创建逻辑，并且是通过使用一个共同的接口来指向新创建的对象。

**意图** 定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
**主要解决** 主要解决接口选择的问题。

**何时使用** 明确地计划不同条件下创建不同实例时。

**优点**
- 一个调用者想创建一个对象，只要知道其名称就可以了
- 扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以
- 屏蔽产品的具体实现，调用者只关心产品的接口。

**缺点**
- 每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，在一定程度上增加了系统的*复杂*度，同时也增加了系统具体类的依赖。这并不是什么好事。

![工厂方法模式](https://github.com/YinglishZhi/struggle-object/blob/master/img/工厂模式.png)

```Java
public interface Product {
   void func();
}
```
```Java
public class ConcreateProduct1 implements Product{
  void func(){
    System.out.println("ConcreateProduct1");
  }
}

public class ConcreateProduct2 implements Product{
  void func(){
    System.out.println("ConcreateProduct2");
  }
}

public class ConcreateProduct3 implements Product{
  void func(){
    System.out.println("ConcreateProduct3");
  }
}
```

```Java
public class ConcreateFactory{
  public Product getProduct(String ClassName){
    Product p = null;
    try{
      p = (Product)Class.forName(ClassName).newInstance();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return p;
  }
}
```
客户端代码
```Java
public static void main(String[] args) {
  Product p = ConcreateFactory.getProduct(ConcreateProduct1.class.getName());
}

```

2. 抽象工厂模式

3. 单例模式(Singleton)

**意图** 保证一个类仅有一个实例，并提供一个访问它的全局访问点。

**主要解决** 一个全局使用的类频繁地创建与销毁。

**何时使用** 当您想控制实例数目，节省系统资源的时候。

**优点**
- 在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例
- 避免对资源的多重占用（比如写文件操作）。

**缺点**
- 没有接口，不能继承，与单一职责原则冲突，一个类应该只关心内部逻辑，而不关心外面怎么样来实例化。

-[单例模式]()

懒汉式：只有第一次调用的时候才会去初始化，避免了内存的浪费

- 懒汉式（线程不安全）
  懒加载、线程不安全
```Java
Class Singleton{

  //持有私有静态实例，防止被引用，实现延迟加载
  private static Singleton instance;

  //私有构造方法，防止被实例化
  private Singleton(){

  }
  //创建实例
  public static Singleton getInstance(){
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}
```

- 懒汉式（线程安全）
懒加载、线程安全

必须加锁才能保证单例，但是加锁会影响效率
```Java
Class Singleton{
private static Singleton instance;

private Singleton(){

}
//防止由于多线程可能产生的冗余对象，加上synchronized，但是效率咔咔降。每次调用getInstance都上锁 mmd！
public static synchronized Singleton getInstance(){
  if (instance == null) {
    instance = new Singleton();
  }
  return instance;
}
}
```

饿汉式： 没有加锁，会提升工作效率

- 饿汉式 不是懒加载，线程安全

类加载就会初始化，浪费内存，很容易产生垃圾对象。它基于 classloder 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。

```Java
Class Singleton{
  private static Singleton instance = new Singleton();

  private Singleton(){

  }
  public static Singleton getInstance(){
    return instance;
  }

}

```

双检锁/双重校验锁（DCL，即 double-checked locking）

JDK版本要求1.5以上，懒加载，线程安全，但是实现比较复杂

```Java
Class Singleton{
  private volatile static Singleton singleton;

  private Singleton(){

  }

  public static Singleton getInstance(){
    if (singleton == null) {
      synchronized (Singleton.class){
        if (singleton == null) {
          singleton = new Singleton();
        }
      }
    }
    return singleton;
  }
}
```

**习惯** 懒汉式用的比较少，饿汉式用的多，B事多的时候可以用双检锁方式。

4. 建造者模式(Builder)

5. 原型模式(Prototype)

**意图** 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。

**主要解决** 在运行期建立和删除原型。

**何时使用**
- 当一个系统应该独立于它的产品创建，构成和表示时
- 当要实例化的类是在运行时刻指定时，例如，通过动态装载
- 为了避免创建一个与产品类层次平行的工厂类层次时
- 当一个类的实例只能有几个不同状态组合中的一种时。建立相应数目的原型并克隆它们可能比每次用合适的状态手工实例化该类更方便一些。
**优点**
- 性能提高
- 逃避构造函数的约束。

**缺点**
- 配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，但对于已有的类不一定很容易，特别当一个类引用不支持串行化的间接对象，或者引用含有循环结构的时候
- 必须实现 Cloneable 接口

-[原型模式]()

原型模式主要包含如下三个角色：
- Prototype：抽象原型类。声明克隆自身的接口。
- ConcretePrototype：具体原型类。实现克隆的具体操作。
- Client：客户类。让一个原型克隆自身，从而获得一个新的对象。

```Java
public abstract class Prototype implements Cloneable{
  private int id;

  abstract void draw();

  public String getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public Object clone() {
      Object clone = null;
      try {
         clone = super.clone();
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      return clone;
   }
}

public class ConcretePrototype1 extends Prototype{
  public ConcretePrototype1(){

  }

  public void draw(){
    System.out.println("ConcretePrototype1");
  }
}

public class Clienr(){
  ConcretePrototype1 c = new ConcretePrototype1();
  c.setId(1);

  Prototype clone = c.clone();
}

```

原型模式涉及到[浅拷贝和深拷贝]()

**瞎BB**
- 原型模式向客户隐藏了创建对象的复杂性。客户只需要知道要创建对象的类型，然后通过请求就可以获得和该对象一模一样的新对象，无须知道具体的创建过程。
- 克隆分为浅克隆和深克隆两种。
- 我们虽然可以利用原型模式来获得一个新对象，但有时对象的复制可能会相当的复杂，比如深克隆。

-  **结构型模式**

6. 适配器模式

7. 装饰模式(Decorator)

8. 代理模式(Proxy)

一个类代表另一个类的功能，创建具有现有对象的对象，向外界提供功能接口

**意图** 为其他对象提供一种代理以控制对这个对象的访问。

**主要解决** 在直接访问对象时带来的问题，比如说：要访问的对象在远程的机器上。在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），直接访问会给使用者或者系统结构带来很多麻烦，我们可以在访问此对象时加上一个对此对象的访问层。

**何时使用** 想在访问一个类时做一些控制。

**优点**
- 职责清晰
- 高扩展性
- 智能化

**缺点**
- 由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度*变慢*
- 实现代理模式需要额外的工作，有些代理模式的实现非常*复杂*

![代理模式结构图](https://github.com/YinglishZhi/struggle-object/blob/master/img/代理模式.png)

```Java
/**
* Subject类， 定义了RealSubject 和 Proxy 的共用接口，
*/
Interface Subject{
  public void Request();
}
```

```Java
/**
* RealSubject类，真实请求体
*/
class RealSubject implements Subject{
  @Override
  public void Request(){
    System.out.println("真实的请求");
  }
}

```

```Java
class Proxy implements Subject{

  RealSubject realSubject;

  @Override
  public void Request(){
    if (realSubject == null) {
      realSubject = new RealSubject();
    }
    before();
    realSubject.Request();
    after();
  }

  public void before(){
    System.out.println("before");
  }

  public void after(){
    System.out.println("after");
  }
}
```
客户端代码
```Java
public static void main(String[] args) {
    Proxy proxy = new Proxy();
    proxy.Request();
}
```

**应用**

- 远程代理，为一个对象在不同的地址空间提供局部代表，可以隐藏一个对象存在于不同空间的事实。
- 虚拟代理，根据需要创建开销很大的对象。通过他来存放实例化需要很长时间的真实对象
- 安全代理，用来控制真实对象访问时候的权限
- 智能指引，是指当调用真实的对象时，代理另外一些事情

9. 外观模式(Facade)

10. 桥接模式(Bridge)

11. 组合模式(Composite)

12. 享元模式(Flyweight)

-  **行为型模式**

**父类与子类关系**

13. 策略模式(strategy)

14. 模板方法模式(Template Method)

**类之间的关系**

15. 观察者模式(Observer)

16. 迭代子模式(Iterator)

17. 责任链模式(Chain of Responsibility)

18. 命令模式(Command)

**类的状态**

19. 备忘录模式(Memento)

20. 状态模式(State)

**通过中间类**

21. 访问者模式(Visitor)

22. 中介者模式(Mediator)

23. 解释器模式(Interpreter)
