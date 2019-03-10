package mapper;

import entity.Bond2;
import entity.BondQueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  【动态SQL   OGNL表达式（类似EL表达式）】
 */
public interface BondMapper {
    /*
    * 动态SQL
    * */

    /* <if>  <trim>  <where> <sql>*/
    List<Bond2> queryBondByCondition(BondQueryCondition bondQueryCondition);

    /* <choose> <when> 只会拼接第一个满足的条件 */
    List<Bond2> queryBondByCondition2(BondQueryCondition bondQueryCondition);

    /*<set>  <trim>*/
    Boolean updateBond(Bond2 bond);

    /*<foreach>*/
    List<Bond2> getBondByBondKeys(@Param("bondKeys")List<String> bondKeys);

    /*<bind> */

    /*批量插入 批量修改  连个内置参数*/
}
