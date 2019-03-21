package mapper;

import entity.BondIssuer;
import entity.IssuerBond;
import entity.IssuerBonds;

import java.util.List;

/**
 * 【级联映射  鉴别器】
 */
public interface BondAndIssuerMapper {
    /*
    * 级联映射
    *
    * */
    List<IssuerBond> getBondAndIssuer1(String issuerCode);  //1:1 多结果集

    List<BondIssuer> getBondAndIssuer2(String issuerCode);  //1:1 多结果集

    BondIssuer getBondAndIssuer3(String bondKey);  //1:1 单结果集

    IssuerBonds getBondAndIssuer4(String issuerCode);           //1:N 单结果

    IssuerBonds getBondAndIssuer5(String issuerCode); //1:N 单结果

    /*
     * 鉴别器[discriminator]
     */
    IssuerBonds getBondAndIssuer6(String issuerCode);
}
