package model;

import java.util.ArrayList;
import java.util.List;

public class Job {
	
	private String name;
	private String cipher;
	private String laboriousness; //трудоемкость
	private List<Employee> listEmployees;
	
	public Job(String name, String cipher, String laboriousness) {
		this(name, cipher, laboriousness, new ArrayList<Employee>());
	}
	
	public Job(String name, String cipher, String laboriousness, List<Employee> listEmployees) {
		this.name = name;
		this.cipher = cipher;
		this.laboriousness = laboriousness;
		this.listEmployees = listEmployees;
	}
	
	public boolean addEmployee(Employee... employee) {
		if (listEmployees != null) {
			for (Employee e : employee) {
				listEmployees.add(e);
			}
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	public String getLaboriousness() {
		return laboriousness;
	}

	public void setLaboriousness(String laboriousness) {
		this.laboriousness = laboriousness;
	}

	public List<Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(List<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	@Override
	public String toString() {
		return "Job [name=" + name + ", cipher=" + cipher + ", laboriousness=" + laboriousness + ", listEmployees="
				+ listEmployees + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (cipher == null) {
			if (other.cipher != null)
				return false;
		} else if (!cipher.equals(other.cipher))
			return false;
		if (laboriousness == null) {
			if (other.laboriousness != null)
				return false;
		} else if (!laboriousness.equals(other.laboriousness))
			return false;
		if (listEmployees == null) {
			if (other.listEmployees != null)
				return false;
		} else if (!listEmployees.equals(other.listEmployees))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
