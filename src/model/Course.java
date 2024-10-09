
package model;

/**
 * Κλάση η οποία περιέχει τα στοιχεία ενός μαθήματος.
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */
public class Course {
    
    private String CourseID;
    private String CName;
    private int Csemester;
    
    public Course(String CourseID, String CName, int CSemester) throws MyExc{
        this.CourseID = CourseID.trim();
        this.CName = CName.trim();
       if (CSemester<1)
            throw new MyExc(MyExc.InvalidSemester,"Invalid Semester Value");
        this.Csemester = CSemester;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String CourseID) {
        this.CourseID = CourseID.trim();
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName.trim();
    }

    public int getCsemester() {
        return Csemester;
    }

    public void setCsemester(int Csemester) {
        this.Csemester = Csemester;
    }
    
    
    @Override
    public String toString(){
        return "ID: " + CourseID + " Name: " + CName + " Semester: " + Csemester;
    }
    
}
