# 这份说明是对版本1.0.1SN...做的

## chenrd-oss-shrio 统一登录
  chenrd-oss-web依赖当前这个包，添加cas及shrio的配置
  这个子模块的主要功能就是实现cas登录回调，及所有系统session注销，以及项目的UserInfo的缓存，shiro动态权限（每个springmvc接口的权限生成）
  几个重要的类说明：com.chenrd.shiro.SingleSignOutHttpSessionListener拦截器，session被项目群中的任意一个去CAS注销登录，项目群中的所有拦截器就会同时得到这个信息，删除缓存。
  com.chenrd.shiro.ShiroCheckFilter过滤器，这个过滤器的目的就是拦截所有的请求，验证用户的缓存是否被注销了，如果已经注销，那么session里面的用户信息需要同样注销掉。
  web.xml文件中必须添加上面两个文件，实现统一注销。
  com.chenrd.shiro.ehcache.UserEhcacheHandle用户信息缓存。
  com.chenrd.shiro.ShiroFilerChainManager通过接口PowerService获取系统的所有接口权限，添加到shirode的org.apache.shiro.web.filter.mgt.DefaultFilterChainManager权限管理里面，详细的可以结合chenrd-oss-web的app-shiro.xml配置文件查看
  
## chenrd-oss-power 实体类字段权限的封装
  根据不同的业务需求一个实体类不同的系统用户查看权限可能一样，系统中有大量的这种需求，这个模块的目的就是把这种查看权限封装起来，真正的开发中根据注解可以很及抽象方法可以很简单的实现功能。
  说这个之前先得了解一下chenrd-oss-web项目：
  chenrd-oss-web这个项目主要有几块功能：应用管理（项目系统）、用户管理、权限管理、角色管理、日志管理
  其中的权限管理又分三种权限：
    1、菜单权限，业务系统中显示的树枝菜单结构
    2、功能权限，业务系统中的一些功能按钮，比方说：查询按钮，添加按钮，修改按钮等等
    3、字段权限，这里的权限就是上面说道的不同用户对实体查看权限不同，这个一般是由字段来决定，比方说，一个类型字段，一个用户只能查看其中一种或几种的类型
  
  再回来讲当前模块：com.chenrd.oss.power.PowerBusiness,这个接口类里面提供了一系列的插叙方法，这些方法在抽象类里面都默认实现了字段过滤功能
  具体实现方式简单介绍一下：com.chenrd.oss.power.cache.PowerScan 实体注解检查类
  
      <bean id="powerScan" class="com.chenrd.oss.power.cache.PowerScan">
		    <property name="applyKey" value="${apply.key}"/>
		    <property name="sessionFactorys">
			    <list>
				    <ref bean="sessionFactory"/>
			    </list>
		    </property>
	    </bean>
     
  在spring的配置文件中添加以上配置，实体类中添加两个注解@DefClassPower及@FieldClassPower，采用构造器模式创建属于实体的单独hql构造类PowerEntityQueryBuilder,这一些的信息都保存在chenrd-ehcache的缓存当中，PowerBusiness的抽象方法在调用构造器的EntityQueryBuilder.builder(QueryInfo info, Map<String, Serializable> params)方法。
  
 ## chenrd-oss-web WEB项目
  项目主要使用在自己抽象架构的基础上搭建，
  页面使用开源的bootstrap框架
