package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import BL.*;
import Entetis.*;

public class Hotel {
	public static Scanner in;
	private String name;
	private Address address;
	private String phone;
	private int maxFloor;
	private static Hotel instance;
	private RoomsManagment roomManagment;
	private GuestManagment guestManagment;
	private OrdersManagment orderManagment;
	
	

	static {
		in = new Scanner(System.in);
	}

	private Hotel() {
		roomManagment = new RoomsManagment();
		orderManagment = new OrdersManagment();
	}

	public static Hotel instance() {
		if (instance == null) {
			instance = new Hotel();
			instance.guestManagment = new GuestManagment(instance.orderManagment);
		}
		return instance;
	}

	public static Hotel instance(String name, Address address, String phone, int maxFloor) {
		instance();
		instance.setName(name);
		instance.setAddress(address);
		instance.setPhone(phone);
		instance.setMaxFloor(maxFloor);
		instance.initTest();
		return instance;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public int getMaxFloor() {
		return maxFloor;
	}

	public void setName(String name) {
		if (instance.getName() == null && name != null && !name.trim().isEmpty())
			this.name = name;
	}

	public void setAddress(Address address) {
		if (instance.getAddress() == null)
			this.address = address;
	}

	public void setPhone(String phone) {
		if (instance.getPhone() != null)
			return;
		if (phone.length() != 10)
			return;
		if (phone.charAt(0) != '0')
			return;
		int number;
		number = Character.getNumericValue(phone.charAt(1));
		if (number < 0 || number > 9)
			return;

		if (phone.charAt(2) != '-')
			return;

		for (int i = 3; i < phone.length(); i++) {
			number = Character.getNumericValue(phone.charAt(i));
			if (number < 0 || number > 9)
				return;
		}
		this.phone = phone;
	}

	private void setMaxFloor(int maxFloor) {
		if (maxFloor > 0)
			this.maxFloor = maxFloor;
	}

	private void initTest() {

		Guest g1 = FactoryGuest.createGuest(0, new Person("209860923", "Mihal", "Hazan", "053-4102139"));
		Guest g2 = FactoryGuest.createGuest(0, new Person("324685403", "Jo", "Morali", "054-4508552"));
		Guest g3 = FactoryGuest.createGuest(0, new Person("986326312", "Yossef", "Levi", "055-6771535"));
		Guest g4 = FactoryGuest.createGuest(0, new Person("376994307", "Shuki", "Kado", "050-4100174"));
		Guest g5 = FactoryGuest.createGuest(1, new Person("273595553", "Riki", "Berger", "057-2357653"));

		Hotel.instance().guestManagment.addGuest(g1);
		Hotel.instance().guestManagment.addGuest(g2);
		Hotel.instance().guestManagment.addGuest(g3);
		Hotel.instance().guestManagment.addGuest(g4);
		Hotel.instance().guestManagment.addGuest(g5);
		
		Room r1_1 = new Room(11, 1, roomsLevel.SIMPLE);
		Room r2_1 = new Room(12, 1, roomsLevel.SIMPLE);
		Room r3_1 = new Room(13, 1, roomsLevel.SIMPLE);
		Room r1_2 = new Room(21, 2, roomsLevel.NORMAL);
		Room r2_2 = new Room(22, 2, roomsLevel.NORMAL);
		Room r3_2 = new Room(23, 2, roomsLevel.NORMAL);
		Room r1_3 = new Room(31, 3, roomsLevel.NORMAL);
		Room r2_3 = new Room(32, 3, roomsLevel.NORMAL);
		Room r3_3 = new Room(33, 3, roomsLevel.NORMAL);
		Room r1_4 = new Room(41, 4, roomsLevel.SUITE);
		Room r2_4 = new Room(42, 4, roomsLevel.SUITE);
		Room r3_4 = new Room(43, 4, roomsLevel.SUITE);

		Hotel.instance().roomManagment.addRoom(r1_1);
		Hotel.instance().roomManagment.addRoom(r2_1);
		Hotel.instance().roomManagment.addRoom(r3_1);
		Hotel.instance().roomManagment.addRoom(r1_2);
		Hotel.instance().roomManagment.addRoom(r2_2);
		Hotel.instance().roomManagment.addRoom(r3_2);
		Hotel.instance().roomManagment.addRoom(r1_3);
		Hotel.instance().roomManagment.addRoom(r2_3);
		Hotel.instance().roomManagment.addRoom(r3_3);
		Hotel.instance().roomManagment.addRoom(r1_4);
		Hotel.instance().roomManagment.addRoom(r2_4);
		Hotel.instance().roomManagment.addRoom(r3_4);
		
		
		Order o1 = new Order(g5, r3_4, LocalDate.of(2024, 10, 3), 1);
		Order o2 = new Order(g1, r3_4, LocalDate.of(2024, 2, 4), 4);
		Order o3 = new Order(g5, r2_2, LocalDate.of(2024, 2, 4), 2);
		Order o4 = new Order(g2, r3_2, LocalDate.of(2024, 5, 3), 14);
		Order o5 = new Order(g4, r1_4, LocalDate.of(2024, 1, 30), 7);
		Order o6 = new Order(g3, r1_3, LocalDate.of(2024, 3, 11), 4);
		Order o7 = new Order(g4, r2_4, LocalDate.of(2025, 3, 10), 43);
		Order o8 = new Order(g4, r3_2, LocalDate.of(2024, 3, 7), 2);

		Hotel.instance().orderManagment.addOrder(o1);
		Hotel.instance().orderManagment.addOrder(o2);
		Hotel.instance().orderManagment.addOrder(o3);
		Hotel.instance().orderManagment.addOrder(o4);
		Hotel.instance().orderManagment.addOrder(o5);
		Hotel.instance().orderManagment.addOrder(o6);
		Hotel.instance().orderManagment.addOrder(o7);
		Hotel.instance().orderManagment.addOrder(o8);
		
	}
	
	public boolean addOrder(Guest g, roomsLevel level, int floor, LocalDate comingDate, int days) {
		if (floor > getMaxFloor() || floor < 0) {
			System.out.println("cannot add this order because the floor is'nt in range");
			return false;
		}

		// search room
		Optional<List<Room>> r_floor_op = roomManagment.searchRoomByFloor(floor);
		List<Room> r_floor = new ArrayList<Room>();
		
		if (r_floor_op.isPresent()) {
			r_floor = r_floor_op.get().stream()
					.filter(r -> !orderManagment.isRoomActiveInDate(r, comingDate, comingDate.plusDays(days))).toList();
		}
		
		Optional<List<Room>> r_level_op = roomManagment.searchRoomByLevel(level);
		List<Room> r_level = new ArrayList<Room>();
		
		if (r_level_op.isPresent()) {
			r_level = r_level_op.get().stream()
					.filter(r -> !orderManagment.isRoomActiveInDate(r, comingDate, comingDate.plusDays(days))).toList();
		}

		// initialize members
		int numberRoom = 0;
		Optional<Room> roomAdd = null;
		boolean found = false;
		List<Room> combList = new ArrayList<Room>();

		// search a room with both conditions
		for (Room r : r_floor) {
			if (r_level.contains(r)) {
				found = true;
				combList.add(r);
			}
		}

		if (found) {
			// let the guest choose any room he would want
			combList.forEach(System.out::println);
			System.out.println("choose your room number: ");
			numberRoom = in.nextInt();
		}

		// if not found a room with both conditions,
		// comb the rooms with 1 of the condition to the combList
		else {
			System.out.println("Sorry, there is no room with all the condtion required! ☹️");
			for (Room r : r_floor) {
				if (!r_level.contains(r))
					combList.add(r);
			}

			for (Room r : r_level) {
				if (!r_floor.contains(r))
					combList.add(r);
			}

			// if combList is steel empty- there is no room with the conditions required
			if (combList.isEmpty()) {
				System.out.println("choose other parameters");
				return false;
			}

			// else, the guest can now choose one of the rooms found
			System.out
					.println("here are all our abled rooms for you. choose which room number you would like to order:");
			combList.forEach(System.out::println);
			numberRoom = in.nextInt();
		}

		roomAdd = roomManagment.searchRoomByNumber(numberRoom);

		// create the order with the parameters
		Order o = new Order(g, roomAdd.get(), comingDate, days);

		// add the new order the order management
		orderManagment.addOrder(o);
		System.out.println("the price for this order is: " + o.calcPrice() + "\nhave a great day! 😊");
		return true;
	}

	public boolean deleteOrder(Guest g) {
		int room = orderManagment.deleteOrder(g);
		Optional<Room> r = roomManagment.searchRoomByNumber(room);
		if (r.isPresent()) {
			r.get().setActive(false);
			return true;
		}
		return false;
	}

	public void menu() {
		System.out.println("Welcome to the " + this.name + " Hotel\n" + "enter 1 to print the hotel details.\n"
				+ "enter 2 to add an order.\n" + "enter 3 to delete an order.\n" + "enter 4 to print all the orders.\n"
				+ "enter 5 to print the orders by guest.\n" + "enter 6 to print the orders by room.\n"
				+ "enter 7 to get a report on active and inactive rooms.\n" + "enter 8 to get the guests details.\n"
				+ "if you want to exit, click another number.");

		int choice;
		while (true) {
			System.out.println("enter your choice");
			choice = in.nextInt();
			switch (choice) {
			case 1: {
				System.out.println(this.toString());
				break;
			}
			case 2: {
				System.out.println("enter the guest ID:");
				String id = in.next();
				Optional<Guest> op = null;
				if (Person.checkID(id))
					op = guestManagment.searchGuestByID(id);
				else {
					System.out.println("this ID is invalid");
					break;
				}
				Guest g;
				if (op.isPresent())
					g = op.get();
				else {
					System.out.println("Hi! you're a new guest!\n"
							+ " enter your details:\n first- if, you are a business guest, enter your code."
							+ " else enter zero.\n then enter your first name, last name, mobile phone.");
					g = FactoryGuest.createGuest(in.nextInt(),
							(new Person(id, in.nextLine(), in.nextLine(), in.nextLine())));
				}

				System.out.println("enter in which floor you would like to be(max floor=" + maxFloor + "): ");
				int floor = in.nextInt();

				System.out.println("enter in which level of room you would like to be" + "(SIMPLE, NORMAL, SUITE): ");
				roomsLevel level = null;
				try {
					level = roomsLevel.valueOf(in.next().toUpperCase());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					break;
				}

				System.out.println("enter the coming date (year, month, day of month): ");
				LocalDate date = LocalDate.of(in.nextInt(), in.nextInt(), in.nextInt());

				System.out.println("how many days you'll stay in our hotel? ");
				int days = in.nextInt();

				addOrder(g, level, floor, date, days);
				break;
			}
			case 3: {
				System.out.println("enter the guest ID:");
				String id = in.next();
				Optional<Guest> op = null;
				if (Person.checkID(id))
					op = guestManagment.searchGuestByID(id);
				else {
					System.out.println("this ID is invalid");
					break;
				}
				if(op.isPresent())
					deleteOrder(op.get());
				break;
			}
			case 4: {
				orderManagment.printOrders();
				break;
			}
			case 5: {
				System.out.println("enter the guest ID:");
				Guest g = guestManagment.searchGuestByID(in.next()).get();
				orderManagment.printOrdersByGuest(g);
				break;
			}
			case 6: {
				System.out.println("enter the room number: ");
				Room r = roomManagment.searchRoomByNumber(in.nextInt()).get();
				orderManagment.printOrdersByRoom(r);
				break;
			}
			case 7: {
				System.out.println("Rooms Reports:");
				roomManagment.writeReportActiveRooms();
				roomManagment.writeReportInactiveRooms();
				System.out.println("ACTIVE ROOMS");
				roomManagment.readReportActiveRooms();
				System.out.println("INACTIVE ROOMS");
				roomManagment.readReportInactiveRooms();
				break;
			}
			case 8: {
				guestManagment.printGuests();
				break;
			}
			default:
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
}
