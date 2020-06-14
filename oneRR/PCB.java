package oneRR;

public class PCB {
	String name;
	PCB next;
	int estTime;//Ҫ������
	int runTime;//�Ѿ�����
	String status;//R������E������һ��ʼ����R
	public PCB(String name, int estTime) {
		super();
		this.name = name;
		//this.next = next;
		this.estTime = estTime;
		this.runTime=0;
		this.status = "R";
	}
	public PCB() {
		super();
	}
	
	public PCB getNext() {
		return next;
	}
	public void setNext(PCB next) {
		this.next = next;
	}
	public void update() {
		if (this.status.equals("R")&&(this.estTime>this.runTime)) {
			this.runTime+=1;
			if (this.runTime>=this.estTime) {
				this.status="E";
			}
			else {;}
		}
		else {return;}
	}
	
}
