package threePage;

public class Instruction {
	String info;//用于区分
	boolean visited;//false初始或未进入执行 true已经执行过，应该撤走（页表）或者跳过（主程序的随机选择）
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
