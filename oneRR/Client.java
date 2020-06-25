package oneRR;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conductor c1=new Conductor();
		PCB p1=new PCB ("one",5);
		PCB p2=new PCB ("two",5);
		PCB p3=new PCB ("three",3);
		PCB p4=new PCB ("four",2);
		PCB p5=new PCB ("five",6);
		c1.append(p1);
		c1.append(p2);
		c1.append(p3);
		c1.append(p4);
		c1.append(p5);
		c1.execute();
		
		
	}

}
