package oneRR;

public class PCB {
	String name;
	PCB next;
	int estTime;//要求运行
	int runTime;//已经运行
	String status;//R就绪，E结束，一开始都是R
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
