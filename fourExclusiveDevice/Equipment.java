package fourExclusiveDevice;

public class Equipment {
	int globalNum;//���Ա��
	int typeNum;//��Ա��
	int type;//����
	boolean inUse;//�ǲ�������ʹ��
	boolean good;//״̬�ǲ�������
	String Occupant;//ռ�õ���ҵ������
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
