package fourExclusiveDevice;

import java.util.Scanner;

public class EquipmentChart {
	Scanner in =new Scanner(System.in);
	int typeMax;
	int arrayLen;
	Typechart [] typechartArray;//存放类表的表，不同表项之间不同类别,号码就是类别
	public void initialize() {
		System.out.println("请输入有多少类设备,然后回车");
		int typenum_tmp=in.nextInt();
		this.typeMax=typenum_tmp;
		this.typechartArray=new Typechart[typenum_tmp];
		for (int i=0;i<typenum_tmp;i++) {//i是表示种类，j表示该类别下的局部编号
			System.out.println("请输入"+i+"号类有多少个设备,然后回车");
			int typetotal=in.nextInt();//i号类的设备总数目
			this.typechartArray[i]=new Typechart(typetotal);//初始化
			this.typechartArray[i].setChartType(i);
			for (int j=0;j<typetotal;j++) {
				System.out.println("为第"+i+"类的设备"+j+"输入一个设备名吧,请避免重名！");
				this.typechartArray[i].chart[j]=new Equipment(j, i, in.next());
			}
			System.out.println("有几个坏机器？");
			int badNum=in.nextInt();//几个坏设备
			for (int k=0;k<badNum;k++) {
				System.out.println("输入损毁的设备号，0开始，"+(typetotal-1)+"结束，不符合条件的不会录入");
				this.typechartArray[i].markTheBad(in.nextInt());
			}
			System.out.println("OK");
		}
		this.arrayLen=this.typechartArray.length;
		return;
	}//initialize
	/////////////////////////////////////MAIN//////////////////////////////
	public void Main() {
		this.initialize();
		while (true) {
			System.out.println("选1分配，选2回收，选3退出 4打印 输入后按下回车");
			int instruct=in.nextInt();
			if (instruct==1) {
				this.allocate();
			}
			else if (instruct==2) {
				this.release();
			}
			else if (instruct==3) {
				System.out.println("正在退出程序");
				return;
			}
			else if (instruct==4) {
				this.printme();
			}
			else {
				System.out.println("输入格式不正确，请重新输入");
				continue;
			}
		}
	}
	////////////////////////////////////MAIN///////////////////////////////
	public void allocate() {
		System.out.println("输入作业名");
		String name=in.next();
		System.out.println("输入类型（数字） 0开始"+(this.typeMax-1)+"结束");
		int type=in.nextInt();
		if (type<0||type>=this.typeMax) {
			System.out.println("类型号输入错误，返回到主菜单");
			return;
		}
		else {;}
		System.out.println("输入相对号,0开始,"+(this.typechartArray[type].getChart().length-1)+"结束");
		int addr=in.nextInt();
		if(addr<0||addr>=this.typechartArray[type].getChart().length) {
			System.out.println("相对号输入错误，返回到主菜单");
			return;
		}
		else {
			this.typechartArray[type].allocate(name,addr);
		}
	}//allocate
	
	public void release() {
		System.out.println("输入作业名");
		String name=in.next();
		System.out.println("输入类型（数字） 0开始"+(this.typeMax-1)+"结束");
		int type=in.nextInt();
		if (type<0||type>=this.typeMax) {
			System.out.println("类型号输入错误，返回到主菜单");
			return;
		}
		else {
			this.typechartArray[type].release(name);
		}
	}//release
	
	public void printme() {
		for (int i=0;i<this.typeMax;i++) {
			System.out.println("以下是类型"+i);
			Typechart tc=this.typechartArray[i];
			System.out.println("可用资源 "+tc.available+"资源总数"+tc.total);
			for (Equipment eq:this.typechartArray[i].chart) {
				System.out.println("占用进程名称:"+eq.getOccupant()+"是否良好"+eq.isGood()+"是否正在使用"+eq.isInUse());
			}
		}
	}
	
	
}
//分配设备要求输入作业名、设备类名和相对号；回收设备要求输入作业名和设 备类名