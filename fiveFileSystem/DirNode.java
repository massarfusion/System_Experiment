package fiveFileSystem;

public class DirNode {
	int label;//不用管
	String name;//指向目录文件，下级目录名称；指向普通文件，普通文件名称
	int type;//指向的是0目录文件1普通文件
	NormFile nf;//类型：1
	DirFile df;//类型：0
	//有就指向，没有空着
	public DirNode() {
		super();
		this.type=810;//初始值
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
