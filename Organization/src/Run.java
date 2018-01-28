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
			//создаем данные
			operation.createData();
			
			//выведем все данные
			printAll(data);
			
			System.out.println("Работники без работы");
			print(operation.getEmployeeWithoutJob());
			System.out.println();
			
			//назначим работу сотруднику без работы
			operation.setWork("ge2", "12fd4");
			System.out.println();
			
			System.out.println("Раотники без работы");
			print(operation.getEmployeeWithoutJob());
			System.out.println();
			
			System.out.println("Выведем все работы отсортированные в порядке убывания необходимого\n" +
					"времени на выполнение");
			print(operation.getSortContract());
			System.out.println();
			
			System.out.println("Выведем работы, где занимаются больше 1 рабочего");
			print(operation.getJobWhereEmplMoreOne());
			System.out.println();
			
			System.out.println("Выведем просроченные работы");
			print(operation.getWorkNotDoneOnTime());
			System.out.println();
			
		} finally {
			if (data != null) {
				data.close();
			}
		}
		//удалим файл БД
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
