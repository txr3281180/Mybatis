package mapper;

import entity.BondInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/3/18.
 */
public interface BondInfoMapper {

    /*
     * 动态SQL
     * */

    /* <if>  <trim>  <where> <sql>*/
    List<BondInfo> queryBond1(BondInfo bondInfo);

    /* <choose> <when> 只会拼接第一个满足的条件 */
    List<BondInfo> queryBond2(BondInfo bondInfo);

    /*<foreach>*/
    List<BondInfo> queryBond3(@Param("bondKeys")List<String> bondKeys);

    /*<set>  <trim>*/
    boolean updateBond(BondInfo bondInfo);

    /*<bind> */
    /*批量插入 批量修改  连个内置参数*/

    List<BondInfo> getBondByIssuerCode(String issuerCode);
}
