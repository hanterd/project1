import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import data.DataOperation;

public class Run {

	public static void main(String[] args) {
		ObjectContainer data = null;
		try {
			data = Db4oEmbedded.openFile("organization");
			DataOperation operation = new DataOperation(data);
			//������� ������
			operation.createData();
			
			//������� ��� ������
			printAll(data);
			
			System.out.println("��������� ��� ������");
			print(operation.getEmployeeWithoutJob());
			System.out.println();
			
			//�������� ������ ���������� ��� ������
			operation.setWork("ge2", "12fd4");
			System.out.println();
			
			System.out.println("�������� ��� ������");
			print(operation.getEmployeeWithoutJob());
			System.out.println();
			
			System.out.println("������� ��� ������ ��������������� � ������� �������� ������������\n" +
					"������� �� ����������");
			print(operation.getSortContract());
			System.out.println();
			
			System.out.println("������� ������, ��� ���������� ������ 1 ��������");
			print(operation.getJobWhereEmplMoreOne());
			System.out.println();
			
			System.out.println("������� ������������ ������");
			print(operation.getWorkNotDoneOnTime());
			System.out.println();
			
		} finally {
			if (data != null) {
				data.close();
			}
		}
		//������ ���� ��
		new File("organization").delete();
		
	}
	
	public static void printAll(ObjectContainer data) {
		System.out.println("----------------------------------------------------");
		Query q = data.query();
		q.execute().forEach(System.out::println);
		System.out.println("----------------------------------------------------");
		System.out.println();
	}
	
	public static void print(ObjectSet<?> result) {
		System.out.println("----------------------------------------------------");
		result.forEach(System.out::println);
		System.out.println("----------------------------------------------------");
	}
}
