// Nicolas Stoian

import java.util.*;

public class Schedule implements ScheduleInterface{

	private HashMap<String, Account> accounts = new HashMap<String, Account>();

	public String authenticate(String userName, String password){
		if (userName.equals("admin") && password.equals("admin")){
			return "admin";
		}
		else if (accounts.containsKey(userName)){
			Account account = accounts.get(userName);
			if (password.equals(account.password)){
				return "user";
			}
			else{
				return "fail";
			}
		}
		else{
			return "fail";
		}
	}

	public void addUser(String userName, String password){
		accounts.put(userName, new Account(userName, password));
	}

	public String deleteUser(String userName){
		if(accounts.containsKey(userName)){
			accounts.remove(userName);
			return "success";
		}
		else{
			return "Invalid user account";
		}
	}

	public String userList(){
		Set<String> users = accounts.keySet();
		return users.toString();
	}

	public String changePassword(String userName, String newPassword){
		if(accounts.containsKey(userName)){
			accounts.get(userName).setPassword(newPassword);
			return "success";
		}
		else{
			return "Invalid user account";
		}
	}

	public String printSchedule(String userName){
		if(accounts.containsKey(userName)){
			return accounts.get(userName).printEventList();
		}
		else{
			return "Invalid user account";
		}
	}

	public String addUserEvent(String userName, int month, int day, int year, String title){
		if(accounts.containsKey(userName)){
			accounts.get(userName).addEvent(month, day, year, title);
			return "Event added";
		}
		else{
			return "Invalid user account, no change made";
		}
	}

	public String editUserEvent(String userName, int eventNumber, int month, int day, int year, String title){
		if(accounts.containsKey(userName)){
			return accounts.get(userName).editEvent(eventNumber, month, day, year, title);
		}
		else{
			return "Invalid user account, no change made";
		}
	}

	public String deleteUserEvent(String userName, int eventNumber){
		if(accounts.containsKey(userName)){
			return accounts.get(userName).deleteEvent(eventNumber);
		}
		else{
			return "Invalid user account, no change made";
		}
	}
}
