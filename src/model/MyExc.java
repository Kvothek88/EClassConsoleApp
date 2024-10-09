
package model;

/**
 * Κλάση που κληρονομεί απο το exception για διαχείρηση λαθών.
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */

// Άπο την στιγμή που κληρονομεί άπο την κλάση Exception και όχι από την RuntimeException, πρόκειται για Checked exception (ή compile-time exception) το οποίο σε όποια μέθοδο συμβεί είτε πρέπει να το διαχειριστούμε
// με ένα try-catch block αλλιώς πρέπει να δηλώσουμε οτι η μέθοδος throws MyExc για να κάνει propagate στο calling chain.
public class MyExc extends Exception {
    
    private final int ErrorCode; // Error code. Θα πάρει την τιμή του στον κατασκευαστή παιρνώντας σαν παράμετρο μία από τις δύο final static variables (constants). 
                                 // Επίσης το error code είναι και αυτό final διότι από όταν αρχικοποιηθεί μέσω του κατασκευαστή δεν έχει νόημα να του αλλάξουμε την τιμή. Γι'αυτό δεν υπάρχει και setter για αυτό το πεδίο.
    final static int InvalidSemester = 1; // Σταθερά για το error code 1 
    final static int InputMismatch = 2; // Σταθερά για το error code 2
    
    public MyExc(int ErrorCode, String message){
        super(message);
        this.ErrorCode = ErrorCode;
    }

    public int getErrorCode() {
        return ErrorCode;
    }
    
    @Override
    public String toString() {
        return "MyExc{" +
                "ErrorCode=" + ErrorCode +
                ", message=" + getMessage() +
                '}';
    }
}
