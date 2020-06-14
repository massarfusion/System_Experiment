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
		//���Ǽ���ִ��ʱ��30S
		//һ��ʼ���ź�ѭ������
		//Ȼ���ڿͻ��˲����Ӻ�ѭ������
		//һ��ʼ��ת�Ƶ���һ��PCB��������UPDATE
		//Ȼ���Զ�����ɨ��ת�Ƶ���һ��PCB
		//��ӡ
		//���Ҫɨ�������ж��ǲ��Ǻ�RUNNING�غϣ��ǵĻ�����Ǩ��RUNNING
		//���Ҫ�ڿͻ��˷�һ�����д�ӡ
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
	public void updateRunning() {//ֻ�ܰ�RUNNING������һ��
		if (head.next==head) {
			return;//û�п��ýڵ�
		}
		else if (head==running) {
			running=head.next;//�Ը�һ��ʼ��Ȼ�Ѿ������ϵ�RUNNING����HEAD��
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
	public void sweep() {//���Ѿ�������ɵĽ��̸ϳ�����,�����������ҲҪ��RUNNING����
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
