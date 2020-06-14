package oneHRRN;

public class Conductor {
	int printer;
	int tape;
	int volumn;
	int curVolumn;
	//JCB executing;
	JCB queue;//�Ӷ���ʱ���Ų��µľ���������������˳��ٳ��Լ���������
	JCB head;
	public Conductor(int printer, int tape, int volumn) {
		super();
		this.printer = printer;
		this.tape = tape;
		this.volumn = volumn;
		this.curVolumn=0;
		this.head=new JCB();
		this.queue=new JCB();
	}
	public void addQueue(JCB adder) {
		JCB block=this.queue;
		while (block.next!=null) {block=block.next;}
		block.next=adder;
	}
	public void popQueue() {
		JCB block=this.queue.next;
		while (block!=null) {
			if (this.appendAllowed(block))
			{this.append(block);return;}
			else
			{block=block.next;}
		}
	}
	public boolean appendAllowed(JCB appex) {
		if (this.curVolumn+appex.size>this.volumn) 
		{return false;}
		else {return true;}
	}
	public boolean append(JCB appex) {
		if (this.curVolumn+appex.size>this.volumn) {this.addQueue(appex);return false;}
		else {
			this.curVolumn+=appex.size;
			JCB block=this.head;
			while (block.next!=null) {block=block.next;}
			block.next=appex;
			return true;
		}
	}
	public void execute() {
		//�����������ҳ����ȼ���ߵģ�Ȼ������Դ��û����ȫ(������Ҫ����)��û�о͵ȴ��ͷţ��оͷ�����Դ
		//����Ľ���һ���趨���Ѿ�������,�趨��ʼʱ��
		//�ͷ���ζ��ʱ�䵽�������ų������У���Դ����ϵͳ
		//ִ�е���󣬱����������нڵ��WAIT��PRIOIRTY
		//����ֻ����ִ��30�룬Ҳ���Ǽ���30��ѭ��
		for (int curTime=1;curTime<=30;curTime++) {
			this.popQueue();
			this.updateAllWaitAndPrior(curTime);
			this.sweep(curTime);
			JCB blc=this.seekHigh();
			this.activate(curTime, blc);
			this.printme(curTime);
			
		}
	}
	public void sweep(int curTime) {
		//��ɨ�Ѿ��ﵽ����ʱ�������
		JCB block=this.head;
		while (block.next!=null) {
			if ((curTime-block.next.iniTime>=block.next.estTime)&&(block.next.isRunning==true)) {
				this.printer+=block.next.pReq;
				this.tape+=block.next.tReq;
				this.curVolumn-=block.next.size;
				block.next=block.next.next;
				//��Դ�黹���޳�
			}
//			else if ((curTime-block.next.iniTime==block.next.estTime)&&(block.next.isRunning==true)&&(block.next.next==null))
//			{block.next=null;}
			else {block=block.next;;}
			
		}
	}
	public void updateAllWaitAndPrior(int curTime) {//�ȴ�ʱ���п�����Ϊ��������ô�ʹ���û���볡
		//Ҫ��û��ִ�е�����
		JCB block=this.head.next;
		while (block!=null) {
//			if (block.arriveTime==curTime) {
//				block.iniTime=curTime;
//				block.setRunning(true);
//			}else {;}
			//Ҫ��Ҫ������EXECUTION��������
			if (block.isRunning) {block=block.next;continue;}
			else {;}
			block.updatewaitTime(curTime);
			block.updatePrior();
			block=block.next;
			
		}
	}
	public void printme(int curTime) {
		//System.out.println();
		System.out.println("NAME   SIZE   PRINTER   TAPER   ESTTIME   ARRIVE   INITIME   isRUNNING   WAIT   PRIORITY");
		JCB block=this.head.next;
		while (block!=null) {
			System.out.printf("%s %d %d %d %d %d %d %b %d %.2f",block.name,block.size,block.pReq,
					block.tReq,block.estTime,block.arriveTime,
					block.iniTime,block.isRunning,block.waitTime,block.priority);
			System.out.println();
			block=block.next;
		}
		System.out.println("Time:"+curTime+" Finished"+"\n");
	
	
	}
	public void activate(int Time,JCB block) {
		if (block==null) {return;}
		if (this.tape-block.tReq>=0&&this.printer-block.pReq>=0) {
		block.isRunning=true;
		block.iniTime=Time;
		this.tape-=block.tReq;
		this.printer-=block.pReq;
		}
		else {
			System.out.println("Resource Lacking,activation suspended");
		}
		
	}
	public JCB seekHigh() {
		//��δ��ʼִ�е�
		JCB block =this.head.next;
		String name = null;
		float prio=-114;
		while (block!=null) {
			if (block.priority>prio&&block.isRunning==false) {name=block.name;prio=block.priority;}
			else {;}
			block=block.next;
		}
		if (prio<1) {return null;}
		else {
			block=this.head.next;
			while(block!=null) {
				if (block.name.equals(name)&&block.priority==prio&&block.isRunning==false) {
					return block;
				}else {block=block.next;}
			}
			
			return null;
		}
		
	}
}
