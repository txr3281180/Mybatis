package entity;

/**
 * Created by xinrui.tian on 2019/3/18.
 */
public class BondIssuer {

    private String id;
    private String bondKey;
    private String shortName;

    private IssuerInfo issuerInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public IssuerInfo getIssuerInfo() {
        return issuerInfo;
    }

    public void setIssuerInfo(IssuerInfo issuerInfo) {
        this.issuerInfo = issuerInfo;
    }

    @Override
    public String toString() {
        return "BondIssuer{" +
                "id='" + id + '\'' +
                ", bondKey='" + bondKey + '\'' +
                ", shortName='" + shortName + '\'' +
                ", issuerInfo=" + issuerInfo.getIssuerCode() + "-->" + issuerInfo.getIssuerName() +
                '}';
    }
}
