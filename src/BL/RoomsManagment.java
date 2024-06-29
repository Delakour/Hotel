package BL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dal.Reports;
import Entetis.Room;
import Entetis.roomsLevel;
import View.Hotel;

public class RoomsManagment {
	public static final String ACTIVE_ROOMS_REPORT = "Report Active Rooms.txt";
	public static final String INACTIVE_ROOMS_REPORT = "Report Inactive Rooms.txt";

	private ArrayList<Room> arrayRooms;

	public RoomsManagment() {
		arrayRooms = new ArrayList<Room>();
	}

	public void addRoom(Room r) {
		if (r.getFloor() <= Hotel.instance().getMaxFloor() && r.getFloor() > 0)
			arrayRooms.add(r);
		else
			System.out.println("cannot add this room because the floor is'nt in range");
	}

	public Optional<Room> searchRoomByNumber(int number) {
		return arrayRooms.stream().filter(r -> r.getRoomID() == number).findAny();
	}

	public Optional<List<Room>> searchRoomByLevel(roomsLevel level) {
		List<Room> list = arrayRooms.stream().filter(r -> r.getLevel() == level).toList();
		return list.isEmpty() ? Optional.empty(): Optional.of(list);
	}

	public Optional<List<Room>> searchRoomByFloor(int floor) {
		List<Room> list = arrayRooms.stream().filter(r -> r.getFloor() == floor).toList();
		return list.isEmpty() ? Optional.empty(): Optional.of(list);
	}

	public void printRooms() {
		arrayRooms.forEach(System.out::println);
	}

	public void printRoomByNumber(int number) {
		System.out.println(searchRoomByNumber(number).get());
	}

	public void printRoomByLevel(roomsLevel level) {
		searchRoomByLevel(level).get().forEach(System.out::println);
	}

	public void printRoomByFloor(int floor) {
		searchRoomByFloor(floor).get().forEach(System.out::println);
	}

	public void writeReportActiveRooms() {
		String fileName = Reports.PATH + ACTIVE_ROOMS_REPORT;
		List<Room> listRooms = arrayRooms.stream().filter(r -> r.isActive()).toList();
		ArrayList<Room> list = new ArrayList<Room>(listRooms);
		Reports.createReport(fileName, list);
	}

	public void writeReportInactiveRooms() {
		String fileName = Reports.PATH + INACTIVE_ROOMS_REPORT;
		List<Room> listRooms = arrayRooms.stream().filter(r -> !r.isActive()).toList();
		ArrayList<Room> list = new ArrayList<Room>(listRooms);
		Reports.createReport(fileName, list);
	}

	public void readReportActiveRooms() {
		String fileName = Reports.PATH + ACTIVE_ROOMS_REPORT;
		Reports.readReport(fileName);
	}

	public void readReportInactiveRooms() {
		String fileName = Reports.PATH + INACTIVE_ROOMS_REPORT;
		Reports.readReport(fileName);
	}
}
