package entity;

/**
 * Created by xinrui.tian on 2019/2/18.
 */
public class BondUnderwriter {

    private String bondKey;
    private String underwritingRole;
    private Underwriter underwriter;

    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    public String getUnderwritingRole() {
        return underwritingRole;
    }

    public void setUnderwritingRole(String underwritingRole) {
        this.underwritingRole = underwritingRole;
    }

    public Underwriter getUnderwriter() {
        return underwriter;
    }

    public void setUnderwriter(Underwriter underwriter) {
        this.underwriter = underwriter;
    }

    @Override
    public String toString() {
        return "BondUnderwriter{" +
                "bondKey='" + bondKey + '\'' +
                ", underwritingRole='" + underwritingRole + '\'' +
                ", underwriter=" + underwriter +
                '}';
    }
}
