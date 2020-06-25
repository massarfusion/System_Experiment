package fourExclusiveDevice;

import java.util.Scanner;

public class EquipmentChart {
	Scanner in =new Scanner(System.in);
	int typeMax;
	int arrayLen;
	Typechart [] typechartArray;//������ı���ͬ����֮�䲻ͬ���,����������
	public void initialize() {
		System.out.println("�������ж������豸,Ȼ��س�");
		int typenum_tmp=in.nextInt();
		this.typeMax=typenum_tmp;
		this.typechartArray=new Typechart[typenum_tmp];
		for (int i=0;i<typenum_tmp;i++) {//i�Ǳ�ʾ���࣬j��ʾ������µľֲ����
			System.out.println("������"+i+"�����ж��ٸ��豸,Ȼ��س�");
			int typetotal=in.nextInt();//i������豸����Ŀ
			this.typechartArray[i]=new Typechart(typetotal);//��ʼ��
			this.typechartArray[i].setChartType(i);
			for (int j=0;j<typetotal;j++) {
				System.out.println("Ϊ��"+i+"����豸"+j+"����һ���豸����,�����������");
				this.typechartArray[i].chart[j]=new Equipment(j, i, in.next());
			}
			System.out.println("�м�����������");
			int badNum=in.nextInt();//�������豸
			for (int k=0;k<badNum;k++) {
				System.out.println("������ٵ��豸�ţ�0��ʼ��"+(typetotal-1)+"�����������������Ĳ���¼��");
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
			System.out.println("ѡ1���䣬ѡ2���գ�ѡ3�˳� 4��ӡ ������»س�");
			int instruct=in.nextInt();
			if (instruct==1) {
				this.allocate();
			}
			else if (instruct==2) {
				this.release();
			}
			else if (instruct==3) {
				System.out.println("�����˳�����");
				return;
			}
			else if (instruct==4) {
				this.printme();
			}
			else {
				System.out.println("�����ʽ����ȷ������������");
				continue;
			}
		}
	}
	////////////////////////////////////MAIN///////////////////////////////
	public void allocate() {
		System.out.println("������ҵ��");
		String name=in.next();
		System.out.println("�������ͣ����֣� 0��ʼ"+(this.typeMax-1)+"����");
		int type=in.nextInt();
		if (type<0||type>=this.typeMax) {
			System.out.println("���ͺ�������󣬷��ص����˵�");
			return;
		}
		else {;}
		System.out.println("������Ժ�,0��ʼ,"+(this.typechartArray[type].getChart().length-1)+"����");
		int addr=in.nextInt();
		if(addr<0||addr>=this.typechartArray[type].getChart().length) {
			System.out.println("��Ժ�������󣬷��ص����˵�");
			return;
		}
		else {
			this.typechartArray[type].allocate(name,addr);
		}
	}//allocate
	
	public void release() {
		System.out.println("������ҵ��");
		String name=in.next();
		System.out.println("�������ͣ����֣� 0��ʼ"+(this.typeMax-1)+"����");
		int type=in.nextInt();
		if (type<0||type>=this.typeMax) {
			System.out.println("���ͺ�������󣬷��ص����˵�");
			return;
		}
		else {
			this.typechartArray[type].release(name);
		}
	}//release
	
	public void printme() {
		for (int i=0;i<this.typeMax;i++) {
			System.out.println("����������"+i);
			Typechart tc=this.typechartArray[i];
			System.out.println("������Դ "+tc.available+"��Դ����"+tc.total);
			for (Equipment eq:this.typechartArray[i].chart) {
				System.out.println("ռ�ý�������:"+eq.getOccupant()+"�Ƿ�����"+eq.isGood()+"�Ƿ�����ʹ��"+eq.isInUse());
			}
		}
	}
	
	
}
//�����豸Ҫ��������ҵ�����豸��������Ժţ������豸Ҫ��������ҵ������ ������