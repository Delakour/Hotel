package BL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dal.Reports;
import Entetis.Guest;

public class GuestManagment {
	
	public static final String ACTIVE_CUSTOMERS_REPORT = "Report Active Customers.txt";
	private ArrayList<Guest> arrayGuest;
	private OrdersManagment orderManagment;

	public GuestManagment(OrdersManagment orderManagment) {
		this.orderManagment = orderManagment;
		arrayGuest = new ArrayList<Guest>();
	}

	public boolean addGuest(Guest g) {
		if (g.getPerson().getID() != null && g.getPerson().getFirstName() != null && g.getPerson().getLastName() != null
				&& g.getPerson().getMobilePhone() != null)
			return arrayGuest.add(g);
		else
			return false;
	}

	public Optional<Guest> searchGuestByID(String ID) {
		return arrayGuest.stream().filter(g -> g.getPerson().getID().equals(ID)).findAny();
	}

	public Optional<List<Guest>> searchGuestByName(String firstName, String lastName) {
		List<Guest> list = arrayGuest.stream().filter(g ->
			   g.getPerson().getFirstName().equals(firstName) 
		&& g.getPerson().getLastName().equals(lastName)).toList();
		return list.isEmpty() ? Optional.empty(): Optional.of(list);
	}

	public Optional<List<Guest>> searchGuestByName(String name) {
		List<Guest> list = arrayGuest.stream().filter(g -> 
		g.getPerson().getFirstName().equals(name) 
		|| g.getPerson().getLastName().equals(name)).toList();
		return list.isEmpty() ? Optional.empty(): Optional.of(list);

	}

	public Optional<Guest> searchGuestByPhone(String phone) {
		Optional<Guest> og = arrayGuest.stream().filter(g ->
		g.getPerson().getMobilePhone().equals(phone)).findAny();
		return og;
	}

	public boolean deleteGuset(Guest g) {
		if (!orderManagment.searchOrderByGuest(g).isPresent()) {
			return arrayGuest.remove(g);
			
		}
		return false;
	}

	public void printGuestByName(String firstName, String lastName) {
		searchGuestByName(firstName, lastName).get().forEach(System.out::println);
	}

	public void printGuestByID(String ID) {
		System.out.println(searchGuestByID(ID).get());
	}

	public void printGuests() {
		arrayGuest.forEach(System.out::println);
	}

	public void readReportActiveCustomer() {
		String filePath = Reports.PATH + ACTIVE_CUSTOMERS_REPORT;
		Reports.readReport(filePath);
	}

	public void writeReportActiveCustomers() {
		String filePath = Reports.PATH + ACTIVE_CUSTOMERS_REPORT;
		List<Guest> listGuest = arrayGuest.stream().filter(g -> orderManagment.searchOrderByGuest(g).isPresent()).toList();
		ArrayList<Guest> list = new ArrayList<Guest>(listGuest);
		Reports.createReport(filePath, list);
	}
}
