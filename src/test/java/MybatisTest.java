import entity.Bond;
import entity.Bond2;
import entity.BondQueryCondition;
import entity.BondUnderwriter;
import entity.BondUnderwriter2;
import entity.Underwriter;
import mapper.BondMapper;
import mapper.BondUnderwriterMapper;
import mapper.UnderwriterMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/2/18.
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *   mybatis  增删改查
     *
     *   旧版本 （传入指定的方法）
     *   param1: sql的唯一标识(namespace + id )；  param2: sql要用的参数
     *   Underwriter underwriter = openSession.selectOne("mapper.UnderwriterMapper.getUnderwriterById", "D000011");
     */
    @Test
    public void testUnderwriter() {
        Underwriter underwriter = new Underwriter();
        underwriter.setUnderwriterId("test001");
        underwriter.setUnderwriterName("Test承销商");
        underwriter.setFullName("Test承销商");

        //获取SqlSession 实例，能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            UnderwriterMapper mapper = openSession.getMapper(UnderwriterMapper.class);
            Integer integer = mapper.addUnderwriter(underwriter);  //增
            System.out.println(integer);

            underwriter.setFullName("Test金融公司");  //改
            Boolean success = mapper.updateUnderwriter(underwriter);
            System.out.println(success);

            Underwriter underwriterById = mapper.getUnderwriterById(underwriter.getUnderwriterId());
            System.out.println(underwriterById);  //查

            integer = mapper.deleteUnderwriter(underwriter.getUnderwriterId());  //删
            System.out.println(integer);

            //openSession.commit();

            List<Underwriter> allUnderwriter = mapper.getUnderwriterLikeName("%北京%");//返回List
            System.out.println(allUnderwriter);

            Map<String, Object> underwriterMap = mapper.getUnderwriterMap("B000071");//返回Map(key属性名， value属性值)
            System.out.println(underwriterMap);

            Map<String, Underwriter> underwriterMap2 = mapper.getUnderwriterMap2("%北京%"); //返回Map （key属性名， value实体类）
            System.out.println(underwriterMap2);

            // 查看原码,相同的Key会覆盖
            //Map<String, List<Underwriter>> underwriterMap3 = mapper.getUnderwriterMap3("北京银行");
            //System.out.println(underwriterMap3);

        } finally {
            openSession.close();
        }
    }

    @Test
    public void testBondUnderwriter() {
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            BondUnderwriterMapper mapper = openSession.getMapper(BondUnderwriterMapper.class);
            List<BondUnderwriter> bondUnderwriters = mapper.getBondUnderwriter("N0000502016CORCSP02");
            System.out.println(bondUnderwriters);

            List<BondUnderwriter> bondUnderwriters2 = mapper.getBondUnderwriter2("N0000502016CORCSP02");
            System.out.println(bondUnderwriters2);

            List<BondUnderwriter> bondUnderwriters3 = mapper.getBondUnderwriter3("N0000502016CORCSP02");
            System.out.println(bondUnderwriters3);

            BondUnderwriter2 bondUnderwriters4 = mapper.getBondUnderwriter4("N0000502016CORCSP02");
            System.out.println(bondUnderwriters4);

            Bond bond = mapper.getBond("N0000502016CORCSP02");
            System.out.println(bond);

            List<BondUnderwriter> bondUnderwriters5 = mapper.getBondUnderwriter5("N0000502016CORCSP02");
            System.out.println(bondUnderwriters5);
        } finally {
            openSession.close();
        }
    }

    /*动态SQL*/
    @Test
    public void testDynamicSql() {
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            BondMapper mapper = openSession.getMapper(BondMapper.class);

            BondQueryCondition condition1 = new BondQueryCondition();
            condition1.setBondName("%海%");
            condition1.setStartDate("2018-04-16");
            condition1.setEndDate("2018-04-18");
            List<Bond2> bonds = mapper.queryBondByCondition(condition1);
            System.out.println(bonds);

            condition1.setBondKey("S0000652018CORLMN02");
            List<Bond2> bonds2 = mapper.queryBondByCondition2(condition1);
            System.out.println(bonds2);

            Bond2 bond = new Bond2();
            bond.setBondKey("S0000292018CORLMN03");
            bond.setBondName("18鲁宏桥MTN003_test");
            bond.setIssuerName("山东宏桥新型材料有限公司_test");
            Boolean aBoolean = mapper.updateBond(bond);
            System.out.println(aBoolean);

            //openSession.commit();

            List<String> bondKeys = new ArrayList<>();
            bondKeys.add("S0000292018CORLMN03");
            bondKeys.add("S0000652018CORLMN02");
            List<Bond2> bonds3 = mapper.getBondByBondKeys(bondKeys);
            System.out.println(bonds3);

        } finally {
            openSession.close();
        }
    }

    /*mybatis 缓存*/
    @Test
    public void testMyBatisCache() {
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            /*测试一级缓存*/

            UnderwriterMapper mapper = openSession.getMapper(UnderwriterMapper.class);
            Underwriter underwriter1 = mapper.getUnderwriterById("F000003");
            System.out.println(underwriter1);

            //sqlSession相同， 两次查询相同, sql只发送了一次
            Underwriter underwriter2 = mapper.getUnderwriterById("F000003");
            System.out.println(underwriter2);

            //sqlSession不同， 两次查询相同, 发送了两次sql
            SqlSession sqlSession2 = sqlSessionFactory.openSession();
            UnderwriterMapper mapper2 = sqlSession2.getMapper(UnderwriterMapper.class);
            Underwriter underwriter3 = mapper2.getUnderwriterById("F000003");
            System.out.println(underwriter3);

            //sqlSession相同，手动清除了一级缓存（缓存清空） 发送两次sql
            sqlSession2.clearCache();
            Underwriter underwriter4 = mapper2.getUnderwriterById("F000003");
            System.out.println(underwriter4);

            //sqlSession相同，查询条件不同.(当前一级缓存中还没有这个数据) 发送两次sql
            //sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影) 发送两次sql
        } finally {
            openSession.close();
        }
    }

}
