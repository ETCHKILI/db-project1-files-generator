package group.cs307lab.entities;

import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class WeekListInfo {
    private int id;
    private List<String> weekList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<String> weekList) {
        this.weekList = weekList;
    }

    public boolean hasSameContent(List<String> list) {
        if (list.size() != weekList.size()) {
            return false;
        }
        for (int i = 0; i < weekList.size(); i++) {
            if (!weekList.get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }
}
