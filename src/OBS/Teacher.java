package OBS;

import java.util.ArrayList;

public class Teacher {

    public String name, surname, email, branch, lectureNo, teacherNo;

    public Teacher(String name, String surname, String teacherNo) {
        this.name = name;
        this.surname = surname;
        this.teacherNo = teacherNo;
    }

    public Teacher(String teacherNo, String lectureNo) {
        this.teacherNo = teacherNo;
        this.lectureNo = lectureNo;
    }

    @Override
    public String toString() {
        if (this.getName() == null && this.getSurname() == null) {
            return this.getTeacherNo() + "," + this.getLectureNo();
        } else {
            return this.getTeacherNo() + "," + this.getName() + "," + this.getSurname().toUpperCase();
        }
    }

    public String getLectureNo() {
        return lectureNo;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    //checking teacher no fr 11-digit number
    public static boolean checkTeacherNo(String s) {
        if (s.length() == 11 && s.charAt(0) != '0') {
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i)) == false) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    //Input validation for saving a new teacher
    public static String inputControl(String name, String surname, String no, ArrayList<Teacher> teacherList) {
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
                    message = "Teacher number can not be empty!";
                } else if (checkTeacherNo(no) == false) {
                    message = "Please enter a valid teacher number! (11 digit number)";
                } else {
                    for (int i = 0; i < teacherList.size(); i++) {
                        if (teacherList.get(i).getTeacherNo().equals(no)) {
                            message = "There is a record of this teacher. You do not need to re-register.";
                        }
                    }
                }
            }
        }
        return message;
    }

    //controlling existing teachers
    public static boolean controlExistingTeacher(ArrayList<Teacher> teacherList, String no) {
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getTeacherNo().equals(no)) {
                return true;
            }
        }
        return false;
    }
}
