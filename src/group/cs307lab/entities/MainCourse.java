package group.cs307lab.entities;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class MainCourse {

    public MainCourse() {}

    public MainCourse(Course course, int id) {
        this.id = id;
        courseId = course.getCourseId();
        prerequisiteStr = course.getPrerequisiteStr();
        courseHour = course.getCourseHour();
        courseCredit = course.getCourseCredit();
        courseName = course.getCourseName();
        courseDept = course.getCourseDept();
    }

    private int id;
    private String courseId;

    private String prerequisiteStr;

    private int courseHour;
    private int courseCredit;
    private String courseName;
    private Department courseDept;

    public boolean hasSameContent(Course course) {
        return courseId.equals(course.getCourseId());
    }

    public boolean courseIdSame(String name) {
        return name.trim().equals(courseId.trim());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Department getCourseDept() {
        return courseDept;
    }

    public void setCourseDept(Department courseDept) {
        this.courseDept = courseDept;
    }
}
