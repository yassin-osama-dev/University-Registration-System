import java.util.ArrayList;

public class Professor extends Person {
    private String department;
    private ArrayList<Courses> teachingCourses=new ArrayList<>();
    Professor(String name,String ID,String Email,String department)
    {
        super(name,ID,Email);
        this.department=department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public void displayProfile() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getID());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + getDepartment());
    }
    public void assignCourses(Courses courses){
        teachingCourses.add(courses);
    }
    public void viewTeachingCourses(){
        for(Courses courses:teachingCourses){
            System.out.println("Course code: "+courses.getCourseCode());
            System.out.println("Title: "+courses.getTitle());
            System.out.println("Credits: "+courses.getCredits());
            System.out.println("---------------------");
        }
    }
    public ArrayList<Courses> getTeachingCourses() {
        return teachingCourses;
    }

    @Override
    public String toString() {
        return super.toString() + "," + getDepartment();
    }
}
