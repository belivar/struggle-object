## Spring AOP
### 1.概述
  AOP(Aspect-Oriented Programming)面向切面编程，可以说是OOP的补充和完善。OOP引入了封装、继承和多态等概念来建立一种对象层次结构。
实现AOP的技术，主要分为两大类：一是采用动态代理技术，利用截取消息的方式，对该消息进行装饰，以取代原有对象行为的执行；二是采用静态织入的方式，引入特定的语法创建“方面”，从而使得编译器可以在编译期间织入有关“方面”的代码。<br>
  AOP体系结构把与AOP相关的概念大致分成由高到低、从使用到实现的三个层次。从上到下，最高层次是语言和开发环境，在这个环境中可以看到几个中重要的概念：<br>
  基础(base)：待增强对象过着说是目标对象；<br>
  切面(aspect)：对于基础的增强应用；<br>
  配置(configuration)：提供配置环境，把基础和切面结合起来，从而完成对目标对象的编织。<br>



### 2.AOP相关概念
-  横切关注点： 对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点；

-  切面（Aspect）：类是对物体特征的抽象，切面就是对横切关注点的抽象

-  连接点（Joinpoint）: 程序执行过程中明确的点，如方法的调用或特定的异常被抛出。

-  通知（Advice）: 定义在连接点做什么，为切面增强提供织入接口，在特定的连接点，AOP框架执行的动作。各种类型的通知包括“around”、“before”和“throws”通知。通知类型将在下面讨论。许多AOP框架包括Spring都是以拦截器做通知模型，维护一个“围绕”连接点的拦截器链。Spring中定义了四个advice: BeforeAdvice, AfterAdvice, ThrowAdvice和DynamicIntroductionAdvice
```Java
/*
** before 方法的实现在Advice中被配置到目标方法以后，会在调用目标方法时被调用
** @method 目标方法的反射对象
** @args 目标方法的输入参数
** @target
*/
void before(Method method, Object[] args, Object target) throws Throwable
```

-  切入点（Pointcut）: 决定Advice通知应该作用于哪个连接点，也就是说通过Pointcut来定义需要增强的方法的集合，这些集合的选取可以按照一定的规定来完成。指定一个通知将被引发的一系列连接点的集合，AOP框架必须允许开发者指定切入点。例如，使用正则表达式。 Spring定义了Pointcut接口，用来组合MethodMatcher和ClassFilter，可以通过名字很清楚的理解， MethodMatcher是用来检查目标类的方法是否可以被应用此通知，而ClassFilter是用来检查Pointcut是否应该应用到目标类上

-  引入（Introduction）: 添加方法或字段到被通知的类。 Spring允许引入新的接口到任何被通知的对象。例如，你可以使用一个引入使任何对象实现 IsModified接口，来简化缓存。Spring中要使用Introduction, 可有通过DelegatingIntroductionInterceptor来实现通知，通过DefaultIntroductionAdvisor来配置Advice和代理类要实现的接口

-  目标对象（Target Object）: 包含连接点的对象。也被称作被通知或被代理对象。POJO

-  AOP代理（AOP Proxy）: AOP框架创建的对象，包含通知。 在Spring中，AOP代理可以是JDK动态代理或者CGLIB代理。

-  织入（Weaving）: 组装方面来创建一个被通知对象。这可以在编译时完成（例如使用AspectJ编译器），也可以在运行时完成。Spring和其他纯Java AOP框架一样，在运行时完成织入。

### 3.设计实现
1. JVM的动态代理特性
  核心技术是动态代理，这种动态代理是JDK的一个特性，可以为任意Java对象创建动态对象，复习[代理模式]()。
  
