# Spring Mysql Demo

## 项目基本说明
1. __Main.java__ 所在路径 `<root>\src\main\java\homework\Main.java` 
2. Spring 命令行启动需重载 __run __方法，故使用 __run __方法列出调用
3. 使用持久层框架：`hibernate` 
4. __源数据脚本__：以java代码的形式写在项目中 __InitService__ `<root>\src\main\java\homework\service\InitService.java`  已配置项目启动时自动建表
5. spring配置文件：`<root>\src\main\resources\application.properties` 
6. 记录的时间包含__网络时延__

## ER 关系图


![er.png | center | 291x589](https://cdn.nlark.com/yuque/0/2018/png/105047/1540449715576-12855001-ab8e-4033-9da0-0247eb5e4e54.png "")


## 数据库设计
1. 两个实体，一个关系。
    1. 用户（user）实体，套餐（scheme）实体，订单（订阅subscription）关系
    2. 用户 与 订单  oneToMany，用户有订单外键
    3. 订单 与 套餐 manyToOne，订单有套餐外键
2. __核心思想__
    1. 用户记录各种功能的使用时长
    2. 订单记录用户的订阅记录，通过设置 startAt 开始时间来解决立即有效和下月有效的问题，通过设置isActive来解决取消订单的问题
    3. 套餐记录所有免费功能和套餐价格

## 操作说明
### 操作1：查询用户订阅历史
#### 操作设计


![image.png | left | 683x202](https://cdn.nlark.com/yuque/0/2018/png/105047/1540450880212-091ab663-3987-4059-bcc0-72fe900a98ca.png "")

#### 运行截图与时间


![image.png | left | 683x77](https://cdn.nlark.com/yuque/0/2018/png/105047/1540450834027-5a2b6846-3655-4d93-b25e-9102546bf4dd.png "")



### 操作2：用户订购套餐
#### 操作设计


![image.png | left | 683x354](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451511517-e0da5bca-14d8-40b6-8c7e-cf3445ad22c8.png "")


#### 运行截图与时间


![image.png | left | 683x35](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451464745-e92a3512-ea5e-4c4a-a33f-4315dd884f5a.png "")


### 操作3：用户退订
#### 操作设计


![image.png | left | 683x265](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451056385-bd3d63a0-79ef-462a-ab25-59749161fdfb.png "")

#### 运行时间与截图


![image.png | left | 683x40](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451090679-dfc6c2f8-b7a5-473c-a56b-a3a0ce73a7a1.png "")


### 操作4：用户通话情况下的资费生成
#### 操作设计
> 操作说明：系统不记录花费，每次通过用户的功能使用数据计算通话前和通话后的花费之差来得到资费生成量，同理流量



![image.png | left | 683x230](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451134142-c1f027c7-81b8-4e6a-821e-2e8288344d5b.png "")

#### 运行时间与截图


![image.png | left | 683x36](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451174786-9501b4ee-374b-4f80-b535-61b77134dbc3.png "")


### 操作5：用户在使用流量情况下的资费生成
#### 操作设计


![image.png | left | 683x203](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451559551-9f3196c6-6aaf-4da2-b706-9cc675b6a311.png "")

#### 运行时间与截图


![image.png | left | 683x33](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451603678-bb29f953-f74e-4853-a4eb-e8377d2b509e.png "")


### 操作6：用户月账单的生成
#### 操作设计


![image.png | left | 683x116](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451660144-fefa0d61-2b9b-49f6-b4b4-1de34a8bfaeb.png "")

#### 运行时间与截图


![image.png | left | 683x45](https://cdn.nlark.com/yuque/0/2018/png/105047/1540451769868-b568ac09-b09d-4f00-a39a-6ae619011c3a.png "")


## 可能的优化思路
1. 使用 `hibernate` 的时候没有使用懒加载，增加查询开销，如果使用可能更快
2. 将用户信息和用户使用各种功能的表拆开，这样能够增加功能变化的灵活性

