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
	//int chartTop;//用来表明最大的索引值
	public Allocater() {
		super();
		this.processChart=new Process[10];
		this.spaceChart=new Space [10];
		//this.chartTop=0;

	}
	public void FillProcess() {
		System.out.println("输入主存容量，整数。");
		this.maxSize=in.nextInt();
		System.out.println("输入系统大小，整数。");
		this.systemSize=in.nextInt();
		this.ramSize=this.maxSize-this.systemSize;
		this.spaceChart[0]=new Space(this.systemSize,this.ramSize);
		System.out.println("有几个需要服务的进程？");
		int count=in.nextInt();
		for (int i=0;i<count;i++) {
			System.out.println("请输入进程大小，进程名称，进程起始时间，进程结束时间，空格或回车分割。");
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
		for (time=0;time<30;time++) {//限定30s时间
			this.Sort();//先排序
			if (time==27) {;}else {;}
			for (int i=0;i<this.processChart.length;i++) {//遍历进程
				this.Sort();
				if (this.processChart[i]==null) {//遍历到NULL直接跳过
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
	private void Sweep(Process tmp) {//进程退出时归还资源
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
			System.out.println("返还失败");
			return;
		}
		else {;}
		this.spaceChart[max]=new Space(tmp.getSpaceStart(),tmp.size);
		this.spaceChart[max].setStatus(1);
	}
	public void Allocate(Process pro) {//进程进入时给他分配
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
				System.out.println("空间不足，拒绝分配");
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
	public void Printme() {//打印基本信息
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
		System.out.println("下面开始打印信息\n空闲区：");
		System.out.println("起始地址 长度 状态");
		for (int i=0;i<=maxSpace;i++) {
			Space temp=this.spaceChart[i];
			System.out.println(temp.start+"#"+temp.len+"#"+temp.status);
		}
		System.out.println("进程区：");
		System.out.println("名称 大小 起始时间 终止时间 ");
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
	public void Join() {//把空间上连续或完全重叠的多个空闲区合在一起
		this.Sort();
		List<Space> list=Arrays.asList(this.spaceChart);//将数组转换为list集合
		List<Space> arr=new ArrayList<Space>(list);
		for (int i=0;i<arr.size();i++) {
			if (arr.get(i)==null) {continue;}
			else {;}
			if (arr.get(i).getLen()==0) {//零一概剔除
				arr.remove(i);
				i--;
			}
			else if (arr.get(i+1)!=null&&(arr.get(i).getLen()!=0)) {//邻接的焊接起来
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
