package lifantou.com.utils;

public class MapLocation {
	private double lat;
	private double lng;
	public MapLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MapLocation(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
}
