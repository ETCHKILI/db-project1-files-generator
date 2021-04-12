package group.cs307lab.generator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


import com.alibaba.fastjson.JSON;
import group.cs307lab.entities.*;
import org.apache.commons.io.FileUtils;

/**
 * @author AnGuangyan
 * @date 2021/3/10
 */
public class CriToCourseGenerator {

    public CriToCourseGenerator(String fileName) {
        File file = new File(fileName);
        try {
            jsonStr = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String jsonStr;
    private List<CourseReadIn> readIns;
    private List<Department> departments = new ArrayList<>();
    private List<WeekListInfo> weekLists = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<MainCourse> mainCourses = new ArrayList<>();
    private List<ClassDetail> classDetails = new ArrayList<>();

    public void readAllCoursesInJsonFile() {
        readIns = JSON.parseArray(jsonStr, CourseReadIn.class);
    }

    private boolean hasDept(String deptName) {
        for (Department de : departments) {
            if (de.getName().equals(deptName)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWeekList(List<String> list) {
        for (WeekListInfo w : weekLists) {
            if (w.hasSameContent(list)) {
                return true;
            }
        }
        return false;
    }

    public void generateDepartments() {
        if (readIns != null) {
            int i = 1;
            for (CourseReadIn cri : readIns) {
                if (!hasDept(cri.getCourseDept())) {
                    Department department = new Department();
                    department.setName(cri.getCourseDept());
                    department.setId(i++);
                    departments.add(department);
                }
            }
        }
    }

    public void generateWeekLists() {
        if (readIns != null) {
            int i = 1;
            for (CourseReadIn cri : readIns) {
                List<CourseReadIn.ClassInfo> l = cri.getClassList();
                for (CourseReadIn.ClassInfo in : l) {
                    if (!hasWeekList(in.getWeekList())) {
                        WeekListInfo weekListInfo = new WeekListInfo();
                        weekListInfo.setWeekList(in.getWeekList());
                        weekListInfo.setId(i++);
                        weekLists.add(weekListInfo);
                    }
                }
            }
        }
    }

    public void generateCourses() {
        int i = 1;
        for (CourseReadIn cri : readIns) {
            courses.add(new Course(cri, i++, weekLists, departments));
        }
    }

    private boolean hasMainCourse(Course course) {
        for (MainCourse m : mainCourses) {
            if (m.hasSameContent(course)) {
                return true;
            }
        }
        return false;
    }

    private int getCourseId(String name) {
        for (MainCourse m : mainCourses) {
            if (m.getCourseName().trim().equals(name.trim())) {
                return m.getId();
            }
        }
        return -1;
    }

    public void generateMainCourses() {
        int i = 1;
        for (Course c : courses) {
            if (!hasMainCourse(c)) {
                mainCourses.add(new MainCourse(c, i++));
            }
        }
    }

    public void manipulateMainCourse() {
        String prerequisite;
        for (MainCourse m0 : mainCourses) {
            prerequisite = m0.getPrerequisiteStr();
            if (prerequisite != null && !"null".equals(prerequisite)) {
                boolean searching = false;
                StringBuilder builder = new StringBuilder();
                int brackets = 0;
                for (int i = 0; i < prerequisite.length(); i++) {
                    if (prerequisite.charAt(i) == '(' && !searching) {
                        continue;
                    } else if (prerequisite.charAt(i) == '(') {
                        if (prerequisite.charAt(i + 1) == '上' || prerequisite.charAt(i + 1) == '下') {
                            brackets++;
                        } else {
                            searching = false;
                            brackets = 0;
                        }
                    } else if (prerequisite.charAt(i) == ')') {
                        if (brackets == 0 && searching) {
                            searching = false;
                        } else if (searching) {
                            brackets--;
                        }
                    } else if (prerequisite.charAt(i) == '或' || prerequisite.charAt(i) == '并') {
                        if (searching) {
                            searching = false;
                            brackets = 0;
                        }
                    } else if (prerequisite.charAt(i) == '者' || prerequisite.charAt(i) == '且') {
                        continue;
                    } else if (prerequisite.charAt(i) == ' ' && !searching){
                        continue;
                    } else {
                        searching = true;
                    }

                    if (searching && i == prerequisite.length() - 1) {
                        builder.append(prerequisite.charAt(i));
                        String beReplaced = builder.toString();
                        int toReplace = getCourseId(beReplaced);
//                        String toReplaceStr = toReplace != -1 ? String.valueOf(toReplace) : "null";
                        if (toReplace == -1) {
                            break;
                        }
                        String toReplaceStr = String.valueOf(toReplace);
                        prerequisite = prerequisite.replace(beReplaced.trim(), toReplaceStr);
                        break;
                    }

                    if (searching) {
                        builder.append(prerequisite.charAt(i));
                    } else if (!builder.isEmpty()) {
                        String beReplaced = builder.toString();
                        builder = new StringBuilder();
                        int toReplace = getCourseId(beReplaced);
                        if (toReplace == -1) {
                            continue;
                        }
                        String toReplaceStr = String.valueOf(toReplace);
                        prerequisite = prerequisite.replace(beReplaced.trim(), toReplaceStr);
                        i -= beReplaced.trim().length() - toReplaceStr.length();
                    }
                }
                prerequisite = prerequisite.replaceAll("或者", "or");
                prerequisite = prerequisite.replaceAll("并且", "and");
                m0.setPrerequisiteStr(prerequisite);
            }
        }
    }

    public void generateClassDetail() {
        for (Course c : courses) {
            classDetails.add(new ClassDetail(c, mainCourses));
        }
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<WeekListInfo> getWeekLists() {
        return weekLists;
    }

    public List<MainCourse> getMainCourses() {
        return mainCourses;
    }

    public List<ClassDetail> getClassDetails() {
        return classDetails;
    }

    public List<CourseReadIn> getReadIns() {
        return readIns;
    }

    public void setReadIns(List<CourseReadIn> readIns) {
        this.readIns = readIns;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void setWeekLists(List<WeekListInfo> weekLists) {
        this.weekLists = weekLists;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setMainCourses(List<MainCourse> mainCourses) {
        this.mainCourses = mainCourses;
    }

    public void setClassDetails(List<ClassDetail> classDetails) {
        this.classDetails = classDetails;
    }
}
