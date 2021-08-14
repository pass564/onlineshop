# onlineshop
## 基于ssm的在线商城系统

### 介绍

我在网上浏览了几个在线商城项目后，综合起来，形成了这个较为简易 的系统

软件架构：基于ssm+maven

目前只有后端页面，前端不够熟练，尝试后发现暂时无法写出完整的前端页面

该系统分为后台管理系统以及前台系统，后台系统为管理员，可对商品进行修改

### 使用说明

测试时使用jdk1.8

sql语句已经放在eb.sql文件中，数据库名称：eb

在onlineshop_backend_web和onlineshop_front_web中使用maven里面提供的tomcat即可运行前台以及后台系统

### 项目结构

数据库结构：

t_sysuser（管理员）：登录属于管理员的页面，对商品类型和商品进行修改。账号分为有效和无效两类。

t_customer（用户）：登录属于用户的页面，账号也有状态的区分，可以查看属于本用户的订单。

t_order（订单）：存储各个用户的购物订单，可专门查询某个用户的所有订单。

t_product（商品）：存储在线商城上面的所有商品，可被用户观看。

t_product_type（商品类型）：可根据需求，为不同的商品指定类型，当此类型不在需要使用时，可禁用此类型，让此类型商品无法被搜索到。

![image-20210814030415259](C:\Users\LEGION\AppData\Roaming\Typora\typora-user-images\image-20210814030415259.png)





#### 后台管理系统

后台系统主要用于管理员进行商品类型的设置，以及具体商品的添加。限时商品可以单独放在一个商品类型中，结束即禁用。

##### ProductController主要功能

![image-20210814025632118](C:\Users\LEGION\AppData\Roaming\Typora\typora-user-images\image-20210814025632118.png)

##### ProductType主要功能

![image-20210814025751627](C:\Users\LEGION\AppData\Roaming\Typora\typora-user-images\image-20210814025751627.png)

##### SysuserController主要功能

![image-20210814025855650](C:\Users\LEGION\AppData\Roaming\Typora\typora-user-images\image-20210814025855650.png)

#### 前台管理系统

前台主要是用户登录，商品展示以及用户购物车的展示

##### CustomerController主要功能

![image-20210814030040790](C:\Users\LEGION\AppData\Roaming\Typora\typora-user-images\image-20210814030040790.png)

##### OrderController主要功能

![image-20210814030117966](C:\Users\LEGION\AppData\Roaming\Typora\typora-user-images\image-20210814030117966.png)

##### ProductController主要功能

![image-20210814030155544](C:\Users\LEGION\AppData\Roaming\Typora\typora-user-images\image-20210814030155544.png)