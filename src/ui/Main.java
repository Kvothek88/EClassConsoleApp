
package ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Course;
import model.Department;
import model.MyExc;
import model.Professor;
import model.Student;


/**
 * Η κύρια κλάση του προγράμματος η οποία διαχειρίζεται την διεπαφή με τον χρήστη.
 * @author ΙΩΑΝΝΗΣ ΚΛΑΔΗΣ MSCICT23028
 */
public class Main {
    
    
    private Department dept;
    private Scanner input;
    
    public Main(){
        dept = new Department();
        input = new Scanner(System.in);
    }
    
    /**
     * Η κύρια διεπαφή με τον χρήστη όπου τρέχει αέναα το μενού μέχρι να γίνει χειροκίνητη έξοδος από αυτό και το οποίο μέσω μιας δομής ελέγχου και το input του χρήστη τρέχει την αντίστοιχη μέθοδο της κλάσης Department . 
     */
    public void Menu(){
        
        int choice = 0;
        do
        {
            System.out.println ("\n     M E N U ");
            System.out.println ("-------------------");
            System.out.println ("[1]......Show Department Members");
            System.out.println ("[2]......Show Courses");
            System.out.println ("[3]......Search Department Member by ID");
            System.out.println ("[4]......Search Course by ID");
            System.out.println ("[5]......Add Department Member");
            System.out.println ("[6]......Edit Department Member");
            System.out.println ("[7]......Delete Department Member");
            System.out.println ("[8]......Add Course");
            System.out.println ("[9]......Edit Course");
            System.out.println ("[10].....Delete Course");           
            System.out.println ("[11].....Assign Course to Department Member");
            System.out.println ("[12].....Show Department Member Courses");
            System.out.println ("[13].....Show Student's Grades");
            System.out.println ("[14].....Register Grade to Student");
            System.out.println ("[15].....Display Average Grade by Student");
            System.out.println ("[16].....Display Average Grade by Course");
            System.out.println ("[17].....E x i t");
            System.out.print ("\nChoose : ");
            try{
                choice = input.nextInt();
                input.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Choice must be an integer number");
                input.nextLine();
                continue;
            }

            switch (choice)
            {
                case 1 : dept.ShowAllDeptMembers(); break;
                case 2 : dept.ShowAllCourses(); break;
                case 3 : dept.FindMemberByID(); break;
                case 4 : dept.FindCourseByID(); break;
                case 5 : dept.AddDepartmentMember(); break;
                case 6 : dept.EditDepartmentMember(); break;
                case 7 : dept.DeleteDepartmentMember() ; break;
                case 8 : dept.AddCourse(); break;
                case 9 : dept.EditCourse(); break;
                case 10 : dept.DeleteCourse() ; break;
                case 11 : dept.AssignCourseToMember(); break;
                case 12 : dept.ShowMemberCourses() ; break;
                case 13 : dept.ShowStudentGrades() ; break;
                case 14 : dept.RegisterGradeToStudent(); break;
                case 15 : dept.GradeAverageByStudent(); break;
                case 16 : dept.GradeAverageByCourse(); break;
            }
            if (choice <= 0 || choice > 17){
                System.out.println("\nGive a number from the ones above");
            }
        }
        while (choice != 17);
        
        input.close();
        
    }
    
    /**
     * Αυτή η μέθοδος τρέχει όλες τις επιμέρους μεθόδους ανάγνωσης csv.
     */
    public void ReadAllFromCSV(){
        try{
            dept.StudentsReadFromCSV(dept.getAllDepMembers());
            dept.ProfessorsReadFromCSV(dept.getAllDepMembers());
            dept.CoursesReadFromCSV(dept.getAllCourses());
            dept.ReadCourseRegFromCSV();
            dept.ReadGradesFromCSV(dept.getGrades());
        }
        catch(MyExc e){
            System.out.println(e.toString());
        }
    }
    
    /**
     * Μέθοδος η οποία τρέχει την μέθοδο EndProgramWriteToCSV.
     */
    public void WriteAllToCSV(){
        dept.EndProgramWriteToCSV();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Main program = new Main();
        program.ReadAllFromCSV();
        program.Menu();
        program.WriteAllToCSV();
        
    }
    
}