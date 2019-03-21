import entity.BondInfo;
import entity.IssuerInfo;
import mapper.BondInfoMapper;
import mapper.IssuerInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xinrui.tian on 2019/3/15.
 */
public class MybatisOtherTest {

    /**
     *  批量执行设置   defaultExecutorType   SIMPLE | REUSE | BATCH
     *      *不要在全局中配置该属性，否则所有sql都是批量
     */
    @Test
    public void testBatchSql() throws IOException {
        //开启批量
        SqlSession openSession = getSqlSessionFactory().openSession(ExecutorType.BATCH); //执行时长：2590
        //SqlSession openSession = getSqlSessionFactory().openSession();    //执行时长：287955
        try {
            IssuerInfoMapper mapper = openSession.getMapper(IssuerInfoMapper.class);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                IssuerInfo issuerInfo = new IssuerInfo();
                issuerInfo.setIssuerCode("test" + i);
                issuerInfo.setIssuerName("aa" + i);
                issuerInfo.setSwSector("AA" + i);
                mapper.addIssuerInfo(issuerInfo);
            }
            long end = System.currentTimeMillis();

            System.out.println("执行时长：" + (end - start));
            //openSession.commit();
        } finally {
            openSession.close();
        }
    }


    /**  调用存储过程
         1、使用select标签定义调用存储过程
         2、statementType="CALLABLE":表示要调用存储过程
         3、{call procedure_name(params)}
         -->
         <select id="getPageByProcedure" statementType="CALLABLE" databaseId="oracle">
         {call hello_procedure(
         #{start,mode=IN,jdbcType=INTEGER},
         #{end,mode=IN,jdbcType=INTEGER},
         #{count,mode=OUT,jdbcType=INTEGER},
         #{emps,mode=OUT,jdbcType=CURSOR,javaType=ResultSet,resultMap=PageEmp}
         )}
         </select>
         <resultMap type="com.atguigu.mybatis.bean.Employee" id="PageEmp">
         <id column="EMPLOYEE_ID" property="id"/>
         <result column="LAST_NAME" property="lastname"/>
         <result column="EMAIL" property="email"/>
         </resultMap>
     */


    /**		【自定义类型枚举】
             ---开发中有时候需要保存一个枚举类型
             可以保存的值
             EmpStatus login = EmpStatus.LOGIN;
             System.out.println("枚举的索引："+login.ordinal());
             System.out.println("枚举的名字："+login.name());

             System.out.println("枚举的状态码："+login.getCode());
             System.out.println("枚举的提示消息："+login.getMsg());

             实现TypeHandler接口。或者继承BaseTypeHandler
             全局配置文件
             <typeHandlers>
             <!--1、配置我们自定义的TypeHandler  -->
             <typeHandler handler="com.atguigu.mybatis.typehandler.MyEnumEmpStatusTypeHandler" javaType="com.atguigu.mybatis.bean.EmpStatus"/>
             <!--2、也可以在处理某个字段的时候告诉MyBatis用什么类型处理器
             保存：#{empStatus,typeHandler=xxxx}
             查询：
             <resultMap type="com.atguigu.mybatis.bean.Employee" id="MyEmp">
             <id column="id" property="id"/>
             <result column="empStatus" property="empStatus" typeHandler=""/>
             </resultMap>
             注意：如果在参数位置修改TypeHandler，应该保证保存数据和查询数据用的TypeHandler是一样的。
             -->
             </typeHandlers>
     */

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
