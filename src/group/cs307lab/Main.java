package group.cs307lab;

import group.cs307lab.generator.CriToCourseGenerator;
import group.cs307lab.generator.CsvToScGenerator;
import group.cs307lab.generator.DmlGenerator;

import java.io.IOException;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class Main {
    private Main() {}

    public static void main(String[] args) throws IOException {
        generateAll();
        return;
    }

    private static void generateAll() throws IOException {
        long startTime = System.currentTimeMillis();
        /* jsonPath: the path to the target json file to read */
        String jsonPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\src\\course_info.json";
        CriToCourseGenerator generator = new CriToCourseGenerator(jsonPath);
        generator.readAllCoursesInJsonFile();
        generator.generateDepartments();
        generator.generateWeekLists();
        generator.generateCourses();
        generator.generateMainCourses();
        generator.manipulateMainCourse();
        generator.generateClassDetail();
        /* ??Path: the path to the target output sql file */
        String deptPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\department.csv";
        String wlPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\week_list.csv";
        String mcPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\main_course.csv";
        String cdPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\class_detail.csv";
        String ctPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\class_timetable.csv";
        DmlGenerator.departmentGenerator(generator.getDepartments(), deptPath);
        DmlGenerator.weekListInfoGenerator(generator.getWeekLists(), wlPath);
        DmlGenerator.mainCourseGenerator(generator.getMainCourses(), mcPath);
        DmlGenerator.classDetailGenerator(generator.getClassDetails(), cdPath);
        DmlGenerator.classTimetableGenerator(generator.getClassDetails(), ctPath);

        /* csvPath: the path to the target csv file to read */
        String csvPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\src\\select_course.csv";
        CsvToScGenerator scGenerator = new CsvToScGenerator(csvPath);
        scGenerator.generateReadIn();
        scGenerator.generateColleges();
        scGenerator.generateGender();
        scGenerator.generateStudent();
        scGenerator.generateSelectCourse(generator);
        /* ??Path: the path to the target output sql file */
        String colPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\college.csv";
        String genPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\gender.csv";
        String stuPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\student.csv";
        String scPath = "E:\\Project\\Database\\CS307_spring_2021\\projects\\01\\myfile\\select_course.csv";
        DmlGenerator.collegeGenerator(scGenerator.getColleges(), colPath);
        DmlGenerator.genderGenerator(scGenerator.getGenders(), genPath);
        DmlGenerator.studentGenerator(scGenerator.getStudents(), stuPath);
        DmlGenerator.selectCourseGenerator(scGenerator.getSelectCourses(), scPath);

        long endTime = System.currentTimeMillis();
        System.out.println("generate-files-time：" + (endTime - startTime) + "ms");
    }
}
