
package model;

/**
 * Κλάση με τα στοιχεία του φοιτητή. 
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */
public class Student extends Person {
    
    private String AM;
    private int Semester;
    
    public Student(String Name, String Email, String Phone,String AM, int Semester) throws MyExc{
        super(Name,Email,Phone);
        this.AM = AM.trim();
        if (Semester<1)
            throw new MyExc(MyExc.InvalidSemester,"Invalid Semester Value");
        this.Semester = Semester;
    }

    public String getAM() {
        return AM;
    }

    public void setAM(String AM) {
        this.AM = AM.trim();
    }

    public int getSemester() {
        return Semester;
    }
    
    // Ο setter του εξαμήνου πετάει exception MyExc σε περίπτωση που δοθεί τιμή <= 0.
    public void setSemester(int Semester) throws MyExc {
        if (Semester<1)
            throw new MyExc(MyExc.InvalidSemester,"Invalid Semester Value");
        this.Semester = Semester;
    }
    
    // 
    @Override
    public String toString(){
        return super.toString() + " AM: " + AM + " Semester: " + Semester;
    }
}
