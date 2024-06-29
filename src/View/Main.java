package View;

import Entetis.*;

public class Main {

	public static void main(String[] args) {

		Hotel.instance("Hilton", new Address("Tel Aviv", "Rozen", 15), "02-6432165", 4);

		Hotel.instance().menu();
	}
}
