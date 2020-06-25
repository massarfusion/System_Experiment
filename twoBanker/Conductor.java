package twoBanker;


import java.util.List;
import java.util.Scanner;

//��ʵ������һ�ɴ�0��ʼ
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
	public void BackupAll() {//����Available,Alloction,Need
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				AvailSave[j]=Available[j];
				this.AlloSave[i][j]=this.Alloction[i][j];
				this.NeedSave[i][j]=this.Need[i][j];
			}
		}
	}
	public void RetrAll() {//�ָ�Available,Alloction,Need
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
		System.out.println("�ܿ�����Դָ����ʼ��������ֵ�ÿո���߻س��ָ�");
		for (int i=0;i<3;i++) {
			this.Available[i]=in.nextInt();
		}
		System.out.println("�ܿ�����Դָ�����");
	}
	public void setMax() {
		System.out.println("�趨����������");
		for (int i=0;i<3;i++) {
			System.out.println("����ָ��"+i+"������������Դ����,�ո���߻س��ֿ�");
			for (int j=0;j<3;j++) {
				this.Max[i][j]=in.nextInt();
			}
		}
		System.out.println("ָ�����");
	}
	public void setAllo() {//������Ҫ����Need��Available
		System.out.println("��ʼָ���ѷ������");
        for (int i = 0; i < 3; i++) {
            System.out.println("�������P" + i + "�ķ�����ֵ");
            for (int j = 0; j < 3; j++) {
                Alloction[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < 3; i++) {//Avail����
            for (int j = 0; j < 3; j++) {
                Available[i] = Available[i] - Alloction[j][i];
            }
        }
        for (int i = 0; i < 3; i++) {//Needָ��
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
			System.out.println("������   Max    Need   Allocation ");
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
		System.out.println("������Ҫ����Դ�Ľ���");
		int i=in.nextInt();
		System.out.println("�������������Դ��������,�ո��س��ֿ�");
		for (int j=0;j<3;j++) {
			this.Request[i][j]=in.nextInt();
		}
		System.out.println("��"+i+"�Ž���Ҫ��ȡ��������Դ����ĿΪ"+this.Request[i][0]+""+this.Request[i][1]+""+this.Request[i][2]);
		System.out.println("���м��㷨��˿�ʼ");
		System.out.println("�·���ӡ�����ݷ����շ�������");
		boolean reach=this.core(i);
		if (reach) {
			System.out.println("����ͨ��");
			return;
		}
		else {
			System.out.println("����δͨ��");
			return;
		}
	}
	public boolean core(int num) {//�㷨���ģ���Ҫָ�����һ��¼��Ľ��̱��
		//���ܵ���ʹ�ã�
		this.BackupAll();
		if (this.Request[num][0]<=this.Need[num][0] && this.Request[num][1]<=this.Need[num][1] && this.Request[num][2]<=this.Need[num][2])
		{
			if (this.Request[num][0]<=this.Available[0] && this.Request[num][1]<=this.Available[1] && this.Request[num][2]<=this.Available[2])
			{
				//������Ҳû�г���Ԥ����Ҫ���Լ���û�г�����Դ����
				for (int i=0;i<3;i++) {
//				backup=Request[num][i];
				Available[i] -= Request[num][i];
                Alloction[num][i] += Request[num][i];
                Need[num][i] -= Request[num][i];
				}
			}
			else {
				System.out.println("����"+num+"����������Դ����");
				return false;
			}
		}
		else {
			System.out.println("����"+num+"�����󳬳��������������Դ�������ֵ");
			return false;
		}
		this.printme();
		System.out.println("����ִ�а�ȫ�㷨");
		boolean T=this.security();
		if (T) {
			System.out.println("��ȫ�㷨ͨ��");
			return true;
		}
		else {
			System.out.println("��ȫ�㷨��ֹ");
			//Ҫȡ�����䣡
			this.RetrAll();
			return false;
		}
		//�д�����
	}
	public boolean security() {
		//Max.length��ʾ�м�������  Available.length��ʾ������Դ����
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
