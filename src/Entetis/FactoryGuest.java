package Entetis;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FactoryGuest implements Serializable{
	
	public static Guest createGuest(int code, Person p) {
		if(code <= 0)
			return new Guest(p);
		return new BussinesGuest(code, p);
	}
}
