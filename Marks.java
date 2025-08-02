import java.util.Scanner;

public class Marks {

    static int[][] studentMarks; 
    static int n; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        n = scanner.nextInt();
        studentMarks = new int[n][3]; 

        scanner.nextLine(); 

        System.out.println("\nCommands:\n" +
                "add [studentID]             - Add marks for a student\n" +
                "update [studentID] [subjectID] - Update mark of a subject for a student\n" +
                "average [studentID]         - Get average marks of a student\n" +
                "average_s [subjectID]       - Get average marks of a subject\n" +
                "total [studentID]           - Get total marks of a student\n" +
                "grades                      - Display grades for all students\n" +
                "exit                        - Exit the program");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts[0].equalsIgnoreCase("exit")) {
                break;
            }

            switch (parts[0]) {
                case "add":
                    int studentIdAdd = Integer.parseInt(parts[1]) - 1;
                    if (isValidStudent(studentIdAdd)) {
                        System.out.print("Enter marks for Math: ");
                        studentMarks[studentIdAdd][0] = scanner.nextInt();
                        System.out.print("Enter marks for Chemistry: ");
                        studentMarks[studentIdAdd][1] = scanner.nextInt();
                        System.out.print("Enter marks for Physics: ");
                        studentMarks[studentIdAdd][2] = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.println("Marks added for student " + (studentIdAdd + 1));
                    }
                    break;

                case "update":
                    int studentIdUpdate = Integer.parseInt(parts[1]) - 1;
                    int subjectId = Integer.parseInt(parts[2]) - 1;
                    if (isValidStudent(studentIdUpdate) && isValidSubject(subjectId)) {
                        System.out.print("Enter new mark: ");
                        studentMarks[studentIdUpdate][subjectId] = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.println("Mark updated.");
                    }
                    break;

                case "average":
                    int studentIdAvg = Integer.parseInt(parts[1]) - 1;
                    if (isValidStudent(studentIdAvg)) {
                        double avg = (studentMarks[studentIdAvg][0] + studentMarks[studentIdAvg][1] + studentMarks[studentIdAvg][2]) / 3.0;
                        System.out.println("Average marks for student " + (studentIdAvg + 1) + ": " + avg);
                    }
                    break;

                case "average_s":
                    int subjectAvgId = Integer.parseInt(parts[1]) - 1;
                    if (isValidSubject(subjectAvgId)) {
                        double total = 0;
                        for (int i = 0; i < n; i++) {
                            total += studentMarks[i][subjectAvgId];
                        }
                        System.out.println("Average for subject " + getSubjectName(subjectAvgId) + ": " + (total / n));
                    }
                    break;

                case "total":
                    int studentIdTotal = Integer.parseInt(parts[1]) - 1;
                    if (isValidStudent(studentIdTotal)) {
                        int total = studentMarks[studentIdTotal][0] + studentMarks[studentIdTotal][1] + studentMarks[studentIdTotal][2];
                        System.out.println("Total marks for student " + (studentIdTotal + 1) + ": " + total);
                    }
                    break;

                case "grades":
                    System.out.printf("\n%-10s %-10s %-12s %-10s\n", "Student", "Math", "Chemistry", "Physics");
                    for (int i = 0; i < n; i++) {
                        System.out.printf("%-10d %-10s %-12s %-10s\n",
                                (i + 1),
                                getGrade(studentMarks[i][0]),
                                getGrade(studentMarks[i][1]),
                                getGrade(studentMarks[i][2]));
                    }
                    break;

                default:
                    System.out.println("Invalid command.");
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }

    private static boolean isValidStudent(int studentId) {
        if (studentId < 0 || studentId >= n) {
            System.out.println("Invalid student ID.");
            return false;
        }
        return true;
    }

    private static boolean isValidSubject(int subjectId) {
        if (subjectId < 0 || subjectId > 2) {
            System.out.println("Invalid subject ID. Use 1 for Math, 2 for Chemistry, 3 for Physics.");
            return false;
        }
        return true;
    }

    private static String getSubjectName(int id) {
        return switch (id) {
            case 0 -> "Math";
            case 1 -> "Chemistry";
            case 2 -> "Physics";
            default -> "Unknown";
        };
    }

    private static String getGrade(int mark) {
        if (mark >= 90) return "Grade A";
        else if (mark >= 80) return "Grade B";
        else if (mark >= 70) return "Grade C";
        else if (mark >= 60) return "Grade D";
        else return "Fail";
    }
}
