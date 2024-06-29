package Entetis;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings("serial")
public class Order implements Serializable{
	static int numOrders = 1000;
	private int orderID;
	private Guest guest;
	private Room room;
	private LocalDate comingDate;
	private LocalDate leaveDate;
	private int numDays;

	public Order(Guest guest, Room room, LocalDate comingDate, int numDays) {
		numOrders++;
		setOrderID();
		setGuest(guest);
		setRoom(room);
		setComingDate(comingDate);
		setNumDays(numDays);
		setLeaveDate();
	}

	public int getNumOrder() {
		return this.orderID;
	}

	public Guest getGuest() {
		return guest;
	}

	public Room getRoom() {
		return room;
	}

	public LocalDate getComingDate() {
		return comingDate;
	}

	public LocalDate getLeaveDate() {
		return leaveDate;
	}

	public int getNumDays() {
		return numDays;
	}

	private void setOrderID() {
		this.orderID = numOrders;
	}

	public void setGuest(Guest guest) {
		guest.setNumVisit(guest.getNumVisit() + 1);
		this.guest = guest;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setComingDate(LocalDate comingDate) {
		this.comingDate = comingDate;
	}

	public void setLeaveDate() {
		this.leaveDate = this.comingDate.plusDays(numDays);
	}

	public void setNumDays(int numDays) {
		if (numDays > 0)
			this.numDays = numDays;
	}
	public boolean isDateBetweenOrEquals(LocalDate toCheckCome, LocalDate toCheckLeave) {
		Stream<LocalDate> date = this.comingDate.datesUntil(this.leaveDate);
		return date.anyMatch(d -> d.equals(toCheckCome) || d.equals(toCheckLeave));
	}
	public double calcPrice() {
		double price = Room.startPrice;

		if (this.room.getLevel() == roomsLevel.NORMAL)
			price += 150;
		else if (this.room.getLevel() == roomsLevel.SUITE)
			price += 500;

		price *= this.numDays;

		if (this.guest instanceof BussinesGuest)
			price -= price * BussinesGuest.discountPercent / 100;
		return price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(guest, orderID, comingDate, room);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(guest, other.guest) && orderID == other.orderID
				&& Objects.equals(comingDate, other.comingDate) && Objects.equals(room, other.room);
	}

	@Override
	public String toString() {
		return "Order: [orderID=" + orderID + ", guest=" + guest + ", room=" + room + ", comingDate=" + comingDate
				+ ", leaveDate=" + leaveDate + ", numDays=" + numDays + "]";
	}


}