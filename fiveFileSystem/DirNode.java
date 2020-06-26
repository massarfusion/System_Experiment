package fiveFileSystem;

public class DirNode {
	int label;//���ù�
	String name;//ָ��Ŀ¼�ļ����¼�Ŀ¼���ƣ�ָ����ͨ�ļ�����ͨ�ļ�����
	int type;//ָ�����0Ŀ¼�ļ�1��ͨ�ļ�
	NormFile nf;//���ͣ�1
	DirFile df;//���ͣ�0
	//�о�ָ��û�п���
	public DirNode() {
		super();
		this.type=810;//��ʼֵ
		// TODO Auto-generated constructor stub
	}
	public void AppointNF(NormFile tmpnf) {
		this.nf=tmpnf;
		this.name=tmpnf.name;
		this.setType(1);
	}
	public void AppointDF(String name) {
		this.name=name;
		this.setType(0);
		this.df=new DirFile(this.name);
	}
	public void Clear() {
		this.df=null;
		this.nf=null;
		this.type=810;
		this.name="";
	}
	
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public NormFile getNf() {
		return nf;
	}
	public DirFile getDf() {
		return df;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setNf(NormFile nf) {
		this.nf = nf;
	}
	public void setDf(DirFile df) {
		this.df = df;
	}
	
}
