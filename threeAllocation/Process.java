package threeAllocation;

public class Process {
	int size;
	String name;
	int startTime;
	int endTime;
	int spaceStart;//ռ�õ�SPACE��ʼ��ַ
	//int spaceLen;//ռ�ݵ�SPACE��С
	//int spaceDivis;//spaceռ�ݺ��з�Ϊ���飬����Ƿֽ��ַ,PROCESS����ǰһ��
	public Process(int size, String name, int startTime, int endTime) {
		super();
		this.size = size;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public boolean Finished(int curTime) {
		if (curTime>=this.endTime) {
			return true;
		}
		else {return false;}
	}
	public boolean Started(int curTime) {
		if (curTime<this.startTime) {
			return true;
		}
		else {return false;}
	}
	public int getSpaceStart() {
		return spaceStart;
	}
	
	public void setSpaceStart(int spaceStart) {
		this.spaceStart = spaceStart;
	}
	
	
	
	
	
}
