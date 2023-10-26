# TIL

🔥 Cached pool beats fixed pool in all case.

Single work is done in 55s. (`RunSingleWork`)

## Cached pool (no limit) - `RunPackedWork`

 | Multiplier | Time |
|------------|------|
| x1         | 56s  |
| x2         | 55s  |
| x3         | 56s  |
| x4         | 56s  |
| x5         | 55s  |
| x6         | 58s  |
| x7         | 60s  |
 | x8         | 61s  |
 | x9         | 65s  |
 | x10        | 68s  |
 | x11        | 67s  |
 | x12        | 71s  |
 | x13        | 80s  |
 | x14        | 93s  |
 | x15        | 98s  |
 | x16        | 105s |
 | x17        | 113s |
 | x18        | 118s |
 | x19        | 123s |

## Fixed pool - `RunPackedWork`

| Multiplier | Time |
|------------|------|
| x1         | 56s  |
| x2         | 128s |
| x3         | 190s |

🔥 10 x CPUs in action: 

