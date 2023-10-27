# TIL

🔥 Cached pool beats fixed pool in all case.

Single work is done in 55s. (`RunSingleWork`)

## Cached pool (no limit) - `RunPackedWork` and `RunSemaphoreWork`

 | Multiplier | Time | Semaphore | Virtual |
|------------|------|-----------|---------|
| x1         | 56s  | 56s       |         |
 | x2         | 55s  |           |         |
 | x3         | 56s  |           |         |
 | x4         | 56s  |           |         |
 | x5         | 55s  |           |         |
 | x6         | 58s  |           |         |
 | x7         | 60s  |           |         |
 | x8         | 61s  |           |         |
 | x9         | 65s  |           |         |
 | x10        | 68s  | 57s       | 57s     |
 | x11        | 67s  | 58s       | 58s     |
 | x12        | 71s  | 59s       | 58s     |
 | x13        | 80s  | 59s       | 59s     |
 | x14        | 93s  | 60s       | 60s     |
 | x15        | 98s  | 62s       | 61s     |
 | x16        | 105s | 65s       | 64s     |
 | x17        | 113s | 69s       | 68s     |
 | x18        | 118s | 73s       | 72s     |
 | x19        | 123s | 78s       | 78s     |

## Fixed pool - `RunPackedWork`

| Multiplier | Time |
|------------|------|
| x1         | 56s  |
| x2         | 128s |
| x3         | 190s |

🔥 10 x CPUs in action: 

