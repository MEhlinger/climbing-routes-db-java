package model;

import java.util.List;

/**
 * @author Marshall Ehlinger
 * @version 0.0.1
 *
 */
public interface IRouteDAO {

	void createRecord(Route route);

	Route retrieveRecordById(int id);

	List<Route> retrieveAllRecords();

	void updateRecord(Route updatedRoute);

	void deleteRecord(int routeId);

	void deleteRecord(Route route);

	String toString();

}