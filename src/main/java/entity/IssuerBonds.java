package entity;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/3/18.
 */
public class IssuerBonds {

    private String id;
    private String issuerCode;
    private String issuerName;

    private List<BondInfo> bondInfos;

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

    public List<BondInfo> getBondInfos() {
        return bondInfos;
    }

    public void setBondInfos(List<BondInfo> bondInfos) {
        this.bondInfos = bondInfos;
    }

    @Override
    public String toString() {
        return "IssuerBonds{" +
                "id='" + id + '\'' +
                ", issuerCode='" + issuerCode + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", bondInfos=" + bondInfos +
                '}';
    }
}
