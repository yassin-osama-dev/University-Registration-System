import java.util.ArrayList;

public class Courses {
     private String courseCode;
     private String title;
     private int credits;
     private int seats=0;
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public int getCredits() {
        return credits;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getTitle(){
        return title;
    }

    Courses(String courseCode, String title, int credits) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
    }

    public boolean full(){
        if(seats<30)
            return false;
        else
            return true;
    }

    public boolean add(){
        if(!full()){
            seats++;
            return true;
        }
        else
            return false;
    }
}
