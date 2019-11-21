/*
Class to handle queries into the database (selecting, inserting, deleting, etc.).
 */
package gradecalculatorv3;

import static gradecalculatorv3.GCV4.connectToCollege;
import static gradecalculatorv3.GCV4.getAbsolutePath;
import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Querying {
    
    public Querying() throws ClassNotFoundException {
        // Check that the course table exists. Create it if not.
        createNewDatabase();
        createCourseTable();
    }
    
    public static void createNewDatabase() {
        String url = "jdbc:sqlite:" + getAbsolutePath();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createCourseTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + getAbsolutePath();

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS course(\n"
                + "	id INTEGER PRIMARY KEY,\n"
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

       
    public double getTotalCreditHours() {
        String sqlString = "SELECT sum(credits) FROM course WHERE final_grade = 'A' OR final_grade = 'B' OR final_grade = 'C' OR final_grade = 'D' OR final_grade = 'F';";
        try (Connection conn = connectToCollege();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlString)) {
            
            double totalCredits = 0;
            while (rs.next()) {
                totalCredits += rs.getDouble(1);
            }
            stmt.close();
            rs.close();
            conn.close();
            return totalCredits;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);  // Show the exception message.
            return 0.0;
        }
    }
    
    public double getGPA() {
        String sqlString = "SELECT final_grade, credits FROM course;";
        try (Connection conn = connectToCollege();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlString)) {
            
            double totalCredits = 0.0;
            double qualityPoints = 0.0;
            while (rs.next()) {
                String grade = rs.getString(1);
                grade = grade.toUpperCase();
                double credits = rs.getDouble(2);
                if (grade.equals("A") || grade.equals("B") || grade.equals("C") || grade.equals("D") || grade.equals("F")) {  // These final grade types count towards GPA.
                    totalCredits += credits;
                }
                if (grade.equals("A")) {
                    qualityPoints += 4.0 * credits;
                } else if (grade.equals("B")) {
                    qualityPoints += 3.0 * credits;
                } else if (grade.equals("C")) {
                    qualityPoints += 2.0 * credits;
                } else if (grade.equals("D")) {
                    qualityPoints += 1.0 * credits;
                } else {
                    // no quality points earned
                }
            }
            stmt.close();
            rs.close();
            conn.close();
            return qualityPoints / totalCredits;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);  // Show the exception message.
            return 0.0;
        }
    }
}
