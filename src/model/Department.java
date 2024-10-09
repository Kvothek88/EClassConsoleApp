
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Course;
import model.Person;
import model.Professor;
import model.Student;

/**
 *
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */
public class Department {
    
    private ArrayList<Course> AllCourses;
    private ArrayList<Person> AllDepMembers;
    private ArrayList<Grade> Grades;
    private Scanner input = new Scanner(System.in);
    
    public Department(){
        AllCourses = new ArrayList<>();
        AllDepMembers = new ArrayList<>();
        Grades = new ArrayList<>();
    }
    
    public ArrayList<Course> getAllCourses(){
        return AllCourses;
    }
    
    public ArrayList<Person> getAllDepMembers(){
        return AllDepMembers;
    }
    
    public ArrayList<Grade> getGrades(){
        return Grades;
    }
    
    /**
     * Η μέθοδος αυτή ανάλογα με την μεταβλητή choice δείχνει όλους τους φοιτητές/καθηγητές/μέλη του τμήματος και τρέχει τις ανάλογες μεθόδους.
     * 
     */
    public void ShowAllDeptMembers(){
        
        int choice = 0;
        
        try{
            System.out.print("Type '1' to show all Students or '2' to show all Professors or '3' to show all Department Members: ");
            choice = input.nextInt(); input.nextLine();          
        }
        catch(InputMismatchException e){
            System.out.println(e.toString());
            System.out.println("Error: Input was not a valid integer.");
            input.nextLine();
            return;
        }
        
        System.out.println();
        
        if (choice==1){
            ShowAllStudents();
        }
        else if (choice==2){
            ShowAllProfessors();
        }
        else if (choice==3){
            System.out.println("DEPARTMENT MEMBERS");
            System.out.println("------------------------------------");  
            AllDepMembers.forEach(m -> System.out.println(m.toString()));
        }
        else
            System.out.println("Wrong choice");

    }
    
    /**
     * Μέθοδος όπου εκτυπώνονται όλοι οι φοιτητές στην κονσόλα
     */
    public void ShowAllStudents(){
        System.out.println("STUDENTS");
        System.out.println("------------------------------------");          
        AllDepMembers.forEach(m -> {
            if (m instanceof Student){
                System.out.println(m.toString());
            }
        });
    }
    
    /**
     * Μέθοδος όπου εκτυπώνονται όλοι οι καθηγητές στην κονσόλα
     */
    public void ShowAllProfessors(){
        System.out.println("PROFESSORS");
        System.out.println("------------------------------------");  
        AllDepMembers.forEach(m -> {
            if (m instanceof Professor){
                System.out.println(m.toString());
            }
        });
    }   
    
    /**
     * Μέθοδος που εκτυπώνει στην κονσόλα όλα τα μαθήματα
     */
    public void ShowAllCourses(){
        System.out.println("COURSES");
        System.out.println("------------------------------------");  
        AllCourses.forEach(m -> System.out.println(m.toString()));
    }
    
    /**
     * Μέθοδος αναζήτησης μέλους του τμήματος με id
     */
    public void FindMemberByID(){
        
        int choice = 0;
        
        try{
            System.out.print("Type '1' if looking for Student or '2' if looking for Professor: ");
            choice = input.nextInt(); input.nextLine();            
        }
        catch(InputMismatchException e){
            System.out.println(e.toString());
            System.out.println("Error: Input was not a valid integer.");
            input.nextLine();
            return;
        }

        if (choice==1){
            System.out.print("Give me the student's AM: ");
            String am = input.nextLine();
            Student stud = SearchStudentByAM(am);
            if (stud != null){
                System.out.println();
                System.out.println(stud.toString());
            }
            else
                System.out.printf("AM %s does not exist\n",am);
        }
        else if (choice==2){
            System.out.print("Give me the Professor's ID: ");
            String id = input.nextLine();
            Professor prof = SearchProfessorByID(id);
            if (prof != null){
                System.out.println();
                System.out.println(prof.toString());
            }
            else
                System.out.printf("ID %s does not exist\n",id);
        }
        else
            System.out.println("Your choice should be '1' or '2'");
    }

     /**
      * Αυτή η μέθοδος παίρνει σαν παράμετρο έναν αριθμό μητρώου και επιστρέφει ένα αντικείμενο Student
      * @param AM
      * @return Student 
      */
    public Student SearchStudentByAM(String AM){
        
        for (Person pers : AllDepMembers){
            if (pers instanceof Student){
                Student stud = (Student) pers;
                if (stud.getAM().equalsIgnoreCase(AM)){
                    return stud;
                }
            }
        }
        return null;
    }
    
     /**
      * Αυτή η μέθοδος παίρνει σαν παράμετρο ένα ID και επιστρέφει ένα αντικείμενο Professor
      * @param ID
      * @return Professor 
      */
    public Professor SearchProfessorByID(String ID){
        
        for (Person pers : AllDepMembers){
            if (pers instanceof Professor){
                Professor prof = (Professor) pers;
                if (prof.getPID().equalsIgnoreCase(ID)){
                    return prof;
                }
            }
        }
        return null;
    }

    /**
     * Μέθοδος αναζήτησης μαθήματος μέσω ID
     */
    public void FindCourseByID(){
        
        System.out.print("Give me course's ID: ");
        String id = input.nextLine();
        
        Course cour = SearchCourseByID(id);
        if (cour != null){
            System.out.println();
            System.out.println(cour.toString());
        }
        else
            System.out.printf("ID %s does not exist\n",id);
        
    }
    
    /**
     * Αυτή η μέθοδος παίρνει σαν παράμετρο ένα ID και επιστρέφει ένα αντικείμενο Course
     * @param ID
     * @return Course
     */
    public Course SearchCourseByID(String ID){
        
        for (Course cour : AllCourses){
            if (cour.getCourseID().equalsIgnoreCase(ID))
                return cour;
        }
        return null;
    }
    
    /**
     * Μέθοδος ανάγνωσης του αρχείου students.csv κατά την εκκίνηση του προγράμματος. Παίρνει σαν παράμετρο το ArrayList AllDepMembers και το γεμίζει με τα δεδομένα του αρχείου μετατρέποντας τα σε αντικείμενα τύπου Student.
     * Επίσης πετάει το Exception MyExc σε περίπτωση που κατά την αρχικοποίηση της μεταβλητής semester γίνει εισαγωγή μη έγκυρου τύπου δεδομένων.
     * @param members
     * @throws MyExc 
     */
    public void StudentsReadFromCSV(ArrayList<Person> members) throws MyExc{

        String csvFile = "./data/students.csv";
        String line;
            
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){

            br.skip(1);
            while((line = br.readLine()) != null){
                String[] values = line.split(";");

                String name = values[0];
                String email = values[1];
                String phone = values[2];
                String am = values[3];
                int semester;
                try{
                    semester = Integer.parseInt(values[4]);
                }
                catch(NumberFormatException e){
                    throw new MyExc(MyExc.InputMismatch, "Semester must be an integer");
                }
                
                Student stud = new Student(name,email,phone,am,semester);
                members.add(stud);

            }
        }
        catch(FileNotFoundException e) {
            e.getMessage();
        }           
        catch(IOException e) {
            e.getMessage();
        }
        catch(MyExc e){
            e.getMessage();
        }
        catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    /**
     * Μέθοδος ανάγνωσης του αρχείου professors.csv κατά την εκκίνηση του προγράμματος. Παίρνει σαν παράμετρο το ArrayList AllDepMembers και το γεμίζει με τα δεδομένα του αρχείου μετατρέποντας τα σε αντικείμενα τύπου Professor.
     * @param members 
     */    
    public void ProfessorsReadFromCSV(ArrayList<Person> members){

        String csvFile = "./data/professors.csv";
        String line;
            
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
                
            br.skip(1);
            while((line = br.readLine()) != null){
                String[] values = line.split(";");

                String name = values[0];
                String email = values[1];
                String phone = values[2];
                String id = values[3];
                String discipline = values[4];
                Professor prof = new Professor(name,email,phone,id,discipline);
                members.add(prof);
            }
        }
        catch(FileNotFoundException e) {
            e.getMessage();
        } 
        catch(IOException e) {
            e.getMessage();
        }
        catch(NumberFormatException e){
            e.getMessage();
        }
            
    }

    /**
     * Μέθοδος ανάγνωσης του αρχείου courses.csv κατά την εκκίνηση του προγράμματος. Παίρνει σαν παράμετρο το ArrayList AllCourses και το γεμίζει με τα δεδομένα του αρχείου μετατρέποντας τα σε αντικείμενα τύπου Course.
     * Επίσης πετάει το Exception MyExc σε περίπτωση που κατά την αρχικοποίηση της μεταβλητής semester γίνει εισαγωγή μη έγκυρου τύπου δεδομένων.
     * @param courses
     * @throws MyExc 
     */
    public void CoursesReadFromCSV(ArrayList<Course> courses) throws MyExc{

        String csvFile = "./data/courses.csv";
        String line;
           
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){

            br.skip(1);
            while((line = br.readLine()) != null){
                String[] values = line.split(";");

                String id = values[0];
                String name = values[1];
                int semester;
                
                try{
                    semester = Integer.parseInt(values[2]);
                }
                catch(NumberFormatException e){
                    throw new MyExc(MyExc.InputMismatch, "Semester must be an integer");
                }
                
                Course cour = new Course(id,name,semester);
                courses.add(cour);

            }
        }
        catch(FileNotFoundException e) {
            e.getMessage();
        } 
        catch(IOException e) {
            e.getMessage();
        }
        catch(NumberFormatException e){
            e.getMessage();
        }
        catch(MyExc e){
            e.getMessage();
        }     
    }

    /**
     * Μέθοδος ανάγνωσης των αρχείων studentsReg.csv και professorsReg.csv κατά την εκκίνηση του προγράμματος τα οποία περιέχουν τον κατάλογο μαθημάτων που είναι εγγεγραμμένοι οι φοιτητές και οι καθηγητές.
     */
    public void ReadCourseRegFromCSV(){
        
        String studRegCsv = "./data/studentsReg.csv";
        String profRegCsv = "./data/professorsReg.csv";
        String line;
            
        try(BufferedReader br = new BufferedReader(new FileReader(studRegCsv))){

            br.skip(1);
            while((line = br.readLine()) != null){
                String[] values = line.split(";");
                
                String studAM = values[0];
                String CID = values[1];
                Student stud = SearchStudentByAM(studAM);
                Course crs = SearchCourseByID(CID);
                stud.AddCourse(crs);
            }
        }
        catch(FileNotFoundException e) {
            e.getMessage();
        } 
        catch(IOException e) {
            e.getMessage();
        }
        catch(NumberFormatException e){
            e.getMessage();
        }
            
        try(BufferedReader br = new BufferedReader(new FileReader(profRegCsv))){

            br.skip(1);
            while((line = br.readLine()) != null){
                String[] values = line.split(";");

                String profID = values[0];
                String CID = values[1];
                Professor prof = SearchProfessorByID(profID);
                Course crs = SearchCourseByID(CID);
                prof.AddCourse(crs);
            }
        }
        catch(FileNotFoundException e) {
            e.getMessage();
        } 
        catch(IOException e) {
            e.getMessage();
        }
        catch(NumberFormatException e){
            e.getMessage();
        } 
    }

    /**
     * Μέθοδος ανάγνωσης του αρχείου grades.csv κατά την εκκίνηση του προγράμματος το οποίο περιέχει τις βαθμολογίες των φοιτητών. Επιπλέον παίρνει σαν παράμετρο το ArrayList Grades και το γεμίζει με αντικείμενα τύπου Grade 
     * το οποίο είναι μία βαθμολογία για ένα συγκεκριμένο φοιτητή και ένα συγκεκριμένο μάθημα.
     * Επίσης πετάει το Exception MyExc σε περίπτωση εισαγωγής μη έγκυρων δεδομένων στην μεταβλητή grades. 
     * @param grades
     * @throws MyExc 
     */
    public void ReadGradesFromCSV(ArrayList<Grade> grades) throws MyExc{
        
        String file = "./data/grades.csv";
        String line;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){

            br.skip(1);
            while((line = br.readLine()) != null){
                String[] values = line.split(";");

                String AM = values[0];
                String CID = values[1];
                String grd = values[2];
                float grade;
                
                try{
                    grade = Float.parseFloat(grd);
                }
                catch(NumberFormatException e){
                    throw new MyExc(MyExc.InputMismatch, "Grade must be a floating point number");
                }
                
                Grade gd = new Grade(AM,CID,grade);
                grades.add(gd);
            }
        }
        catch(FileNotFoundException e) {
            e.getMessage();
        } 
        catch(IOException e) {
            e.getMessage();
        }
        catch(NumberFormatException e){
            e.getMessage();
        }       
        
    }
        
    /**
     * Μέθοδος προσθήκης νέου μέλους του τμήματος. Για data validation χρησιμοποιείται η κλάση Regex.
     */
    public void AddDepartmentMember(){
        
        int choice;
        Student stud = null;
        String name = "";
        String email = "";
        String phone = "";
        String am = "";
        String id = "";
        int semester = 0;
        String discipline;
        String member;
        boolean exists;
        boolean validEmail;
        boolean validAM;
        boolean validPhone;
        System.out.print("Type '1' for Student or '2' for Professor: ");
        choice = input.nextInt(); input.nextLine();
        
        if (choice==1)
            member="student";
        else
            member="professor";
        
        // Εισαγωγή από τον χρήστη του ονόματος του φοιτητή/καθηγητή. Επαναλαμβάνεται μια do while loop μέχρι να δοθεί από τον χρήστη input με 1 ή περισσότερα γράμματα.
        do{
            System.out.printf("Give %s's name: ",member);
            name = input.nextLine();
            if (name.length()==0)
                System.out.println("Name should have at least one character");
        }while(name.length()==0);

        // Εισαγωγή από τον χρήστη του email του φοιτητή/καθηγητή. Γίνεται data validation με την κλάση regex ώστε το domain του mail να είναι @uniwa.gr.
        do{
            System.out.printf("\nGive %s's email: ",member);
            email = input.nextLine(); 
            validEmail = isValidEmail(email);
            if (!validEmail)
                System.out.println("Email should have the domain @uniwa.gr");
        }while (!validEmail);

        // Εισαγωγή από τον χρήστη του τηλεφώνου του φοιτητή/καθηγητή. Γίνεται data validation με την κλάση regex ώστε το τηλέφωνο να έχει το format 69xxxxxxxx.
        do{
            System.out.printf("\nGive %s's phone: ",member);
            phone = input.nextLine();
            validPhone = isValidPhone(phone);
            if (!validPhone)
                System.out.println("Phone should have the format 69xxxxxxxx");
        }while(!validPhone);        
        
        switch(choice){
            case 1:

                // Εισαγωγή από τον χρήστη του AM του φοιτητή. Γίνεται data validation με την κλάση regex ώστε το τηλέφωνο να έχει το format MSCICTxxxxx. Επίσης γίνεται έλεγχος αν ο αριθμός μητρώου υπάρχει ήδη.               
                do{
                    exists = false;
                    System.out.print("\nGive student am: ");
                    am = input.nextLine();
                    validAM = isValidAM(am);
                    if (!validAM)
                        System.out.println("AM should have the format MSCICTxxxxx");
                    ArrayList<String> amList = new ArrayList<>();
                    AllDepMembers.forEach(m -> {
                        if (m instanceof Student){
                            amList.add(((Student) m).getAM());
                        }
                    });                    
                    for (String chkAM : amList){
                        if (chkAM.equals(am)){
                            exists=true;
                            System.out.println("AM already exists");
                        }   
                    }
                }while(exists || !validAM);

                //Εισαγωγή από τον χρήστη του εξαμήνου του φοιτητή. Σε περίπτωση που γίνειεισαγωγή αρνητικού αριθμού ή 0 ή αλφαριθμητική τιμή επαναλαμβανεται η λούπα.
                boolean validInput = false;
                do{
                    try{
                        System.out.print("\nGive student's semester: ");
                        semester = input.nextInt(); input.nextLine();
                        validInput = true;
                        if (semester<1)
                            System.out.println("Semester should be bigger than zero");                       
                    }
                    catch(InputMismatchException e){
                        System.err.println("InputMismatch: Semester must be an integer. Please try again.");
                        input.nextLine();                      
                    }
                }while(!validInput || semester<1);
                
                try{
                    stud = new Student(name,email,phone,am,semester);
                }
                catch(MyExc e){
                    System.err.println(e.toString());
                }
                
                AllDepMembers.add(stud);
            break;
            case 2:
                
                // Εισαγωγή από τον χρήστη του id του καθηγητή. Σε περίπτωση ζητείται εκ νέου τιμή μέχρι να δοθεί έγρυρη
                do{
                    exists=false;
                    System.out.print("\nGive professor id: ");
                    id = input.nextLine();
                    ArrayList<String> idList = new ArrayList<>();
                    AllDepMembers.forEach(m -> {
                        if (m instanceof Professor){
                            idList.add(((Professor) m).getPID());
                        }
                    });                    
                    for (String chkID : idList){
                        if (chkID.equals(id)){
                            exists=true;
                            System.out.println("ID already exists");
                        }    
                    }
                }while(exists==true);
                
                System.out.print("\nGive professor discipline: ");
                discipline = input.nextLine();

                Professor prof = new Professor(name,email,phone,id,discipline);
                AllDepMembers.add(prof);
            default: System.out.println("Invalid choice"); break;
        }
        
    }
    
    /**
     * Μέθοδος εισαγωγής νέου μαθήματος. Για data validation χρησιμοποιείται η κλάση Regex.   
     */
    public void AddCourse(){
        
        String id;
        String name;
        int semester=0;
        boolean exists;
        boolean validID;
        Course crs;
        
        do{
            exists=false;
            System.out.print("Give course's id: ");
            id = input.nextLine();
            validID = isValidCourseID(id);
            if (!validID)
                System.out.println("ID should have the format ICTxxx");
            ArrayList<String> idList = new ArrayList<>();
            
            AllCourses.forEach(c -> idList.add(c.getCourseID()));
            
            for (String chkID : idList){
                if (chkID.equals(id)){
                    exists=true;
                    System.out.println("ID already exists");
                }
            }
        }while(exists || !validID);
        
        System.out.print("Give course's name: ");
        name = input.nextLine();
        
        do{
            try{
                System.out.print("Give course's semester: ");
                semester = input.nextInt();
                if (semester<1)
                    System.out.println("Semester should be bigger than zero");
            }
            catch(InputMismatchException e){
                System.err.println("InputMismatch: Semester must be an integer. Please try again.");
                input.nextLine();                      
            }
        }while(semester<1);
        
        try{
            crs = new Course(id,name,semester);
            this.getAllCourses().add(crs);
        }
        catch(MyExc e){
            System.out.println(e.getMessage());
        }        
        
    }
    
    /**
     * Μέθοδος διαγραφής μέλους του τμήματος.
     */
    public void DeleteDepartmentMember(){
        
        int choice = 0;
        System.out.print("Type '1' to delete student or '2' to delete professor: ");
        choice = input.nextInt(); input.nextLine();
        
        if (choice==1){
            System.out.println("\nSTUDENTS\n");
            ShowAllStudents();
            System.out.print("Give the am of the student you want to delete: ");
            String am = input.nextLine();
            Student stud = SearchStudentByAM(am);
            if (stud != null){
                AllDepMembers.remove(stud);
            }
            else{
                System.out.printf("Student with AM %s doesn't exist",am);
            }
        }
        else if (choice==2){
            System.out.println("\nPROFESSORS\n");
            ShowAllProfessors();
            System.out.print("Give the id of the professor you want to delete: ");
            String id = input.nextLine();
            Professor prof = SearchProfessorByID(id);
            if (prof != null){
                AllDepMembers.remove(prof);
            }
            else{
                System.out.printf("Professor with ID %s doesn't exist\n",id);
            }
        }
        else{
            System.out.println("Invalid choice");
        }
    }
    
    /**
     * Μέθοδος διαγραφής μαθήματος.
     */
    public void DeleteCourse(){
        
        System.out.print("Give the id of the course you want to delete: ");
        String id = input.nextLine();
        Course crs = SearchCourseByID(id);
        if (crs != null){
            AllCourses.remove(crs);
        }
        else{
            System.out.printf("Course with ID %s doesn't exist",id);
        }
    }
    
    /**
     * Στατική μέθοδος που χρησιμοποιεί την κλάση regex για data validation του τηλεφωνου.
     * @param input
     * @return Επιστρέφει μία boolean μεταβλητή η οποία είναι true εαν η παράμετρος που δίνουμε στην συνάρτηση ταιριάζει με το format του regex.
     */
    public static boolean isValidPhone(String input) {

        String regex = "^69\\d{8}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
    
    /**
     * Στατική μέθοδος που χρησιμοποιεί την κλάση regex για data validation του ID.
     * @param input
     * @return Επιστρέφει μία boolean μεταβλητή η οποία είναι true εαν η παράμετρος που δίνουμε στην συνάρτηση ταιριάζει με το format του regex.
     */    
    public static boolean isValidCourseID(String input) {

        String regex = "^ICT\\d{3}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
    
    /**
     * Στατική μέθοδος που χρησιμοποιεί την κλάση regex για data validation του AM.
     * @param input
     * @return Επιστρέφει μία boolean μεταβλητή η οποία είναι true εαν η παράμετρος που δίνουμε στην συνάρτηση ταιριάζει με το format του regex.
     */
    public static boolean isValidAM(String input) {

        String regex = "^MSCICT\\d{5}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
    
    /**
     * Στατική μέθοδος που χρησιμοποιεί την κλάση regex για data validation του email.
     * @param input
     * @return Επιστρέφει μία boolean μεταβλητή η οποία είναι true εαν η παράμετρος που δίνουμε στην συνάρτηση ταιριάζει με το format του regex.
     */    
    public static boolean isValidEmail(String input) {

        String regex = "^\\w+@uniwa\\.gr$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        return matcher.matches();
    }  
    
    /**
     * Μέθοδος επεξεργασίας στοιχείων μέλους του τμήματος. Χρησιμοποιεί την κλάση regex για data validation.
     */
    public void EditDepartmentMember(){
        
        int choice = 0;
        String name;
        String email;
        String phone;
        boolean validEmail;
        boolean validAM;
        boolean validPhone;
        System.out.print("Type '1' to edit student or '2' to edit professor: ");
        choice = input.nextInt(); input.nextLine();  
        
        if (choice==1){
            ShowAllStudents();
            int semester=0;
            String am;
            String semesterInput;
            System.out.print("Give the am of the student you want to edit: ");
            am = input.nextLine();
            Student stud = SearchStudentByAM(am);
            if (stud != null){
                System.out.println(stud.toString());
                
                System.out.print("Give new student name (Press enter to leave the old one): ");
                name = input.nextLine();
                if (name.length()>0)
                    stud.setName(name);
                
                do{
                    System.out.print("Give new student phone (Press enter to leave the old one): ");
                    phone = input.nextLine();
                    if (phone.length()==0)
                        break;
                    validPhone = isValidPhone(phone);
                    if (!validPhone)
                        System.out.println("Phone should have the format 69xxxxxxxx");
                    else
                        stud.setPhone(phone);
                }while(!validPhone);
                
                
                do{
                    System.out.print("Give new student email (Press enter to leave the old one): ");
                    email = input.nextLine();
                    if (email.length()==0)
                        break;
                    validEmail = isValidEmail(email);
                    if (!validEmail)
                        System.out.println("Email should have the domain @uniwa.gr");
                    else
                        stud.setEmail(email);
                }while(!validEmail);
                
                do{
                    System.out.print("Give new student am (Press enter to leave the old one): ");
                    am = input.nextLine();
                    if (am.length()==0)
                        break;
                    validAM = isValidAM(am);
                    if (!validAM)
                        System.out.println("AM should have the format MSCICTxxxxx");
                    else
                        stud.setAM(am);
                }while(!validAM);

 
                // Λαμβάνει από τπν χρήστη τιμή για το semesterInput. Δίνουμε το input αρχικά σε String καθώς θέλουμε αν έχει μηδενικό μήκος να προχωράμε παρακάτω και να κρατάμε την παλιά τιμή.
                // Στην συνέχεια το μετατρέπουμε σε int με την στατική μέθοδο parseInt() του Integer, για αυτό το λόγο έχουμε βάλει όλο το μπλοκ σε ένα try-catch που διαχειρίζεται NumberFormatException για την περίπτωση που ο χρήστης θα δώσει κείμενο ως input.
                do{
                    try{
                        System.out.print("Give new student's semester (Press enter to leave the old one): ");
                        semesterInput = input.nextLine();
                        if (semesterInput.length()==0)
                            break;
                        semester = Integer.parseInt(semesterInput);
                        try{
                            stud.setSemester(semester); // Είναι μέσα σε try-catch καθώς ο setter setSemester πετάει MyExc
                        }
                        catch(MyExc e){
                            System.out.println(e.getMessage());
                        }                       
                    }
                    catch(NumberFormatException  e){
                        System.err.println("NumberFormatException: Semester must be an integer. Please try again.");                    
                    }
                }while(semester<1);                
   
            }
            else{
                System.out.printf("Student with AM %s doesn't exist",am);
            }
        }
        else if (choice==2){
            System.out.println("\nPROFESSORS\n");
            ShowAllProfessors();
            String discipline;
            System.out.print("Give the id of the professor you want to edit: ");
            String id = input.nextLine();
            Professor prof = SearchProfessorByID(id);
            if (prof != null){
                System.out.println(prof.toString());
                
                System.out.print("Give new professor name (Press enter to leave the old one): ");
                name = input.nextLine();
                if (name.length()>0)
                    prof.setName(name);
                
                do{
                    System.out.print("Give new professor phone (Press enter to leave the old one): ");
                    phone = input.nextLine();
                    if (phone.length()==0)
                        break;
                    validPhone = isValidPhone(phone);
                    if (!validPhone)
                        System.out.println("Phone should have the format 69xxxxxxxx");
                    else
                        prof.setPhone(phone);
                }while(!validPhone);
                
                do{
                    System.out.print("Give new professor email (Press enter to leave the old one): ");
                    email = input.nextLine();
                    if (email.length()==0)
                        break;
                    validEmail = isValidEmail(email);
                    if (!validEmail)
                        System.out.println("Email should have the domain @uniwa.gr");
                    else
                        prof.setEmail(email);
                }while(!validEmail);
                
                do{
                    System.out.print("Give new student id (Press enter to leave the old one): ");
                    id = input.nextLine();
                    if (id.length()==3)
                        prof.setPID(id);
                    else
                        System.out.println("Professor ID number should have 3 digits");
                }while(id.length()!=3);
                
                System.out.print("Give new professor discipline: ");
                discipline = input.nextLine();
                if (discipline.length()>0)
                    prof.setDiscipline(discipline);
            }
            else
                System.out.printf("Professor with ID %s doesn't exist",id);      
        }
        else
            System.out.println("Invalid choice");
    }
    
    /**
     * Μέθοδος επεξεργασίας στοιχείων μαθήματος. Χρησιμοποιεί την κλάση regex για data validation.
     */
    public void EditCourse(){
        
        String name;
        String semesterInput;
        int semester = 0;
        boolean validID;
        boolean exists;
        ShowAllCourses();
        System.out.print("Give the id of the course you want to edit: ");
        String id = input.nextLine();
        Course crs = SearchCourseByID(id);
        
        if (crs != null){
            do{
                exists=false;
                System.out.print("Give new course's id (Press enter to leave the old one): ");
                id = input.nextLine();
                if (id.length()==0)
                    break;
                validID = isValidCourseID(id);
                if (!validID){
                    System.out.println("ID should have the format ICTxxx");
                    continue;
                }
                  
                ArrayList<String> idList = new ArrayList<>();

                AllCourses.forEach(c -> idList.add(c.getCourseID()));

                for (String chkID : idList){
                    if (chkID.equals(id)){
                        exists=true;
                        System.out.println("ID already exists");
                    }
                }
            }while(exists || !validID);

            System.out.print("Give new course name (Press enter to leave the old one): ");
            name = input.nextLine();
            if (name.length()>0)
                crs.setCName(name);

            do{
                try{
                    System.out.print("Give new course's semester (Press enter to leave the old one): ");
                    semesterInput = input.nextLine();
                    if (semesterInput.length()==0)
                        break;
                    semester = Integer.parseInt(semesterInput);
                    crs.setCsemester(semester);
                   
                }
                catch(NumberFormatException  e){
                    System.err.println("NumberFormatException: Semester must be an integer number. Please try again.");                    
                }
            }while(semester<1); 
        }
    }
    
    /**
     * Μέθοδος ανάθεσης μαθήματος σε μέλος του τμήματος.
     */
    public void AssignCourseToMember(){
        
        int choice = 0;
        String id;
        String CID;
        boolean exists = false;
        boolean alreadyRegistered = false;
        
        System.out.print("Type '1' for student or '2' for professor: ");
        choice = input.nextInt(); input.nextLine();
        
        if (choice==1){        
            ShowAllStudents();
            System.out.print("Give the am of the student: ");
            id = input.nextLine();
            Student stud = SearchStudentByAM(id);
            if (stud==null){
                System.out.println("Student AM doesn't exist");
                return;
            }
            System.out.println("\nALL COURSES LIST\n");
            ShowAllCourses();
            System.out.printf("\nGive the id of the course you want to register for student %s: ", stud.getName());
            CID = input.nextLine();
                       
            for (Course crs : AllCourses){
                if (crs.getCourseID().equals(CID)){
                    exists=true;
                }
            }
            if (!exists){
                System.out.printf("Course ID %s doesn't exist\n",CID);
                return;
            }
                
            
            for (Course crs : stud.GetCourses()){
                if (crs.getCourseID().equals(CID)){
                    alreadyRegistered=true;
                }   
            }
            if (alreadyRegistered){
                System.out.println("Already registered in this course");
                return;
            }
                
            
            Course crs = SearchCourseByID(CID);
            stud.AddCourse(crs);
        }
        else if (choice==2){
            ShowAllProfessors();
            System.out.print("Give the id of the professor: ");
            id = input.nextLine();
            Professor prof = SearchProfessorByID(id);
            if (prof==null){
                System.out.println("Professor ID doesn't exist");
                return;
            }
            ShowAllCourses();
            System.out.printf("\nGive the id of the course you want to register for professor %s: ", prof.getName());
            CID = input.nextLine();
                       
            for (Course crs : AllCourses){
                if (crs.getCourseID().equals(CID)){
                    exists=true;
                }
            }
            
            if (!exists){
                System.out.printf("Course ID %s doesn't exist\n",CID);
                return;
            }
            for (Course crs : prof.GetCourses()){
                if (crs.getCourseID().equals(CID)){
                    System.out.println("Already registered in this course");
                    return;
                }   
            }
            Course crs = SearchCourseByID(CID);
            prof.AddCourse(crs);            
        } 
    }
    
    /**
     * Μέθοδος η οποία εκτυπώνει στην κονσόλα όλα τα μαθήματα ενός μέλους. 
     */
    public void ShowMemberCourses(){
        
        int choice;
        String id;
        
        System.out.print("Type '1' for student or '2' for professor: ");
        choice = input.nextInt(); input.nextLine();

        if (choice==1){
            ShowAllStudents();
            System.out.print("Give the am of the student: ");
            id = input.nextLine();
            Student stud = SearchStudentByAM(id);
            if (stud != null)
                stud.ShowCourses();
            else
                System.out.println("Student AM doesn't exist");
        }
        else if (choice==2){
            ShowAllProfessors();
            System.out.print("Give the id of the professor: ");
            id = input.nextLine();
            Professor prof = SearchProfessorByID(id);
            if (prof != null)
                prof.ShowCourses();
            else
                System.out.println("Professor ID doesn't exist");
        }
        else
            System.out.println("Invalid choice"); 
    }
    
    /**
     * Μέθοδος η οποία εκτυπώνει στην κονσόλα όλες τις βαθμολογίες ενός φοιτητή.
     */
    public void ShowStudentGrades(){
        
        String am;
        
        System.out.print("Give student's am: ");
        am = input.nextLine();
        
        Student stud = SearchStudentByAM(am);
        
        if (stud != null){
            System.out.println("\nGrades of " + stud.getName());
            System.out.println("-----------------------------------");
            getGrades().forEach(g -> {
                if (stud.getAM().equals(g.getAM())){
                    System.out.println("CourseID: " + g.getCID() + " " + "Grade: " + g.getCourseGrade());
                }
            });
        }
    }
    
    /**
     * Μέθοδος καταχώρησης βαθμολογίας σε φοιτητή.
     */
    public void RegisterGradeToStudent(){
        
        String am;
        String cid;
        float gr;
        
        System.out.print("Give student's am: ");
        am = input.nextLine();
        
        Student stud = SearchStudentByAM(am);
        
        if (stud == null){
            System.out.println("Wrong AM");
            return;
        }
            
        
        System.out.print("Give course's id: ");
        cid = input.nextLine();
        
        Course crs = this.SearchCourseByID(cid);
        
        if (crs == null){
            System.out.println("Wrong ID");
        }
        else{
            
            // Check whether there is already a grade for this student and course
            for (Grade grd : this.getGrades()){
                if (grd.getAM().equals(am) && grd.getCID().equals(cid)){
                    System.out.println("The student already has a grade for this course");
                    return;
                }
            }
            
            
            // Check if registered in this course
            if (!stud.GetCourses().contains(crs)){
                System.out.println("The student is not registered in this course");
                return;                
            }
            
            System.out.printf("\nGive grade for course %s and student %s: ", crs.getCName(), stud.getName());
            gr = input.nextFloat(); input.nextLine();
            
            Grade grd = new Grade(stud.getAM(),crs.getCourseID(),gr);
            this.getGrades().add(grd);
        }
    }
    
    /** 
     * Μέθοδος υπολογισμού μέσου όρου βαθμολογιών ανα φοιτητή.
     */
    public void GradeAverageByStudent(){
        
        for (Person p : AllDepMembers){
            
            float sum = 0;
            int count = 0;              
            
            if (p instanceof Student){
                
                Student stud = (Student) p;
                for (Grade g : this.getGrades()){
                    
                    

                    if (stud.getAM().equals(g.getAM())){
                        sum += g.getCourseGrade();
                        count++;
                    }
                }
                if (count>0)
                    System.out.printf("The average grade score for %s is %.2f\n", stud.getName(),sum/count);
            }
        }
    }
            
    /** 
     * Μέθοδος υπολογισμού μέσου όρου βαθμολογιών ανα μάθημα.
     */ 
    public void GradeAverageByCourse(){
                
        for (Course crs : this.getAllCourses()){
            
            float sum = 0;
            int count = 0;
            
            for (Grade g : this.getGrades()){

                if (crs.getCourseID().equals(g.getCID())){
                    sum += g.getCourseGrade();
                    count++;
                }
            }
            if (count>0)
                System.out.printf("The average grade score for %s is %.2f\n", crs.getCName(),sum/count);
        }
    }
    
    /**
     * Μέθοδος η οποία κατα την έξοδο από το πρόγραμμα εκχωρεί τις τροποποιήσεις που έγιναν στα αντίστοιχα csv.
     */     
    public void EndProgramWriteToCSV(){
        
        String csvStudent = ".\\data\\students.csv";
        String csvProfessor = ".\\data\\professors.csv";
        String csvCourses = ".\\data\\courses.csv";
        String csvStudReg = ".\\data\\studentsReg.csv";
        String csvProfReg = ".\\data\\professorsReg.csv";
        String csvGrades = ".\\data\\grades.csv";
        
        try(FileOutputStream fos = new FileOutputStream(csvStudent);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw)){
            
            bw.write("\ufeff"); // Κατά το σώσιμο του αρχείου εισάγεται χειροκίνητα ένα BOM (Byte Order Mark) διότι διαφορετικά κατά το διάβασμα του αρχείου
                                // η μέθοδος skip() του BufferedReader θα προσπεράσει τον πρώτο χαρακτήρα των δεδομένων το οποίο είναι ανεπιθύμητο.
            for (Person p : AllDepMembers) {
                if (p instanceof Student) {
                    Student stud = (Student) p;
                    String name = stud.getName();
                    String email = stud.getEmail();
                    String phone = stud.getPhone();
                    String am = stud.getAM();
                    int semester = stud.getSemester();
                    String semesterStr = Integer.toString(semester);
                    
                    String[] data = {name,email,phone,am,semesterStr};
                    bw.write(String.join(";",data));
                    bw.newLine();
                }
            }

        }
        catch(IOException e){
            e.getMessage();
        }

        try(FileOutputStream fos = new FileOutputStream(csvProfessor);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw)){
            
            bw.write("\ufeff");
            for (Person p : AllDepMembers) {
                if (p instanceof Professor) {
                    Professor prof = (Professor) p;
                    String name = prof.getName();
                    String email = prof.getEmail();
                    String phone = prof.getPhone();
                    String id = prof.getPID();
                    String discipline = prof.getDiscipline();
                    
                    String[] data = {name,email,phone,id,discipline};
                    bw.write(String.join(";",data));
                    bw.newLine(); 
                }
            }

        }
        catch(IOException e){
            e.getMessage();
        }
        
        try(FileOutputStream fos = new FileOutputStream(csvCourses);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw)){
            
            bw.write("\ufeff");
            for (Course c : AllCourses) {
                String id = c.getCourseID();
                String name = c.getCName();
                int semester = c.getCsemester();
                String semesterStr = Integer.toString(semester);
                    
                String[] data = {id,name,semesterStr};
                bw.write(String.join(";",data));
                bw.newLine();
            }
        }
        catch(IOException e){
            e.getMessage();
        }
        
        try(FileOutputStream fos = new FileOutputStream(csvStudReg);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw)){
            
            bw.write("\ufeff");
            for (Person p : AllDepMembers){
                if (p instanceof Student && !p.GetCourses().isEmpty()){
                    Student stud = (Student) p;
                    String am=stud.getAM();
                    String cid;
                    for (Course crs : p.GetCourses()){
                        cid = crs.getCourseID();
                        String[] data = {am,cid};
                        bw.write(String.join(";",data));
                        bw.newLine();
                    }
                }
            }
        }
        catch(IOException e){
            e.getMessage();
        }
        
        try(FileOutputStream fos = new FileOutputStream(csvProfReg);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw)){
            
            bw.write("\ufeff");
            for (Person p : AllDepMembers){
                if (p instanceof Professor && !p.GetCourses().isEmpty()){
                    Professor prof = (Professor) p;
                    String id=prof.getPID();
                    String cid;
                    for (Course crs : p.GetCourses()){
                        cid = crs.getCourseID();
                        String[] data = {id,cid};
                        bw.write(String.join(";",data));
                        bw.newLine();
                    }
                }
            }
        }
        catch(IOException e){
            e.getMessage();
        }
        
        try(FileOutputStream fos = new FileOutputStream(csvGrades);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw)){
            
            bw.write("\ufeff");
            for (Grade grd : this.getGrades()){
                
                String am = grd.getAM();
                String cid = grd.getCID();
                float grad = grd.getCourseGrade();
                String grade = Float.toString(grad);

                String[] data = {am,cid,grade};
                bw.write(String.join(";",data));
                bw.newLine();
            }
        }
        catch(IOException e){
            e.getMessage();
        }  
        
    }
}

