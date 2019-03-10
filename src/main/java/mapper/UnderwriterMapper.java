package mapper;

import entity.Underwriter;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/2/18.
 *
 * 【参数解析  返回值映射】
 */
public interface UnderwriterMapper {

    /*
    * 【参数解析  返回值映射】
    *
    * 1.参数（Parameters）传递
        单个参数
        – 可以接受基本类型，对象类型，集合类型的值。这种情况
        MyBatis可直接使用这个参数，不需要经过任何处理。
        • 多个参数
        – 任意多个参数，都会被MyBatis重新包装成一个Map传入。
        Map的key是param1，param2，0，1…，值就是参数的值。
        • 命名参数
        – 为参数使用@Param起一个名字，MyBatis就会将这些参数封
        装进map中，key就是我们自己指定的名字
        • POJO
        – 当这些参数属于我们业务POJO时，我们直接传递POJO
        • Map
        – 我们也可以封装多个参数为map，直接传递

        ==============================
        public Employee getEmp(@Param("id")Integer id, String lastName);
	    取值：id==>#{id/param1}   lastName==>#{param2}

        public Employee getEmp(Integer id,@Param("e")Employee emp);
	    取值：id==>#{param1}    lastName===>#{param2.lastName/e.lastName}

        ##特别注意：如果是Collection（List、Set）类型或者是数组，
		也会特殊处理。也是把传入的list或者数组封装在map中。
			key：Collection（collection）,如果是List还可以使用这个key(list)
				数组(array)
        public Employee getEmpById(List<Integer> ids);
	    取值：取出第一个id的值：   #{list[0]}

      2.增删改返回值支持： Integer, Long,  Boolean, 也可以 void

      3.#{} 与 ${} 都可以获取map中的值或者pojo对象属性的值；
        区别：#{}:是以预编译的形式，将参数设置到sql语句中；PreparedStatement；防止sql注入
             ${}:取出的值直接拼装在sql语句中；会有安全问题； 比如分表、排序 可以使用${}
                    select * from tbl_employee order by ${f_name} ${order}
    * */

    Underwriter getUnderwriterById (String underwriterId);

    Integer addUnderwriter(Underwriter underwriter);

    Boolean updateUnderwriter(Underwriter underwriter);

    Integer deleteUnderwriter(String underwriterId);

    List<Underwriter> getUnderwriterLikeName(String name);

    Map<String, Object> getUnderwriterMap(String underwriterId);

    @MapKey("underwriterId")  //指定哪个属性作为 map的key
    Map<String, Underwriter> getUnderwriterMap2(String name);

    @MapKey("underwriterName")  //相同的Key会覆盖
    Map<String, List<Underwriter>> getUnderwriterMap3(String name);
}
