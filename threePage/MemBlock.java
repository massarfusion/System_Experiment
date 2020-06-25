package threePage;

public class MemBlock {
	int blockNum;//编号
	int lastIOTime;//最近写入的时刻
	Page pageSave;//暂存的页
	public MemBlock(int blockNum) {
		super();
		this.blockNum = blockNum;
		this.lastIOTime=-114;//特殊初始值
	}
	public int getBlockNum() {
		return blockNum;
	}
	public int getLastIOTime() {
		return lastIOTime;
	}
	public Page getPageSave() {
		return pageSave;
	}
	public void setBlockNum(int blockNum) {
		this.blockNum = blockNum;
	}
	public void setLastIOTime(int lastIOTime) {
		this.lastIOTime = lastIOTime;
	}
	public void setPageSave(Page pageSave) {
		this.pageSave = pageSave;
	}
	
	
}
