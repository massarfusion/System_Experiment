package fiveFileSystem;

import java.util.Scanner;

public class Manager {
	DirNode root;//Je suis Alpla...
	DirFile curdf;//current DirFile
	Scanner in =new Scanner(System.in);
	public Manager() {
		super();
		this.root=new DirNode();
		root.AppointDF("ROOT");
		this.curdf=this.root.getDf();
	}
	public void Main() {
		System.out.println("CD <Ŀ¼��>�任Ŀ¼\nCREATE <�ļ���> <�ļ���>�����ļ�\n"
				+ "DEL <�ļ���>ɾ���ļ�\nLSALL ȫ���б�\n"
				+ "MD <Ŀ¼����>����Ŀ¼\n"
				+ "RD <Ŀ¼��>�Ƴ�Ŀ¼\n"+
				"HELP ��ʾ������Ϣ");
		while (true) {
			System.out.println("\n����������ָ��س�������");
			String op=in.next();
			if (op.equalsIgnoreCase("CD")) {
				String name=in.next();
				this.ChangeDir(name);
			}
			else if (op.equalsIgnoreCase("CREATE")) {
				String name=in.next();
				int size=in.nextInt();
				NormFile nf=new NormFile(name,size);
				this.CreateNF(nf);
			}
			else if (op.equalsIgnoreCase("DEL")) {
				String name=in.next();
				this.DeleteFile(name);
			}
			else if (op.equalsIgnoreCase("LSALL")) {
				this.displayDir(root);
			}
			else if (op.equalsIgnoreCase("MD")) {
				String name=in.next();
				this.MakeDir(name);
			}
			else if (op.equalsIgnoreCase("RD")) {
				String name=in.next();
				this.removeDir(name);
			}
			else if (op.equalsIgnoreCase("HELP")) {
				System.out.println("CD <Ŀ¼��>�任Ŀ¼\nCREATE <�ļ���> <�ļ���>�����ļ�\n"
						+ "DEL <�ļ���>ɾ���ļ�\nLSALL ȫ���б�\n"
						+ "MD <Ŀ¼����>����Ŀ¼\nRD <Ŀ¼��>�Ƴ�Ŀ¼");
			}
			else {
				System.out.println("Wrong Operant please check again");
			}
		}
//		String op=in.next();
//		if (op.equalsIgnoreCase("CD")) {
//			String name=in.next();
//			this.ChangeDir(name);
//		}
//		else if (op.equalsIgnoreCase("CREATE")) {
//			String name=in.next();
//			int size=in.nextInt();
//			NormFile nf=new NormFile(name,size);
//			this.CreateNF(nf);
//		}
//		else if (op.equalsIgnoreCase("DEL")) {
//			String name=in.next();
//			this.DeleteFile(name);
//		}
//		else if (op.equalsIgnoreCase("LSALL")) {
//			this.displayDir(root);
//		}
//		else if (op.equalsIgnoreCase("MD")) {
//			String name=in.next();
//			this.MakeDir(name);
//		}
//		else if (op.equalsIgnoreCase("RD")) {
//			String name=in.next();
//			this.removeDir(name);
//		}
//		else {
//			System.out.println("Wrong Operant please check again");
//		}
	}//��Ҫ��������
	public void ChangeDir(String dirName) {
		DirNode tmpdn=curdf.FindDF(dirName);
		if (tmpdn==null) {
			System.out.println("\n!No such Directory!\n");
			return;
		}else {
			System.out.println("Directory "+tmpdn.getName()+"has been found.JUMPING...");
			this.curdf=tmpdn.getDf();
		}
	}//CD
	public void CreateNF(NormFile tmpnf) {
		DirNode node=new DirNode();
		node.AppointNF(tmpnf);
		this.curdf.AddDirNode(node);
	}//CREATE
	public void DeleteFile(String fileName) {
		DirNode tmpdn=this.curdf.FindNF(fileName);
		if (tmpdn==null) {
			System.out.println("No such File.");
		}
		else {
			this.curdf.deleteFile(tmpdn.name);
			System.out.println("File founded and deleted.");
		}
	}//DEL
	public void displayDir(DirNode startNode) {
		DirFile tmpdf=startNode.getDf();
		System.out.println("-----------Dir name "+tmpdf.dirName+" displaying-----------");
		int limit=tmpdf.nodeArr.size();
		for (int i=0;i<limit;i++) {
			if (tmpdf.nodeArr.get(i)!=null) {
				if (tmpdf.nodeArr.get(i).getType()==1) {
					System.out.println("File,name as "+tmpdf.nodeArr.get(i).getName());
				}
				else if (tmpdf.nodeArr.get(i).getType()==0) {
					displayDir(tmpdf.nodeArr.get(i));//�ݹ�
				}else {
					;
				}
			}else {
				;
			}
		}
		System.out.println("-----------Dir name "+tmpdf.dirName+" display over-----------");
	}//LSALL
	public void MakeDir(String name) {
		DirNode node=new DirNode();
		node.AppointDF(name);
		this.curdf.AddDirNode(node);
		System.out.println("MD finished.");
	}//MD
	public  void removeDir(String dirName) {
		DirNode toDelete=this.curdf.FindDF(dirName);
		if (toDelete==null) {
			System.out.println("No such Dir.");
		}
		else {
		//	this.curdf.deleteFile(tmpdn.name);
			this.curdf.deleteDir(toDelete);
			System.out.println("Dir founded and removed.");
		}
	}//RD
	
}
