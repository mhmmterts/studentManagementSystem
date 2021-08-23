package OBS;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
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
        setIcon();
        centerFrame();
        createListeners();
    }

    public static void main(String args[]) {
        OBSGUI o = new OBSGUI();
        o.setVisible(true);

    }

    //changing default java icon
    private void setIcon() {
        Image image = new ImageIcon(this.getClass().getResource("/Images/turk-alman-universitesi-logo.png")).getImage();
        this.setIconImage(image);
    }

    //creating selection listeners for the tables
    private void createListeners() {
        studentTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int i = studentTable.getSelectedRow();
            if (i >= 0) {
                studentName.setText((String) studentTable.getModel().getValueAt(i, 0));
                studentSurname.setText((String) studentTable.getModel().getValueAt(i, 1));
                studentNo.setText((String) studentTable.getModel().getValueAt(i, 2));
            }
        });
        teacherTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int i = teacherTable.getSelectedRow();
            if (i >= 0) {
                teacherName.setText((String) teacherTable.getModel().getValueAt(i, 0));
                teacherSurname.setText((String) teacherTable.getModel().getValueAt(i, 1));
                teacherNo.setText((String) teacherTable.getModel().getValueAt(i, 2));
            }
        });
        lectureTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int i = lectureTable.getSelectedRow();
            if (i >= 0) {
                lectureName.setText((String) lectureTable.getModel().getValueAt(i, 0));
                lectureCode.setText((String) lectureTable.getModel().getValueAt(i, 1));
            }
        });
    }

    //positioning the frame in the center of the screen
    private void centerFrame() {
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
        setLocationRelativeTo(null);
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
        studentTable.clearSelection();
    }

    //adding registered teacher to the tables
    private void teacherAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) teacherTable.getModel();
        dtm.addRow(nameArray);
        DefaultTableModel dtn = (DefaultTableModel) teacherTable1.getModel();
        dtn.addRow(nameArray);
        teacherTable.clearSelection();
    }

    //adding registered lecture to the tables
    private void lectureAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) lectureTable.getModel();
        dtm.addRow(nameArray);
        DefaultTableModel dtn = (DefaultTableModel) lectureTable1.getModel();
        dtn.addRow(nameArray);
        lectureTable.clearSelection();
    }

    //adding enrolled student to lecture table 
    private void lectureAndStudentAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) lecturesOfStudentsTable.getModel();
        dtm.addRow(nameArray);
        studentNoForLectureRegister.setText("");
        lectureCodeForStudentRegister.setText("");
        lecturesOfStudentsTable.clearSelection();
    }

    //adding assigned teacher to lecture table
    private void lectureAndTeacherAddRowsToTable(String[] nameArray) {
        DefaultTableModel dtm = (DefaultTableModel) lecturesOfTeachersTable.getModel();
        dtm.addRow(nameArray);
        teacherNoForLectureAssign.setText("");
        lectureCodeForTeacherAssign.setText("");
        lecturesOfTeachersTable.clearSelection();
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
        teacherNo.setText("");
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
        activateStudentHint = new javax.swing.JButton();
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
        activateTeacherHint = new javax.swing.JButton();
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
        activateLectureHint = new javax.swing.JButton();
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
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        studentNoForLectureRegister = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        lectureCodeForStudentRegister = new javax.swing.JTextField();
        enrollStudentInLecture = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        lecturesOfStudentsTable = new javax.swing.JTable();
        unenrollStudentFromLecture = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        studentAndLectureSearch = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        activateEnrollmentHint = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
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
        activateAssignmentHint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Management System 1.1.0");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(115, 126, 171));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(209, 236, 241));
        jPanel1.setAutoscrolls(true);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        addNewStudent.setBackground(new java.awt.Color(68, 56, 255));
        addNewStudent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addNewStudent.setForeground(new java.awt.Color(255, 255, 255));
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
        studentTable.setMaximumSize(new java.awt.Dimension(45, 0));
        jScrollPane4.setViewportView(studentTable);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Registered Students");

        deleteStudent.setBackground(new java.awt.Color(255, 0, 0));
        deleteStudent.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        deleteStudent.setForeground(new java.awt.Color(255, 255, 255));
        deleteStudent.setText("Delete Student");
        deleteStudent.setFocusable(false);
        deleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStudentActionPerformed(evt);
            }
        });

        updateStudent.setBackground(new java.awt.Color(68, 56, 255));
        updateStudent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateStudent.setForeground(new java.awt.Color(255, 255, 255));
        updateStudent.setText("Update");
        updateStudent.setFocusable(false);
        updateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStudentActionPerformed(evt);
            }
        });

        activateStudentHint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help-web-buton.png"))); // NOI18N
        activateStudentHint.setToolTipText("Info");
        activateStudentHint.setBorderPainted(false);
        activateStudentHint.setContentAreaFilled(false);
        activateStudentHint.setFocusable(false);
        activateStudentHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateStudentHintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(deleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(activateStudentHint, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
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
                            .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(studentSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(studentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addComponent(activateStudentHint)
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(deleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
        );

        jTabbedPane1.addTab("Add/Delete Student", jPanel1);

        jPanel2.setBackground(new java.awt.Color(209, 236, 241));
        jPanel2.setAutoscrolls(true);

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

        addNewTeacher.setBackground(new java.awt.Color(68, 56, 255));
        addNewTeacher.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addNewTeacher.setForeground(new java.awt.Color(255, 255, 255));
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
        teacherTable.setFocusable(false);
        jScrollPane6.setViewportView(teacherTable);

        deleteTeacher.setBackground(new java.awt.Color(255, 0, 0));
        deleteTeacher.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteTeacher.setForeground(new java.awt.Color(255, 255, 255));
        deleteTeacher.setText("Delete Teacher");
        deleteTeacher.setFocusable(false);
        deleteTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTeacherActionPerformed(evt);
            }
        });

        updateTeacher.setBackground(new java.awt.Color(68, 56, 255));
        updateTeacher.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateTeacher.setForeground(new java.awt.Color(255, 255, 255));
        updateTeacher.setText("Update");
        updateTeacher.setFocusable(false);
        updateTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTeacherActionPerformed(evt);
            }
        });

        activateTeacherHint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help-web-buton.png"))); // NOI18N
        activateTeacherHint.setToolTipText("Info");
        activateTeacherHint.setBorderPainted(false);
        activateTeacherHint.setContentAreaFilled(false);
        activateTeacherHint.setFocusable(false);
        activateTeacherHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateTeacherHintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(activateTeacherHint, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(activateTeacherHint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(deleteTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        jTabbedPane1.addTab("Add/Delete Teacher", jPanel2);

        jPanel3.setBackground(new java.awt.Color(209, 236, 241));
        jPanel3.setAutoscrolls(true);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Name:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Lecture Code:");

        lectureName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lectureCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        addNewLecture.setBackground(new java.awt.Color(68, 56, 255));
        addNewLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addNewLecture.setForeground(new java.awt.Color(255, 255, 255));
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
        lectureTable.setFocusable(false);
        jScrollPane8.setViewportView(lectureTable);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("Registered Lectures");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        deleteLecture.setBackground(new java.awt.Color(255, 0, 0));
        deleteLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteLecture.setForeground(new java.awt.Color(255, 255, 255));
        deleteLecture.setText("Delete Lecture");
        deleteLecture.setFocusable(false);
        deleteLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteLectureActionPerformed(evt);
            }
        });

        updateLecture.setBackground(new java.awt.Color(68, 56, 255));
        updateLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateLecture.setForeground(new java.awt.Color(255, 255, 255));
        updateLecture.setText("Update ");
        updateLecture.setFocusable(false);
        updateLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLectureActionPerformed(evt);
            }
        });

        activateLectureHint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help-web-buton.png"))); // NOI18N
        activateLectureHint.setToolTipText("Info");
        activateLectureHint.setBorderPainted(false);
        activateLectureHint.setContentAreaFilled(false);
        activateLectureHint.setFocusable(false);
        activateLectureHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateLectureHintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deleteLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGap(98, 98, 98)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addNewLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182)
                        .addComponent(activateLectureHint, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(lectureCode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(addNewLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel56)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(activateLectureHint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(deleteLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))))
        );

        jTabbedPane1.addTab("Add/Delete Lecture", jPanel3);

        jPanel4.setBackground(new java.awt.Color(209, 236, 241));
        jPanel4.setAutoscrolls(true);

        studentTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b>Name<b>", "<html><b>Surname<b>", "<html><b>Student No<b>"
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
        studentTable1.setFocusable(false);
        jScrollPane5.setViewportView(studentTable1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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
                "<html><b>Name<b>", "<html><b>Surname<b>", "<html><b>Teacher No<b>"
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
        teacherTable1.setFocusable(false);
        jScrollPane7.setViewportView(teacherTable1);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Registered Teachers");

        teacherSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teacherSearchKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Search:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Search:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Registered Lectures");

        lectureTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        lectureTable1.setFocusable(false);
        jScrollPane9.setViewportView(lectureTable1);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Search:");

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
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel16)
                                .addGap(29, 29, 29)
                                .addComponent(studentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(jLabel17)
                        .addGap(30, 30, 30)
                        .addComponent(teacherSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addGap(36, 36, 36)
                                    .addComponent(lectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jLabel23)
                                .addGap(86, 86, 86)))))
                .addContainerGap(18, Short.MAX_VALUE))
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
                        .addComponent(jLabel16))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(teacherSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
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

        jPanel5.setBackground(new java.awt.Color(209, 236, 241));
        jPanel5.setAutoscrolls(true);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Student No:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Lecture Code:");

        enrollStudentInLecture.setBackground(new java.awt.Color(68, 56, 255));
        enrollStudentInLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        enrollStudentInLecture.setForeground(new java.awt.Color(255, 255, 255));
        enrollStudentInLecture.setText("Enroll");
        enrollStudentInLecture.setFocusable(false);
        enrollStudentInLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollStudentInLectureActionPerformed(evt);
            }
        });

        lecturesOfStudentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b>Name<b>", "<html><b>Surname<b>", "<html><b>Student No<b>", "<html><b>Lecture Code<b>"
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
        lecturesOfStudentsTable.setFocusable(false);
        jScrollPane10.setViewportView(lecturesOfStudentsTable);

        unenrollStudentFromLecture.setBackground(new java.awt.Color(255, 0, 0));
        unenrollStudentFromLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        unenrollStudentFromLecture.setForeground(new java.awt.Color(255, 255, 255));
        unenrollStudentFromLecture.setText("Unenroll");
        unenrollStudentFromLecture.setFocusable(false);
        unenrollStudentFromLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unenrollStudentFromLectureActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Search:");

        studentAndLectureSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentAndLectureSearchKeyReleased(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Students/Lectures");

        activateEnrollmentHint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help-web-buton.png"))); // NOI18N
        activateEnrollmentHint.setToolTipText("Info");
        activateEnrollmentHint.setBorderPainted(false);
        activateEnrollmentHint.setContentAreaFilled(false);
        activateEnrollmentHint.setFocusable(false);
        activateEnrollmentHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateEnrollmentHintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(studentAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lectureCodeForStudentRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(studentNoForLectureRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enrollStudentInLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unenrollStudentFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(activateEnrollmentHint, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(studentNoForLectureRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel28))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(lectureCodeForStudentRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(enrollStudentInLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(activateEnrollmentHint)
                        .addGap(22, 22, 22)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(unenrollStudentFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("<html>Enroll/Unenroll<br> Student in lection", jPanel5);

        jPanel6.setBackground(new java.awt.Color(209, 236, 241));
        jPanel6.setAutoscrolls(true);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Teacher No:");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Lecture Code:");

        assignTeacherToLection.setBackground(new java.awt.Color(68, 56, 255));
        assignTeacherToLection.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        assignTeacherToLection.setForeground(new java.awt.Color(255, 255, 255));
        assignTeacherToLection.setText("Assign");
        assignTeacherToLection.setFocusable(false);
        assignTeacherToLection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignTeacherToLectionActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Teachers/Lectures");

        lecturesOfTeachersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b>Name<b>", "<html><b>Surname<b>", "<html><b>Teacher No<b>", "<html><b>Lecture Code<b>"
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
        lecturesOfTeachersTable.setFocusable(false);
        jScrollPane12.setViewportView(lecturesOfTeachersTable);

        removeTeacherFromLecture.setBackground(new java.awt.Color(255, 0, 0));
        removeTeacherFromLecture.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        removeTeacherFromLecture.setForeground(new java.awt.Color(255, 255, 255));
        removeTeacherFromLecture.setText("Unassign");
        removeTeacherFromLecture.setFocusable(false);
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

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Search:");

        activateAssignmentHint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help-web-buton.png"))); // NOI18N
        activateAssignmentHint.setToolTipText("Info");
        activateAssignmentHint.setBorderPainted(false);
        activateAssignmentHint.setContentAreaFilled(false);
        activateAssignmentHint.setFocusable(false);
        activateAssignmentHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateAssignmentHintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel52)
                        .addGap(344, 344, 344))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel45)
                                .addGap(37, 37, 37)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lectureCodeForTeacherAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(teacherNoForLectureAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(69, 69, 69)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(assignTeacherToLection, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(activateAssignmentHint, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel58)
                        .addGap(33, 33, 33)
                        .addComponent(teacherAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removeTeacherFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(130, 130, 130)
                            .addComponent(jLabel52))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(teacherNoForLectureAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lectureCodeForTeacherAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(assignTeacherToLection, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(activateAssignmentHint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(removeTeacherFromLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherAndLectureSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("<html>Assign/Unassign <br>Teacher to lection", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 752, 606));
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
            String a = (String) lectureTable.getModel().getValueAt(rowNumber, 1);
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
        if (result == null) {
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
            lectureTable.clearSelection();
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
                lectureTable.getModel().setValueAt(name, i, 0);
                lectureTable1.getModel().setValueAt(name, i, 0);
                //queries.updateLecture(s.lectureName, lectureList.get(i).getLectureCode());
                //showLectures();
                clearLectureInputFields();
                JOptionPane.showMessageDialog(this, "Lecture info is updated.");
            } else {
                JOptionPane.showMessageDialog(this, result);
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
        if (result == null) {
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

    private void activateStudentHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateStudentHintActionPerformed
        JOptionPane.showMessageDialog(this, "<html>-Student number must be a 7 digit number. <br>-Name and surname can contain only  (Max.20)  letters.<br>"
                + "-Select the student from the table to update.<br>-Student number is unique and can not be updated.<br>-Select the student from the table to delete.", "Information", 1);
    }//GEN-LAST:event_activateStudentHintActionPerformed

    private void activateTeacherHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateTeacherHintActionPerformed
        JOptionPane.showMessageDialog(this, "<html>-Teacher number must be a 11 digit number. <br>-Name and surname can contain only  (Max.20)  letters.<br>"
                + "-Select the teacher from the table to update.<br>-Teacher number is unique and can not be updated.<br>-Select the teacher from the table to delete.", "Information", 1);
    }//GEN-LAST:event_activateTeacherHintActionPerformed

    private void activateLectureHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateLectureHintActionPerformed
        JOptionPane.showMessageDialog(this, "<html>-Lecture code should be 3 letters followed by 3 digits. (Example: INF202) <br>-Name and surname can contain only  (Max.20)  letters.<br>"
                + "-Select the lecture from the table to update.<br>-Lecture code is unique can not be updated.<br>-Select the lecture from the table to delete.", "Information", 1);
    }//GEN-LAST:event_activateLectureHintActionPerformed

    private void activateEnrollmentHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateEnrollmentHintActionPerformed
        JOptionPane.showMessageDialog(this, "<html>-Use the student number of registered student and an existing lecture code to enroll her/him.<br>\n"
                + "-Select from the table to unenroll student from the lecture.", "Information", 1);
    }//GEN-LAST:event_activateEnrollmentHintActionPerformed

    private void activateAssignmentHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateAssignmentHintActionPerformed
        JOptionPane.showMessageDialog(this, "<html>-Use the teacher number of registered teacher and an existing lecture code to assign the teacher.<br>\n"
                + "-Select from the table to unassign teacher from the lecture.", "Information", 1);
    }//GEN-LAST:event_activateAssignmentHintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activateAssignmentHint;
    private javax.swing.JButton activateEnrollmentHint;
    private javax.swing.JButton activateLectureHint;
    private javax.swing.JButton activateStudentHint;
    private javax.swing.JButton activateTeacherHint;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
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
