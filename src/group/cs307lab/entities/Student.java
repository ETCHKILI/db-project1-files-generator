package group.cs307lab.entities;

import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class Student {
    private int studentId;
    private String name;
    private Gender gender;
    private College college;

    public Student() {
    }

    public Student(CsvReadIn csvReadIn, List<Gender> genders, List<College> colleges) {
        studentId = csvReadIn.getStudentId();
        name = csvReadIn.getStudentName();
        for (Gender g : genders) {
            if (g.sameGender(csvReadIn.getGender())) {
                gender = g;
                break;
            }
        }
        for (College c : colleges) {
            if (c.isSame(csvReadIn.getCollege())) {
                college = c;
                break;
            }
        }
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
