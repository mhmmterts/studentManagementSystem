package OBS;

import java.util.ArrayList;

public class Lecture {

    public String lectureName, lectureCode;

    public Lecture(String lectureName, String lectureCode) {
        this.lectureName = lectureName;
        this.lectureCode = lectureCode;
    }

    @Override
    public String toString() {
        return this.getLectureName() + "," + this.getLectureCode();
    }

    public void setLectureCode(String lectureCode) {
        this.lectureCode = lectureCode;
    }

    public String getLectureCode() {
        return lectureCode;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureName() {
        return lectureName;
    }

    //checking lecture code for 3 letters followed by 3 digits
    public static boolean checkLectureCode(String k) {
        if (k.length() == 6) {
            if (Character.isLetter(k.charAt(0)) && Character.isLetter(k.charAt(1)) && Character.isLetter(k.charAt(2))) {
                return Character.isDigit(k.charAt(3)) && Character.isDigit(k.charAt(4)) && Character.isDigit(k.charAt(5));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //Input validation for saving a new lecture
    public static String inputControl(String name, String lectureCode, ArrayList<Lecture> lectureList) {
        boolean nameControl = false;
        String message = null;
        for (int k = 0; k < name.length(); k++) {
            if (Character.isLetter(name.charAt(k)) == false) {
                nameControl = true;
            }
        }
        if (name.length() == 0) {
            message = "Name can not be empty!";
        } else if (name.length() > 20) {
            message = "Name can not contain more than 20 letters!";
        } else if (nameControl) {
            message = "Name can not contain numbers or other characters!";
        } else {
            if (!lectureCode.equals("updateEvent")) {
                if (lectureCode.length() == 0) {
                    message = "Lecture code can not be empty!";
                } else if (checkLectureCode(lectureCode) == false) {
                    message = "Please enter a valid lecture code! (Example: INF202)";
                } else {
                    for (int i = 0; i < lectureList.size(); i++) {
                        if (lectureList.get(i).getLectureCode().equals(lectureCode)) {
                            message = "There is a record of this lecture. You do not need to re-register.";
                        }
                    }
                }
            }
        }

        return message;
    }

    //controlling existing lectures
    public static boolean controlExistingLecture(ArrayList<Lecture> lectureList, String code) {
        for (int i = 0; i < lectureList.size(); i++) {
            if (lectureList.get(i).getLectureCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

}
