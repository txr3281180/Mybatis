import entity.BondInfo;
import entity.BondIssuer;
import entity.IssuerBond;
import entity.IssuerBonds;
import entity.IssuerInfo;
import mapper.BondAndIssuerMapper;
import mapper.BondInfoMapper;
import mapper.IssuerInfoMapper;
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
    public void test1() {
        IssuerInfo issuerInfo = new IssuerInfo();
        issuerInfo.setIssuerCode("test001");
        issuerInfo.setIssuerName("test_Issuer");
        issuerInfo.setSwSector("申万Test");
        issuerInfo.setSwSubSector("申万SubTest");

        //获取SqlSession 实例，能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            IssuerInfoMapper mapper = openSession.getMapper(IssuerInfoMapper.class);
            long l = mapper.addIssuerInfo(issuerInfo);//增
            System.out.println(l);

            issuerInfo.setIssuerName("test_Issuer_update");  //改
            boolean success = mapper.updateIssuerInfo(issuerInfo);
            System.out.println(success);

            IssuerInfo result = mapper.getIssuerByIssuerCode(issuerInfo.getIssuerCode());
            System.out.println(result);  //查

            int i = mapper.deleteIssuerInfo(issuerInfo.getIssuerCode());  //删
            System.out.println(i);

            //openSession.commit();

            List<IssuerInfo> issuerByName = mapper.getIssuerLikeName("%北京%");//返回List
            System.out.println(issuerByName);

            Map<String, Object> issuerMap1 = mapper.getIssuerMap1("B000071");//返回Map(key属性名， value属性值)
            System.out.println(issuerMap1);

            Map<String, IssuerInfo> issuerMap2 = mapper.getIssuerMap2("%北京%"); //返回Map （key属性名， value实体类）
            System.out.println(issuerMap2);

            // 查看原码,相同的Key会覆盖
            //Map<String, List<IssuerInfo>> issuerMap3 = mapper.getIssuerMap3("北京银行");
            //System.out.println(issuerMap3);

        } finally {
            openSession.close();
        }
    }

    /** 级联映射 */
    @Test
    public void test2() {
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            BondAndIssuerMapper mapper = openSession.getMapper(BondAndIssuerMapper.class);
            List<IssuerBond> bondAndIssuer1 = mapper.getBondAndIssuer1("G000047");
            System.out.println(bondAndIssuer1);  //（一个）issuer -> (多个)bond

            //使用 IssuerBond 做映射  bondInfo 会被覆盖，获取的只有最后一个bond
            List<BondIssuer> bondAndIssuer2 = mapper.getBondAndIssuer2("G000047");
            System.out.println(bondAndIssuer2);  //(多个) bond ->（一个）issuer []

            BondIssuer bondIssuer1 = mapper.getBondAndIssuer3("G0000472015NCD070");
            System.out.println(bondIssuer1);

            IssuerBonds issuerBonds1 = mapper.getBondAndIssuer4("G000047");
            System.out.println(issuerBonds1);

            IssuerBonds issuerBonds2 = mapper.getBondAndIssuer5("G000047");
            System.out.println(issuerBonds2);

            IssuerBonds issuerBonds = mapper.getBondAndIssuer6("G000047");  //T000316
            System.out.println(issuerBonds);
        } finally {
            openSession.close();
        }
    }

    /**
     * 动态SQL  BondInfoMapper
     * */
    @Test
    public void testDynamicSql() {
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            BondInfoMapper mapper = openSession.getMapper(BondInfoMapper.class);

            BondInfo condition = new BondInfo();
            condition.setShortName("%浦发%");
            condition.setIssueStartDate("20140416");
            condition.setIssueEndDate("20180418");
            List<BondInfo> bonds = mapper.queryBond1(condition);
            System.out.println(bonds);

            condition.setBondKey("S0001192015NCD032");
            List<BondInfo> bonds2 = mapper.queryBond2(condition);
            System.out.println(bonds2);

            BondInfo bond = new BondInfo();
            bond.setBondKey("S0001192015NCD032");
            bond.setShortName("浦发test");
            bond.setFullName("浦东发展银行test");
            boolean aBoolean = mapper.updateBond(bond);
            System.out.println(aBoolean);

            //openSession.commit();

            List<String> bondKeys = new ArrayList<>();
            bondKeys.add("B0001492014CORPPN03");
            bondKeys.add("H0001112014CORPPN03");
            List<BondInfo> bonds3 = mapper.queryBond3(bondKeys);
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

            IssuerInfoMapper mapper = openSession.getMapper(IssuerInfoMapper.class);
            IssuerInfo issuerInfo1 = mapper.getIssuerByIssuerCode("F000003");
            System.out.println(issuerInfo1);

            //sqlSession相同， 两次查询相同, sql只发送了一次
            IssuerInfo issuerInfo2 = mapper.getIssuerByIssuerCode("F000003");
            System.out.println(issuerInfo2);

            //sqlSession不同， 两次查询相同, 发送了两次sql
            SqlSession sqlSession2 = sqlSessionFactory.openSession();
            IssuerInfoMapper mapper2 = sqlSession2.getMapper(IssuerInfoMapper.class);
            IssuerInfo issuerInfo3 = mapper2.getIssuerByIssuerCode("F000003");
            System.out.println(issuerInfo3);

            //sqlSession相同，手动清除了一级缓存（缓存清空） 发送两次sql
            sqlSession2.clearCache();
            IssuerInfo issuerInfo4 = mapper2.getIssuerByIssuerCode("F000003");
            System.out.println(issuerInfo4);

            //sqlSession相同，查询条件不同.(当前一级缓存中还没有这个数据) 发送两次sql
            //sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影) 发送两次sql
        } finally {
            openSession.close();
        }
    }

}
