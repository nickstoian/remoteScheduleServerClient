// Nicolas Stoian

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ScheduleInterface extends Remote{

	String authenticate(String userName, String password) throws RemoteException;
	void addUser(String userName, String password) throws RemoteException;
	String deleteUser(String userName) throws RemoteException;
	String userList() throws RemoteException;
	String changePassword(String userName, String newPassword) throws RemoteException;
	String printSchedule(String userName) throws RemoteException;
	String addUserEvent(String userName, int month, int day, int year, String title) throws RemoteException;
	String editUserEvent(String userName, int eventNumber, int month, int day, int year, String title) throws RemoteException;
	String deleteUserEvent(String userName, int eventNumber) throws RemoteException;
}
