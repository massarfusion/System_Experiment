package oneHRRN;

public class Conductor {
	int printer;
	int tape;
	int volumn;
	int curVolumn;
	//JCB executing;
	JCB queue;//加队列时，放不下的就塞到这里，有任务退出再尝试加入主队列
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
		//挨个遍历，找出优先级最高的，然后检查资源有没有齐全(两个都要满足)，没有就等待释放，有就分配资源
		//加入的进程一概设定其已经在运作,设定起始时间
		//释放意味着时间到，将其排除出队列，资源还给系统
		//执行的最后，遍历更新所有节点的WAIT和PRIOIRTY
		//假设只允许执行30秒，也就是计数30次循环
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
		//清扫已经达到运行时间的任务
		JCB block=this.head;
		while (block.next!=null) {
			if ((curTime-block.next.iniTime>=block.next.estTime)&&(block.next.isRunning==true)) {
				this.printer+=block.next.pReq;
				this.tape+=block.next.tReq;
				this.curVolumn-=block.next.size;
				block.next=block.next.next;
				//资源归还后剔除
			}
//			else if ((curTime-block.next.iniTime==block.next.estTime)&&(block.next.isRunning==true)&&(block.next.next==null))
//			{block.next=null;}
			else {block=block.next;;}
			
		}
	}
	public void updateAllWaitAndPrior(int curTime) {//等待时间有可能设为负数，那么就代表没有入场
		//要求没有执行的任务
		JCB block=this.head.next;
		while (block!=null) {
//			if (block.arriveTime==curTime) {
//				block.iniTime=curTime;
//				block.setRunning(true);
//			}else {;}
			//要不要激活由EXECUTION函数决定
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
		//找未开始执行的
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
