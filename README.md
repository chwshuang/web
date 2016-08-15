# Spring4.X + Spring MVC + Mybatis3 零配置应用开发框架搭建详解(1) - 基本介绍
> 两年前一直在做后台的纯java开发，很少涉及web开发这块，最近换了个纯的互联网公司，需要做Web后台管理系统，之前都是用xml配置的项目，接触了公司Spring4.x的零配置项目，觉得非常有感觉，不仅仅配置简单，而且条理清晰，所以，这里把学习的内容记录下来，一来加深对这块技术的印象，另外准备做个简单的教程，如果给其他人分享的时候还可以拿来直接用。

> 首先讲讲为什么要做框架搭建？现在的开源软件，都是一些很有想法的一群技术大牛利用业余时间弄出来的，而刚开始做框架的时候，是要解决他们工作中面临的一些问题？比如解决javaBean的依赖管理，出现了spring，解决数据库访问操作的问题诞生了Hibernate，Mybatis，解决业务逻辑代码与视图代码的分离，诞生了Struts，SpringMVC......可能你会问，为什么不直接来一个包含全部解决方案的框架呢？我个人认为有以下几个原因：

<p>
<ul>
<li>刚开始的时候，框架只解决某个业务领域的问题，且这个框架的创始人精力有限，不可能做到面面俱到！</li>
<li>代码的维护、重构、升级的时间其实比开发更耗时间；</li>
<li>三、大而全的框架适应力不如解决某一领域框架好，软件开发面对的需求变化和场景已经足够多了，一个大而全的框架必定会因为种种原因，限制其发展，就像spring-side，jfinal等框架。因为如果它已经与其他第三方框架集成好了，如果用户需要对某一部分修改，会涉及很多变更和适配。</li>
<li>由于这些软件受众小，遇到一些特殊场景，如果要集成不常用的第三方库和内容，学习成本、稳定性、安全性等因素变得比较重要了。虽然它已经将很多库集成了，看起来不需要那么繁琐的配置很管理，其实，如果你需要做一些适配和修改的工作，这种大集成的套件往往对技术的要求又很高，不是一般人就能改、也不是一时半会就能改出来的！</li>
</ul>
</p>

> 所以，用现有成熟、稳定的库去搭建一个属于自己的框架，对技术要求、学习成本的需求更低一点！

> 先来说说零配置的实现原理：Servlet3.0规范，支持将web.xml相关配置也硬编码到代码中[servlet，filter，listener,等等]，并由javax.servlet.ServletContainerInitializer的实现类负责在容器启动时进行加载，spring提供了一个实现类SpringServletContainerInitializer（在spring-web包中的org.springframework.web目录）,该类会调用所有org.springframework.web.WebApplicationInitializer实现类的onStartup方法，将相关的组件注册到服务器；而我们的WebApplicationInitializer继承自AbstractAnnotationConfigDispatcherServletInitializer，而AbstractAnnotationConfigDispatcherServletInitializer就实现了org.springframework.web.WebApplicationInitializer的onStartup方法，所以WebApplicationInitializer就是整个项目的关键，我们的整个项目就是通过它来启动。这个WebApplicationInitializer在我们例子的代码中会详细介绍。

###### 由于篇幅较长，所以我把它分成了以下几个部分，逐一进行讲解：

+ （一）基本介绍
+ （二）基础框架搭建
+ （三）实现最基本的登录处理
+ （四）任务调度管理
+ （五）Redis缓存配置
+ （六）安全框架集成
+ （七）页面样式优化
+ （八）[git版本源代码下载][1]

> 由于时间的关系，不可能一下全写完，所以会陆续更新。

> 提示：想学习Spring零配置的内容，最好是下载源码运行，然后自己照着写一遍，加深对框架的理解和认识。



[1]:https://github.com/chwshuang/web.git "源代码下载"
