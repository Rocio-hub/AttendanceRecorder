
package attendancerecorder.be;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String message;
    private String dayOfWeek;
    private int status; // 1: present 0: absent
    private float absencePercentage;

    //Getters and Setters for all variables
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getAbsencePercentage() {
        return absencePercentage;
    }

    public void setAbsencePercentage(float absencePercentage) {
        this.absencePercentage = absencePercentage;
    }

    //Constructors
    
    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Student(int id, String firstName, String message) {
        this.id = id;
        this.firstName = firstName;
        this.message = message;
    }

    public Student(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public Student(String name, int status) {
        this.firstName = name;
        this.status = status;
    }

    public Student(int id,String name, float absencePercentage, String email) {
        this.id = id;
        this.firstName = name;
        this.absencePercentage = absencePercentage;
        this.email = email;
    }
    public Student(int id, String dayOfWeek ){
        this.id=id;
        this.dayOfWeek=dayOfWeek;
    }

    public Student(int id, int status, String dayOfWeek) {
        this.id = id;
        this.status = status;
        this.dayOfWeek = dayOfWeek;        
    }
    
    //ToString
    
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + '}';
    }

}
