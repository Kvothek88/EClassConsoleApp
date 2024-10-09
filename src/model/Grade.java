
package model;

/**
 * Κλάση όπου κάθε αντικείμενό της είναι μία βαθμολογία για ένα συσκεκριμένο φοιτητή κι ένα συγκεκριμένο μάθημα.
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */
public class Grade {
    
    private String AM;
    private String CID;
    private float courseGrade;
    
    public Grade(String AM, String CID, float courseGrade) {
        this.AM = AM;
        this.CID = CID;
        this.courseGrade = courseGrade;
    }

    public String getAM() {
        return AM;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public float getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(float courseGrade) {
        this.courseGrade = courseGrade;
    }
    
}