package oneHRRN;

public class JCB {
	String name;
	int size;
	int pReq;//��Ҫ�Ĵ�ӡ��
	int tReq;//��Ҫ�ĴŴ���
	int estTime;//Ԥ��ʹ��ʱ��
	int arriveTime;//Ӧ���ִ�ʱ��
	int waitTime;//�ȴ�ʱ�䣨��Ҫ���£�
	int iniTime;//��ʼִ�е�ʱ��
	boolean isRunning;//�ǲ�����ִ��
	JCB next;//��һ����ָ��
	float priority;//���ȼ���Ҫʵʱ����
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
