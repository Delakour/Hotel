package Entetis;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class Address implements Serializable {
	private String city;
	private String street;
	private int numHouse;
	
	public Address(String city, String street, int numHouse) {
		setCity(city);
		setStreet(street);
		setNumHouse(numHouse);
	}
	
	public String getCity() {
		return this.city;
	}
	public String getStreet() {
		return this.street;
	}
	public int getNumHouse() {
		return this.numHouse;
	}
	
	public void setCity(String city) {
		if(city != null && !city.trim().isEmpty())
			this.city = city;
	}
	public void setStreet(String street) {
		if(street != null && !street.trim().isEmpty())
			this.street = street;
	}	
	public void setNumHouse(int numHouse) {
		if(numHouse > 0)
			this.numHouse = numHouse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, numHouse, street);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(obj instanceof Address) {
			if(((Address)obj).getCity().equals(this.city) && 
					((Address)obj).getStreet().equals(this.street) &&
					((Address)obj).getNumHouse() == this.numHouse)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Address: [city=" + city + ", street=" + street + 
				", numHouse=" + numHouse + "]";
	}
}
