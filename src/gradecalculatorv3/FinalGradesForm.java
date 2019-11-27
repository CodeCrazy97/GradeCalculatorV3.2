/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradecalculatorv3;

import static gradecalculatorv3.GCV4.connectToCollege;
import static gradecalculatorv3.GCV4.getAbsolutePath;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ethan_2
 */
public class FinalGradesForm extends javax.swing.JPanel {

    /**
     * Creates new form EditingClassesForm
     */
    public FinalGradesForm(JFrame frame) {
        initComponents();
        super.setOpaque(true);
        this.setBackground(Color.cyan);

        // Create the JFrame for this window.
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Below is custom close operation.
        WindowListener exitListener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Show the GPA calculator form on closing this form.
                GPACalculator gpacalc = new GPACalculator();
                String[] arguments = {};
                gpacalc.main(arguments);

            }
        };
        frame.addWindowListener(exitListener);  // Add the custom designed listener.

        classNameTextField.setEditable(false);
        finalGradeTextField.setEditable(false);
        semesterTextField.setEditable(false);
        creditsTextField.setEditable(false);
        reqSatisfactionjComboBox1.setEditable(false);
        reqSatisfactionjComboBox1.setEnabled(false);

        // make pressing the Enter key the same as clicking the mouse
        frame.getRootPane().setDefaultButton(addAClassButton);

        getAllSemestersAndCourses();
    }

    public void getInfoAboutCourse(String title, String semester, int year) {
        try {
            String sql = "SELECT id, title, credits, semester_taken, year_taken, final_grade, requirement_satisfaction FROM course WHERE title = '" + title + "' AND semester_taken = '" + semester + "' AND year_taken = " + year + ";";
            try (Connection conn = connectToCollege();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                // loop through the result set
                while (rs.next()) {
                    classNameTextField.setText(title);
                    creditsTextField.setText(rs.getString(3));
                    semesterTextField.setText(semester + " " + year);
                    finalGradeTextField.setText(rs.getString(6));
                    reqSatisfactionjComboBox1.setSelectedItem(rs.getString(7));
                }
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error trying to fetch course info: " + e);  // Show the exception message.
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error trying to fetch course info: " + ex);  // Show the exception message.
        }
    }

    private void getAllSemestersAndCourses() {
        try {
            String sql = "SELECT title, semester_taken, year_taken FROM course;";
            try (Connection conn = connectToCollege();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                // First, remove anything that was already there.
                classesComboBox.removeAllItems();

                // loop through the result set
                while (rs.next()) {
                    String title = rs.getString(1);
                    String semester = rs.getString(2);
                    int year = Integer.parseInt(rs.getString(3));
                    classesComboBox.addItem(title + " - " + semester + " " + year);
                }
                if (classesComboBox.getItemCount() == 0) {
                    deleteButton.setVisible(false);
                    editButton1.setVisible(false);
                }
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error trying to fetch course info: " + e);  // Show the exception message.
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error trying to fetch course info: " + ex);  // Show the exception message.
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

        classesComboBox = new javax.swing.JComboBox<>();
        classNameTextField = new javax.swing.JTextField();
        creditsTextField = new javax.swing.JTextField();
        finalGradeTextField = new javax.swing.JTextField();
        semesterTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addAClassButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton1 = new javax.swing.JButton();
        editButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        reqSatisfactionjComboBox1 = new javax.swing.JComboBox<>();

        setForeground(new java.awt.Color(102, 255, 255));
        setOpaque(false);

        classesComboBox.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        classesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        classesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classesComboBoxActionPerformed(evt);
            }
        });

        classNameTextField.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        classNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                classNameTextFieldFocusGained(evt);
            }
        });
        classNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                classNameTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                classNameTextFieldKeyReleased(evt);
            }
        });

        creditsTextField.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        creditsTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                creditsTextFieldFocusGained(evt);
            }
        });
        creditsTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                creditsTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                creditsTextFieldKeyReleased(evt);
            }
        });

        finalGradeTextField.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        finalGradeTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                finalGradeTextFieldFocusGained(evt);
            }
        });
        finalGradeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                finalGradeTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                finalGradeTextFieldKeyReleased(evt);
            }
        });

        semesterTextField.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setText("Class name:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("Final grade:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Semester:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Credits:");

        addAClassButton.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        addAClassButton.setText("Add a Class");
        addAClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAClassButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        deleteButton.setText("Delete Class");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        editButton1.setText("Edit Class");
        editButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton1ActionPerformed(evt);
            }
        });

        editButton2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        editButton2.setText("Quit");
        editButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setText("Requirement:");

        reqSatisfactionjComboBox1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        reqSatisfactionjComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gen Ed E1", "Gen Ed E2", "Gen Ed E3", "Gen Ed E4", "Gen Ed E5", "Gen Ed E6", "Supporting", "Core", "Concentration", "Elective" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(classesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(finalGradeTextField)
                                    .addComponent(semesterTextField)
                                    .addComponent(creditsTextField)
                                    .addComponent(classNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(reqSatisfactionjComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(44, 44, 44))))
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addAClassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(creditsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finalGradeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(reqSatisfactionjComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(addAClassButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addAClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAClassButtonActionPerformed
        if (addAClassButton.getText().equals("Add a Class")) {
            classesComboBox.setVisible(false);
            addAClassButton.setText("Submit");
            deleteButton.setVisible(true);
            deleteButton.setText("Cancel");
            classNameTextField.setText("");
            finalGradeTextField.setText("");
            semesterTextField.setText("");
            creditsTextField.setText("");
            editButton1.setVisible(false);
            classNameTextField.requestFocusInWindow();
            addAClassButton.setText("Submit");
            classNameTextField.setEditable(true);
            finalGradeTextField.setEditable(true);
            semesterTextField.setEditable(true);
            creditsTextField.setEditable(true);
            reqSatisfactionjComboBox1.setEnabled(true);

        } else {
            // Try submitting to database.
            try {
                // SQLite connection string
                String url = "jdbc:sqlite:" + getAbsolutePath();
                String wholeSemester = "";
                String semester = "";
                int year = -1;
                try {

                    // Error checking.
                    wholeSemester = semesterTextField.getText();
                    if (wholeSemester.contains("-")) {
                        JOptionPane.showMessageDialog(null, "Semester cannot contain dashes or hyphens.");
                        return;
                    }
                    if (finalGradeTextField.getText().length() > 1) {
                        JOptionPane.showMessageDialog(null, "Invalid final grade. Must contain one or fewer characters.");
                        return;
                    }
                    try {
                        Double.parseDouble(creditsTextField.getText());
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Check credits that you entered. Must be a double or integer.");
                        return;
                    }
                    // Make sure there is exactly one space in entire input.
                    String semesterTextFieldText = semesterTextField.getText().toString();
                    int spaceCount = 0;
                    for (int i = 0; i < semesterTextField.getText().length(); i++) {
                        if (semesterTextFieldText.charAt(i) == ' ') {
                            spaceCount++;
                        }
                    }
                    if (spaceCount != 1) {
                        JOptionPane.showMessageDialog(null, "The semester you took the class must contain exactly one space, for example: \nFall 2015, summer 2011, spring_term_a 2013.");
                        return;
                    }

                    semester = wholeSemester.substring(0, wholeSemester.indexOf(" "));
                    year = Integer.parseInt(wholeSemester.substring(wholeSemester.indexOf(" ") + 1));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Be sure your semester is of correct format." + e + "\nExample correct formats: fall 2018, summer 2020, spring_session_a 2021");
                }
                String sql = "INSERT INTO course(title, credits, semester_taken, year_taken, final_grade, requirement_satisfaction) VALUES('" + classNameTextField.getText().toUpperCase() + "', " + creditsTextField.getText() + ", '" + semester.toUpperCase() + "', " + year + ", '" + finalGradeTextField.getText().toUpperCase() + "', '" + reqSatisfactionjComboBox1.getSelectedItem().toString() + "');";
                try (Connection conn = connectToCollege(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
                    pstmt.executeUpdate();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }

                deleteButton.setVisible(true);
                classNameTextField.setText(classNameTextField.getText().toUpperCase());
                finalGradeTextField.setText(finalGradeTextField.getText().toUpperCase());
                semesterTextField.setText(semesterTextField.getText().toUpperCase());

                classesComboBox.addItem(classNameTextField.getText().toUpperCase() + " - " + semester.toUpperCase() + " " + year);
                classesComboBox.setSelectedIndex(classesComboBox.getItemCount() - 1);
                JOptionPane.showMessageDialog(this,
                        "Successfully added the class.",
                        "Class Added",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);  // Show the exception message.
                classesComboBox.setSelectedIndex(0);
            }

            editButton1.setVisible(true);
            classesComboBox.setVisible(true);
            deleteButton.setText("Delete Class");
            addAClassButton.setText("Add a Class");
            classNameTextField.setEditable(false);
            finalGradeTextField.setEditable(false);
            semesterTextField.setEditable(false);
            creditsTextField.setEditable(false);
            reqSatisfactionjComboBox1.setEnabled(false);
        }
    }//GEN-LAST:event_addAClassButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (deleteButton.getText().equals("Cancel")) {  //canceling out of adding a class
            if (classesComboBox.getItemCount() == 0) {
                deleteButton.setVisible(false);
                editButton1.setVisible(false);

            } else {
                editButton1.setVisible(true);
            }
            editButton1.setText("Edit Class");
            classesComboBox.setVisible(true);
            deleteButton.setText("Delete Class");
            addAClassButton.setText("Add a Class");
            addAClassButton.setVisible(true);
            classNameTextField.setEditable(false);
            finalGradeTextField.setEditable(false);
            semesterTextField.setEditable(false);
            creditsTextField.setEditable(false);
            reqSatisfactionjComboBox1.setEnabled(false);
            classesComboBox.setSelectedIndex(0);
        } else if (classesComboBox.getItemCount() > 0 && JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete the course?", "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String course = classesComboBox.getSelectedItem().toString();
            String title = course.substring(0, course.indexOf("-") - 1);
            String semester = course.substring(course.indexOf("-") + 2, course.lastIndexOf(" "));
            int year = Integer.parseInt(course.substring(course.lastIndexOf(" ") + 1));

            // remove it from the database
            String url = "jdbc:sqlite:" + getAbsolutePath();

            // First remove assignments for course to be deleted.
            String sql = "DELETE FROM assignment WHERE course_id = " + getCourseID(title, semester, year) + ";";

            try (Connection conn = connectToCollege(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }

            // Now delete the course in the course table.
            sql = "DELETE FROM course WHERE id = " + getCourseID(title, semester, year) + ";";

            try (Connection conn = connectToCollege(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }

            classesComboBox.removeItemAt(classesComboBox.getSelectedIndex());

            if (classesComboBox.getItemCount() == 0) {
                deleteButton.setVisible(false);
                editButton1.setVisible(false);
                // Null out text fields
                classNameTextField.setText("");
                finalGradeTextField.setText("");
                semesterTextField.setText("");
                creditsTextField.setText("");
            }
            JOptionPane.showMessageDialog(null, "Successfully deleted the class!");  // Show the exception message.

        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private int getCourseID(String title, String semester, int year) {
        String sql = "SELECT id FROM course WHERE title = '" + title + "' AND semester_taken = '" + semester + "' AND year_taken = " + year + ";";
        try (Connection conn = connectToCollege();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                return rs.getInt(1);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "There was an error trying to find the course id for course with title = " + title + ", semester taken = " + semester + ", and year taken = " + year, "Error!", JOptionPane.ERROR_MESSAGE);
        throw new Error("Could not find course id!");
    }

    private void classesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classesComboBoxActionPerformed
        try {
            String course = classesComboBox.getSelectedItem().toString();
            String title = course.substring(0, course.indexOf("-") - 1);
            String semester = course.substring(course.indexOf("-") + 2, course.lastIndexOf(" "));
            int year = Integer.parseInt(course.substring(course.lastIndexOf(" ") + 1));

            getInfoAboutCourse(title, semester, year);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException: " + npe);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem trying to get info about a course: " + e);  // Show the exception message.
        }
    }//GEN-LAST:event_classesComboBoxActionPerformed

    private void classNameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_classNameTextFieldKeyPressed

    }//GEN-LAST:event_classNameTextFieldKeyPressed

    private void creditsTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditsTextFieldKeyPressed

    }//GEN-LAST:event_creditsTextFieldKeyPressed

    private void finalGradeTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finalGradeTextFieldKeyPressed

    }//GEN-LAST:event_finalGradeTextFieldKeyPressed

    private void classNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_classNameTextFieldKeyReleased
        /*        
// the entire name has been entered; move on to next textfield
        if (classNameTextField.getText().toString().length() == 6) {
            creditsTextField.requestFocusInWindow();
        }
         */
    }//GEN-LAST:event_classNameTextFieldKeyReleased

    private void creditsTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditsTextFieldKeyReleased
        /*
        if (creditsTextField.getText().length() == 1) {
            finalGradeTextField.requestFocusInWindow();
        }
         */
    }//GEN-LAST:event_creditsTextFieldKeyReleased

    private void finalGradeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finalGradeTextFieldKeyReleased
        /*
        // The grade has been entered. Move on.
        if (finalGradeTextField.getText().length() == 1) {
            semesterTextField.requestFocusInWindow();
        }
         */
    }//GEN-LAST:event_finalGradeTextFieldKeyReleased

    private void classNameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_classNameTextFieldFocusGained

    }//GEN-LAST:event_classNameTextFieldFocusGained

    private void creditsTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_creditsTextFieldFocusGained
        if (creditsTextField.getText().length() == 1) {
            creditsTextField.setText("");
        }
    }//GEN-LAST:event_creditsTextFieldFocusGained

    private void finalGradeTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_finalGradeTextFieldFocusGained
        if (finalGradeTextField.getText().length() == 1) {
            finalGradeTextField.setText("");
        }
    }//GEN-LAST:event_finalGradeTextFieldFocusGained

    private void editButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton1ActionPerformed
        if (editButton1.getText().equals("Edit Class")) {
            editButton1.setText("Submit Changes");
            addAClassButton.setVisible(false);
            classesComboBox.setVisible(false);
            deleteButton.setText("Cancel");

            classNameTextField.requestFocusInWindow();
            classNameTextField.setEditable(true);
            finalGradeTextField.setEditable(true);
            semesterTextField.setEditable(true);
            creditsTextField.setEditable(true);
            reqSatisfactionjComboBox1.setEnabled(true);

        } else {
            // Try submitting changes to database.
            try {

                if (finalGradeTextField.getText().length() > 1) {
                    JOptionPane.showMessageDialog(null, "Invalid final grade. Must contain one or fewer characters.");
                    return;
                }
                try {
                    Double.parseDouble(creditsTextField.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Check credits that you entered. Must be a double or integer.");
                    return;
                }

                // Make sure there is exactly one space in entire input.
                String semesterTextFieldText = semesterTextField.getText().toString();
                int spaceCount = 0;
                for (int i = 0; i < semesterTextField.getText().length(); i++) {
                    if (semesterTextFieldText.charAt(i) == ' ') {
                        spaceCount++;
                    }
                }
                if (spaceCount != 1) {
                    JOptionPane.showMessageDialog(null, "The semester you took the class must contain exactly one space, for example: \nFall 2015, summer 2011, spring_term_a 2013.");
                    return;
                }

                // SQLite connection string
                String url = "jdbc:sqlite:" + getAbsolutePath();

                String oldWholeSemester = classesComboBox.getSelectedItem().toString();
                String oldTitle = oldWholeSemester.substring(0, oldWholeSemester.indexOf(" "));
                String oldSemester = oldWholeSemester.substring(oldWholeSemester.indexOf("-") + 2, oldWholeSemester.lastIndexOf(" "));
                int oldYear = Integer.parseInt(oldWholeSemester.substring(oldWholeSemester.lastIndexOf(" ") + 1));

                String wholeSemester = semesterTextField.getText();
                String semester = wholeSemester.substring(0, wholeSemester.indexOf(" "));
                int year = Integer.parseInt(wholeSemester.substring(wholeSemester.indexOf(" ") + 1));

                String sql = "UPDATE course SET title = '" + classNameTextField.getText().toUpperCase() + "', credits = " + creditsTextField.getText() + ", final_grade = '" + finalGradeTextField.getText().toUpperCase() + "', semester_taken = '" + semester.toUpperCase()
                        + "', year_taken = " + year + ", requirement_satisfaction = '" + reqSatisfactionjComboBox1.getSelectedItem().toString() + "' WHERE id = " + getCourseID(oldTitle, oldSemester, oldYear) + ";";

                try (Connection conn = connectToCollege(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
                    pstmt.executeUpdate();
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                editButton1.setText("Edit Class");
                addAClassButton.setVisible(true);
                classesComboBox.addItem(classNameTextField.getText().toUpperCase() + " - " + semester.toUpperCase() + " " + year);
                classesComboBox.removeItemAt(classesComboBox.getSelectedIndex());
                classesComboBox.setSelectedIndex(classesComboBox.getItemCount() - 1);
                JOptionPane.showMessageDialog(this,
                        "Successfully modified the class.",
                        "Class Added",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);  // Show the exception message.
                classesComboBox.setSelectedIndex(0);
            }

            classesComboBox.setVisible(true);
            deleteButton.setText("Delete Class");
            addAClassButton.setText("Add a Class");
            classNameTextField.setEditable(false);
            finalGradeTextField.setEditable(false);
            semesterTextField.setEditable(false);
            creditsTextField.setEditable(false);
            reqSatisfactionjComboBox1.setEnabled(false);

        }
    }//GEN-LAST:event_editButton1ActionPerformed

    private void editButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_editButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAClassButton;
    private javax.swing.JTextField classNameTextField;
    private javax.swing.JComboBox<String> classesComboBox;
    private javax.swing.JTextField creditsTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton1;
    private javax.swing.JButton editButton2;
    private javax.swing.JTextField finalGradeTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> reqSatisfactionjComboBox1;
    private javax.swing.JTextField semesterTextField;
    // End of variables declaration//GEN-END:variables
}
