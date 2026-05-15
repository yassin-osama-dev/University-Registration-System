import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Enrollment> enrollments= new ArrayList<>();
    private String major;
    private double gpa;
    int credit_hours=0;

    public Student(String name, String ID, String Email, String major, double gpa){
        super(name, ID, Email);
        this.major=major;
        this.gpa=gpa;
    }

    public double getGpa() {
        return gpa;
    }
    public String getMajor() {
        return major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    public void displayProfile(){
        System.out.println("Name: "+ getName());
        System.out.println("ID: "+ getID());
        System.out.println("Email: "+ getEmail());
        System.out.println("Major: "+ major);
        System.out.println("gpa: "+ gpa);
    }
    public void registerCourse(Courses course,String semester){
        for (Enrollment e: enrollments){
            if (e.getCourse().getCourseCode().equals(course.getCourseCode())){
                System.out.println("Already registered");
                return;
            }
        }
        if (CalculateCredithours()+course.getCredits()>18)
        {
            System.out.println("Cannot exceed 18 credits");
            return;
        }
        if (!course.add())
        {
            System.out.println("No seats available");
            return;
        }
        Enrollment enrollment  = new Enrollment(course,semester);
        enrollments.add(enrollment);
        System.out.println("Course registered successfully");
    }
    public void viewCourse()
    {
        if (enrollments.isEmpty())
            {
            System.out.println("No enrollments available");
            }
        else {
            for (Enrollment e: enrollments)
            {
                System.out.println("Course code: "+e.getCourse().getCourseCode());
                System.out.println("Course title: "+e.getCourse().getTitle());
                System.out.println("Course credits: "+e.getCourse().getCredits());
                System.out.println("---------------------");
            }
        }
    }
    public int CalculateCredithours()
    {
        int total=0;
        for (Enrollment e: enrollments){
            total+=e.getCourse().getCredits();
        }
        return total;
    }
    @Override
    public String toString() {
        return super.toString()+","+getMajor()+","+getGpa();
    }

}
