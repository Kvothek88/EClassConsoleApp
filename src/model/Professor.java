
package model;

/**
 * Κλάση με τα στοιχεία του καθηγητή.
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */
public class Professor extends Person {
    
    private String PID;
    private String Discipline;
    
    public Professor(String Name, String Email, String Phone, String PID, String Discipline){
        super(Name,Email,Phone);
        this.PID = PID.trim();
        this.Discipline = Discipline.trim();
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID.trim();
    }

    public String getDiscipline() {
        return Discipline;
    }

    public void setDiscipline(String Discipline) {
        this.Discipline = Discipline.trim();
    }

    @Override
    public String toString() {
        return super.toString() + " PID: " + PID + " Discipline: " + Discipline;
    }
   
}
