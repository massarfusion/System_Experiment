package threeAllocation;

public class Process {
	int size;
	String name;
	int startTime;
	int endTime;
	int spaceStart;//占用的SPACE开始地址
	//int spaceLen;//占据的SPACE大小
	//int spaceDivis;//space占据后，切分为两块，这就是分界地址,PROCESS总在前一块
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
