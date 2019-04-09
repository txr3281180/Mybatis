import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.IssuerInfo;
import mapper.IssuerInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by xinrui.tian on 2019/3/15.
 */
public class PluginTest {

    /** 自定义插件  分页插件 */

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 插件编写
     *  1.编写Interceptor的实现类
     *  2.使用@Integercepts注解完成插件签名
     *  3.将写好的插件注册到全局配置文件中
     *
     *      --配置多个插件，按顺序执行（产生多层代理）
     */
    @Test
    public void testMyPlugin() throws IOException {
        SqlSession openSession = getSqlSessionFactory().openSession();
        try {
            IssuerInfoMapper mapper = openSession.getMapper(IssuerInfoMapper.class);
            IssuerInfo issuerInfo = mapper.getIssuerByIssuerCode("F000003");
            System.out.println(issuerInfo);
        }finally {
            openSession.close();
        }
    }


    /*https://github.com/pagehelper/Mybatis-PageHelper/blob/master/README_zh.md*/
    @Test
    public void testPageHelper() throws IOException {

        SqlSession openSession = getSqlSessionFactory().openSession();
        try {
            IssuerInfoMapper mapper = openSession.getMapper(IssuerInfoMapper.class);
            //分页
            /*
                //
                Page<Object> page = PageHelper.startPage(10, 5);
                page.setOrderBy("id desc");
            */
            Page<Object> page = PageHelper.startPage(10, 5, "id desc");

            List<IssuerInfo> list = mapper.getAllIssuer();
            for (IssuerInfo issuerInfo : list) {
                System.out.println(issuerInfo);
            }

            //分页信息
            System.out.println("当前页码 :" + page.getPageNum());
            System.out.println("总记录数 :" + page.getTotal());
            System.out.println("每页记录数 :" + page.getPageSize());
            System.out.println("总页码 :" + page.getPages());

            System.out.println("===========pageInfo=====================");

            //pageInfo信息
            //PageInfo pageInfo = new PageInfo(list);
            PageInfo pageInfo = new PageInfo(list, 10);  //navigatePages 连续打印页码
            System.out.println("当前页码 :" + pageInfo.getPageNum());
            System.out.println("总记录数 :" + pageInfo.getTotal());
            System.out.println("每页记录数 :" + pageInfo.getPageSize());
            System.out.println("总页码 :" + pageInfo.getPages());

            //获取导航页
            //int navigatePages = pageInfo.getNavigatePages();
            //int navigateFirstPage = pageInfo.getNavigateFirstPage();
            //int navigateLastPage = pageInfo.getNavigateLastPage();
            //  首页  1 2 3 4 5 6 7 8 9 10  尾页

            int[] nums = pageInfo.getNavigatepageNums();
            for (int num : nums) {
                System.out.println(num);
            }

        }finally {
            openSession.close();
        }
    }
}
