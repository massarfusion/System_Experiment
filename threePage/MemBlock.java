package threePage;

public class MemBlock {
	int blockNum;//���
	int lastIOTime;//���д���ʱ��
	Page pageSave;//�ݴ��ҳ
	public MemBlock(int blockNum) {
		super();
		this.blockNum = blockNum;
		this.lastIOTime=-114;//�����ʼֵ
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
