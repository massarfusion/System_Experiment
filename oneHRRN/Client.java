package oneHRRN;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//public Conductor(int printer, int tape, int volumn)
		Conductor c1=new Conductor(10, 10, 20);
		//public JCB(String name, int size, int pReq, int tReq, int estTime, int arriveTime)
		JCB block1=new JCB("one",   5,  1,  2, 5, 2) ;
		JCB block2=new JCB("two",   5,  3,  6, 1, 9) ;
		JCB block3=new JCB("three", 15, 10, 3, 3, 5) ;
		JCB block4=new JCB("four", 1, 6, 3, 3, 5) ;
		JCB block5=new JCB("five", 15, 10, 3, 3, 5) ;
		c1.append(block1);
		c1.append(block2);
		c1.append(block3);
		c1.append(block4);
		c1.append(block5);
		c1.execute();
		block1.toString();
	}

}
