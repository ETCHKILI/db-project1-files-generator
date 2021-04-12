package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class SelectCourse {
    private int studentId;
    private List<MainCourse> courses = new ArrayList<>();

    public SelectCourse(CsvReadIn csvReadIn, List<MainCourse> courses) {
        studentId = csvReadIn.getStudentId();
        for (String s : csvReadIn.getCourseList()) {
            for (MainCourse m : courses) {
                if (m.courseIdSame(s)) {
                    this.courses.add(m);
                }
            }
        }
    }

    public SelectCourse() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<MainCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<MainCourse> courses) {
        this.courses = courses;
    }
}
