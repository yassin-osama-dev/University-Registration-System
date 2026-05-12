public class Professor extends Person {
    private String department;
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

}
