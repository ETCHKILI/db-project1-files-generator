package group.cs307lab.entities;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class Gender {
    private int id;
    private String genderName;

    public Gender() {
    }

    public Gender(int id, String genderName) {
        this.id = id;
        this.genderName = genderName;
    }

    public boolean sameGender(String genderName) {
        return this.genderName.trim().equals(genderName.trim());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
