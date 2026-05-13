
public class Student extends Person {
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
    @Override
    public String toString() {
        return super.toString()+","+getMajor()+","+getGpa();
    }

}
