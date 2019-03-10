package mapper;

import entity.Bond;
import entity.BondUnderwriter;
import entity.BondUnderwriter2;

import java.util.List;

/**
 * 【级联映射  鉴别器】
 */
public interface BondUnderwriterMapper {
    /*
    * 级联映射
    *
    * */
    List<BondUnderwriter> getBondUnderwriter(String bondKey);

    List<BondUnderwriter> getBondUnderwriter2(String bondKey);

    List<BondUnderwriter> getBondUnderwriter3(String bondKey);

    BondUnderwriter2 getBondUnderwriter4(String bondKey);

    Bond getBond(String bondKey);

    /*
     * 鉴别器[discriminator]
     */
    List<BondUnderwriter> getBondUnderwriter5(String bondKey);
}
