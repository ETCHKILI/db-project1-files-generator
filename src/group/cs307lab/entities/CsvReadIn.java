package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class CsvReadIn {

    private String studentName;
    private String gender;
    private String college;
    private int studentId;
    private List<String> courseList = new ArrayList<>();

    public CsvReadIn() {}

    public CsvReadIn(String line) {
        String[] arr = line.split(",");
        int size = arr.length;
        studentName = arr[0];
        gender = arr[1];
        college = arr[2];
        studentId = Integer.parseInt(arr[3]);
        int courseSize = size - 4;
        courseList.addAll(Arrays.asList(arr).subList(4, courseSize + 4));
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }
}
