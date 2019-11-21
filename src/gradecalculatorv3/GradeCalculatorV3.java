/*
Grade Calculator, Version 3
December, 2018
Ethan Vaughan

################################################################################
################################################################################

12/20/2018 - added GPA Calculator functionality.
12/20/2018 - officially finished Grade Calculator, Version 3.
05/07/2019 - allowed storing of final grades; tweaked grade calculator

################################################################################
################################################################################

 */
package gradecalculatorv3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class GradeCalculatorV3 {

    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
        GCV4 gcv4 = new GCV4();
        gcv4.main(args);
    }

    public static void msgBox(String msg) {
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, msg, "GradeCalculatorV3", javax.swing.JOptionPane.DEFAULT_OPTION);
    }
}
