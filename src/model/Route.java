package model;

/**
 * Route class represents a single rock climbing route.
 * 
 * @author Marshall Ehlinger
 * @version 0.0.1
 *
 */
public class Route {

	private int routeId;
	private String routeName;
	private String grade;
	private String crag;

	public Route() {
		routeId = 0;
		routeName = "";
		grade = "";
		crag = "";
	}

	public Route(int routeId, String routeName, String grade, String crag) {
		this.routeId = routeId;
		this.routeName = routeName;
		this.grade = grade;
		this.crag = crag;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}


	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCrag() {
		return crag;
	}

	public void setCrag(String crag) {
		this.crag = crag;
	}

	@Override
	public String toString() {
		return String.format("%5d : %s, %s, %s", this.getRouteId(), this.getRouteName(),
				this.getGrade(), this.getCrag());
	}
}