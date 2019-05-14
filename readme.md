java 线程的学习总结
------
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

* 引用数据类型的原子操作类 AtomicReference ,AtomicReferenceArray 操作引用数组类

* 多线程操作引用数据类型的字段 ,可以使用  AtomicReferenceFieldUpdater<T,V> : T 代表实际的引用类，V 要操作的字段的数据类型类  

        字段不可以是 final 
        字段不可以 static
        字段必须对  AtomicReferenceFieldUpdater<T,V> 有可见性
        字段必须是 volatile

* 多线程更新数据的时候，经过一番操作，数据可能会还原，可以添加 stamp ,可以确定数据被操作过：AtomicStampedReference<V> 类进行操作
类中的泛型 V 代表操作的数据类型，可以是基本数据类型也可以是引用数据类型,该类封装了一个内部类 Pair 其中有两个重要的参数反别是实际操作的数据类型V,和一个整形的标记 stamp  

### JUC 包中的锁(刚学习难度较大，先会用，然后研究)
* JUC 包中的几个重要的类关系图，如下图所示:  

![] (./img/juc.jpg)

* 一个重要的类 AbstractQueuedSynchronizer 该类简称 AQS 是 JUC 保重一个非常重要的类，理解 JUC 必须阅读该类的源码，熟悉运作机制

* ReenTrantLock 是一个独占锁，有公平锁和非公平锁，常用的方法 lock(),unlock()),默认构造方法是非公平锁

* ReenTrantReadWriteLock 中有两个锁读取锁 ReadLock, 写入锁 WriteLock,读取锁可以允许多线程同时获取锁没有限制，写入锁同一时间只有一个线程获取到锁，其它线程等待

* Condition 类常用方法 await(),用来暂停当前线程，让出 cpu 执行权力给其它线程，其它线程使用 Condition 的 signal()/signalAll(),去恢复暂停的线程执行,condition 的实例通过 Lock 类以及子类可以获取得到
,该类同 Lock 的子类们一起使用，该类的学习可以类比 Object 的 wait() 和 notify()/notifyAll().

* LockSupport 用来暂停线程的执行，常用方法 park() 和 unpark() ,使用了底层的 UnSafe 类处理线程的暂停和开始。

* CountDownLatch 是线程计数器，常用方法 countDown(),await(),countDown () 用来做减法操作，await() 方法用来暂停当前线程，直到计数器是0的时候开始执行

* ClyclicBarrier 也有一个类似计数器的东西，除默认的构造方法外（带有 parties 参数），还有有一个构造方法 public CyclicBarrier(int parties, Runnable barrierAction),常用方法有
await(),在线程数达到 parties 之前该方法会一直阻塞当前线程，当线程数达到 parties ,将会执行构造方法中的 Runnable 中定义的任务，然后所有线程将会执行下去。该类可以重复使用（线程数达到 parties 之后，
如果还有线程,后面的线程数重新计数，直到数目达到 parties，才会运行）

* Semaphore 称作信号量，常用的方法是 acquire(int permits) ,线程用来获取该指定数量的信号量，当信号量不足线程将会被阻塞，每个线程得到信号量需要使用方法release(int permits)去释放信号量，返还信号量。






  


