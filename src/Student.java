import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Enrollment> enrollments = new ArrayList<>();
    private String major;
    private double gpa;
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

    public String displayProfile(){
        String profile = "Name: " + getName()
                + "\nID: " + getID()
                + "\nEmail: " + getEmail()
                + "\nMajor: " + major
                + "\nGPA: " + gpa;
        System.out.println(profile);
        return profile;
    }
    public boolean registerCourse(Courses course){
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
        Enrollment enrollment = new Enrollment(course);
        enrollments.add(enrollment);
        System.out.println("Course registered successfully");
        return true;
    }

    public void addEnrollment(Courses course) {
        for (Enrollment e : enrollments) {
            if (e.getCourse().getCourseCode().equals(course.getCourseCode())) {
                return;
            }
        }
        enrollments.add(new Enrollment(course));
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
        StringBuilder result = new StringBuilder(super.toString())
                .append(",").append(getMajor())
                .append(",").append(getGpa());
        for (Courses course : getRegisteredCourses()) {
            result.append(",").append(course.getCourseCode());
        }
        return result.toString();
    }

    public boolean DropSubject(Courses course){
        Enrollment found = null;
        for (Enrollment e: enrollments){
            if (e.getCourse().getCourseCode().equals(course.getCourseCode())){
                found=e;
                break;
            }
        }
        if (found == null) {
            System.out.println("You are not enrolled");
            return false;
        }
        course.drop();
        enrollments.remove(found);
        return true;
    }
    public ArrayList<Courses> getRegisteredCourses() {
        ArrayList<Courses> registeredCourses = new ArrayList<>();
        for (Enrollment e : enrollments) {
            registeredCourses.add(e.getCourse());
        }
        return registeredCourses;
    }
}
