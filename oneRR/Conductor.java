package oneRR;


public class Conductor {
	PCB head;
	PCB running;
	public Conductor() {
		this.head=new PCB();
		this.running=new PCB();
		head.next=head;
		running=head;
	}
	public void execute() {
		//我们假设执行时间30S
		//一开始先排好循环链表
		//然后在客户端操作加好循环链表
		//一开始先转移到第一个PCB，对他做UPDATE
		//然后尝试对它清扫，转移到下一个PCB
		//打印
		//如果要扫除，先判断是不是和RUNNING重合，是的话必须迁移RUNNING
		//最后要在客户端放一个序列打印
		this.updateRunning();
//		this.running.update();
		this.printme(0);
		for (int i=1;i<=30;i++) {
			this.running.update();
			this.sweep();
			this.printme(i);
		}
	}
	
	public boolean append(PCB block) {
		if (head.next==head) {
			head.setNext(block);
			block.setNext(head);
			return true;
		}
		else {
			PCB tmp=head;
			while (tmp.next!=head) {
				
				tmp=tmp.next;
			}
			tmp.next=block;
			block.next=head;
			return true;
		}
	}
	public void updateRunning() {//只能把RUNNING往后推一格
		if (head.next==head) {
			return;//没有可用节点
		}
		else if (head==running) {
			running=head.next;//对付一开始虽然已经添加完毕但RUNNING还在HEAD上
		}
		else {
			PCB tmp=running.next;
			if (tmp==head) 
			{
				tmp=tmp.next;
				this.running=tmp;
			}
			else{running=tmp;}
		}
	}
	public void sweep() {//把已经运行完成的进程赶出队列,并且无论如何也要把RUNNING后推
		if (running.status.equals("E")) {
			PCB tmp=this.head;
			while (tmp.next!=running) {
				tmp=tmp.next;
			}
			this.updateRunning();
			tmp.next=tmp.next.next;
		}
		else {
			this.updateRunning();
		}
	}
	public void printme(int curTime) {
		System.out.println("NAME   ESTTIME   RUNTIME");
		//System.out.printf("%s %d %d %d %d %d %d %b %d %.2f");
		PCB tmp=this.head;
		while (tmp.next!=this.head) {
			System.out.printf("%s %d %d",tmp.next.name,tmp.next.estTime,tmp.next.runTime);
			System.out.println();
			tmp=tmp.next;
		}
		System.out.println("Time "+curTime+" finished");
	}
	
	
	
	
	
}
