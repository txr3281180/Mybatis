package plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by xinrui.tian on 2019/3/15.
 */


/*
    插件签名
        告诉Mybatis当前插件用来拦截哪个对象的哪个方法


     四大对象
        Executor  (update, query, flushStatements, commit, rollback,getTransaction, close, isClosed)
        ParameterHandler  (getParameterObject, setParameters)
        ResultSetHandler  (handleResultSets, handleOutputParameters)
        StatementHandler  (prepare, parameterize, batch, update, query)

 */
@Intercepts({
        @Signature(type= StatementHandler.class,
                method = "parameterize",
                args = java.sql.Statement.class)
})
public class MyPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyPlugin...intercept ==>" + invocation);

        /*当前拦截对象*/
        Object[] args = invocation.getArgs();
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();

        //拿到 StatementHandler ==>  ParameterHandler ==> parameterObject
        //拿到target的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        //拿到sql参数
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("拦截 SQL 语句的参数：" + value);

        //重新设置值
        //metaObject.setValue("parameterHandler.parameterObject", "xxxxx");

        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }

    //包装目标对象， 为目标对象创建一个代理对象
    @Override
    public Object plugin(Object target) {
        //System.out.println("MyPlugin...plugin ==>" + target);

        //借助Plugin的wrap方法来使用当前Interceptor包装目标对象
        Object wrap = Plugin.wrap(target, this);
        //返回当前target创建的动态代理
        return wrap;
    }

    //将插件注册时的property属性设置进来
    @Override
    public void setProperties(Properties properties) {
        System.out.println("自定义插件配置的信息：" + properties);
        //properties.getProperty("name");
    }
}
