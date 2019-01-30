// Nicolas Stoian

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ScheduleServer{

	public static void main(String[] args) {
        try {
        	String name = "data";
            ScheduleInterface systemData = new Schedule();
            ScheduleInterface stub = (ScheduleInterface) UnicastRemoteObject.exportObject(systemData, 0);
            Registry registry = LocateRegistry.createRegistry(24680);
            registry.rebind(name, stub);
            System.out.println("Schedule Server Online");
        } catch (Exception e) {
            System.err.println("Schedule Server Exception:");
            e.printStackTrace();
        }
    }
}