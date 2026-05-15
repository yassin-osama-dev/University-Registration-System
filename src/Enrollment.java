public class Enrollment {
    private Courses course;
    private String semester;
    private String grade;
    Enrollment(Courses course, String semester) {
        this.course = course;
        this.semester = semester;
    }
    public Courses getCourse(){
        return course;
    }
    public String getSemester(){
        return semester;
    }
    public String getGrade(){
        return grade;
    }
    public void setCourse(Courses course){
        this.course = course;
    }
    public void setSemester(String semester){
        this.semester = semester;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }
}
