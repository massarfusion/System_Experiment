package fourExclusiveDevice;

public class Typechart {
	Equipment [] chart;//����豸
	int total;//�ܹ���ŵ��豸��Ŀ
	//int globalStartAddr;//���豸�������ʼλ��
	int chartType;//INT��ʾ������
	int available;//Ŀǰ���õģ��õģ���û�з��䣩
	
	public void markTheBad(int index) {
		if (index<0||index>=this.chart.length) {
			System.out.println("�������ޣ��ܾ�¼��");
			return;
		}
		else {
			this.chart[index].setGood(false);
			this.available--;
		}
	}
	
	
	
	public Typechart(int total) {//һ��ʼ���ǺõĿ��еġ�
		super();
		this.total = total;
		this.chart=new Equipment[total];
		this.available=this.total;
	}

	public Typechart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Equipment[] getChart() {
		return chart;
	}
	public int getTotal() {
		return total;
	}
	public int getChartType() {
		return chartType;
	}
	public int getAvailable() {
		return available;
	}
	public void setChart(Equipment[] chart) {
		this.chart = chart;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setChartType(int chartType) {
		this.chartType = chartType;
	}
	public void setAvailable(int available) {
		this.available = available;
	}



	public void allocate(String name, int addr) {
		// TODO Auto-generated method stub
		Equipment tmpeq=this.chart[addr];
		if (tmpeq.isGood()&&!tmpeq.isInUse()) {
			tmpeq.setOccupant(name);
			tmpeq.setInUse(true);
			this.available--;
		}
		else {
			System.out.println("Equipment not available at this moment \nPlease release it or wait until it's repaired.");}
	}



	public void release(String name) {
		// TODO Auto-generated method stub
		for (Equipment tmpeq:this.chart) {
			if (tmpeq.compareOccupantName(name)) {
				tmpeq.setInUse(false);
				tmpeq.setOccupant("");
				this.available++;
				return;
			}
			else {continue;}
		}
		System.out.println("No result released Please check your input.");
		return;
	}
	
	
}
