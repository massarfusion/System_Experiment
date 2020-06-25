package threePage;

//import java.util.ArrayList;
import java.util.Random;

public class Conductor {
	Random rGenerator = new Random();
	public static final int instruNum=320;//320个地址指令，
	public static final int pages=32; //一共32页
	public static final int memBlock=4; //一共4物理块，i页对应物理块号是i/8,产生置换则移除
	int time;
	Page [] pageChart;//存放页面信息
	Instruction [] insChart;//存放进程
	MemBlock [] blockChart;//存放物理块信息
	Instruction i1;
	Instruction i2;
	Instruction i3;
	Instruction i4;
	
	public Conductor() {
		super();
		// TODO Auto-generated constructor stub
		this.pageChart=new Page[this.pages];
		this.insChart=new Instruction[this.instruNum];
		this.blockChart=new MemBlock[this.memBlock];
	}
	public void Main() {
		this.initialize();
		System.out.println("页面 物理0 物理1 物理2 物理3");
		for (time=0;time<10;time++) {
			this.setInsSequel();//安排好I1~I4都是什么块
			this.insertPageChart(i1);
			this.insertPageChart(i2);
			this.insertPageChart(i3);
			this.insertPageChart(i4);
			
		}
	}
	public void initialize() {//初始化三个表
		for (int i=0;i<this.instruNum;i++) {
			this.insChart[i]=new Instruction("Instruction "+i,i);
		}
		for (int i=0;i<this.pages;i++) {
			this.pageChart[i]=new Page(i);
		}
		for (int i=0;i<this.memBlock;i++) {
			this.blockChart[i]=new MemBlock(i);
		}
	}//initia()
	
	public void setInsSequel() {//设定I1 I2 I3 I4
		int m1=-1;
		int m2=-1;
		int m3=-1;
		int m4=-1;
		int times=0;
		while (times<=300) {
		int m=this.rGenerator.nextInt(320);
		m1=m+1;
		m2=this.rGenerator.nextInt(m+2);
		m3=m2+1;
		m4=this.rGenerator.nextInt(318-m2)+2+m2;
		if (this.setInsSequelSecure(m1)&&this.setInsSequelSecure(m2)&&
				this.setInsSequelSecure(m3)&&this.setInsSequelSecure(m4))
		{
			break;
		}
		else {times++;continue;}
		}
		
		if (times>300) {System.out.println("Attempt tims out of limit");return;}
		else {
		this.i1=this.insChart[m1];
		this.i2=this.insChart[m2];
		this.i3=this.insChart[m3];
		this.i4=this.insChart[m4];
		return;}
	}
	
	public Boolean setInsSequelSecure(int index) {
		if (index>=this.insChart.length||index<0) {
			return false;
		}
		else if (this.insChart[index].isVisited()) {
			return false;
			
		}
		else {return true;}
	}
	
	public Instruction fetchIns(int index) {//超限者排除，在数组内的，如果已经执行过也排除
		if (index>=this.insChart.length||index<0||this.insChart[index].visited==true) {
			return null;
		}
		else {
			return this.insChart[index];
		}
	}//fetchIns
	
	public void insertPageChart(Instruction inst) {//插入替换页表里的INS数据项
		if (inst==null) {
			System.out.println("Null Error");
			return;}
		else {;}
		inst.recalcPageOffset();//计算应该分入哪一个页块
		if (inst.getDestPage()>=0&&inst.getDestPage()<this.pageChart.length) {
			inst.setVisited(true);
			this.pageChart[inst.getDestPage()].setIns(inst);//该页的INS置为最新的INS
			this.insertBlockChart(this.pageChart[inst.getDestPage()]);//该页块插入物理块
			return;
		}
		else {
			System.out.println("\n\n\n\n\nError at insertPageChart\n\n\n\n\n");//越界问题
			return;
		}
		
	}//出来了无缝衔接insertBlockChart
	public void insertBlockChart(Page pg) {
		pg.reCalcDestOffset();//重新计算理论块表号
		int tmpNum=pg.getDestBlock();//理论物理块号，引导到块表里的某一块
		if (this.blockChart[tmpNum].getLastIOTime()==-114) {//要是空就直接放入
			this.blockChart[tmpNum].setPageSave(pg);//存入页
			this.blockChart[tmpNum].setLastIOTime(time);//更新时间（全局时钟）
			this.printme(pg);
			return;
		}else {
			MemBlock tmp = null;
			int minTime=1919;//找最小的，或者另外找到空就直接置入
			for (MemBlock mb:this.blockChart) {
				if (mb.getLastIOTime()==-114) {
					mb.setPageSave(pg);
					mb.setLastIOTime(time);
					this.printme(pg);
					return;
				}
				else {
					if (mb.getLastIOTime()<minTime) {
						tmp=mb;
						minTime=mb.getLastIOTime();
					}
					else {
						;
					}
				}
			}//第一次遍历
			if (tmp==null) {
				System.out.println("\n\n\nUnknown Error At insertBlockChart\n\n\n");
				return ;
			}
			else {
			tmp.setLastIOTime(time);
			tmp.setPageSave(pg);
			this.printme(pg);
			return;}
		}
	}//insertMemBlockChart
	public void printme(Page pg) {
		System.out.print(pg.getPageNum()+"#");
		if (this.blockChart[0].getPageSave()!=null) {
			System.out.print(this.blockChart[0].getPageSave().getPageNum()+"#");
		}else {System.out.print(" "+"#");}
		if (this.blockChart[1].getPageSave()!=null) {
			System.out.print(this.blockChart[1].getPageSave().getPageNum()+"#");
		}else {System.out.print(" "+"#");}
		if (this.blockChart[2].getPageSave()!=null) {
			System.out.print(this.blockChart[2].getPageSave().getPageNum()+"#");
		}else {System.out.print(" "+"#");}
		if (this.blockChart[3].getPageSave()!=null) {
			System.out.print(this.blockChart[3].getPageSave().getPageNum()+"#");
		}else {System.out.print(" "+"#");}
		
		System.out.println();
//		System.out.println(
//				pg.getPageNum()+"#"+
//				this.blockChart[0].getPageSave().getPageNum()+"#"+
//				this.blockChart[1].getPageSave().getPageNum()+"#"+
//				this.blockChart[2].getPageSave().getPageNum()+"#"+
//				this.blockChart[3].getPageSave().getPageNum()+"#");
	}//printme
}
