// Nicolas Stoian

import java.util.*;

public class Account {
	protected String userName;
	protected String password;
	protected Vector<Event> eventList = new Vector<Event>();

	public Account(String un, String p){
		userName = un;
		password = p;
	}

	public void setPassword(String newPassword){
		password = newPassword;
	}

	public String printEventList(){
		String list = (userName + "'s schedule\n");
		for (int i=0; i<eventList.size(); i++){
			list += (i + ": " + eventList.elementAt(i).printEvent() + "\n");
		}
		return list;
	}

	public void addEvent(int month, int day, int year, String title){
		eventList.add(new Event(month, day, year, title));
	}

	public String editEvent(int eventNumber, int month, int day, int year, String title){
		if (eventNumber < 0 || eventNumber > (eventList.size()-1)){
			return "Invalid event number, no change made";
		}
		else{
			eventList.elementAt(eventNumber).edit(month, day, year, title);
			return "Event edited";
		}
	}

	public String deleteEvent(int eventNumber){
		if (eventNumber < 0 || eventNumber > (eventList.size()-1)){
			return "Invalid event number, no change made";
		}
		else{
			eventList.removeElementAt(eventNumber);
			return "Event deleted";
		}
	}
}
