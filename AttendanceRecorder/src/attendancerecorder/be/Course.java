
package attendancerecorder.be;

public class Course {
    
    private int id;
    private String name;
    
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
    
    //Constructors

    public Course() {
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }  
        
}
