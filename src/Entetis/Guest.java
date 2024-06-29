package Entetis;

import java.io.Serializable;
import java.util.Objects;


public class Guest  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Person person;
	private int numVisit;

	Guest(Person person) {
		setPerson(person);
		setNumVisit(0);
	}

	public Person getPerson() {
		return this.person;
	}

	public int getNumVisit() {
		return this.numVisit;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setNumVisit(int numVisit) {
		if (numVisit >= 0)
			this.numVisit = numVisit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(person);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		return Objects.equals(person, other.person);
	}

	@Override
	public String toString() {
		return "Guest: [" + person.toString() + ", numVisit=" + numVisit + "]";
	}

}