package model;

import java.util.List;

/**
 * @author Marshall Ehlinger
 * @version 0.0.1
 *
 */
public interface IRouteDAO {

	/**
	 * Creates a new record for a given rock climb route
	 * 
	 * @param route A new Route instance
	 */
	void createRecord(Route route);

	/**
	 * Retrieves a route record
	 * 
	 * @param id The id number of the record to fetch
	 * @return The route object with the specified routeId
	 */
	Route retrieveRecordById(int id);

	/**
	 * Retrieves all records as a list of Route instances
	 * 
	 * @return A list of Route instances
	 */
	List<Route> retrieveAllRecords();

	/**
	 * Updates a given route with a new details
	 * 
	 * @param updatedRoute The new route object whose details will write over the db entry, so long as they have the same id.
	 */
	void updateRecord(Route updatedRoute);

	
	/**
	 * Deletes a single record, specified by id number
	 * 
	 * @param routeId The id value of the route to delete
	 */
	void deleteRecord(int routeId);

	/**
	 * Deletes a single record, specified by passing a reference to that particular Route object
	 * 
	 * @param route The Route instance to delete. Must exist.
	 */
	void deleteRecord(Route route);
	
	/**
	 * Takes a search string, searches all relevant fields in every entry for match.
	 * 
	 * @param searchString The string to search for among the name / grade / crag
	 * @return a list of all routes where the searchString was matched in entirety
	 */
	List<Route> searchAllRecords(String searchString);
	
	/**
	 * Lists all crags among all entries as a concatenated string
	 * 
	 * @return a string listing all crags among entries
	 */
	String listAllCrags();

	/**
	 * Converts all records into a concatenated string
	 * 
	 * @return A string, obviously.
	 */
	String toString();

}