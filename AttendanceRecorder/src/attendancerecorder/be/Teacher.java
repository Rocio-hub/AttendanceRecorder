
package attendancerecorder.be;

public class Teacher {
    private int id;
    private String name;
    private String email;
    private String password;
   
    //Getters and Setters for all variables

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Constructors
    
    public Teacher() {
    }

    public Teacher(String name,String email, String password) {
        this.name=name;
        this.password = password;
        this.email = email;
    }

    public Teacher(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
}

