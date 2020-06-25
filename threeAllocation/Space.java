package threeAllocation;

public class Space {
	int start;//起始地址
	int len;//长度
	int status;//0表示未分配，1表示空闲
	public Space(int start, int len) {
		super();
		this.start = start;
		this.len = len;
		this.status = 0;
	}
	public int getStart() {
		return start;
	}
	public int getLen() {
		return len;
	}
	public int getStatus() {
		return status;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int compareTo(Space oneSpace) {
		return Double.compare(this.getStart(),oneSpace.getStart());
	}
	
}
