/*
Class to handle queries into the database (selecting, inserting, deleting, etc.).
 */
package gradecalculatorv3;

import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Querying {

    public String url = "jdbc:mysql://localhost:3306/college";

    public Querying() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public LinkedList<String> getSemesters() {
        try {
            LinkedList<String> semesters = new LinkedList<>();

            Connection conn = DriverManager.getConnection(url, "root", "");
            String sqlString = "SELECT semester FROM grades GROUP BY semester ORDER BY SUBSTRING(semester, LENGTH(semester) - 4) DESC, SUBSTRING(semester, 1) ASC;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                semesters.add(rs.getString(1));
            }
            conn.close();
            return semesters;
        } catch (Exception e) {
            System.err.println("No semesters returned. ");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public LinkedList<String> getClasses(String semester) {
        try {
            LinkedList<String> classes = new LinkedList<>();

            Connection conn = DriverManager.getConnection(url, "root", "");
            String sqlString = "SELECT class FROM grades WHERE semester = '" + semester + "' GROUP BY class;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                classes.add(rs.getString(1));
            }
            conn.close();
            return classes;
        } catch (Exception e) {
            System.err.println("No classes returned. ");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //getGradesAndWeights returns a linked list of doubles. 
    //Every 3 values is a new grade.
    //EXAMPLE: 0 ~ description ('Test 1', 'Team Project', etc.), 1 ~ grade, 2 ~ weight.    
    public LinkedList<String> getGradesWeightsDescriptions(String className, String semester) {
        try {
            LinkedList<String> gradesWeightsDesc = new LinkedList<>();

            Connection conn = DriverManager.getConnection(url, "root", "");
            String sqlString = "SELECT description, grade, weight FROM grades WHERE semester = '" + semester + "' AND class = '" + className + "';";
            PreparedStatement stmt = conn.prepareStatement(sqlString);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                gradesWeightsDesc.add(rs.getString(1));
                gradesWeightsDesc.add(rs.getString(2));
                gradesWeightsDesc.add(rs.getString(3));
            }
            conn.close();
            return gradesWeightsDesc;
        } catch (Exception e) {
            System.err.println("No grades returned.");
            System.err.println(e.getMessage());
            return null;
        }
    }

    //createAndInitializeNewClass - creates a new class and inserts the first grade for it.
    public void createAndInitializeNewClass(String className, String initialGrade, String initialWeight, String initialDescription, String semester) {
        className = fixStringForMySQL(className);
        initialDescription = fixStringForMySQL(initialDescription);
        String sqlString = "INSERT INTO grades (class, description, grade, weight, semester) VALUES ('" + className + "', '" + initialDescription + "'," + initialGrade + ",'" + initialWeight + "','" + semester + "'); ";
        //Now, execute the insert statement.
        try {
            Connection conn = DriverManager.getConnection(url, "root", "");
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            // execute the preparedstatement
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Successfully added the class.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            JOptionPane.showMessageDialog(null, "Error inserting into MySQL. Try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }

    public void sendNewGradesToDatabase(LinkedList<String> newGrades, String className, String semester) throws SQLException {
        //First, remove any grades already present for the specified class (this avoids repeating grades).        
        try {
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sqlString = "DELETE FROM grades WHERE class = '" + className + "' AND semester = '" + semester + "';";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        //Now, submit the new grades.
        for (int i = 0; i < newGrades.size() - 2; i += 3) {
            String desc = fixStringForMySQL(newGrades.get(i).toString());
            String sqlString = "INSERT INTO grades (description, grade, weight, class, semester) VALUES ('" + desc + "'," + newGrades.get(i + 1) + "," + newGrades.get(i + 2) + ",'" + className.toUpperCase() + "','" + semester + "'); ";
            //Now, execute the insert statement.
            try {
                Connection conn = DriverManager.getConnection(url, "root", "");
                PreparedStatement stmt = conn.prepareStatement(sqlString);
                // execute the preparedstatement
                stmt.execute();

                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
        }
    }

    //Fix the apostrophe and newline so that they fit in MySQL properly.
    public String fixStringForMySQL(String s) {       
        String s2 = s.replace("\'", "\\'");        
        s2 = s2.replace("\n", "\\n");
        return s2;
    }
}
