import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Enrollment> enrollments= new ArrayList<>();
    private String major;
    private double gpa;
    int credit_hours=0;
    private ArrayList<Courses> registeredCourses = new ArrayList<>();
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
    public boolean registerCourse(Courses course,String semester){
        for (Enrollment e: enrollments){
            if (e.getCourse().getCourseCode().equals(course.getCourseCode())){
                System.out.println("Already registered");
                return false;
            }
        }
        int max_credit;
        if(gpa<2.0)
            max_credit=9;
        else
            max_credit=18;
        if (CalculateCredithours()+course.getCredits()>max_credit)
        {
            System.out.println("Cannot exceed " + max_credit + " credits");
            return false;
        }
        if (!verify(course)) {
            System.out.println("Prerequisites not met");
            return false;
        }
        if (!course.add())
        {
            System.out.println("No seats available");
            return false;
        }
        Enrollment enrollment  = new Enrollment(course,semester);
        enrollments.add(enrollment);
        registeredCourses.add(course);
        System.out.println("Course registered successfully");
        return true;
    }

    public boolean verify(Courses course) {
        for (String pre : course.getPrerequisites()) {
            boolean found = false;
            for (Enrollment e : enrollments) {
                if (e.getCourse().getCourseCode().equals(pre)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
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

    //drop course part
    public boolean DropSubject(Courses course){
        Enrollment found = null;
        for (Enrollment e: enrollments){
            if (e.getCourse().getCourseCode().equals(course.getCourseCode())){
                found=e;
                break;
            }
        }
        if(found==null){
            System.out.println("You are not enrolled");
            return false;
        }
        course.drop();
        enrollments.remove(found);
        registeredCourses.remove(course);
        credit_hours-=course.getCredits();
        return true;
    }
    public ArrayList<Courses> getRegisteredCourses() {
        return registeredCourses;
    }
}
