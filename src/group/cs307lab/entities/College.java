package group.cs307lab.entities;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class College {

    public College() {}

    public College(String originalStr, int id) {
        this.id = id;
        this.originalStr = originalStr;
        oStrHandler();
    }

    private void oStrHandler() {
        String temp = originalStr.trim();
        temp = temp.replace("(", " ");
        temp = temp.replace(")", "");
        String[] arr = temp.split(" ");
        nameChinese = arr[0];
        nameEnglish = arr[1];
    }

    private int id;
    private String originalStr;
    private String nameChinese;
    private String nameEnglish;

    public boolean isSame(String oStr) {
        return oStr.trim().equals(originalStr.trim());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameChinese() {
        return nameChinese;
    }

    public void setNameChinese(String nameChinese) {
        this.nameChinese = nameChinese;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }
}
