package OBS;
// Muhammet ERTAŞ / 190503054

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class University {

    private String NAME;
    private String ADDRESS;
    private int PHONE;

    public University(String name, String address, int phone) {
        this.NAME = name;
        this.ADDRESS = address;
        this.PHONE = phone;
    }

    public String getisim() {
        return NAME;
    }

    public String getAdres() {
        return ADDRESS;
    }

    public int getTelNo() {
        return PHONE;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public void setPHONE(int PHONE) {
        this.PHONE = PHONE;
    }

    //changing the first letter of the name to uppercase and the others to lowercase 
    public static String setNameConvention(String name) {
        char ch[] = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (i == 0 && ch[i] != ' '
                    || ch[i] != ' ' && ch[i - 1] == ' ') {
                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    ch[i] = (char) (ch[i] - 'a' + 'A');
                }
            } else if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        name = new String(ch);
        return name;
    }

    public static String enrollStudentInLectureInputControl(ArrayList<Student> studentList, ArrayList<Student> lectureAndStudentList,
            ArrayList<Lecture> lectureList, String no, String code) {
        boolean studentIsExisting = Student.controlExistingStudent(studentList, no);
        boolean lectureIsExisting = Lecture.controlExistingLecture(lectureList, code);
        String message = null;
        if (no.length() == 0) {
            message = "Student no can not be empty!";
        } else if (Student.checkStudentNo(no) == false) {
            message = "Please enter a valid student number! (7 digit number)";
        } else if (code.length() == 0) {
            message = "Lecture code can not be empty!";
        } else if (Lecture.checkLectureCode(code) == false) {
            message = "Please enter a valid lecture code (Example: INF202)\nFirst 3 letters must be uppercase!";
        } else if (!studentIsExisting) {
            message = "You did not enter an existing student number!";
        } else if (!lectureIsExisting) {
            message = "You did not enter an existing lecture code!";
        } else {
            for (int i = 0; i < lectureAndStudentList.size(); i++) {
                if (lectureAndStudentList.get(i).getStudentNo().equals(no) && lectureAndStudentList.get(i).getLectureCode().equals(code)) {
                    message = "The student is registered for this course. You do not need to re-register.";
                }
            }
        }
        return message;
    }
    
    public static String assingTeacherToLectureInputControl(ArrayList<Teacher> teacherList, ArrayList<Teacher> lectureAndTeacherList,
            ArrayList<Lecture> lectureList, String no, String code) {
        boolean teacherIsExisting = Teacher.controlExistingTeacher(teacherList, no);
        boolean lectureIsExisting = Lecture.controlExistingLecture(lectureList, code);
        String message = null;
        if (no.length() == 0) {
            message = "Teacher no can not be empty!";
        } else if (Teacher.checkTeacherNo(no) == false) {
            message = "Please enter a valid teacher number! (11 digit number)";
        } else if (code.length() == 0) {
            message = "Lecture code can not be empty!";
        } else if (Lecture.checkLectureCode(code) == false) {
            message = "Please enter a valid lecture code (Example: INF202)\nFirst 3 letters must be uppercase!";
        } else if (!teacherIsExisting) {
            message = "You did not enter an existing teacher number!";
        } else if (!lectureIsExisting) {
            message = "You did not enter an existing lecture code!";
        } else {
            for (int i = 0; i < lectureAndTeacherList.size(); i++) {
                if (lectureAndTeacherList.get(i).getTeacherNo().equals(no) && lectureAndTeacherList.get(i).getLectureNo().equals(code)) {
                    message = "The teacher is already assigned to this course. You do not need to re-assign.";
                }
            }
        }
        return message;
    }
}
