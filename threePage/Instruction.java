package threePage;

public class Instruction {
	String info;//��������
	boolean visited;//false��ʼ��δ����ִ�� true�Ѿ�ִ�й���Ӧ�ó��ߣ�ҳ����������������������ѡ��
	int addr;//0~319
	int destPage;
	int offset;
	public Instruction(String info, int addr) {
		super();
		this.info = info;
		this.addr = addr;
		this.visited=false;
		this.destPage=addr/10;
		this.offset=addr%10;
	}
	/////////////get&&set
	public void recalcPageOffset() {
		this.destPage=addr/10;
		this.offset=addr%10;
	}
	
	public String getInfo() {
		return info;
	}
	public int getDestPage() {
		return destPage;
	}

	public int getOffset() {
		return offset;
	}
	public boolean isVisited() {
		return visited;
	}
	public int getAddr() {
		return addr;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public void setAddr(int addr) {
		this.addr = addr;
		this.destPage=addr/10;
		this.offset=addr%10;
	}
	
	
}
