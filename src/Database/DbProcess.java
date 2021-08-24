package Database;

import Classes.Lecture;
import Classes.Student;
import Classes.Teacher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbProcess {

    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public DbProcess() {
        String url = "jdbc:mysql://" + Database.HOST + ":" + Database.PORT + "/" + Database.DB_NAME + "?useUnicode=true&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found...");
        }
        try {
            con = DriverManager.getConnection(url, Database.USER_NAME, Database.PASSWORD);
            System.out.println("Connection successful...");
        } catch (SQLException ex) {
            System.out.println("Connection failed...");
        }
    }

    public ArrayList<Student> getStudentFromDb() {
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            statement = con.createStatement();
            String query = "Select * From student";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("student_name");
                String surname = rs.getString("student_surname");
                String studentNo = rs.getString("student_no");
                studentList.add(new Student(name, surname, studentNo));
            }
            return studentList;
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<Teacher> getTeacherFromDb() {
        ArrayList<Teacher> teacherList = new ArrayList<>();
        try {
            statement = con.createStatement();
            String query = "Select * From teacher";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("teacher_name");
                String surname = rs.getString("teacher_surname");
                String teacherNo = rs.getString("teacher_no");
                teacherList.add(new Teacher(name, surname, teacherNo));
            }
            return teacherList;
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<Lecture> getLectureFromDb() {
        ArrayList<Lecture> lectureList = new ArrayList<>();
        try {
            statement = con.createStatement();
            String query = "Select * From lecture";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("lecture_name");
                String lectureCode = rs.getString("lecture_code");
                lectureList.add(new Lecture(name, lectureCode));
            }
            return lectureList;
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<Student> getLectureAndStudentFromDb() {
        ArrayList<Student> lectureAndStudentList = new ArrayList<>();
        try {
            statement = con.createStatement();
            String query = "Select * From lecture_and_student";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("student_name");
                String surname = rs.getString("student_surname");
                String studentNo = rs.getString("student_no");
                String lectureCode = rs.getString("lecture_code");
                lectureAndStudentList.add(new Student(studentNo, lectureCode));
            }
            return lectureAndStudentList;
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Teacher> getLectureAndTeacherFromDb() {
        ArrayList<Teacher> lectureAndTeacherList = new ArrayList<>();
        try {
            statement = con.createStatement();
            String query = "Select * From lecture_and_teacher";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("teacher_name");
                String surname = rs.getString("teacher_surname");
                String teacherNo = rs.getString("teacher_no");
                String lectureCode = rs.getString("lecture_code");
                lectureAndTeacherList.add(new Teacher(teacherNo, lectureCode));
            }
            return lectureAndTeacherList;
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addStudentToDb(String name, String surname, String studentNo) {
        String query = "Insert into student (student_name, student_surname, student_no) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, studentNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTeacherToDb(String name, String surname, String teacherNo) {
        String query = "Insert into teacher (teacher_name, teacher_surname, teacher_no) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, teacherNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLectureToDb(String lectureName, String lectureCode) {
        String query = "Insert into lecture (lecture_name, lecture_code) VALUES (?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, lectureName);
            preparedStatement.setString(2, lectureCode);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLectureAndStudentToDb(String studentName, String studentSurname, String studentNo, String lectureCode) {
        String query = "Insert into lecture_and_student (student_name, student_surname, student_no, lecture_code) VALUES (?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, studentSurname);
            preparedStatement.setString(3, studentNo);
            preparedStatement.setString(4, lectureCode);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLectureAndTeacherToDb(String teacherName, String teacherSurname, String teacherNo, String lectureCode) {
        String query = "Insert into lecture_and_teacher (teacher_name, teacher_surname, teacher_no, lecture_code) VALUES (?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, teacherName);
            preparedStatement.setString(2, teacherSurname);
            preparedStatement.setString(3, teacherNo);
            preparedStatement.setString(4, lectureCode);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudent(String name, String surname, String studentNo) {
        String query = "Update student set student_name = ?, student_surname = ? where student_no = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, studentNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTeacher(String name, String surname, String teacherNo) {
        String query = "Update teacher set teacher_name = ?, teacher_surname = ? where teacher_no = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, teacherNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLecture(String lectureName, String lectureCode) {
        String query = "Update lecture set lecture_name = ? where lecture_code=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, lectureName);
            preparedStatement.setString(2, lectureCode);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStudent(String studentNo) {
        String query = "Delete from student where student_no=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, studentNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTeacher(String teacherNo) {
        String query = "Delete from teacher where teacher_no = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, teacherNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteLecture(String lectureCode) {
        String query = "Delete from lecture where lecture_code = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, lectureCode);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteLectureAndStudent(String studentNo, String lectureCode) {
        String query = "Delete from lecture_and_student where student_no = ? and lecture_code = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, studentNo);
            preparedStatement.setString(2, lectureCode);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteLectureAndTeacher(String teacherNo, String lectureCode) {
        String query = "Delete from lecture_and_teacher where teacher_no = ? and lecture_code = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, teacherNo);
            preparedStatement.setString(2, lectureCode);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
