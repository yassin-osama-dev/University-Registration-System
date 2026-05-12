import java.util.ArrayList;

public class Courses {
     String courseCode;
     String title;
     int credits;
     static int seats=0;
    static public void counter()
    {
        if (seats<=30)
        {
            seats++;
        }
    }
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
        counter();
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
    }
}
