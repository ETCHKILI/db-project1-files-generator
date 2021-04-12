package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class ClassDetail {

    public ClassDetail() {}

    public ClassDetail(Course course, List<MainCourse> mainCourses) {
        id = course.getId();
        for (MainCourse m : mainCourses) {
            if (course.getCourseId().equals(m.getCourseId())) {
                mainCourse = m;
                break;
            }
        }
        totalCapacity = course.getTotalCapacity();
        teacher = course.getTeacher();
        className = course.getClassName();
        classInfos = new ArrayList<>();
        for (Course.ClassInfo c : course.getClassInfo()) {
            classInfos.add(new ClassInfo(c));
        }
    }

    private int id;
    private MainCourse mainCourse;
    private int totalCapacity;
    private String teacher;

    private String className;
    private List<ClassDetail.ClassInfo> classInfos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MainCourse getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(MainCourse mainCourse) {
        this.mainCourse = mainCourse;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(List<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    public static class ClassInfo {

        public ClassInfo() {}

        public ClassInfo(Course.ClassInfo info) {
            weekLists = info.getWeekLists();
            location = info.getLocation();
            classTime = info.getClassTime();
            weekDay = info.getWeekDay();
        }
        private WeekListInfo weekLists;
        private String location;
        private String classTime;
        private int weekDay;

        public WeekListInfo getWeekLists() {
            return weekLists;
        }

        public void setWeekLists(WeekListInfo weekLists) {
            this.weekLists = weekLists;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getClassTime() {
            return classTime;
        }

        public void setClassTime(String classTime) {
            this.classTime = classTime;
        }

        public int getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(int weekDay) {
            this.weekDay = weekDay;
        }
    }
}
