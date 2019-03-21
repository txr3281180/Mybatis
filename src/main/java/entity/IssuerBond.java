package entity;

/**
 * Created by xinrui.tian on 2019/3/18.
 */
public class IssuerBond {

    private String id;
    private String issuerCode;
    private String issuerName;

    private BondInfo bondInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public BondInfo getBondInfo() {
        return bondInfo;
    }

    public void setBondInfo(BondInfo bondInfo) {
        this.bondInfo = bondInfo;
    }

    @Override
    public String toString() {
        return "IssuerBond{" +
                "id='" + id + '\'' +
                ", issuerCode='" + issuerCode + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", bondInfo=" + bondInfo.getBondKey() + "-->" + bondInfo.getShortName() +
                '}';
    }
}
