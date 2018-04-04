# templates
模板工程集成了通用Mapper/pagehelper/shiro组件,以备开发中复用.

注意:
由于使用了通用Mapper,所以如果实体中存在数据库表中不存在的字段,需要添加@Transient,否则在mvn install时就会出错.另外通用Mapper版本过高可能也会出现错误(待考证).

配置ShiroConfig类时,使用到的Bean不能使用@Autowired注入,需要使用@Bean注解方法返回手动new对象的形式.

filterChainDefinitionMap使用数据库动态获取完成拦截配置.

数据库脚本在resources/sql/templates.sql.

求教:
个人对权限表的权限获取设计不太满意,希望有大神指导.