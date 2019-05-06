<center>java 线程的学习总结 </center>  

### java 关键字 synchronized 的总结：

* synchronized 给线程加锁，锁是有 Object 给的，一个 Object 只有一把锁,多个线程需要共用一把锁的时候就需要添加 synchronized
这个关键字用来表示锁唯一，只有持有相同对象的锁才可以顺利执行，否则等待。 Object 的 wait() 方法操作的是当前线程，目的是让当
前的线程释放锁。

* 关于锁是要判断处在那个线程中，线程拿到锁执行功能，对象提供锁，对象调用 wait 释放该线程所在的锁。

* 线程中 interrupt() 的方法的使用，当一个线程在循环的执行一个任务，往往需要一个中断的机制，当线程处于阻塞状态的时候线程
调用这个方法会有异常抛出，通常于 isInterrupted(),搭配使用，这个通常用在 while 循环当中作为标志位（true/false）,去跳出循坏条件！

* 线程的 join() 的原理是让当前线程的父线程释放持有当前线程对象的锁，父线程陷入阻塞状态，该线程正常运行！

* 线程的优先级是 1-10，优先级高的线程优先执行，线程分为用户线程和守护线程，用户线程执行完的时候，守护线程会自动关闭。

### java 原子类的详解

* 理解系统的底层类 Unsafe 的各个方法的使用，方便理解 CAS(compare and swap)

* 基本数据类型的原子类跟新 AtomicInteger,AtomicLong,功能类似，可以参照源码进行理解

* 基本数据类型的数组原子类 AtomicIntegerArray,AtomicLongArray,通过数组索引更改设置元素

* 引用数据类型的原子操作类 AtomicReference ,AtomicRefenceArray 操作引用数组类

* 多线程操作引用数据类型的字段 ,可以使用  AtomicReferenceFieldUpdater<T,V> : T 代表实际的引用类，V 要操作的字段的数据类型类  

        字段不可以是 final 
        字段不可以 static
        字段必须对  AtomicReferenceFieldUpdater<T,V> 有可见性
        字段必须是 volatile

* 多线程更新数据的时候，经过一番操作，数据可能会还原，可以添加 stamp ,可以确定数据被操作过：AtomicStampedReference<V> 类进行操作
类中的泛型 V 代表操作的数据类型，可以是基本数据类型也可以是引用数据类型,该类封装了一个内部类 Pair 其中有两个重要的参数反别是实际操作的数据类型V,
和一个整形的标记 stamp  




  


