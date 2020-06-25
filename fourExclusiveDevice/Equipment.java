package fourExclusiveDevice;

public class Equipment {
	int globalNum;//绝对编号
	int typeNum;//相对编号
	int type;//类型
	boolean inUse;//是不是正在使用
	boolean good;//状态是不是良好
	String Occupant;//占用的作业的名称
	public Equipment(int typeNum, int type, String occupant) {
		super();
		this.typeNum = typeNum;
		this.type = type;
		Occupant = occupant;
		this.inUse=false;
		this.good=true;
	}
	public boolean compareOccupantName(String comp) {
		if (comp.equals(this.Occupant)) {return true;}
		else {return false;}
	}
	public int getGlobalNum() {
		return globalNum;
	}
	public int getTypeNum() {
		return typeNum;
	}
	public int getType() {
		return type;
	}
	public boolean isInUse() {
		return inUse;
	}
	public boolean isGood() {
		return good;
	}
	public String getOccupant() {
		return Occupant;
	}
	public void setGlobalNum(int globalNum) {
		this.globalNum = globalNum;
	}
	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	public void setGood(boolean good) {
		this.good = good;
	}
	public void setOccupant(String occupant) {
		Occupant = occupant;
	}
	
	
}
