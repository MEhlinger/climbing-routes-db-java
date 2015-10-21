package viewui;

import java.util.Scanner;

import model.Route;
import model.IRouteDAO;
import model.datastore.mysql.RouteDAO;

/**
 * @author Marshall Ehlinger
 * @version 0.0.1
 * 
 */
public class RouteRecorderMenuApp {

	IRouteDAO routeList = new RouteDAO();
	Scanner sc = new Scanner(System.in);

	public RouteRecorderMenuApp() {
		menuLoop();
	}

	private void menuLoop() {
		int routeId;
		String routeName, grade, crag;
		String choice = "1";
		while (!choice.equals("0")) {
			System.out.println("\nRock Route App");
			System.out.println("0 = Quit");
			System.out.println("1 = List All Records");
			System.out.println("2 = Create New Record");
			System.out.println("3 = Retrieve Record");
			System.out.println("4 = Update Record");
			System.out.println("5 = Delete Record");
			choice = Validator.getLine(sc, "Number of choice: ", "^[0-5]$");

			switch (choice) {
			case "1":
				System.out.println(routeList.toString());
				break;
			case "2":
				routeId = Validator.getInt(sc, "New Route ID: ");
				routeName = Validator.getLine(sc, "Route Name: ");
				grade = Validator.getLine(sc, "Route Grade: ");
				crag = Validator.getLine(sc, "Crag / Location: ");
				routeList.createRecord(new Route(routeId, routeName, grade, crag));
				break;
			case "3":
				routeId = Validator.getInt(sc, "Route id to retrieve: ");
				System.out.println(routeList.retrieveRecordById(routeId));
				break;
			case "4":
				routeId = Validator.getInt(sc, "Route ID to update: ");
				routeName = Validator.getLine(sc, "Route Name: ");
				grade = Validator.getLine(sc, "Grade: ");
				crag = Validator.getLine(sc, "Crag / Location: ");
				routeList.updateRecord(new Route(routeId, routeName, grade, crag));
				break;
			case "5":
				routeId = Validator.getInt(sc, "Route ID to delete: ");
				System.out.println(routeList.retrieveRecordById(routeId));
				String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					routeList.deleteRecord(routeId);
				}
				break;
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new RouteRecorderMenuApp();
	}
}
