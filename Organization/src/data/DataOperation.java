package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import model.Contract;
import model.Employee;
import model.Job;

public class DataOperation {
	
	private ObjectContainer data;
		
	public DataOperation(ObjectContainer data) {
		this.data = data;
	}

	public void createData() {
		Employee employee = new Employee("Петров Сергей", "Менеджер", "45fd4");
		data.store(employee);
		Employee employee2 = new Employee("Петров Иван", "Старший менеджер", "4fd4");
		data.store(employee2);
		Employee employee3 = new Employee("Петров Андрей", "Администратор", "12fd4");
		data.store(employee3);
		Employee employee4 = new Employee("Петров Владимир", "Менеджер", "d4");
		data.store(employee4);
		Employee employee5 = new Employee("Петров Илья", "Директор", "45");
		data.store(employee5);
		Employee employee6 = new Employee("Петров Петр", "Администратор", "4sd");
		data.store(employee6);
		Employee employee7 = new Employee("Петров Валерий", "Менеджер", "5i3");
		data.store(employee7);
		Employee employee8 = new Employee("Петров Святослав", "Зам. Директора", "df66");
		data.store(employee8);
		Employee employee9 = new Employee("Петров Артем", "Зам. Директора", "sdf48");
		data.store(employee9);
		
		Job job = new Job("Настрока оборудования", "ff4", "часы");
		job.addEmployee(employee);
		data.store(job);
		Job job2 = new Job("Заполнение документов", "4r4", "часы");
		job2.addEmployee(employee4, employee7);
		data.store(job2);
		Job job3 = new Job("Написать приказ", "ge2", "часы");
		data.store(job3);
		Job job4 = new Job("Оформить посылку", "223", "часы");
		data.store(job4);
		Job job5 = new Job("Провести совещание", "ka4", "часы");
		job5.addEmployee(employee8, employee9);
		data.store(job5);
		Job job6 = new Job("Командировка", "asd", "дни");
		job6.addEmployee(employee5);
		data.store(job6);
		
		Contract contract = new Contract(parseDate("01-12-2017"), parseDate("02-12-2017"), parseDate("01-12-2017"), job);
		data.store(contract);
		Contract contract2 = new Contract(parseDate("05-12-2017"), parseDate("07-12-2017"), parseDate("08-12-2017"), job2);
		data.store(contract2);
		Contract contract3 = new Contract(parseDate("26-12-2017"), null, parseDate("26-12-2017"), job3);
		data.store(contract3);
		Contract contract4 = new Contract(parseDate("24-12-2017"), null, parseDate("24-12-2017"), job4);
		data.store(contract4);
		Contract contract5 = new Contract(parseDate("20-12-2017"), parseDate("20-12-2017"), parseDate("20-12-2017"), job5);
		data.store(contract5);
		Contract contract6 = new Contract(parseDate("25-12-2017"), null, parseDate("30-12-2017"), job6);
		data.store(contract6);
	}
	
	public ObjectSet<Job> getJobWhereEmplMoreOne() {
		return data.query(new Predicate<Job>() {

			@Override
			public boolean match(Job arg) {
				return arg.getListEmployees().size() > 1;
			}
		});
	}
	
	public ObjectSet<Contract> getSortContract() {
		Query query = data.query();
		query.constrain(Contract.class);
		query.sortBy(new Comparator<Contract>() {

			@Override
			public int compare(Contract o1, Contract o2) {
				long one = o1.getContractEnd().getTime() - o1.getStart().getTime();
				long two = o2.getContractEnd().getTime() - o2.getStart().getTime();
				if (one < two) {
					return 1;
				} else if (one == two) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		return query.execute();
	}
	
	public ObjectSet<Contract> getWorkNotDoneOnTime() {
		return data.query(new Predicate<Contract>() {

			@Override
			public boolean match(Contract arg) {
				if (arg.getEnd().getTime() > arg.getContractEnd().getTime()) {
					return true;
				}
				return false;
			}
		});
	}
	
	public ObjectSet<Employee> getEmployeeWithoutJob() {
		ObjectSet<Job> j = data.query(new Predicate<Job>() {

			@Override
			public boolean match(Job arg) {
				if (arg.getListEmployees() != null && !arg.getListEmployees().isEmpty()) {
					return true;
				}
				return false;
			}
		});
		return data.query(new Predicate<Employee>() {

			@Override
			public boolean match(Employee arg) {
				for (Job job : j) {
					for (Employee emp : job.getListEmployees()) {
						if (emp.equals(arg)) {
							return false;
						}
					}
				}
				return true;
			}
		});
	}
	
	public void setWork(String cipherJob, String numEmployee) {
		Job job = getJobByCipher(cipherJob);
		if (job != null) {
			job.addEmployee(getEmployeeByNumber(numEmployee));
		}
		data.store(job);
	}
	
	public Job getJobByCipher(String cipher) {
		ObjectSet<Job> jobs = data.query(new Predicate<Job>() {

			@Override
			public boolean match(Job arg) {
				return arg.getCipher().equals(cipher);
			}
		});
		if (!jobs.isEmpty()) {
			return jobs.next();
		}
		return null;
	}
	
	public Employee getEmployeeByNumber(String number) {
		ObjectSet<Employee> emp = data.query(new Predicate<Employee>() {

			@Override
			public boolean match(Employee arg) {
				return arg.getNumber().equals(number);
			}
		});
		if (!emp.isEmpty()) {
			return emp.next();
		}
		return null;
	}
	
	public static Date parseDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern("dd-MM-yyyy");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
