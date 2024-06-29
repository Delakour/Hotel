package BL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dal.Reports;
import Entetis.*;
import View.Hotel;

public class OrdersManagment {
	public static final String ORDERS_BY_NUM_DAYS_REPORT = " Report Orders by number of days.txt";
	public static final String ORDERS_BY_DATE_REPORT = " Report Orders by  date.txt";

	private ArrayList<Order> arrayOrder;

	public OrdersManagment() {
		arrayOrder = new ArrayList<Order>();
	}

	public void addOrder(Order o) {
		arrayOrder.add(o);
	}

	public boolean isRoomActiveInDate(Room room, LocalDate come, LocalDate leave) {
		for (Order o : arrayOrder) {
			if (o.getRoom().equals(room) && o.isDateBetweenOrEquals(come, leave))
				return true;
		}
		return false;
	}

	public Optional<List<Order>> searchOrderByGuest(Guest g) {
		List<Order> list = arrayOrder.stream().filter(o -> o.getGuest().equals(g)).toList();
		return list.isEmpty() ? Optional.empty(): Optional.of(list);

	}

	public Optional<List<Order>> searchOrderByRoom(Room r) {
		List<Order> list = arrayOrder.stream().filter(o -> o.getRoom().equals(r)).toList();
		return list.isEmpty() ? Optional.empty(): Optional.of(list);
	}

	public int deleteOrder(Guest g) {
		//get all this guest's orders
		List<Order> o = arrayOrder.stream().filter(o1 -> o1.getGuest().equals(g)).toList();
		
		//id there is no orders corresponded
		if (o.size() == 0) {
			System.out.println("cannot find any order.");
			return 0;
		}
		
		// if the size is exactly 1- the guest has exactly 1 order in process
		Order toDelete = o.get(0);
		
		//if the size is bigger than 1- the guest has more than 1 order in process
		if (o.size() > 1) {
			Order element = null;
			
			//delete all the guest's orders that have been done
			for(int i = 0; i < o.size(); i ++) {
				element = o.get(i);
				if (element.getLeaveDate().isBefore(LocalDate.now())) {
					arrayOrder.remove(element);
				}
			}
			
			o = arrayOrder.stream().filter(o1 -> o1.getGuest().equals(g)).toList();
			
			//print him all the orders he steel have and he can choose to delete
			System.out.println("all your orders in process:");
			o.forEach(System.out::println);
			
			System.out.println("choose which order number you would like to delete");
			int ID = Hotel.in.nextInt();
			
			//search this order
			Optional<Order> op = o.stream().filter(o1 -> o1.getNumOrder() == ID).findFirst();
			if (op.isPresent())
				toDelete = op.get();
			else
				System.out.println("cannot found this order!");
		}
		
		//delete the order chosen by the guest
		arrayOrder.remove(toDelete);
		return toDelete.getRoom().getRoomID();
	}

	public void printOrdersByGuest(Guest g) {
		searchOrderByGuest(g).get().forEach(System.out::println);
	}

	public void printOrdersByRoom(Room r) {
		searchOrderByRoom(r).get().forEach(System.out::println);
	}

	public void printOrders() {
		arrayOrder.forEach(System.out::println);
	}

	public void writeReportOrdersByDays(int numDays) {
		String fileName = Reports.PATH + ORDERS_BY_NUM_DAYS_REPORT;
		List<Order> listOrder = arrayOrder.stream().filter(o -> o.getNumDays() == numDays).toList();
		ArrayList<Order> list = new ArrayList<Order>(listOrder);
		Reports.createReport(fileName, list);
	}

	public void writeReportOrdersByDate(LocalDate date) {
		String fileName = Reports.PATH + ORDERS_BY_DATE_REPORT;
		List<Order> listOrder = arrayOrder.stream().filter(o -> o.getComingDate().equals(date)).toList();
		ArrayList<Order> list = new ArrayList<Order>(listOrder);
		Reports.createReport(fileName, list);
	}
	
	public void readReportOrdersByDays() {
		String fileName = Reports.PATH + ORDERS_BY_NUM_DAYS_REPORT;
		Reports.readReport(fileName);
	}
	
	public void readReportOrdersByDate() {
		String fileName = Reports.PATH + ORDERS_BY_DATE_REPORT;
		Reports.readReport(fileName);
	}
}
