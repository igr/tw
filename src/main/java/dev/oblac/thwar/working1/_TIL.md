# TIL

🔥 N X CPU threads == CPU threads x N

Single work is done in 2.4s. (`RunSingleWork`)

## Cached pool (no limit) - `RunCompletableWork`

 | Threads | Time  | Description                              |
|---------|-------|------------------------------------------|
| x1      | 3.7s  | Slightly more, other threads are running |
| x2      | 6.7s  |                                          |
| x3      | 10.3s |                                          |
| x4      | 13s   |                                          |
| x5      | 16.4s |                                          |
| x6      | 19.4s |                                          |
| x7      | 23s   |                                          |

## Fixed pool - `RunCompletableWork`

| Threads | Time   | Description                              |
|---------|--------|------------------------------------------|
| x1      | 3.7s   | Slightly more, other threads are running |
| x2      | 6.8s   |                                          |
| x3      | 10.14s |                                          |
| x4      | 13.10s |                                          |
| x5      | 16.2s  |                                          |
| x6      | 19.3s  |                                          |
| x7      | 22.7s  |                                          |

🔥 100 x CPUs in action: 

![](100xCPU.png)

All threads are in Running state, but they all are starving.

🔥 Threads are starving when they can not run 100% of the time.

![](7x.png)

Here we see that CPU time is 2.9s = calculation time + some extra (probably context switching). However, it takes 23s to complete the task. CPU was doing other jobs for 88% of time.