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
			System.out.println("6 = Search Records");
			System.out.println("7 = List All Areas / Crags");
			choice = Validator.getLine(sc, "Number of choice: ", "^[0-7]$");

			switch (choice) {
			case "1":
				System.out.println("================================");
				System.out.println(routeList.toString());
				break;
			case "2":
				System.out.println("================================");
				routeId = Validator.getInt(sc, "New Route ID: ");
				routeName = Validator.getLine(sc, "Route Name: ");
				grade = Validator.getLine(sc, "Route Grade: ");
				crag = Validator.getLine(sc, "Crag / Location: ");
				routeList.createRecord(new Route(routeId, routeName, grade, crag));
				break;
			case "3":
				System.out.println("================================");
				routeId = Validator.getInt(sc, "Route id to retrieve: ");
				System.out.println(routeList.retrieveRecordById(routeId));
				break;
			case "4":
				System.out.println("================================");
				routeId = Validator.getInt(sc, "Route ID to update: ");
				routeName = Validator.getLine(sc, "Route Name: ");
				grade = Validator.getLine(sc, "Grade: ");
				crag = Validator.getLine(sc, "Crag / Location: ");
				routeList.updateRecord(new Route(routeId, routeName, grade, crag));
				break;
			case "5":
				System.out.println("================================");
				routeId = Validator.getInt(sc, "Route ID to delete: ");
				System.out.println(routeList.retrieveRecordById(routeId));
				String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					routeList.deleteRecord(routeId);
				}
				break;
			case "6":
				System.out.println("================================");
				System.out.println("Search only recognizes complete matches for any one field. ");
				System.out.println(routeList.searchAllRecords(Validator.getLine(sc, "Enter a term to search for: ")).toString());
				break;
			case "7":
				System.out.println("================================");
				System.out.println("All crags with climbs recorded: ");
				System.out.println(routeList.listAllCrags());
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
