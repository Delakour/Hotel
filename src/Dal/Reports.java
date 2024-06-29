package Dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Reports implements Serializable{
	public static final String PATH = "files/";
	
	public static void createReport(String filePath, ArrayList<? extends Serializable> list) {
		//writeObject
		ObjectOutputStream o = null;

		try {
			o = new ObjectOutputStream(new FileOutputStream(filePath));
			o.writeObject(list);
			o.flush();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (o != null) {
				try {
					o.close();
				} 
				catch (IOException ex) {
				}
			}
		}

	}
	
	public static void readReport(String filePath) {
		//readObject
		ObjectInputStream o = null;
		
		try {
			o = new ObjectInputStream(new FileInputStream(filePath));
			try {
				System.out.println(o.readObject());
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
