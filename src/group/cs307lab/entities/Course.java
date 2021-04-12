package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class Course {

    public Course() {}

    public Course(CourseReadIn readIn, int id, List<WeekListInfo> weekListInfos, List<Department> departments) {
        this.id = id;
        totalCapacity = readIn.getTotalCapacity();
        courseId = readIn.getCourseId();
        prerequisiteStr = readIn.getPrerequisite();
        courseHour = readIn.getCourseHour();
        courseCredit = readIn.getCourseCredit();
        courseName = readIn.getCourseName();
        className = readIn.getClassName();
        teacher = readIn.getTeacher();

        for (Department d : departments) {
            if (readIn.getCourseDept().equals(d.getName())) {
                courseDept = d;
                break;
            }
        }

        classInfo = new ArrayList<>();
        for (CourseReadIn.ClassInfo i : readIn.getClassList()) {
            classInfo.add(new ClassInfo(i, weekListInfos));
        }
    }

    private int id;
    private int totalCapacity;
    private String courseId;

    private String prerequisiteStr;

    private int courseHour;
    private int courseCredit;
    private String courseName;
    private String className;
    private Department courseDept;
    private String teacher;
    private List<ClassInfo> classInfo;

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPrerequisiteStr() {
        return prerequisiteStr;
    }

    public void setPrerequisiteStr(String prerequisiteStr) {
        this.prerequisiteStr = prerequisiteStr;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Department getCourseDept() {
        return courseDept;
    }

    public void setCourseDept(Department courseDept) {
        this.courseDept = courseDept;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<ClassInfo> getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(List<ClassInfo> classInfo) {
        this.classInfo = classInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class ClassInfo {

        public ClassInfo() {}

        public ClassInfo(CourseReadIn.ClassInfo info, List<WeekListInfo> weekListInfos) {
            location = info.getLocation();
            classTime = info.getClassTime();
            weekDay = info.getWeekDay();
            for (WeekListInfo i : weekListInfos) {
                if (i.hasSameContent(info.getWeekList())) {
                    weekLists = i;
                }
            }
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
