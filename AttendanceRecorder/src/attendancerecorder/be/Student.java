/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.be;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String message;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

//    public Student(int id, String email, String password) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//    }
    public Student(int id,String firstName, String message) {
        this.id=id;
        this.firstName = firstName;
        this.message = message;
    }
    public Student(int id, int status) {
        this.id = id;
        this.status = status;
    }
    public Student(String name, int status){
        this.firstName=name;
        this.status=status;
    }
    //ToString
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + '}';
    }
    
    

}
