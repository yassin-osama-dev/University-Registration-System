import java.util.ArrayList;

public class Courses {
    private static final int MAX_SEATS = 30;
    private String courseCode;
    private String title;
    private int credits;
    private int seats = 0;
    private ArrayList<String> prerequisites = new ArrayList<>();

    public int getCredits() {
        return credits;
    }

    public int getSeats() {
        return seats;
    }

    public int getSeatsLeft() {
        return MAX_SEATS - seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getCourseCode(){
        return courseCode;
    }
    public String getTitle(){
        return title;
    }
    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    Courses(String courseCode, String title, int credits) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
    }

    public boolean full() {
        return seats >= MAX_SEATS;
    }

    public boolean add(){
        if(!full()){
            seats++;
            return true;
        }
        else
            return false;
    }

    public void drop(){
        if (seats > 0) {
            seats--;
        }
    }
    public void addPrerequisite(String courseCode) {
        prerequisites.add(courseCode);
    }
}
