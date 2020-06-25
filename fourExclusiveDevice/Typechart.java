package fourExclusiveDevice;

public class Typechart {
	Equipment [] chart;//存放设备
	int total;//总共存放的设备数目
	//int globalStartAddr;//在设备表里的起始位置
	int chartType;//INT表示的种类
	int available;//目前可用的（好的，且没有分配）
	
	public void markTheBad(int index) {
		if (index<0||index>=this.chart.length) {
			System.out.println("超出界限，拒绝录入");
			return;
		}
		else {
			this.chart[index].setGood(false);
			this.available--;
		}
	}
	
	
	
	public Typechart(int total) {//一开始都是好的空闲的。
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
