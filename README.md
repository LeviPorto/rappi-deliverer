# RAPPI-EVALUATOR

## Motivation

To isolate all entities that is important to make order, rappi-deliverer was 
created. Considering that order requests (save order and find it) are, basically
, the heart of rappi application, that has many demand, i saw necessity of create that micro-service,
because of the quantity that requests (we can scale horizontally, creating many 
instances of it). Also, this micro-service uses NO-SQL database to find faster the 
information, instead of has more trustworthy data

# Domain

There are two entities: Order and OrderItem. Order was created to user can make any order, 
getting foods, promotions and combo of any restaurant. OrderItem was created by purpose of
control items of any Order, quantity and entity (combo, restaurant and promotion)

# Architecture and Technologies

rappi-deliverer is a micro-service, that is discovered by Eureka, to communicate with 
others micro-services. Made in Kotlin, using functional programming to clean code and 
make more predictable, feign to communicate with others micro-services, kafka to produce and consume
messages, redis to cache, mongo to store data. This micro-service uses event-driven 
pattern to communicate with others micro-services

# Flux

Base end point is {host}/deliverer/. To test, we considering localhost.
 
* We can send POST on /order, passing OrderWithItemsFoodDTO entity, that contains order
and ItemsFood of that order, in a body (see a code) - Create order and ItemsFood, sending
order was created message in kafka
* We can find by user, send GET order/user/{userId}.

This, together with manager, implements a functionality that send job demand, automatic,
to delivery man as order was sended and system has free deliverers.  


# Dependencies

* RAPPI-EUREKA
* RAPPI-CONFIG
* RAPPI-MANAGER
* RAPPI-TRACKER
* Redis
* Kafka
* Mongo


