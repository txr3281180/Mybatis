package entity;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/3/9.
 */
public class Bond {

    private String bondKey;
    private String bondName;
    private List<BondUnderwriter> bondUnderwriters;

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

    public List<BondUnderwriter> getBondUnderwriters() {
        return bondUnderwriters;
    }

    public void setBondUnderwriters(List<BondUnderwriter> bondUnderwriters) {
        this.bondUnderwriters = bondUnderwriters;
    }

    @Override
    public String toString() {
        return "Bond{" +
                "bondKey='" + bondKey + '\'' +
                ", bondName='" + bondName + '\'' +
                ", bondUnderwriters=" + bondUnderwriters +
                '}';
    }
}
