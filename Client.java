// Nicolas Stoian

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Client {

	public static void main(String args[]){
        try {
            String name = "data";
            Registry registry = LocateRegistry.getRegistry("localhost", 24680);
            ScheduleInterface systemData = (ScheduleInterface) registry.lookup(name);
            Scanner consoleIn = new Scanner(System.in);
            System.out.println("Client Program for Schedule Server\n");
            System.out.println("Enter your Username:");
            String userName = consoleIn.nextLine();
            System.out.println("Enter your Password:");
            String password = consoleIn.nextLine();
            String auth = systemData.authenticate(userName, password);
            if (auth.equals("fail")){
            	System.out.println("\nAuthentication failed, program exiting\n");
            	consoleIn.close();
            	return;
            }
            if (auth.equals("admin")){
            	boolean isRunning = true;
            	while (isRunning){
            		System.out.println("\nAdministrator Menu\n");
            		System.out.println("1: Create a user account");
            		System.out.println("2: Delete a user account");
            		System.out.println("3: Reset password for a user account");
            		System.out.println("4: Display list of all user accounts");
            		System.out.println("5: Display schedule of a user");
            		System.out.println("6: Add an event to a users schedule");
            		System.out.println("7: Edit an event from a users schedule");
            		System.out.println("8: Delete an event from a users schedule");
            		System.out.println("9: Exit program\n");
            		System.out.println("Enter the number of your selection:");
            		int selection = consoleIn.nextInt();
            		consoleIn.nextLine();
            		switch (selection){
            			case 1:
            				System.out.println("Create a user account");
            				System.out.println("Enter username for account");
            				String newUserName = consoleIn.nextLine();
            				System.out.println("Enter password for account");
            				String newPassword = consoleIn.nextLine();
            				systemData.addUser(newUserName, newPassword);
            				System.out.println("User: " + newUserName + " added to system");
            				break;

            			case 2:
            				System.out.println("Delete a user account");
            				System.out.println("User Accounts");
            				System.out.println(systemData.userList());
            				System.out.println("Enter username for account to be deleted");
            				String userToDelete = consoleIn.nextLine();
            				String result = systemData.deleteUser(userToDelete);
            				if(result.equals("success")){
            					System.out.println("User: " + userToDelete + " deleted from system");
            				}
            				else{
            					System.out.println(result);
            				}
            				break;

            			case 3:
            				System.out.println("Reset password for a user account");
            				System.out.println("User Accounts");
            				System.out.println(systemData.userList());
            				System.out.println("Enter username for account to have its password reset");
            				String userToReset = consoleIn.nextLine();
            				System.out.println("Enter new password for account");
            				String newPassword2 = consoleIn.nextLine();
            				String result2 = systemData.changePassword(userToReset, newPassword2);
            				if(result2.equals("success")){
            					System.out.println("User: " + userToReset + " password set to: " + newPassword2);
            				}
            				else{
            					System.out.println(result2);
            				}

            				break;

            			case 4:
            				System.out.println("User Accounts");
            				System.out.println(systemData.userList());
            				break;

            			case 5:
            				System.out.println("Display schedule of a user");
            				System.out.println("User Accounts");
            				System.out.println(systemData.userList());
            				System.out.println("Enter username for account to view its schedule");
            				String userToView = consoleIn.nextLine();
            				System.out.println(systemData.printSchedule(userToView));
            				break;

            			case 6:
            				System.out.println("Add an event to a users schedule");
            				System.out.println("User Accounts");
            				System.out.println(systemData.userList());
            				System.out.println("Enter username for account to add an event");
            				String userToAddEvent = consoleIn.nextLine();
            				System.out.println("Enter month");
            				int month = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter day");
            				int day = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter year");
            				int year = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter event title");
            				String title = consoleIn.nextLine();
            				System.out.println(systemData.addUserEvent(userToAddEvent, month, day, year, title));
            				break;

            			case 7:
            				System.out.println("Edit an event from a users schedule");
            				System.out.println("User Accounts");
            				System.out.println(systemData.userList());
            				System.out.println("Enter username for account to edit an event");
            				String userToEditEvent = consoleIn.nextLine();
            				String schedule = systemData.printSchedule(userToEditEvent);
            				if(schedule.equals("Invalid user account")){
            					System.out.println(schedule);
            					break;
            				}
            				System.out.println(schedule);
            				System.out.println("\nEnter number of the event to edit");
            				int eventNumber = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter month");
            				int month2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter day");
            				int day2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter year");
            				int year2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter event title");
            				String title2 = consoleIn.nextLine();
            				System.out.println(systemData.editUserEvent(userToEditEvent, eventNumber, month2, day2, year2, title2));
            				break;

            			case 8:
            				System.out.println("Delete an event from a users schedule");
            				System.out.println("User Accounts");
            				System.out.println(systemData.userList());
            				System.out.println("Enter username for account to delete an event");
            				String userToDeleteEvent = consoleIn.nextLine();
            				String schedule2 = systemData.printSchedule(userToDeleteEvent);
            				if(schedule2.equals("Invalid user account")){
            					System.out.println(schedule2);
            					break;
            				}
            				System.out.println(schedule2);
            				System.out.println("\nEnter number of the event to delete");
            				int eventNumber2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println(systemData.deleteUserEvent(userToDeleteEvent, eventNumber2));
            				break;

            			case 9:
            				isRunning = false;
            				System.out.println("Thank you for using the client program, goodbye\n");
            				break;

            			default:
            				System.out.println("Invalid Selection - Program Exiting\n");
            				isRunning = false;
            				break;
            		}
            	}
            	consoleIn.close();
            	return;
            }
            if (auth.equals("user")){
            	boolean isRunning = true;
            	while (isRunning){
            		System.out.println("\nUser Menu for account: " + userName + "\n");
            		System.out.println("1: Display schedule");
            		System.out.println("2: Add an event to schedule");
            		System.out.println("3: Edit an event from schedule");
            		System.out.println("4: Delete an event from schedule");
            		System.out.println("5: Reset password");
            		System.out.println("6: Exit program\n");
            		System.out.println("Enter the number of your selection:");
            		int selection = consoleIn.nextInt();
            		consoleIn.nextLine();
            		switch (selection){
            			case 1:
            				System.out.println("Display schedule for account: " + userName);
            				System.out.println(systemData.printSchedule(userName));
            				break;

            			case 2:
            				System.out.println("Add an event to schedule for account: " + userName);
            				System.out.println("Enter month");
            				int month = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter day");
            				int day = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter year");
            				int year = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter event title");
            				String title = consoleIn.nextLine();
            				systemData.addUserEvent(userName, month, day, year, title);
            				System.out.println("\nEvent added");
            				break;

            			case 3:
            				System.out.println("Edit an event from schedule for account: " + userName);
            				System.out.println(systemData.printSchedule(userName));
            				System.out.println("\nEnter number of the event to edit");
            				int eventNumber = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter month");
            				int month2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter day");
            				int day2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter year");
            				int year2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println("Enter event title");
            				String title2 = consoleIn.nextLine();
            				System.out.println(systemData.editUserEvent(userName, eventNumber, month2, day2, year2, title2));
            				break;

            			case 4:
            				System.out.println("Delete an event from schedule for account: " + userName);
            				System.out.println(systemData.printSchedule(userName));
            				System.out.println("\nEnter number of the event to delete");
            				int eventNumber2 = consoleIn.nextInt();
            				consoleIn.nextLine();
            				System.out.println(systemData.deleteUserEvent(userName, eventNumber2));
            				break;

            			case 5:
            				System.out.println("Reset password for account: " + userName);
            				System.out.println("Enter new password for account");
            				String newPassword = consoleIn.nextLine();
            				systemData.changePassword(userName, newPassword);
            				System.out.println("User: " + userName + " password set to: " + newPassword);
            				break;

            			case 6:
            				isRunning = false;
            				System.out.println("Thank you for using the client program, goodbye\n");
            				break;

            			default:
            				System.out.println("Invalid Selection - Program Exiting\n");
            				isRunning = false;
            				break;
            		}
            	}
            }
            consoleIn.close();
        }
        catch (Exception e){
            System.err.println("Schedule Server Exception:");
            e.printStackTrace();
        }

    }
}
