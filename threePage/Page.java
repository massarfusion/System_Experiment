package threePage;

public class Page {
	
	Instruction ins;//�ݴ��ָ��.-114��ʾ��ʼ״̬
	int pageNum;//���
	int destBlock;
	int offset;
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(int pageNum) {
		super();
		this.pageNum = pageNum;
		this.destBlock=pageNum/8;
		this.offset=pageNum%8;
	}
	
	public Instruction getIns() {
		return ins;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getDestBlock() {
		return destBlock;
	}
	public int getOffset() {
		return offset;
	}
	public void setDestBlock(int destBlock) {
		this.destBlock = destBlock;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public void setIns(Instruction ins) {
		this.ins = ins;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.destBlock=pageNum/10;
		this.offset=pageNum%10;
	}
	public void reCalcDestOffset() {
		this.destBlock=pageNum/10;
		this.offset=pageNum%10;
	}
}
