package fiveFileSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class DirFile {
	ArrayList <DirNode> nodeArr;
	String dirName;
	public DirFile() {
		super();
		this.nodeArr=new ArrayList<DirNode>();
		// TODO Auto-generated constructor stub
	}
	public DirFile(String dirName) {
		super();
		this.dirName = dirName;
		this.nodeArr=new ArrayList<DirNode>();
	}
	
	public void AddDirNode(DirNode tmpdn) {
		this.nodeArr.add(tmpdn);
	}
	
	public DirNode FindDF(String name) {
		Iterator <DirNode> it = nodeArr.iterator(); //������
        while (it.hasNext()) {
        	DirNode tmp=it.next();
        	if (name.equals(tmp.getName())&&tmp.getType()==0) {
        		return tmp;
        	}
        	else {;}
        	
        }
        //System.out.println("\n!No such Directory!\n");
        return null;
	}//������¼�Ŀ¼�ļ�,���Ƿ���ָ������Ŀ¼�ڵ�
	
	public DirNode FindNF(String name) {
		Iterator <DirNode> it = nodeArr.iterator(); //������
        while (it.hasNext()) {
        	DirNode tmp=it.next();
        	if (name.equals(tmp.getName())&&tmp.getType()==1) {
        		return tmp;
        	}
        	else {;}
        	
        }
        //System.out.println("\n!No such Directory!\n");
        return null;
	}//���������ͨ�ļ�,���Ƿ���ָ������Ŀ¼�ڵ�
	public void deleteFile(String name) {
		if (this.nodeArr!=null) {
			int limit=this.nodeArr.size();
			for (int i=0;i<limit;i++) {
				if (this.nodeArr.get(i)==null) {
					continue;
				}
				else {
					if (name.equals(this.nodeArr.get(i).getName())&&this.nodeArr.get(i).getType()==1)
					{
						this.nodeArr.remove(i);
						return;
					}
					else {;}
				}
			}
		}
		else {
			System.out.println("Null Array!");
		}
	}//���Ҫɾ�����ļ����ڵ�DirNode
	public void deleteDir(DirNode toDelete) {
		if (this.nodeArr!=null) {
			int limit=this.nodeArr.size();
			for (int i=0;i<limit;i++) {
				if (this.nodeArr.get(i)==null) {
					continue;
				}
				else {
					if (toDelete.getName().equals(this.nodeArr.get(i).getName())&&this.nodeArr.get(i).getType()==0)
					{
						this.nodeArr.remove(i);
						return;
					}
					else {;}
				}
			}
		}
		else {
			System.out.println("Null Array!");
		}
	}//����Ŀ¼�ļ���Ŀ¼�ڵ�ɾ��
}
