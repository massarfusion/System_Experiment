package twoBanker;


import java.util.List;
import java.util.Scanner;

//本实验数组一律从0开始
public class Conductor {
    int[] Available ;
    int[][] Max ;
    int[][] Alloction ;
    int[][] Need  ;
    int[][] Request;
    int[] Security;
    int [] Work ;
    int [] AvailSave;
    int [][] AlloSave;
    int [][] NeedSave;
    Scanner in = new Scanner(System.in);
	public Conductor() {
		super();
		Available = new int [3];
	    Max = new int[3][3];
	    Alloction = new int[3][3];
	    Need = new int[3][3];
	    Request = new int[3][3];
	    Security = new int[3];
		Work =new int [3];
		AvailSave=new int [3];
		AlloSave=new int [3][3];
		NeedSave=new int [3][3];
	}
	public void BackupAll() {//保存Available,Alloction,Need
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				AvailSave[j]=Available[j];
				this.AlloSave[i][j]=this.Alloction[i][j];
				this.NeedSave[i][j]=this.Need[i][j];
			}
		}
	}
	public void RetrAll() {//恢复Available,Alloction,Need
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				Available[j]=AvailSave[j];
				this.Alloction[i][j]=this.AlloSave[i][j];
				this.Need[i][j]=this.NeedSave[i][j];
			}
		}
	}
	public void initialize() {
		this.setAvail();
		this.setMax();
		this.setAllo();
		this.printme();
		this.issueRequest();
	}
	
	public void setAvail() {
		System.out.println("总可用资源指定开始，三个数值用空格或者回车分割");
		for (int i=0;i<3;i++) {
			this.Available[i]=in.nextInt();
		}
		System.out.println("总可用资源指定完成");
	}
	public void setMax() {
		System.out.println("设定最大需求矩阵");
		for (int i=0;i<3;i++) {
			System.out.println("下面指定"+i+"号任务的最大资源需求,空格或者回车分开");
			for (int j=0;j<3;j++) {
				this.Max[i][j]=in.nextInt();
			}
		}
		System.out.println("指定完成");
	}
	public void setAllo() {//这里需要更新Need和Available
		System.out.println("开始指定已分配矩阵");
        for (int i = 0; i < 3; i++) {
            System.out.println("输入进程P" + i + "的分配数值");
            for (int j = 0; j < 3; j++) {
                Alloction[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < 3; i++) {//Avail更新
            for (int j = 0; j < 3; j++) {
                Available[i] = Available[i] - Alloction[j][i];
            }
        }
        for (int i = 0; i < 3; i++) {//Need指定
            for (int j = 0; j < 3; j++) {
                Need[i][j] = Max[i][j] - Alloction[i][j];
            }
        }
	}
	
	
	public void printme() {
		for (int i=0;i<3;i++) {
			//System.out.println("Available");
			if (i==0) {
				System.out.println("Available");
				for (int j=0;j<3;j++) {
					System.out.print(this.Available[j]+" ");
				}
			}
			else {;}
			System.out.println();
			System.out.println("进程名   Max    Need   Allocation ");
			//System.out.println();
			System.out.print("P"+i);
			System.out.print("#");
			for (int j=0;j<3;j++) {
				System.out.print(this.Max[i][j]+" ");
			}
			System.out.print("#");
			for (int j=0;j<3;j++) {
				System.out.print(this.Need[i][j]+" ");
			}
			System.out.print("#");
			for (int j=0;j<3;j++) {
				System.out.print(this.Alloction[i][j]+" ");
			}
			System.out.print("#");
		}
	}
	
	public void issueRequest() {
		System.out.println("键入想要新资源的进程");
		int i=in.nextInt();
		System.out.println("下面键入三个资源的需求量,空格或回车分开");
		for (int j=0;j<3;j++) {
			this.Request[i][j]=in.nextInt();
		}
		System.out.println("第"+i+"号进程要求取得三种资源的数目为"+this.Request[i][0]+""+this.Request[i][1]+""+this.Request[i][2]);
		System.out.println("银行家算法审核开始");
		System.out.println("下方打印的数据非最终分配数据");
		boolean reach=this.core(i);
		if (reach) {
			System.out.println("分配通过");
			return;
		}
		else {
			System.out.println("分配未通过");
			return;
		}
	}
	public boolean core(int num) {//算法核心，需要指定最近一次录入的进程编号
		//不能单独使用！
		this.BackupAll();
		if (this.Request[num][0]<=this.Need[num][0] && this.Request[num][1]<=this.Need[num][1] && this.Request[num][2]<=this.Need[num][2])
		{
			if (this.Request[num][0]<=this.Available[0] && this.Request[num][1]<=this.Available[1] && this.Request[num][2]<=this.Available[2])
			{
				//上面检查也没有超出预期需要，以及有没有超出资源局限
				for (int i=0;i<3;i++) {
//				backup=Request[num][i];
				Available[i] -= Request[num][i];
                Alloction[num][i] += Request[num][i];
                Need[num][i] -= Request[num][i];
				}
			}
			else {
				System.out.println("进程"+num+"超出可用资源限制");
				return false;
			}
		}
		else {
			System.out.println("进程"+num+"的请求超出它允许请求的资源数的最大值");
			return false;
		}
		this.printme();
		System.out.println("现在执行安全算法");
		boolean T=this.security();
		if (T) {
			System.out.println("安全算法通过");
			return true;
		}
		else {
			System.out.println("安全算法阻止");
			//要取消分配！
			this.RetrAll();
			return false;
		}
		//有待补完
	}
	public boolean security() {
		//Max.length表示有几个进程  Available.length表示几个资源种类
		boolean [] finished= {false,false,false};
		for (int i=0;i<this.Available.length;i++)
		{
			Work[i]=this.Available[i];
		}
		int pro=this.Max.length;
		int res=this.Available.length;
		for (int tmp=0;tmp<pro;tmp++) {
			for (int i=0;i<pro;i++) {
				if (finished[i]==false&&Need[i][0]<=Work[0]&&Need[i][1]<=Work[1]&&Need[i][2]<=Work[2])
				{
					for (int k=0;k<res;k++) {
						this.Work[k]+=this.Alloction[i][k];
					}
					finished[i]=true;
					break;
				}
			}
			
		}
		
		for (int i=0;i<pro;i++) {
			if (finished[i]==false) {
				return false;
			}
			else {;}
		}
		return true;

	}
	
	
}
