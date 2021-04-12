package group.cs307lab.generator;

import group.cs307lab.entities.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class CsvToScGenerator {

    private final Scanner scanner;
    private final List<CsvReadIn> csvReadIns = new ArrayList<>();
    private List<College> colleges = new ArrayList<>();
    private List<Gender> genders = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<SelectCourse> selectCourses = new ArrayList<>();

    public CsvToScGenerator(String fileName) throws IOException{
        File file = new File(fileName);
        scanner = new Scanner(FileUtils.openInputStream(file));
    }

    private boolean hasCollege(String college) {
        for (College c : colleges) {
            if (c.isSame(college)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasGender(String gender) {
        for (Gender g : genders) {
            if (g.sameGender(gender)) {
                return true;
            }
        }
        return false;
    }

    public void generateReadIn() {
        while (scanner.hasNextLine()) {
            csvReadIns.add(new CsvReadIn(scanner.nextLine()));
        }
    }

    public void generateColleges() {
        int i = 1;
        for (CsvReadIn c : csvReadIns) {
            if (!hasCollege(c.getCollege())) {
                colleges.add(new College(c.getCollege(), i++));
            }
        }
    }

    public void generateGender() {
        int i = 1;
        for (CsvReadIn c : csvReadIns) {
            if (!hasGender(c.getGender())) {
                genders.add(new Gender(i++, c.getGender()));
            }
        }
    }

    public void generateStudent() {
        for (CsvReadIn csvReadIn : csvReadIns) {
            students.add(new Student(csvReadIn, genders, colleges));
        }
    }

    public void generateSelectCourse(CriToCourseGenerator generator) {
        for (CsvReadIn csvReadIn : csvReadIns) {
            selectCourses.add(new SelectCourse(csvReadIn, generator.getMainCourses()));
        }
    }

    public List<College> getColleges() {
        return colleges;
    }

    public void setColleges(List<College> colleges) {
        this.colleges = colleges;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<SelectCourse> getSelectCourses() {
        return selectCourses;
    }

    public void setSelectCourses(List<SelectCourse> selectCourses) {
        this.selectCourses = selectCourses;
    }
}
