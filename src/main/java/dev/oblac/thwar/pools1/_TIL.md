# TIL

🔥 If you use a queue that does not block (`LinkedBlockingQueue`), the max threads setting has no effect, only core threads are used.

Run with:

```java
var pool = new BoundedCachedThreadPool(10, false);
```

The threads in the console are named from `1` to `10` in both runs.

🔥 Threads are allocated all the time, even if they are not used. We need to `allowCoreThreadTimeOut(true);`

Run with:

```java
var pool = new BoundedCachedThreadPool(10, true);
```

The threads in the first run are named `1-10`, the threads in the second run are named `11-20`. During the wait, threads have been time-outed and removed from the pool.

🔥 Setting `0` is equal to having a single threaded pool. Since the max threads value is not used, only the core size is used, which is `0`, and that means a single thread.

Run with:

```java
var pool = new BoundedCachedThreadPool(0, true);
```

Only one thread is used in both runs.