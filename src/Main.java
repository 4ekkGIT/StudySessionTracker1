import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SessionManager manager = new SessionManager();
        manager.loadSessions();

        while (true) {
            System.out.println("---Study Session Tracker---");
            System.out.println("1. Start studying session");
            System.out.println("2. Stop session");
            System.out.println("3. Show today's study time");
            System.out.println("4. Show weekly statistics");
            System.out.println("5. Show subject statistics");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                manager.startSession(scanner);
            }
            else if (choice == 2) {
                manager.stopSession();
                manager.saveSessions();
            }
            else if (choice == 3) {
                boolean inTodaysTimeMenu = true;
                while (inTodaysTimeMenu) {
                    manager.showTodayStats();
                    System.out.print("Enter 0 to go back to main menu: ");
                    int goback = scanner.nextInt();
                    if (goback == 0) {
                        inTodaysTimeMenu = false;
                    }
                }
            }
            else if (choice == 4) {
                boolean inWeeklyMenu = true;
                while (inWeeklyMenu) {
                    manager.showWeeklyStats();
                    System.out.print("Enter 0 to go back to main menu: ");
                    int goback = scanner.nextInt();
                    if (goback == 0) {
                        inWeeklyMenu = false;
                    }
                }
            }
            else if (choice == 5) {
                boolean inSubMenu = true;
                while (inSubMenu) {
                    manager.showSubStats();
                    System.out.print("Enter 0 to go back to main menu: ");
                    int goback = scanner.nextInt();
                    if (goback == 0) {
                        inSubMenu = false;
                    }
                }
            }
            else if (choice == 0) {
                manager.saveSessions();
                return;
            }
            else System.out.println("Invalid option!");
        }
    }
}