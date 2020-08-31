package Rails_IL;

public class Station {
	
	private String location;
	private String time;
	
	public Station(String location, String time) {
		this.location = location;
		this.time = time;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	//for example "10:47" ==> 1047
	public int getTimeAsInt() {
		String temp = time.substring(0, 2) + time.substring(3, 5);
		return Integer.parseInt(temp);
	}
	
	public boolean compareStationsChronologically(Station station) {
		if(getTimeAsInt() <= station.getTimeAsInt()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(location);
		sb.append(", ");
		sb.append(time);
		sb.append(". ");
		return sb.toString();
		
	}
	
	@Override 
	public boolean equals(Object other) {
		if(!(other instanceof Station)) {
			return false;
		}
		
		Station temp = (Station)other;
		return location.equals(temp.location) && time.equals(temp.time);
	}
}
