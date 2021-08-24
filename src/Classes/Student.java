package Classes;

import java.util.ArrayList;

public class Student {

    public String studentNo, grade, address, city, country, lectureCode, name, surname, email, branch, lecture;

    public Student(String name, String surname, String studentNo) {
        this.name = name;
        this.surname = surname;
        this.studentNo = studentNo;
    }

    public Student(String studentNo, String lectureCode) {
        this.studentNo = studentNo;
        this.lectureCode = lectureCode;
    }

    @Override
    public String toString() {
        if (this.getName() == null && this.getSurname() == null) {
            return this.getStudentNo() + "," + this.getLectureCode();
        } else {
            return this.getStudentNo() + "," + this.getName() + "," + this.getSurname();
        }
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getGrade() {
        return grade;
    }

    public String getLectureCode() {
        return lectureCode;
    }

    //checking student no for 7-digit number
    public static boolean checkStudentNo(String studentNo) {
        if (studentNo.length() == 7 && studentNo.charAt(0) != '0') {
            for (int i = 0; i < studentNo.length(); i++) {
                if (Character.isDigit(studentNo.charAt(i)) == false) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    //Input validation for saving a new student
    public static String inputControl(String name, String surname, String no, ArrayList<Student> studentList) {
        boolean nameControl = false, surnameControl = false;
        String message = null;
        for (int k = 0; k < name.length(); k++) {
            if (Character.isLetter(name.charAt(k)) == false) {
                nameControl = true;
            }
        }
        for (int k = 0; k < surname.length(); k++) {
            if (Character.isLetter(surname.charAt(k)) == false) {
                surnameControl = true;
            }
        }
        if (name.length() == 0) {
            message = "Name can not be empty!";
        } else if (name.length() > 20) {
            message = "Name can not contain more than 20 letters!";
        } else if (nameControl) {
            message = "Name can not contain numbers or other characters!";
        } else if (surname.length() == 0) {
            message = "Surname can not be empty!";
        } else if (surname.length() > 20) {
            message = "Surname can not contain more than 20 letters!";
        } else if (surnameControl) {
            message = "Surname can not contain numbers or other characters!";
        } else {
            if (!no.equals("updateEvent")) {
                if (no.length() == 0) {
                    message = "Student number can not be empty.";
                } else if (checkStudentNo(no) == false) {
                    message = "Please enter a valid student number! (7 digit number)";
                } else {
                    for (int i = 0; i < studentList.size(); i++) {
                        if (studentList.get(i).getStudentNo().equals(no)) {
                            message = "There is a record of this student. You do not need to re-register.";
                        }
                    }
                }
            }
        }
        return message;
    }

    //controlling existing students
    public static boolean controlExistingStudent(ArrayList<Student> studentList, String no) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentNo().equals(no)) {
                return true;
            }
        }
        return false;
    }
}
