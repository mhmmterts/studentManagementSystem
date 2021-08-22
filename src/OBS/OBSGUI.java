package OBS;
// Muhammet ERTAŞ / 190503054 / Projekt für die Finalabgabe

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;

public class OBSGUI extends javax.swing.JFrame {

    ArrayList<Student> studentList = new ArrayList<>();
    ArrayList<Teacher> teacherList = new ArrayList<>();
    ArrayList<Lecture> lectureList = new ArrayList<>();
    ArrayList<Student> lectureAndStudentList = new ArrayList<>();
    ArrayList<Teacher> lectureAndTeacherList = new ArrayList<>();
    DefaultTableModel model1, model2, model3, model4, model5, model6, model7;
    DbProcess queries = new DbProcess();

    public OBSGUI() {
        initComponents();
        //showStudents();
        //showTeachers();
        //showLectures();
        //Selection listeners for the tables
        studentTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int i = studentTable.getSelectedRow();
            if (i >= 0) {
                studentName.setText((String) studentTable.getModel().getValueAt(i, 0));
                studentSurname.setText((String) studentTable.getModel().getValueAt(i, 1));
            }
        });
        teacherTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int i = teacherTable.getSelectedRow();
            if (i >= 0) {
                teacherName.setText((String) teacherTable.getModel().getValueAt(i, 0));
                teacherSurname.setText((String) teacherTable.getModel().getValueAt(i, 1));
            }
        });
        lectureTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int i = lectureTable.getSelectedRow();
            if (i >= 0) {
                lectureName.setText((String) lectureTable.getModel().getValueAt(i, 0));
            }
        });
        
        //changing default java icon
        Image image = new ImageIcon(this.getClass().getResource("/Images/turk-alman-universitesi-logo.png")).getImage();
        this.setIconImage(image);

    }

    public static void main(String args[]) {
        OBSGUI o = new OBSGUI();
        o.setVisible(true);

    }

    //getting student data from DB for the tables
    private void showStudents() {
        model1 = (DefaultTableModel) studentTable.getModel();
        model1.setRowCount(0);
        model2 = (DefaultTableModel) studentTable1.getModel();
        model2.setRowCount(0);
        studentList = queries.getStudentFromDb();
        if (studentList != null) {
            for (Student student : studentList) {
                Object[] studentObj = {student.getName(), student.getSurname(), student.getStudentNo()};
                model1.addRow(studentObj);
                model2.addRow(studentObj);
            }
        }
    }

    //getting teacher data from DB for the tables
    private void showTeachers() {
        model3 = (DefaultTableModel) teacherTable.getModel();
        model3.setRowCount(0);
        model4 = (DefaultTableModel) teacherTable1.getModel();
        model4.setRowCount(0);
        teacherList = queries.getTeacherFromDb();
        if (teacherList != null) {
            for (Teacher teacher : teacherList) {
                Object[] teacherObj = {teacher.getName(), teacher.getSurname(), teacher.getTeacherNo()};
                model3.addRow(teacherObj);
                model4.addRow(teacherObj);
            }
        }
    }

    //getting lecture data from DB for the tables
    private void showLectures() {
        model5 = (DefaultTableModel) lectureTable.getModel();
        model5.setRowCount(0);
        model6 = (DefaultTableModel) lectureTable1.getModel();
        model6.setRowCount(0);
        lectureList = queries.getLectureFromDb();
        if (lectureList != null) {
            for (Lecture lecture : lectureList) {
                Object[] lectureObj = {lecture.getLectureName(), lecture.getLectureCode()};
                model5.addRow(lectureObj);
                model6.addRow(lectureObj);
            }
        }
    }

    //adding registered student to the tables
    private void studentAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) studentTable.getModel();
        dtm.addRow(nameArray);
        DefaultTableModel dtn = (DefaultTableModel) studentTable1.getModel();
        dtn.addRow(nameArray);
    }

    //adding registered teacher to the tables
    private void teacherAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) teacherTable.getModel();
        dtm.addRow(nameArray);
        DefaultTableModel dtn = (DefaultTableModel) teacherTable1.getModel();
        dtn.addRow(nameArray);
    }

    //adding registered lecture to the tables
    private void lectureAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) lectureTable.getModel();
        dtm.addRow(nameArray);
        DefaultTableModel dtn = (DefaultTableModel) lectureTable1.getModel();
        dtn.addRow(nameArray);
    }

    //adding enrolled student to lecture table 
    private void lectureAndStudentAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) lecturesOfStudentsTable.getModel();
        dtm.addRow(nameArray);
        studentNoForLectureRegister.setText("");
        lectureCodeForStudentRegister.setText("");
    }

    //adding assigned teacher to lecture table
    private void lectureAndTeacherAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) lecturesOfTeachersTable.getModel();
        dtm.addRow(nameArray);
        teacherNoForLectureAssign.setText("");
        lectureCodeForTeacherAssign.setText("");
    }

    //removing student from the tables
    private void deleteStudentRowsFromTable(int rowNumber) {
        DefaultTableModel dtm = (DefaultTableModel) studentTable.getModel();
        dtm.removeRow(rowNumber);
        DefaultTableModel dtn = (DefaultTableModel) studentTable1.getModel();
        dtn.removeRow(rowNumber);
        studentName.setText("");
        studentSurname.setText("");
        studentNo.setText("");
    }

    //removing teacher from the tables
    private void deleteTeacherRowsFromTable(int rowNumber) {
        DefaultTableModel dtm = (DefaultTableModel) teacherTable.getModel();
        dtm.removeRow(rowNumber);
        DefaultTableModel dtn = (DefaultTableModel) teacherTable1.getModel();
        dtn.removeRow(rowNumber);
        teacherName.setText("");
        teacherSurname.setText("");
    }

    //removing lecture from the tables
    private void deleteLectureRowsFromTable(int rowNumber) {
        DefaultTableModel dtm = (DefaultTableModel) lectureTable.getModel();
        dtm.removeRow(rowNumber);
        DefaultTableModel dtn = (DefaultTableModel) lectureTable1.getModel();
        dtn.removeRow(rowNumber);
        lectureName.setText("");
        lectureCode.setText("");
    }

    //clearing input fields after adding new student
    private void clearStudentInputFields() {
        studentName.setText("");
        studentSurname.setText("");
        studentNo.setText("");
        studentTable.clearSelection();
    }

    //clearing input fields after adding new teacher
    private void clearTeacherInputFields() {
        teacherName.setText("");
        teacherSurname.setText("");
        teacherNo.setText("");
        teacherTable.clearSelection();
    }

    //clearing input fields after adding new lecture
    private void clearLectureInputFields() {
        lectureName.setText("");
        lectureCode.setText("");
        lectureTable.clearSelection();
    }

    //accessing student name and surname 
    private String[] getStudentNameAndSurname() {
        String[] nameAndSurname = new String[2];
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentNo().equals(studentNoForLectureRegister.getText())) {
                nameAndSurname[0] = studentList.get(i).getName();
                nameAndSurname[1] = studentList.get(i).getSurname();
            }
        }
        return nameAndSurname;
    }

    //accessing teacher name and surname
    private String[] getTeacherNameAndSurname() {
        String[] nameAndSurname = new String[2];
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getTeacherNo().equals(teacherNoForLectureAssign.getText())) {
                nameAndSurname[0] = teacherList.get(i).getName();
                nameAndSurname[1] = teacherList.get(i).getSurname();
            }
        }
        return nameAndSurname;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        studentName = new javax.swing.JTextField();
        studentSurname = new javax.swing.JTextField();
        studentNo = new javax.swing.JTextField();
        addNewStudent = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        deleteStudent = new javax.swing.JButton();
        updateStudent = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        teacherName = new javax.swing.JTextField();
        teacherSurname = new javax.swing.JTextField();
        teacherNo = new javax.swing.JTextField();
        addNewTeacher = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        teacherTable = new javax.swing.JTable();
        deleteTeacher = new javax.swing.JButton();
        updateTeacher = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lectureName = new javax.swing.JTextField();
        lectureCode = new javax.swing.JTextField();
        addNewLecture = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        lectureTable = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        deleteLecture = new javax.swing.JButton();
        updateLecture = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        studentTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        studentSearch = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        teacherTable1 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        teacherSearch = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        lectureTable1 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        lectureSearch = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        studentNoForLectureRegister = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        lectureCodeForStudentRegister = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        enrollStudentInLecture = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        lecturesOfStudentsTable = new javax.swing.JTable();
        unenrollStudentFromLecture = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        studentAndLectureSearch = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        teacherNoForLectureAssign = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        lectureCodeForTeacherAssign = new javax.swing.JTextField();
        assignTeacherToLection = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        lecturesOfTeachersTable = new javax.swing.JTable();
        removeTeacherFromLecture = new javax.swing.JButton();
        teacherAndLectureSearch = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Management System");
        setLocation(new java.awt.Point(425, 150));
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(115, 126, 171));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(209, 236, 241));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Name:");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Surname:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Student No:");

        studentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        studentSurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        studentNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        addNewStudent.setBackground(new java.awt.Color(255, 0, 0));
        addNewStudent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addNewStudent.setText("Add Student");
        addNewStudent.setFocusable(false);
        addNewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewStudentActionPerformed(evt);
            }
        });

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b>Student Name<b>", "<html><b>Surname<b>", "<html><b>Student No<b>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.setToolTipText("");
        studentTable.setFocusable(false);
        jScrollPane4.setViewportView(studentTable);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Registered Students");

        deleteStudent.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        deleteStudent.setText("Delete Student");
        deleteStudent.setFocusable(false);
        deleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStudentActionPerformed(evt);
            }
        });

        updateStudent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateStudent.setText("Update");
        updateStudent.setFocusable(false);
        updateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStudentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(deleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addNewStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(studentName, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(studentSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(studentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(deleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
        );

        jTabbedPane1.addTab("Add/Delete Student", jPanel1);

        jPanel2.setBackground(new java.awt.Color(209, 236, 241));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Name:");
        jLabel9.setFocusable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Surname:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Teacher No:");

        teacherName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        teacherSurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        teacherNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        addNewTeacher.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addNewTeacher.setText("Add Teacher");
        addNewTeacher.setFocusable(false);
        addNewTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewTeacherActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Registered Teachers");

        teacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html> <b>Teacher Name<b>", "<html><b>Surname<b>", "<html><b>Teacher No<b>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(teacherTable);

        deleteTeacher.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteTeacher.setText("Delete Teacher");
        deleteTeacher.setFocusable(false);
        deleteTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTeacherActionPerformed(evt);
            }
        });

        updateTeacher.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateTeacher.setText("Update");
        updateTeacher.setFocusable(false);
        updateTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTeacherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(teacherName, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherSurname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(87, 87, 87)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNewTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteTeacher)
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(teacherName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(teacherSurname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(addNewTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(teacherNo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jLabel13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))))
        );

        jTabbedPane1.addTab("Add/Delete Teacher", jPanel2);

        jPanel3.setBackground(new java.awt.Color(209, 236, 241));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Name:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Lecture Code:");

        lectureName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lectureCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        addNewLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addNewLecture.setText("Add Lecture");
        addNewLecture.setFocusable(false);
        addNewLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewLectureActionPerformed(evt);
            }
        });

        lectureTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b>Lecture Name<b>", "<html><b>Lecture Code<b>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(lectureTable);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("Registered Lectures");

        deleteLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteLecture.setText("Delete Lecture");
        deleteLecture.setFocusable(false);
        deleteLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteLectureActionPerformed(evt);
            }
        });

        updateLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateLecture.setText("Update Lecture");
        updateLecture.setFocusable(false);
        updateLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLectureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteLecture)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lectureCode, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateLecture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addNewLecture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel19)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addNewLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lectureCode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel56)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(deleteLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))))))
        );

        jTabbedPane1.addTab("Add/Delete Lecture", jPanel3);

        jPanel4.setBackground(new java.awt.Color(209, 236, 241));

        studentTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Nachname", "Matrikelnummer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(studentTable1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Registered Students");

        studentSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentSearchKeyReleased(evt);
            }
        });

        teacherTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Nachname", "TC Kimlik No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(teacherTable1);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Registered Teachers");

        teacherSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teacherSearchKeyReleased(evt);
            }
        });

        jLabel16.setText("Suchen:");

        jLabel17.setText("Suchen:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Existierende LVAs");

        lectureTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name der LVA", "Kuerzel der LVA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(lectureTable1);

        jLabel25.setText("Suchen:");

        lectureSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lectureSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(studentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(teacherSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(133, 133, 133)
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(121, 121, 121)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(studentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17))
                    .addComponent(teacherSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(191, 191, 191))
        );

        jTabbedPane1.addTab("Search", jPanel4);

        jPanel6.setBackground(new java.awt.Color(209, 236, 241));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Matrikelnummer:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Student zur LVA anmelden");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Kuerzel der LVA:");

        jLabel29.setText("Geben Sie die Matrikelnummer des Studenten und den Kuerzel der LVA ein");

        jLabel30.setText("und klicken Sie auf anmelden.");

        enrollStudentInLecture.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        enrollStudentInLecture.setText("Anmelden");
        enrollStudentInLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollStudentInLectureActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Dozent zur LVA zuweisen");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("TC No:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Kuerzel der LVA:");

        jLabel34.setText("Geben Sie die TC No des Dozenten und den Kuerzel der LVA ein und");

        jLabel35.setText("klicken Sie auf anmelden.");

        lecturesOfStudentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Nachname", "Matrikelnummer", "LVA Kuerzel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(lecturesOfStudentsTable);

        unenrollStudentFromLecture.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        unenrollStudentFromLecture.setText("Abmelden");
        unenrollStudentFromLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unenrollStudentFromLectureActionPerformed(evt);
            }
        });

        jLabel36.setText("Suchen:");

        studentAndLectureSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentAndLectureSearchKeyReleased(evt);
            }
        });

        jLabel37.setText("Um Student von der LVA zu abmelden klicken sie auf die Name des");

        jLabel38.setText("Studenten und dann auf abmelden.");

        jLabel39.setText("Um alle LVAs eines Studenten zu zeigen geben Sie die Matrikelnummer");

        jLabel40.setText("des Studenten ein.");

        jLabel41.setText("Suchen:");

        jLabel42.setText("Um Dozent von der LVA zu abmelden klicken sie auf die Name des");

        jLabel43.setText("Dozenten und dann auf abmelden.");

        jLabel46.setText("Um alle angemeldeten Studenten der LVA zu zeigen geben Sie den Kuerzel");

        jLabel48.setText("der LVA ein.");

        jLabel49.setText("Um alle LVAs des Dozenten zu zeigen geben Sie die TC Kimlik No des Dozenten ein.");

        jLabel50.setText("Um alle verantwortlichen Dozenten der LVA zu zeigen geben Sie den Kuerzel ");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Student/LVA Liste");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Dozent/LVA Liste");

        jLabel60.setText("der LVA ein.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lectureCodeForStudentRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(studentNoForLectureRegister)))
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(412, 412, 412))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(352, 352, 352))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(188, 188, 188)
                                                .addComponent(jLabel41))
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING))))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34)
                                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(129, 129, 129)
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(unenrollStudentFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enrollStudentInLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(studentNoForLectureRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(lectureCodeForStudentRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel30)
                    .addComponent(enrollStudentInLecture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unenrollStudentFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Enroll/Unenroll<br> Student in lection", jPanel6);

        jPanel5.setBackground(new java.awt.Color(209, 236, 241));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("Dozent zur LVA zuweisen");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("TC No:");

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Kuerzel der LVA:");

        assignTeacherToLection.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        assignTeacherToLection.setText("Anmelden");
        assignTeacherToLection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignTeacherToLectionActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Dozent/LVA Liste");

        lecturesOfTeachersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Nachname", "TC Kimlik No", "LVA Kuerzel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(lecturesOfTeachersTable);

        removeTeacherFromLecture.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        removeTeacherFromLecture.setText("Abmelden");
        removeTeacherFromLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTeacherFromLectureActionPerformed(evt);
            }
        });

        teacherAndLectureSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teacherAndLectureSearchKeyReleased(evt);
            }
        });

        jLabel58.setText("Suchen:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(199, 199, 199)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lectureCodeForTeacherAssign, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(teacherNoForLectureAssign))
                                .addGap(1, 1, 1))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(removeTeacherFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel58))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(teacherAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(assignTeacherToLection, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(165, 165, 165)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)))
                    .addContainerGap(128, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(97, 97, 97)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(teacherNoForLectureAssign))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel52)
                        .addComponent(lectureCodeForTeacherAssign))
                    .addGap(38, 38, 38)
                    .addComponent(assignTeacherToLection, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(removeTeacherFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(teacherAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(98, 98, 98)))
        );

        jTabbedPane1.addTab("<html>Assign/Unassign <br>Teacher to lection", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void addNewStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewStudentActionPerformed
        String name = studentName.getText();
        String surname = studentSurname.getText();
        String no = studentNo.getText();
        String result = Student.inputControl(name, surname, no, studentList);
        if (result == null) {
            name = University.setNameConvention(name);
            surname = surname.toUpperCase();
            String[] nameArray = new String[3];
            nameArray[0] = name;
            nameArray[1] = surname;
            nameArray[2] = no;
            studentAddRowsToTable(nameArray);
            studentList.add(new Student(name, surname, no));
            //queries.addStudentToDb(name, surname, no);
            clearStudentInputFields();
            JOptionPane.showMessageDialog(this, "Student registration has been completed.");
        } else {
            JOptionPane.showMessageDialog(this, result);
        }
    }//GEN-LAST:event_addNewStudentActionPerformed

    private void deleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStudentActionPerformed
        int rowNumber = studentTable.getSelectedRow();
        if (rowNumber >= 0) {
            String a = (String) studentTable.getModel().getValueAt(rowNumber, 2);
            //queries.deleteStudent(a);
            studentList.remove(rowNumber);
            deleteStudentRowsFromTable(rowNumber);
            //showStudents();
        }
    }//GEN-LAST:event_deleteStudentActionPerformed

    private void addNewTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewTeacherActionPerformed
        String name = teacherName.getText();
        String surname = teacherSurname.getText();
        String no = teacherNo.getText();
        String result = Teacher.inputControl(name, surname, no, teacherList);
        if (result == null) {
            name = University.setNameConvention(name);
            surname = surname.toUpperCase();
            String[] nameArray = new String[3];
            nameArray[0] = name;
            nameArray[1] = surname;
            nameArray[2] = no;
            teacherAddRowsToTable(nameArray);
            teacherList.add(new Teacher(name, surname, no));
            //queries.addTeacherToDb(name, surname, no);
            //showTeachers();
            clearTeacherInputFields();
            JOptionPane.showMessageDialog(this, "Teacher registration has been completed.");
        } else {
            JOptionPane.showMessageDialog(this, result);
        }
    }//GEN-LAST:event_addNewTeacherActionPerformed

    private void deleteTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTeacherActionPerformed
        int rowNumber = teacherTable.getSelectedRow();
        if (rowNumber >= 0) {
            String a = (String) teacherTable.getModel().getValueAt(rowNumber, 2);
            //queries.deleteStudent(a);
            teacherList.remove(rowNumber);
            deleteTeacherRowsFromTable(rowNumber);
            //showTeachers();
        }
    }//GEN-LAST:event_deleteTeacherActionPerformed

    private void addNewLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewLectureActionPerformed
        String name = lectureName.getText();
        String code = lectureCode.getText();
        String result = Lecture.inputControl(name, code, lectureList);
        if (result == null) {
            name = University.setNameConvention(name);
            code = code.toUpperCase();
            String[] nameArray = new String[2];
            nameArray[0] = name;
            nameArray[1] = code;
            lectureAddRowsToTable(nameArray);
            lectureList.add(new Lecture(name, code));
            //queries.addLectureToDb(name, code);
            //showLectures();
            clearLectureInputFields();
            JOptionPane.showMessageDialog(this, "Lecture registration has been completed.");
        } else {
            JOptionPane.showMessageDialog(this, result);
        }
    }//GEN-LAST:event_addNewLectureActionPerformed

    private void deleteLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteLectureActionPerformed
        int rowNumber = lectureTable.getSelectedRow();
        if (rowNumber >= 0) {
            String a = (String) lectureTable.getModel().getValueAt(rowNumber, 2);
            //queries.deleteLecture(a);
            lectureList.remove(rowNumber);
            deleteLectureRowsFromTable(rowNumber);
            //showLectures();
        }
    }//GEN-LAST:event_deleteLectureActionPerformed

    private void enrollStudentInLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollStudentInLectureActionPerformed
        String no = studentNoForLectureRegister.getText();
        String code = lectureCodeForStudentRegister.getText();
        String result = University.enrollStudentInLectureInputControl(studentList, lectureAndStudentList, lectureList, no, code);
        if (result != null) {
            String[] infos = new String[4];
            infos[0] = getStudentNameAndSurname()[0];
            infos[1] = getStudentNameAndSurname()[1];
            infos[2] = no;
            infos[3] = code;
            lectureAndStudentAddRowsToTable(infos);
            lectureAndStudentList.add(new Student(no, code));
            JOptionPane.showMessageDialog(this, "The student has been successfully enrolled in the course.");
        } else {
            JOptionPane.showMessageDialog(this, result);
        }

    }//GEN-LAST:event_enrollStudentInLectureActionPerformed

    private void unenrollStudentFromLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unenrollStudentFromLectureActionPerformed
        int i = lecturesOfStudentsTable.getSelectedRow();
        if (i >= 0) {
            lectureAndStudentList.remove(i);
            DefaultTableModel dtm = (DefaultTableModel) lecturesOfStudentsTable.getModel();
            dtm.removeRow(i);
        }
    }//GEN-LAST:event_unenrollStudentFromLectureActionPerformed

    private void updateStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStudentActionPerformed
        int i = studentTable.getSelectedRow();
        String name = studentName.getText();
        String surname = studentSurname.getText().toUpperCase();
        String no = "updateEvent";
        if (i >= 0) {
            name = University.setNameConvention(name);
            String result = Student.inputControl(name, surname, no, studentList);
            if (result == null) {
                studentTable.getModel().setValueAt(name, i, 0);
                studentTable.getModel().setValueAt(surname, i, 1);
                studentTable1.getModel().setValueAt(name, i, 0);
                studentTable1.getModel().setValueAt(surname, i, 1);
                System.out.println(studentList.get(i).getStudentNo());
                //queries.updateStudent(name, surname, studentList.get(i).getStudentNo());
                //showStudents();
                clearStudentInputFields();
                JOptionPane.showMessageDialog(this, "Student info is updated.");
            } else {
                JOptionPane.showMessageDialog(this, result);
            }
        }
    }//GEN-LAST:event_updateStudentActionPerformed

    private void updateTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTeacherActionPerformed
        int i = teacherTable.getSelectedRow();
        String name = teacherName.getText();
        String surname = teacherSurname.getText().toUpperCase();
        String no = "updateEvent";
        if (i >= 0) {
            name = University.setNameConvention(name);
            String result = Teacher.inputControl(name, surname, no, teacherList);
            if (result == null) {
                teacherTable.getModel().setValueAt(name, i, 0);
                teacherTable.getModel().setValueAt(surname, i, 1);
                teacherTable1.getModel().setValueAt(name, i, 0);
                teacherTable1.getModel().setValueAt(surname, i, 1);
                //queries.updateTeacher(name, surname, teacherList.get(i).getTeacherNo());
                //showTeachers();
                clearTeacherInputFields();
                JOptionPane.showMessageDialog(this, "Teacher info is updated.");
            } else {
                JOptionPane.showMessageDialog(this, result);
            }
        }
    }//GEN-LAST:event_updateTeacherActionPerformed

    private void updateLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLectureActionPerformed
        int i = lectureTable.getSelectedRow();
        String name = lectureName.getText();
        String code = "updateEvent";

        if (i >= 0) {
            name = University.setNameConvention(name);
            String result = Lecture.inputControl(name, code, lectureList);
            if (result == null) {

            } else {
                lectureTable.getModel().setValueAt(name, i, 0);
                lectureTable1.getModel().setValueAt(name, i, 0);
                //queries.updateLecture(s.lectureName, lectureList.get(i).getLectureCode());
                //showLectures();
                clearLectureInputFields();
                JOptionPane.showMessageDialog(this, "Lecture info is updated.");
            }
        }
    }//GEN-LAST:event_updateLectureActionPerformed

    private void studentSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentSearchKeyReleased
        DefaultTableModel dtn = (DefaultTableModel) studentTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtn);
        studentTable1.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(studentSearch.getText()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + studentSearch.getText()));
    }//GEN-LAST:event_studentSearchKeyReleased

    private void teacherSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacherSearchKeyReleased
        DefaultTableModel dtn = (DefaultTableModel) teacherTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtn);
        teacherTable1.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(teacherSearch.getText()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + teacherSearch.getText()));
    }//GEN-LAST:event_teacherSearchKeyReleased

    private void studentAndLectureSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentAndLectureSearchKeyReleased
        DefaultTableModel dtn = (DefaultTableModel) lecturesOfStudentsTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtn);
        lecturesOfStudentsTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(studentAndLectureSearch.getText()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + studentAndLectureSearch.getText()));
    }//GEN-LAST:event_studentAndLectureSearchKeyReleased

    private void lectureSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lectureSearchKeyReleased
        DefaultTableModel dtn = (DefaultTableModel) lectureTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtn);
        lectureTable1.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(lectureSearch.getText()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + lectureSearch.getText()));
    }//GEN-LAST:event_lectureSearchKeyReleased

    private void assignTeacherToLectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignTeacherToLectionActionPerformed
        String no = teacherNoForLectureAssign.getText();
        String code = lectureCodeForTeacherAssign.getText();
        String result = University.assingTeacherToLectureInputControl(teacherList, lectureAndTeacherList, lectureList, no, code);
        if (result != null) {
            String[] infos = new String[4];
            infos[0] = getTeacherNameAndSurname()[0];
            infos[1] = getTeacherNameAndSurname()[1];
            infos[2] = no;
            infos[3] = code;
            lectureAndTeacherAddRowsToTable(infos);
            lectureAndTeacherList.add(new Teacher(no, code));
            JOptionPane.showMessageDialog(this, "The teacher has been successfully assigned to the course.");
        } else {
            JOptionPane.showMessageDialog(this, result);
        }
    }//GEN-LAST:event_assignTeacherToLectionActionPerformed

    private void removeTeacherFromLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTeacherFromLectureActionPerformed
        int i = lecturesOfTeachersTable.getSelectedRow();
        if (i >= 0) {
            lectureAndTeacherList.remove(i);
            DefaultTableModel dtm = (DefaultTableModel) lecturesOfTeachersTable.getModel();
            dtm.removeRow(i);
        }
    }//GEN-LAST:event_removeTeacherFromLectureActionPerformed

    private void teacherAndLectureSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacherAndLectureSearchKeyReleased
        DefaultTableModel dtn = (DefaultTableModel) lecturesOfTeachersTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtn);
        lecturesOfTeachersTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(teacherAndLectureSearch.getText()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + teacherAndLectureSearch.getText()));
    }//GEN-LAST:event_teacherAndLectureSearchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewLecture;
    private javax.swing.JButton addNewStudent;
    private javax.swing.JButton addNewTeacher;
    private javax.swing.JButton assignTeacherToLection;
    private javax.swing.JButton deleteLecture;
    private javax.swing.JButton deleteStudent;
    private javax.swing.JButton deleteTeacher;
    private javax.swing.JButton enrollStudentInLecture;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lectureCode;
    private javax.swing.JTextField lectureCodeForStudentRegister;
    private javax.swing.JTextField lectureCodeForTeacherAssign;
    private javax.swing.JTextField lectureName;
    private javax.swing.JTextField lectureSearch;
    private javax.swing.JTable lectureTable;
    private javax.swing.JTable lectureTable1;
    private javax.swing.JTable lecturesOfStudentsTable;
    private javax.swing.JTable lecturesOfTeachersTable;
    private javax.swing.JButton removeTeacherFromLecture;
    private javax.swing.JTextField studentAndLectureSearch;
    private javax.swing.JTextField studentName;
    private javax.swing.JTextField studentNo;
    private javax.swing.JTextField studentNoForLectureRegister;
    private javax.swing.JTextField studentSearch;
    private javax.swing.JTextField studentSurname;
    private javax.swing.JTable studentTable;
    private javax.swing.JTable studentTable1;
    private javax.swing.JTextField teacherAndLectureSearch;
    private javax.swing.JTextField teacherName;
    private javax.swing.JTextField teacherNo;
    private javax.swing.JTextField teacherNoForLectureAssign;
    private javax.swing.JTextField teacherSearch;
    private javax.swing.JTextField teacherSurname;
    private javax.swing.JTable teacherTable;
    private javax.swing.JTable teacherTable1;
    private javax.swing.JButton unenrollStudentFromLecture;
    private javax.swing.JButton updateLecture;
    private javax.swing.JButton updateStudent;
    private javax.swing.JButton updateTeacher;
    // End of variables declaration//GEN-END:variables

}
