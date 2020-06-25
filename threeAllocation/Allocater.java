package threeAllocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Allocater {
	Scanner in =new Scanner(System.in);
	Space [] spaceChart;
	Process [] processChart;
	int systemSize;
	int maxSize;
	int ramSize;
	int time;
	//int chartTop;//����������������ֵ
	public Allocater() {
		super();
		this.processChart=new Process[10];
		this.spaceChart=new Space [10];
		//this.chartTop=0;

	}
	public void FillProcess() {
		System.out.println("��������������������");
		this.maxSize=in.nextInt();
		System.out.println("����ϵͳ��С��������");
		this.systemSize=in.nextInt();
		this.ramSize=this.maxSize-this.systemSize;
		this.spaceChart[0]=new Space(this.systemSize,this.ramSize);
		System.out.println("�м�����Ҫ����Ľ��̣�");
		int count=in.nextInt();
		for (int i=0;i<count;i++) {
			System.out.println("��������̴�С���������ƣ�������ʼʱ�䣬���̽���ʱ�䣬�ո��س��ָ");
			this.processChart[i]=new Process(in.nextInt(), in.next(), in.nextInt(), in.nextInt());
		}
	}
	public void Sort() {
		int max=0;
		for (int i=0;i<this.spaceChart.length;i++) {
			if (this.spaceChart[i]==null) {
				max=i-1;
				break;
			}
			else {;}
		}
		for (int i=0;i<max;i++) {
			for (int j=0;j<max-i;j++) {
				if (spaceChart[j].getStart()>spaceChart[j+1].getStart()) {
					Space temp=spaceChart[j];
					spaceChart[j]=spaceChart[j+1];
					spaceChart[j+1]=temp;
				}
				else;
			}
		}
	}
	public void Main() {
		this.FillProcess();
		for (time=0;time<30;time++) {//�޶�30sʱ��
			this.Sort();//������
			if (time==27) {;}else {;}
			for (int i=0;i<this.processChart.length;i++) {//��������
				this.Sort();
				if (this.processChart[i]==null) {//������NULLֱ������
					continue;
				}
				else {
					Process tmp=this.processChart[i];
					if (tmp.startTime==time) {
						this.Allocate(tmp);
						this.Join();
						}
					else if (tmp.endTime==time) {
						this.Sweep(tmp);
						this.Join();
						}
					else {
						this.Join();
						continue;}
				}//else

			}//for
			System.out.println("-----------Time Is "+time);
			this.Printme();
		}//for
	}//main
	private void Sweep(Process tmp) {//�����˳�ʱ�黹��Դ
		int max=0;
		for (int i=0;i<this.spaceChart.length;i++) {
			if (this.spaceChart[i]==null) {
				max=i-1;
				break;
			}
			else {;}
		}
		max++;
		if (max>=this.spaceChart.length) {
			System.out.println("����ʧ��");
			return;
		}
		else {;}
		this.spaceChart[max]=new Space(tmp.getSpaceStart(),tmp.size);
		this.spaceChart[max].setStatus(1);
	}
	public void Allocate(Process pro) {//���̽���ʱ��������
		int max=0;
		for (int i=0;i<this.spaceChart.length;i++) {
			if (this.spaceChart[i]==null) {
				max=i-1;
				break;
			}
			else {;}
		}
		if (max==0) {
			if (this.spaceChart[0].len<pro.size) {
				System.out.println("�ռ䲻�㣬�ܾ�����");
				return;
			}
			else {
				pro.setSpaceStart(this.spaceChart[0].start);
				this.spaceChart[0].len-=pro.size;
				this.spaceChart[0].start+=pro.size;
				
				return;
			}
		}
		else {
			for (int i=0;i<max;i++) {
				if (this.spaceChart[i].len<pro.size) {
					continue;
				}
				else {
					pro.setSpaceStart(this.spaceChart[i].start);
					this.spaceChart[i].len-=pro.size;
					this.spaceChart[i].start+=pro.size;
					continue;
				}
			}
		}
	}
	public void Printme() {//��ӡ������Ϣ
		int maxSpace=0;
		for (int i=0;i<this.spaceChart.length;i++) {
			if (this.spaceChart[i]==null) {
				maxSpace=i-1;
				break;
			}
			else {;}
		}
		int maxProc=0;
		for (int i=0;i<this.processChart.length;i++) {
			if (this.processChart[i]==null) {
				maxProc=i-1;
				break;
			}
			else {;}
		}
		System.out.println("���濪ʼ��ӡ��Ϣ\n��������");
		System.out.println("��ʼ��ַ ���� ״̬");
		for (int i=0;i<=maxSpace;i++) {
			Space temp=this.spaceChart[i];
			System.out.println(temp.start+"#"+temp.len+"#"+temp.status);
		}
		System.out.println("��������");
		System.out.println("���� ��С ��ʼʱ�� ��ֹʱ�� ");
		for (int i=0;i<=maxProc;i++) {
			Process temp=this.processChart[i];
			System.out.println(temp.name+"#"+temp.size+"#"+temp.startTime+"#"+temp.endTime);
		}
	}

	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void Join() {//�ѿռ�����������ȫ�ص��Ķ������������һ��
		this.Sort();
		List<Space> list=Arrays.asList(this.spaceChart);//������ת��Ϊlist����
		List<Space> arr=new ArrayList<Space>(list);
		for (int i=0;i<arr.size();i++) {
			if (arr.get(i)==null) {continue;}
			else {;}
			if (arr.get(i).getLen()==0) {//��һ���޳�
				arr.remove(i);
				i--;
			}
			else if (arr.get(i+1)!=null&&(arr.get(i).getLen()!=0)) {//�ڽӵĺ�������
				if (arr.get(i).getStart()+arr.get(i).getLen()==arr.get(i+1).start) {
					arr.get(i).setLen(arr.get(i).len + arr.get(i+1).getLen());
					arr.remove(i+1);
				}
				else {;}
			}
			else {;}
		}
		for (int i=0;i<this.spaceChart.length;i++) {
			this.spaceChart[i]=null;
		}
		for (int i=0;i<arr.size();i++) {
			this.spaceChart[i]=arr.get(i);
		}
	}
	
	
}
