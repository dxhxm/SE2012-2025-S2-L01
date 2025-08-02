import java.util.Scanner;

public class Welcome {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your First Name: ");
        String firstname = scan.nextLine();

        System.out.print("Enter your Last Name: ");
        String lastname = scan.nextLine();

        System.out.println("Welcome to the Second Year " + firstname + " " + lastname);
        scan.close();
    }
}
