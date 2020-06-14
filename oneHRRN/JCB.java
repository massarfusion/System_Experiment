package oneHRRN;

public class JCB {
	String name;
	int size;
	int pReq;//需要的打印机
	int tReq;//需要的磁带机
	int estTime;//预计使用时间
	int arriveTime;//应当抵达时间
	int waitTime;//等待时间（需要更新）
	int iniTime;//开始执行的时刻
	boolean isRunning;//是不是在执行
	JCB next;//下一个的指针
	float priority;//优先级需要实时更新
	public JCB() {
		super();
		next=null;
	}
	public JCB(String name, int size, int pReq, int tReq, int estTime, int arriveTime) {
		super();
		this.name = name;
		this.size = size;
		this.pReq = pReq;
		this.tReq = tReq;
		this.estTime = estTime;
		this.arriveTime = arriveTime;
		this.priority=(float) 1;
		this.isRunning=false;
		this.waitTime=0;
		this.iniTime=-1;
		this.next=null;
	}
	public void setNext(JCB next) {
		this.next = next;
	}
	
	public int getIniTime() {
		return iniTime;
	}
	public void setIniTime(int iniTime) {
		this.iniTime = iniTime;
	}
	public void updatePrior() {
		this.priority=(float) (1+(float)(this.waitTime)/(float)(this.estTime));
	}
	public void updatewaitTime(int currentTime) {
		this.waitTime=currentTime-this.arriveTime;
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	
	
}
