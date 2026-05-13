import java.util.ArrayList;

public class Courses {
     String courseCode;
     String title;
     int credits;
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
