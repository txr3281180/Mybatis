package entity;

/**
 * Created by xinrui.tian on 2019/3/10.
 */
public class Bond2 {

    private String bondKey;
    private String bondName;
    private String issuerCode;
    private String issuerName;
    private String bondType;
    private String issueEndDate;

    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
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

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getIssueEndDate() {
        return issueEndDate;
    }

    public void setIssueEndDate(String issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    @Override
    public String toString() {
        return "Bond2{" +
                "bondKey='" + bondKey + '\'' +
                ", bondName='" + bondName + '\'' +
                ", issuerCode='" + issuerCode + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", bondType='" + bondType + '\'' +
                ", issueEndDate='" + issueEndDate + '\'' +
                '}';
    }
}
