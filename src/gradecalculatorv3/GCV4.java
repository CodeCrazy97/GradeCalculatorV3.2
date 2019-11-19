/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradecalculatorv3;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ethan
 */
public class GCV4 extends javax.swing.JFrame {

    /**
     * Creates new form GCV4
     */
    public GCV4() {
        initComponents();

        titleList.add("Assignment 5");
        weightList.add("10.5");
        scoreList.add("98.3");
        titleList.add("Test 1");
        weightList.add("25");
        scoreList.add("68");

        calculateOverallGrade();
        calculateOverallPercentagePoints();
        calculatePercentageComplete();

        newClassjPanel2.setVisible(false);
        infojLabel1.setVisible(false);

    }

    public static String getAbsolutePath() {
        return (new File("").getAbsolutePath() + "/college.db").replace("\\", "/");
    }

    public static void insert(String title, double credits) {
        // SQLite connection string
        String url = "jdbc:sqlite:" + getAbsolutePath();

        String sql = "INSERT INTO course(id, title, credits, semester_taken, year_taken, final_grade) VALUES(1, ?, ?, 'fall', 2017, 'A')";

        try (Connection conn = connectToCollege(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, title);
            pstmt.setDouble(2, credits);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection connectToCollege() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + getAbsolutePath();
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void selectAll() {
        String sql = "SELECT id, title, credits FROM course;";

        try (Connection conn = connectToCollege();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("title"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createCourseTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + getAbsolutePath();

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS course(\n"
                + "	id INT PRIMARY KEY,\n"
                + "	title CHAR(7) NOT NULL,\n"
                + "	credits DOUBLE NOT NULL,\n"
                + "	semester_taken CHAR(12) NOT NULL,\n"
                + "	year_taken INT(4) NOT NULL,\n"
                + "	final_grade CHAR(1),\n"
                + "	UNIQUE (title, semester_taken, year_taken)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createAssignmentTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + getAbsolutePath();

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS assignment(\n"
                + "	id INT PRIMARY KEY,\n"
                + "	title CHAR(50),\n"
                + "	weight DOUBLE NOT NULL,\n"
                + "	grade DOUBLE NOT NULL,\n"
                + "	course_id INT NOT NULL, \n"
                + "	CONSTRAINT course_id_constraint FOREIGN KEY (course_id) REFERENCES course(id),\n"
                + "	UNIQUE (title, course_id)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + getAbsolutePath();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
        }
        return conn;
    }

    public static void createNewDatabase() {
        System.out.println(getAbsolutePath());
        String url = "jdbc:sqlite:" + getAbsolutePath();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gradesjPanel1 = new javax.swing.JPanel();
        titleList = new java.awt.List();
        currentGradeLabel = new javax.swing.JLabel();
        scoreList = new java.awt.List();
        percentagePointsLabel = new javax.swing.JLabel();
        weightList = new java.awt.List();
        percentCompleteLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        assignmentTitlejTextField1 = new javax.swing.JTextField();
        weightjTextField2 = new javax.swing.JTextField();
        scoreReceivedjTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        newClassjPanel2 = new javax.swing.JPanel();
        classNamejTextField1 = new javax.swing.JTextField();
        yearTakenjTextField1 = new javax.swing.JTextField();
        semesterTakenjTextField1 = new javax.swing.JTextField();
        coursesjPanel1 = new javax.swing.JPanel();
        coursesComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        semestersComboBox = new javax.swing.JComboBox<>();
        canceljButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        assignmentTitleTextField = new javax.swing.JTextField();
        weightTextField = new javax.swing.JTextField();
        scoreTextField = new javax.swing.JTextField();
        infojLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleListMouseClicked(evt);
            }
        });
        titleList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                titleListFocusGained(evt);
            }
        });
        titleList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleListActionPerformed(evt);
            }
        });

        currentGradeLabel.setText("Current grade:");

        scoreList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scoreListMouseClicked(evt);
            }
        });

        percentagePointsLabel.setText("Percentage points earned: ");

        weightList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                weightListMouseClicked(evt);
            }
        });

        percentCompleteLabel.setText("Percent complete: ");

        jButton1.setText("Delete Assignment");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Submit Changes");

        javax.swing.GroupLayout gradesjPanel1Layout = new javax.swing.GroupLayout(gradesjPanel1);
        gradesjPanel1.setLayout(gradesjPanel1Layout);
        gradesjPanel1Layout.setHorizontalGroup(
            gradesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradesjPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(gradesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradesjPanel1Layout.createSequentialGroup()
                        .addComponent(assignmentTitlejTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(weightjTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreReceivedjTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gradesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(percentCompleteLabel)
                        .addComponent(percentagePointsLabel)
                        .addComponent(currentGradeLabel))
                    .addGroup(gradesjPanel1Layout.createSequentialGroup()
                        .addComponent(titleList, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(weightList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(scoreList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(gradesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        gradesjPanel1Layout.setVerticalGroup(
            gradesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradesjPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gradesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weightList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gradesjPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gradesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assignmentTitlejTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weightjTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoreReceivedjTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(currentGradeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percentagePointsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percentCompleteLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("Add a Class");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        classNamejTextField1.setText("Class Name (max. 7 characters)");
        classNamejTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                classNamejTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                classNamejTextField1FocusLost(evt);
            }
        });

        yearTakenjTextField1.setText("Year Taken (4 digits)");
        yearTakenjTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                yearTakenjTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                yearTakenjTextField1FocusLost(evt);
            }
        });

        semesterTakenjTextField1.setText("Semester Taken");
        semesterTakenjTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                semesterTakenjTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                semesterTakenjTextField1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout newClassjPanel2Layout = new javax.swing.GroupLayout(newClassjPanel2);
        newClassjPanel2.setLayout(newClassjPanel2Layout);
        newClassjPanel2Layout.setHorizontalGroup(
            newClassjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newClassjPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newClassjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(classNamejTextField1)
                    .addComponent(yearTakenjTextField1)
                    .addComponent(semesterTakenjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newClassjPanel2Layout.setVerticalGroup(
            newClassjPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newClassjPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classNamejTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearTakenjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(semesterTakenjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Courses:");

        jLabel4.setText("Semesters:");

        javax.swing.GroupLayout coursesjPanel1Layout = new javax.swing.GroupLayout(coursesjPanel1);
        coursesjPanel1.setLayout(coursesjPanel1Layout);
        coursesjPanel1Layout.setHorizontalGroup(
            coursesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coursesjPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coursesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(coursesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semestersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        coursesjPanel1Layout.setVerticalGroup(
            coursesjPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, coursesjPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(semestersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coursesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        canceljButton5.setText("CANCEL");
        canceljButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceljButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("Add a New Grade");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        assignmentTitleTextField.setText("Assignment Title");
        assignmentTitleTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                assignmentTitleTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                assignmentTitleTextFieldFocusLost(evt);
            }
        });
        assignmentTitleTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                assignmentTitleTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                assignmentTitleTextFieldKeyReleased(evt);
            }
        });

        weightTextField.setText("Weight (%)");
        weightTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                weightTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                weightTextFieldFocusLost(evt);
            }
        });

        scoreTextField.setText("Score (%)");
        scoreTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                scoreTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                scoreTextFieldFocusLost(evt);
            }
        });

        infojLabel1.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        infojLabel1.setText("Enter information for a new grade in above text fields");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newClassjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(coursesjPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gradesjPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(358, 358, 358))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(1, 1, 1)
                                .addComponent(assignmentTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(weightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(348, 348, 348))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(infojLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(canceljButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(coursesjPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newClassjPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gradesjPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(assignmentTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infojLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(canceljButton5)
                .addGap(75, 75, 75))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void titleListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleListActionPerformed

    }//GEN-LAST:event_titleListActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jButton4.getText().equals("Add a Class")) {
            newClassjPanel2.setVisible(true);
            jButton4.setText("Add");
        } else {
            newClassjPanel2.setVisible(false);
            jButton4.setText("Add Class");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void semesterTakenjTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_semesterTakenjTextField1FocusLost
        if (semesterTakenjTextField1.getText().equals("")) {
            semesterTakenjTextField1.setText("Semester Taken");
        }
    }//GEN-LAST:event_semesterTakenjTextField1FocusLost

    private void classNamejTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_classNamejTextField1FocusGained
        if (classNamejTextField1.getText().equals("Class Name (max. 7 characters)")) {
            classNamejTextField1.setText("");
        }
    }//GEN-LAST:event_classNamejTextField1FocusGained

    private void yearTakenjTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearTakenjTextField1FocusGained
        if (yearTakenjTextField1.getText().equals("Year Taken (4 digits)")) {
            yearTakenjTextField1.setText("");
        }
    }//GEN-LAST:event_yearTakenjTextField1FocusGained

    private void semesterTakenjTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_semesterTakenjTextField1FocusGained
        if (semesterTakenjTextField1.getText().equals("Semester Taken")) {
            semesterTakenjTextField1.setText("");
        }
    }//GEN-LAST:event_semesterTakenjTextField1FocusGained

    private void classNamejTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_classNamejTextField1FocusLost
        if (classNamejTextField1.getText().equals("")) {
            classNamejTextField1.setText("Class Name (max. 7 characters)");
        }
    }//GEN-LAST:event_classNamejTextField1FocusLost

    private void yearTakenjTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearTakenjTextField1FocusLost
        if (yearTakenjTextField1.getText().equals("")) {
            yearTakenjTextField1.setText("Year Taken (4 digits)");
        }
    }//GEN-LAST:event_yearTakenjTextField1FocusLost

    private void canceljButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceljButton5ActionPerformed
        newClassjPanel2.setVisible(false);
        classNamejTextField1.setText("Class Name (max. 7 characters)");
        yearTakenjTextField1.setText("Year Taken (4 digits)");
        semesterTakenjTextField1.setText("Semester Taken");
        jButton4.setText("Add a Class");
        assignmentTitleTextField.setText("Assignment Title");
        weightTextField.setText("Weight (%)");
        scoreTextField.setText("Score (%)");
        infojLabel1.setVisible(false);
        jButton3.setText("Add a New Grade");

        weightList.select(-1);
        scoreList.select(-1);
        titleList.select(-1);
    }//GEN-LAST:event_canceljButton5ActionPerformed

    private void assignmentTitleTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_assignmentTitleTextFieldKeyPressed
    }//GEN-LAST:event_assignmentTitleTextFieldKeyPressed

    private void assignmentTitleTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_assignmentTitleTextFieldKeyReleased
        System.out.println(titleList.getSelectedIndex());
        if (titleList.getSelectedIndex() == -1) {
            jButton3.setVisible(true);
        }
    }//GEN-LAST:event_assignmentTitleTextFieldKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jButton3.getText().equals("Add a New Grade")) {
            weightList.select(-1);
            scoreList.select(-1);
            titleList.select(-1);

            jButton3.setText("Add");
            assignmentTitleTextField.setText("Assignment Title");
            weightTextField.setText("Weight (%)");
            scoreTextField.setText("Score (%)");
            infojLabel1.setVisible(true);
        } else {
            jButton3.setText("Add a New Grade");
            infojLabel1.setVisible(false);

            // Recalculate percentage points, overall grade, and percent complete.
            calculateOverallGrade();
            calculateOverallPercentagePoints();
            calculatePercentageComplete();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void assignmentTitleTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_assignmentTitleTextFieldFocusGained
        if (assignmentTitleTextField.getText().equals("Assignment Title")) {
            assignmentTitleTextField.setText("");
        }
    }//GEN-LAST:event_assignmentTitleTextFieldFocusGained

    private void assignmentTitleTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_assignmentTitleTextFieldFocusLost
        if (assignmentTitleTextField.getText().equals("") && titleList.getSelectedIndex() == -1) {
            assignmentTitleTextField.setText("Assignment Title");
        }
    }//GEN-LAST:event_assignmentTitleTextFieldFocusLost

    private void weightTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_weightTextFieldFocusGained
        if (weightTextField.getText().equals("Weight (%)")) {
            weightTextField.setText("");
        }
    }//GEN-LAST:event_weightTextFieldFocusGained

    private void weightTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_weightTextFieldFocusLost
        if (weightTextField.getText().equals("") && weightList.getSelectedIndex() == -1) {
            weightTextField.setText("Weight (%)");
        }
    }//GEN-LAST:event_weightTextFieldFocusLost

    private void scoreTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_scoreTextFieldFocusGained
        if (scoreTextField.getText().equals("Score (%)")) {
            scoreTextField.setText("");
        }
    }//GEN-LAST:event_scoreTextFieldFocusGained

    private void scoreTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_scoreTextFieldFocusLost
        if (scoreTextField.getText().equals("") && scoreList.getSelectedIndex() == -1) {
            scoreTextField.setText("Score (%)");
        }
    }//GEN-LAST:event_scoreTextFieldFocusLost

    private void titleListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleListFocusGained
    }//GEN-LAST:event_titleListFocusGained

    private void titleListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleListMouseClicked
        if (titleList.getSelectedIndex() != -1) {
            weightList.select(titleList.getSelectedIndex());
            scoreList.select(titleList.getSelectedIndex());
            weightjTextField2.setText(weightList.getSelectedItem());
            assignmentTitlejTextField1.setText(titleList.getSelectedItem());
            scoreReceivedjTextField3.setText(scoreList.getSelectedItem());
        }
    }//GEN-LAST:event_titleListMouseClicked

    private void weightListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_weightListMouseClicked
        if (weightList.getSelectedIndex() != -1) {
            titleList.select(weightList.getSelectedIndex());
            scoreList.select(weightList.getSelectedIndex());
            weightjTextField2.setText(weightList.getSelectedItem());
            assignmentTitlejTextField1.setText(titleList.getSelectedItem());
            scoreReceivedjTextField3.setText(scoreList.getSelectedItem());
        }
    }//GEN-LAST:event_weightListMouseClicked

    private void scoreListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scoreListMouseClicked
        if (scoreList.getSelectedIndex() != -1) {
            titleList.select(scoreList.getSelectedIndex());
            weightList.select(scoreList.getSelectedIndex());
            weightjTextField2.setText(weightList.getSelectedItem());
            assignmentTitlejTextField1.setText(titleList.getSelectedItem());
            scoreReceivedjTextField3.setText(scoreList.getSelectedItem());
        }
    }//GEN-LAST:event_scoreListMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (titleList.getSelectedIndex() != -1) {
            if (titleList.getItem(titleList.getSelectedIndex()).equals(assignmentTitlejTextField1.getText())) {
                weightjTextField2.setText("");
                assignmentTitlejTextField1.setText("");
                scoreReceivedjTextField3.setText("");
            }
            titleList.remove(titleList.getSelectedIndex());
            weightList.remove(weightList.getSelectedIndex());
            scoreList.remove(scoreList.getSelectedIndex());
        }

        // Recalculate percentage points, overall grade, and percent complete.
        calculateOverallGrade();
        calculateOverallPercentagePoints();
        calculatePercentageComplete();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void calculateOverallGrade() {
        double sum = 0.0;
        for (int i = 0; i < weightList.getItemCount(); i++) {
            sum += Double.parseDouble(weightList.getItem(i));
        }

        double pPoints = 0.0;
        for (int i = 0; i < weightList.getItemCount(); i++) {
            pPoints += Double.parseDouble(weightList.getItem(i)) * (Double.parseDouble(scoreList.getItem(i)) / 100.0);
        }
        currentGradeLabel.setText("Current grade: " + ((int) ((pPoints / sum) * 1000)) / 10.0);
    }

    private void calculateOverallPercentagePoints() {
        double pPoints = 0.0;
        for (int i = 0; i < weightList.getItemCount(); i++) {
            pPoints += Double.parseDouble(weightList.getItem(i)) * (Double.parseDouble(scoreList.getItem(i)) / 100.0);
        }
        percentagePointsLabel.setText("Percentage points earned: " + (int) (pPoints * 10) / 10.0);
    }

    private void calculatePercentageComplete() {
        double sum = 0.0;
        for (int i = 0; i < weightList.getItemCount(); i++) {
            sum += Double.parseDouble(weightList.getItem(i));
        }
        percentCompleteLabel.setText("Percent complete: " + (int) (sum * 10) / 10.0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GCV4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GCV4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GCV4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GCV4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GCV4().setVisible(true);
            }
        });

        createNewDatabase();
        createCourseTable();
        createAssignmentTable();
        insert("CSC191", 3.0);
        selectAll();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField assignmentTitleTextField;
    private javax.swing.JTextField assignmentTitlejTextField1;
    private javax.swing.JButton canceljButton5;
    private javax.swing.JTextField classNamejTextField1;
    private javax.swing.JComboBox<String> coursesComboBox;
    private javax.swing.JPanel coursesjPanel1;
    private javax.swing.JLabel currentGradeLabel;
    private javax.swing.JPanel gradesjPanel1;
    private javax.swing.JLabel infojLabel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel newClassjPanel2;
    private javax.swing.JLabel percentCompleteLabel;
    private javax.swing.JLabel percentagePointsLabel;
    private java.awt.List scoreList;
    private javax.swing.JTextField scoreReceivedjTextField3;
    private javax.swing.JTextField scoreTextField;
    private javax.swing.JTextField semesterTakenjTextField1;
    private javax.swing.JComboBox<String> semestersComboBox;
    private java.awt.List titleList;
    private java.awt.List weightList;
    private javax.swing.JTextField weightTextField;
    private javax.swing.JTextField weightjTextField2;
    private javax.swing.JTextField yearTakenjTextField1;
    // End of variables declaration//GEN-END:variables
}
