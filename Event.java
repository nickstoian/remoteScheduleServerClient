// Nicolas Stoian

public class Event {
	protected int month;
	protected int day;
	protected int year;
	protected String title;

	public Event(int m, int d, int y, String t) {
		month = m;
		day = d;
		year = y;
		title = t;
	}

	public String printEvent(){
		return (month + "/" + day + "/" + year + "   " + title);
	}

	public void edit(int m, int d, int y, String t){
		month = m;
		day = d;
		year = y;
		title = t;
	}
}
