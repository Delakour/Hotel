package Entetis;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class BussinesGuest extends Guest implements Serializable{
	
	private int bussinesGuestCode;
	static int discountPercent = 30;
	
	BussinesGuest(int bussinesGuestCode, Person person) {
		super(person);
		setBussinesGuestCode(bussinesGuestCode);
	}

	public int getBussinesGuestCode() {
		return bussinesGuestCode;
	}

	public void setBussinesGuestCode(int bussinesGuestCode) {
		if(bussinesGuestCode > 0)
			this.bussinesGuestCode = bussinesGuestCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bussinesGuestCode);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BussinesGuest other = (BussinesGuest) obj;
		return bussinesGuestCode == other.bussinesGuestCode;
	}

	@Override
	public String toString() {
		return "BussinesGuest: [bussinesGuestCode=" + bussinesGuestCode +
				", "+ super.toString() + "]";
	}
	
}
