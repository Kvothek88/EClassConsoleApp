
package model;

import java.util.ArrayList;

/**
 * Η πατρική κλάση του Student και του Professor.
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */
public class Person {
    
    protected String Name;
    protected String Email;
    protected String Phone;
    protected ArrayList<Course> Courses;
    
    public Person(String Name, String Email, String Phone){
        this.Name = Name.trim();
        this.Email = Email.trim();
        this.Phone = Phone.trim();
        Courses = new ArrayList<Course>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name.trim();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email.trim();
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone.trim();
    }
    
    public void AddCourse(Course crs){
        Courses.add(crs);
    }
    
    public ArrayList<Course> GetCourses(){
        return Courses;
    }
    
    public void ShowCourses(){
        System.out.println("\nCourses of " + this.getName());
        System.out.println("------------------------------------");
        Courses.forEach(c -> System.out.println(c.getCName()));
    }
    
    
    @Override
    public String toString(){
        return "Name: " + Name + " Email: " + Email + " Phone: " + Phone;
    }
    
}
