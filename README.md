🍃 Spring Data Redis

_**What Is Redis?**_
Redis is an open-source (BSD licensed), in-memory data structure store, used as a database, cache, and message broker. It supports data structures such as string, hashes, lists, sets, sorted sets with range queries, bitmaps, hyper logs, and geospatial indexes with radius queries.

_**Why Redis?**_
Redis is basically used for cache management. It reduces the client workload and speeds up the application.

_Let's discuss a scenario where Redis could be helpful._

**1) Calling an External Application API**

Your microservice is calling other application’s API in order to get some data. The call is made often, the data in response is huge, and you know the response data is not going to change often in the other application. In our case, we are calling one API and we get the response in 10000 to 15000 ms. It is not good for any application to wait for such a long time to get the response. In that case, Redis is helpful.

![alt text](https://github.com/eshanwp/spring-data-redis/blob/master/img/1.jpg)

We can cache the response in Redis with an expiration time so instead of the actual call, you will get the response from the Redis cache. In our case, we had set the expiration time of the data to 1 day. When the data expires, the call goes to the actual web service and refreshes the data in the cache. The next time, the data will be returned from the cache.

![alt text](https://github.com/eshanwp/spring-data-redis/blob/master/img/2.jpg)

**2) Frequently Querying the Reference Table or Master Table in the Database**

Another scenario is when you have a table in your database, and that table contains reference data that is not going to change frequently. You query that table often to get the data and populate the UI. In this scenario, you can also use Redis. Instead of querying the database each time from the disk, you can cache the table in Redis with an expiration time so the load time of your UI will be faster.

![alt text](https://github.com/eshanwp/spring-data-redis/blob/master/img/3.jpg)

Following technologies being used
* spring-data-redis
* jedis

