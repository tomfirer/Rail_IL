package Rails_IL;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean toContinue = true;
		ArrayList<Ride> rides = new ArrayList<Ride>();
		
		do {
			System.out.println("Type '1' to set ride, type '2' to view rides, type '3' to search a ride, type '9' to exit menu.");
			int keyNum = input.nextInt();
			input.nextLine(); //clear the scanner buffer
			switch (keyNum) {
			case 1:
				//Adding departure station
				Ride rideEx = new Ride();
				System.out.println("enter departure location");
				String depLocation = input.nextLine();
				System.out.println("enter daparture time");
				String depTime = input.nextLine();
				rideEx.addStation(new Station(depLocation, depTime));
				
				//Adding midway stations
				boolean toContinue2 = true;
				do {
					System.out.println("would you like to add midway stations? y/n");
					if(input.nextLine().equals("y")) {
						System.out.println("enter stopping location");
						String midLocation = input.nextLine();
						System.out.println("enter stopping time");
						String midTime = input.nextLine();
						rideEx.addStation(new Station(midLocation, midTime));
					} else {
						toContinue2 = false;
					}
					
				} while (toContinue2);

				//Adding final station
				System.out.println("enter arrival location");
				String finLocation = input.nextLine();
				System.out.println("enter arrival time");
				String finTime = input.nextLine();
				rideEx.addStation(new Station(finLocation, finTime));
				
				rides.add(rideEx);
				System.out.println("A ride has been created!\n");
				break;

			case 2:
				System.out.println("\nYou've chosen to display existent rides.");
				sortRideArrayList(rides);
				printRideArrayList(rides);
				break;
				
			case 3:
				System.out.println("\nYou've decided to search for a ride.");
				System.out.println("Enter start station location.");
				String start = input.nextLine();
				System.out.println("Enter time for start station");
				String time = input.nextLine();
				System.out.println("Enter end station.");
				String end = input.nextLine();
				printRideArrayList(searchRides(rides, new Station(start, time), end));
				break;

			case 9:
				System.out.println("You've chosen to exit the menu.");
				toContinue = false;
				break;
				
			default:
				System.out.println("Invalid input!");
			}
			
		} while (toContinue);
		
		input.close();
	}

	
	//sub method for sortArrsyList()
	public static void swap(ArrayList<Ride> arr, int i, int j) {
		Ride temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
	}
	
	public static void sortRideArrayList(ArrayList<Ride> arr) {
		boolean hasChanged = true;
		
		for(int i = arr.size() - 1; i > 0 && hasChanged; i--) {
			hasChanged = false;
			for(int j = 0; j < i; j++) {
				if(arr.get(j).getDepartureStation().getTimeAsInt() > arr.get(j + 1).getDepartureStation().getTimeAsInt()) {
					swap(arr, j, j + 1);
					hasChanged = true;
				}
			}
		}
	}
	
	public static void printRideArrayList(ArrayList<Ride> arr) {
		if(arr.size() == 0) {
			System.out.println("Sorry. No rides to print.");
		}
		for(int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i).toString() + "\n");
		}
		System.out.println();
	}
	

	public static ArrayList<Ride> searchRides(ArrayList<Ride> arr, Station startStation, String endLocation) {
		int counter = 0;
		ArrayList<Ride> result = new ArrayList<Ride>();
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).stationsLocationExist(startStation.getLocation(), endLocation)) {
				if(startStation.compareStationsChronologically(arr.get(i).getStationByLocation(startStation.getLocation()))) {
					if(counter < 3) {
						result.add(arr.get(i).createSubRide(startStation.getLocation(), endLocation));
						counter++;
					}
				}
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}




