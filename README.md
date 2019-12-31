# GradeCalculatorV3
Java project that stores grades, calculates "what-ifs". GPA calculator embedded. Modeled after the Ben Eggleston grade calculator.


## Grade Calculator, Version 3

This calculator helps me keep track of my grades in classes. It stores information about past grades (a description of the grade, such as “Test 1” or “Term Project”; the grade I received, like 89.6%; and the assignment’s weight as percentage, such as 15%). The grade calculator calculates my overall grade in the course and allows me to run “what-if” calculations. (A what-if calculation tells me what grade I’ll need on remaining assignments in the course in order to finish with a specified grade; it also reports what grade I’ll have overall if I make a certain grade on remaining assignments.) Finally, there is section in the calculator for finding my GPA. This section shows me what GPA I’ll have overall, given certain grades on up to six classes. Another screen allows me to store the final (letter) grades of completed courses.


## Installation Instructions

To run this program, you must have Netbeans 8.1 installed. You will need to add the SQLite driver (which is included in the \src\gradecalculatorv3\ folder of the project). Follow the below instructions to do so:

1. Open Netbeans IDE.
2. Click "File" > "Open Project" and find the unzipped project. Open it.
3. When you first attempt to open the project in Netbeans, the following window will pop up. It is warning you about references to the SQLite database that don't exist. You will resolve this in step 4.

![Pop up Error](https://github.com/CodeCrazy97/GradeCalculatorV3.2/blob/master/projecterror.PNG)

Ignore this warning by clicking "Close".

4. Right click on "Libraries" in the project structure and select "Add JAR/Folder".
5. Go to the src folder in the project and select the sqlite-jdbc-3.27.2.1 jar file (jar is located in "[path to project]\GradeCalculatorV3\src\gradecalculatorv3\sqlite-jdbc-3.27.2.1.jar"). This will allow the project to use the SQLite database.
6. You should now be able to run the project successfully.

![Resolving SQLite Driver Error](https://github.com/CodeCrazy97/GradeCalculatorV3.2/blob/master/fixerror.PNG)

If you have any questions, or there are bugs with this project, please reach out to me.




## Built With
Netbeans 8.1.


