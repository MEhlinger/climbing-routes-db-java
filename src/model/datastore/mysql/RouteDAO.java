package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;

import model.Route;
import model.IRouteDAO;

/**
 * @author Marshall Ehlinger
 * @version 0.0.1
 *
 */
public class RouteDAO implements IRouteDAO {
	
	protected final static boolean DEBUG = true;

	@Override
	public void createRecord(Route route) {
		final String QUERY = "insert into route (routeId, routeName, grade, crag) VALUES (null, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, route.getRouteName());
			stmt.setString(2, route.getGrade());
			stmt.setString(3, route.getCrag());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public Route retrieveRecordById(int routeId) {
		final String QUERY = "select routeId, routeName, grade, crag from route where routeId =" + routeId;
		// final String QUERY = "select empId, lastName, firstName, homePhone,
		// salary from employee where empId = ?";
		Route emp = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				emp = new Route(rs.getInt("routeId"), rs.getString("routeName"),
						rs.getString("grade"), rs.getString("crag"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordById SQLException: " + ex.getMessage());
		}

		return emp;
	}

	@Override
	public List<Route> retrieveAllRecords() {
		final List<Route> myList = new ArrayList<>();
		final String QUERY = "select routeId, routeName, grade, crag from route";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Route(rs.getInt("routeId"), rs.getString("routeName"),
						rs.getString("grade"), rs.getString("crag")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	@Override
	public void updateRecord(Route updatedRoute) {
		final String QUERY = "update route set routeName=?, grade=?, crag=? where routeId=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedRoute.getRouteName());
			stmt.setString(2, updatedRoute.getGrade());
			stmt.setString(3, updatedRoute.getCrag());
			stmt.setInt(4, updatedRoute.getRouteId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(int id) {
		final String QUERY = "delete from route where routeId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(Route route) {
		final String QUERY = "delete from route where routeId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, route.getRouteId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public List<Route> searchAllRecords(String searchString) {
		final String QUERY = "select routeId, routeName, grade, crag from route";
		List<Route> routesWithMatches = new ArrayList<>();
		Pattern searchPattern = Pattern.compile(searchString);
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			ResultSet results = stmt.executeQuery(QUERY);
			while (results.next()) {
				Matcher nameMatcher = searchPattern.matcher(results.getString("routeName"));
				Matcher gradeMatcher = searchPattern.matcher(results.getString("grade"));
				Matcher cragMatcher = searchPattern.matcher(results.getString("crag"));
				if (nameMatcher.matches() || gradeMatcher.matches() || cragMatcher.matches()) {
					routesWithMatches.add(new Route(results.getInt("routeId"), results.getString("routeName"), results.getString("grade"), results.getString("crag")));
				}
			}
		} catch (SQLException ex) {
			System.out.println("SQLException : " + ex.getMessage());
		}
		
		return routesWithMatches;
	}
	
	@Override
	public String listAllCrags() {
		final String QUERY = "select crag from route";
		StringBuilder sb = new StringBuilder();
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			ResultSet results = stmt.executeQuery(QUERY);
			List<String> resultsList = new ArrayList<>();
			
			while (results.next()) {
				resultsList.add(results.getString("crag"));	
			}
			
			// Eliminate duplicates.
			// I'm sorry.
			List<String> uniqueCrags = new ArrayList<>(new HashSet<>(resultsList));
			
			for (String cragName : uniqueCrags) {
				sb.append("+	" + cragName + "\n");
			}
			return sb.toString();
		} catch (SQLException ex) {
			System.out.println("listAllCrags SQLException: " + ex.getMessage());
		}
		
		return "Error -- uncaught exception in listAllCrags. This string should never be returned.";
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Route route : retrieveAllRecords()) {
			sb.append(route.toString() + "\n");
		}

		return sb.toString();
	}
}
