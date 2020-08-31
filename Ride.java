package Rails_IL;

import java.util.ArrayList;

public class Ride {
	
	private ArrayList<Station> journey;
	
	
	public Ride() {
		journey = new ArrayList<Station>();
	}
	
	public Station getDepartureStation() {
		return journey.get(0);
	}
	
	public Station getArrivalStation() {
		return journey.get((journey.size() - 1));
	}
	
	public void addStation(Station station) {
		journey.add(station);
	}
	
	public boolean stationsLocationExist(String startLocation, String endLocation) {
		if(journey.size() == 0) {
			return false;
		}
		
		for(int i = 0; i < journey.size() - 1; i++) {
			if(journey.get(i).getLocation().equals(startLocation)) {
				for(int j = i + 1; j < journey.size(); j++) {
					if(journey.get(j).getLocation().equals(endLocation)) { 
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private int getIndexOfLocation(String location) {
		if(journey.size() == 0) {
			return -1;
		}
		for(int i = 0; i < journey.size(); i++) {
			if(journey.get(i).getLocation().equals(location)) {
				return i;
			}
		}
		return -1;
	}
	
	public Station getStationByLocation(String location) {
		int index = getIndexOfLocation(location);
		if(index == -1) {
			return null;
		}
		return journey.get(index);
	}
	
	public Ride createSubRide(String startLocation, String endLocation) {
		if(!stationsLocationExist(startLocation, endLocation)) {
			return null;
		}
		
		Ride result = new Ride();
		for(int i = getIndexOfLocation(startLocation); i <= getIndexOfLocation(endLocation); i++) {
			result.addStation(journey.get(i));
		}
		
		return result;
	}
	
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < journey.size(); i++) {
			sb.append(journey.get(i).toString());
		}
		
		return sb.toString();
	}
}
