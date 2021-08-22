package OBS;

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
            System.out.println("connection failed...");
        }
    }

    public ArrayList<Student> getStudentFromDb() {
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            statement = con.createStatement();
            String query = "Select*From student";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String studentNo = rs.getString("studentNo");
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
            String query = "Select*From teacher";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String teacherNo = rs.getString("teacherNo");
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
            String query = "Select*From ders";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("lectureName");
                String lectureNo = rs.getString("lectureNo");
                lectureList.add(new Lecture(name, lectureNo));
            }
            return lectureList;
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void addStudentToDb(String name, String surname, String studentNo) {
        String query = "Insert into student (name, surname, studentNo) VALUES (?,?,?)";
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

    public void addTeacherToDb(String name, String surname, String tacherNo) {
        String query = "Insert into teacher (name, surname, studentNo) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, tacherNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLectureToDb(String dersAdi, String dersKodu) {
        String sorgu = "Insert into ders (dersAdi,dersKodu)VALUES(?,?)";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, dersAdi);
            preparedStatement.setString(2, dersKodu);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudent(String name, String surname, String studentNo) {
        String query = "Update student set name = ?, surname = ? where studentNo = ?";
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
        String query = "Update teacher set name = ?, surname = ? where teacherNo = ?";
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

    public void updateLecture(String lectureName, String lectureNo) {
        String query = "Update lecture set lectureName = ? where lectureNo=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, lectureName);
            preparedStatement.setString(2, lectureNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStudent(String studentNo) {
        String query = "Delete from student where studentNo=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, studentNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTeacher(String teacherNo) {
        String query = "Delete from teacher where teacherNo = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, teacherNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteLecture(String lectureNo) {
        String query = "Delete from lecture where lectureNo = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, lectureNo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DbProcess dbProcess = new DbProcess();
    }

}
