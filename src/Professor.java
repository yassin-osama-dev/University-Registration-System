import java.util.ArrayList;

public class Professor extends Person {
    private String department;
    private ArrayList<Courses> teachingCourses=new ArrayList<>();
    Professor(String name,String ID,String Email,String department)
    {
        super(name,ID,Email);
        setDepartment(department);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            this.department = "General";
        } else {
            this.department = department.trim();
        }
    }

    @Override
    public String displayProfile() {
        String profile = "Name: " + getName()
                + "\nID: " + getID()
                + "\nEmail: " + getEmail()
                + "\nDepartment: " + getDepartment();
        System.out.println(profile);
        return profile;
    }
    public void assignCourses(Courses courses){
        for (Courses teachingCourse : teachingCourses) {
            if (teachingCourse.getCourseCode().equals(courses.getCourseCode())) {
                return;
            }
        }
        teachingCourses.add(courses);
    }
    public ArrayList<Courses> getTeachingCourses() {
        return teachingCourses;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString())
                .append(",").append(getDepartment());
        for (Courses course : getTeachingCourses()) {
            result.append(",").append(course.getCourseCode());
        }
        return result.toString();
    }
}
