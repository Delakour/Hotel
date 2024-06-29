package Entetis;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class Person implements Serializable {
	private String ID;
	private String firstName;
	private String lastName;
	private String mobilePhone;

	public Person(String ID, String firstName, String lastName, String mobilePhone) {
		setID(ID);
		setFirstName(firstName);
		setLastName(lastName);
		setMobilePhone(mobilePhone);
	}

	public String getID() {
		return this.ID;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setID(String ID) {
		if(checkID(ID))
			this.ID = ID;
	}

	public void setFirstName(String firstName) {
		if (firstName != null && !firstName.trim().isEmpty())
			this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		if (lastName != null && !lastName.trim().isEmpty())
			this.lastName = lastName;
	}

	public void setMobilePhone(String mobilePhone) {
		if (mobilePhone.length() != 11)
			return;
		if (mobilePhone.charAt(0) != '0' || mobilePhone.charAt(1) != '5')
			return;

		int number;
		number = Character.getNumericValue(mobilePhone.charAt(2));
		if (number < 0 || number > 9)
			return;

		if (mobilePhone.charAt(3) != '-')
			return;

		for (int i = 4; i < mobilePhone.length(); i++) {
			number = Character.getNumericValue(mobilePhone.charAt(i));
			if (number < 0 || number > 9)
				return;
		}
		this.mobilePhone = mobilePhone;
	}

	public static boolean checkID(String ID) {
		if (ID.length() != 9)
			return false;
		int bikoret = 0;
		int sum = 0, sumID = 0, number;

		for (int i = 0; i < 8; i++) {
			number = Character.getNumericValue(ID.charAt(i));
			if (number >= 0 && number <= 9) {
				if (i % 2 != 0) {
					sum = 2 * number;
				} else {
					sum = number;
				}
			} else {
				return true;
			}

			if (sum > 9) {
				sum = ((sum % 10) + (sum / 10));
			}
			sumID += sum;
			sum = 0;
		}
		bikoret = (10 - (sumID % 10));
		if (bikoret == 10)
			bikoret = 0;

		if (bikoret == Character.getNumericValue(ID.charAt(ID.length() - 1))) 
			return true;
		return false;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(ID, other.ID);
	}

	@Override
	public String toString() {
		return "Person: [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", mobilePhone="
				+ mobilePhone + "]";
	}

}
