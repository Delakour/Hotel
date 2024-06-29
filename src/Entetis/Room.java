package Entetis;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class Room implements Serializable{
	private int roomID;
	private int floor;
	private roomsLevel level;
	private boolean isActive;
	static int startPrice = 700;
	
	public Room(int roomID, int floor, roomsLevel level) {
		setFloor(floor);
		setRoomID(roomID);
		setLevel(level);
		setActive(false);
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public roomsLevel getLevel() {
		return level;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	private void setRoomID(int roomID) {
		if(roomID > 0 && roomID / 10 == this.floor)
			this.roomID = roomID;
	}
	
	public void setFloor(int floor) {
		if(floor > 0)
			this.floor = floor;
	}
	
	public void setLevel(roomsLevel level) {
		this.level = level;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		return Objects.hash(floor, level, roomID);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return floor == other.floor && level == other.level &&
				roomID == other.roomID;
	}
	
	@Override
	public String toString() {
		return "Room: [roomID=" + roomID + ", floor=" + floor +
				", level=" + level + ", isActive=" + isActive + "]";
	}
}
