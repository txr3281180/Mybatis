package entity;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/2/18.
 */
public class BondUnderwriter2 {

    private String bondKey;
    private List<Underwriter> underwriters;

    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    public List<Underwriter> getUnderwriters() {
        return underwriters;
    }

    public void setUnderwriters(List<Underwriter> underwriters) {
        this.underwriters = underwriters;
    }

    @Override
    public String toString() {
        return "BondUnderwriter2{" +
                "bondKey='" + bondKey + '\'' +
                ", underwriters=" + underwriters +
                '}';
    }
}
