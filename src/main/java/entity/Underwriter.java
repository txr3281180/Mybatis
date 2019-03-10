package entity;

/**
 * Created by xinrui.tian on 2019/2/18.
 */
public class Underwriter {

    private String id;

    private String underwriterId;
    private String underwriterName;
    private String fullName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(String underwriterId) {
        this.underwriterId = underwriterId;
    }

    public String getUnderwriterName() {
        return underwriterName;
    }

    public void setUnderwriterName(String underwriterName) {
        this.underwriterName = underwriterName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Underwriter{" +
                "id='" + id + '\'' +
                ", underwriterId='" + underwriterId + '\'' +
                ", underwriterName='" + underwriterName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
