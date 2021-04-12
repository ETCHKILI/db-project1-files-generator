package group.cs307lab.generator;

import group.cs307lab.entities.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class DmlGenerator {

    private DmlGenerator() {}

    private static final String END_TAG = "\n";
//    private static final String END_TAG = "');\n";

    private static final String BEGIN = "BEGIN;\n";
    private static final String COMMIT = "COMMIT;\n";
    private static final String UTF8 = "UTF-8";

    private static String deptToStr(Department department) {
//        return "INSERT INTO departments(name) VALUES ('" + department.getName() + END_TAG;
        return department.getName() + END_TAG;
    }

    private static String wliToStr(WeekListInfo weekListInfo) {
//        String sql = "INSERT INTO week_list_info(week_list) VALUES ('";
        String sql = "";
        sql += Arrays.toString(weekListInfo.getWeekList().toArray());
        sql += END_TAG;
        return sql;
    }

    private static String mcToStr(MainCourse course) {
//        String sql = "INSERT INTO main_course(course_id, course_name, course_credit, course_hour, prerequisite, dept_id) VALUES ('%s', '%s', %d, %d, %s, %d);\n";
        String sql = "%s,%s,%d,%d,%s,%d\n";
        return String.format(sql, course.getCourseId(), course.getCourseName(),
                course.getCourseCredit(), course.getCourseHour(),
//                (course.getPrerequisiteStr() == null ? "null" : "'" + course.getPrerequisiteStr() + "'"),
                (course.getPrerequisiteStr() == null ? "null" : course.getPrerequisiteStr()),
                course.getCourseDept().getId());
    }

    private static String cdToStr(ClassDetail classDetail) {
        return String.format("%d|%d|%s|%s\n",
                classDetail.getMainCourse().getId(), classDetail.getTotalCapacity(), classDetail.getTeacher(), classDetail.getClassName());
    }

    private static String ctToStr(ClassDetail classDetail) {
        String sql = "%d,%d,%s,%s,%d\n";
        StringBuilder builder = new StringBuilder();
        for (ClassDetail.ClassInfo c : classDetail.getClassInfos()) {
            builder.append(String.format(sql, classDetail.getId(), c.getWeekLists().getId(), c.getLocation(), c.getClassTime(), c.getWeekDay()));
        }
        return builder.toString();
    }

    private static String colToStr(College college) {
        return college.getNameChinese() + "," + college.getNameEnglish() + END_TAG;
    }

    private static String genToStr(Gender gender) {
        return  gender.getGenderName() + END_TAG;
    }

    private static String stuToStr(Student student) {
        return  student.getStudentId() + "," + student.getName() + ","
                + student.getGender().getId() + "," + student.getCollege().getId() + "\n";
    }

    private static String scToStr(SelectCourse selectCourse) {
        String sql = selectCourse.getStudentId() + ",";
        StringBuilder result = new StringBuilder();
        for (MainCourse c : selectCourse.getCourses()) {
            result.append(sql).append(c.getId()).append("\n");
        }
        return result.toString();
    }

    public static void departmentGenerator(List<Department> departments, String output) {
        File file = FileUtils.getFile(output);
        departments.sort(Comparator.comparingInt(Department::getId));
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (Department d : departments) {
                FileUtils.write(file, deptToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void weekListInfoGenerator(List<WeekListInfo> weekListInfos, String output) {
        File file = FileUtils.getFile(output);
        weekListInfos.sort(Comparator.comparingInt(WeekListInfo::getId));
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (WeekListInfo d : weekListInfos) {
                FileUtils.write(file, wliToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainCourseGenerator(List<MainCourse> mainCourses, String output) {
        File file = FileUtils.getFile(output);
        mainCourses.sort(Comparator.comparingInt(MainCourse::getId));
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (MainCourse d : mainCourses) {
                FileUtils.write(file, mcToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void classDetailGenerator(List<ClassDetail> details, String output) {
        File file = FileUtils.getFile(output);
        details.sort(Comparator.comparingInt(ClassDetail::getId));
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (ClassDetail d : details) {
                FileUtils.write(file, cdToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void classTimetableGenerator(List<ClassDetail> details, String output) {
        File file = FileUtils.getFile(output);
        details.sort(Comparator.comparingInt(ClassDetail::getId));
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (ClassDetail d : details) {
                FileUtils.write(file, ctToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void collegeGenerator(List<College> colleges, String output) {
        File file = FileUtils.getFile(output);
        colleges.sort(Comparator.comparingInt(College::getId));
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (College d : colleges) {
                FileUtils.write(file, colToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void genderGenerator(List<Gender> genders, String output) {
        File file = FileUtils.getFile(output);
        genders.sort(Comparator.comparingInt(Gender::getId));
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (Gender d : genders) {
                FileUtils.write(file, genToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void studentGenerator(List<Student> students, String output) {
        File file = FileUtils.getFile(output);
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (Student d : students) {
                FileUtils.write(file, stuToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selectCourseGenerator(List<SelectCourse> selectCourses, String output) {
        File file = FileUtils.getFile(output);
        try {
//            FileUtils.write(file, BEGIN, UTF8, true);
            for (SelectCourse d : selectCourses) {
                FileUtils.write(file, scToStr(d), UTF8, true);
            }
//            FileUtils.write(file, COMMIT, UTF8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
