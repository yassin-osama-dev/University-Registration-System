public abstract class Person {
    private String ID;
    private String name;
    private String email;

    public Person(String name, String ID, String email){
        this.name=name;
        this.ID=ID;
        this.email=email;
    }

    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public abstract void displayProfile();
}
