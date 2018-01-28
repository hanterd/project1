package model;

import java.util.Date;

public class Contract {
	
	private Date start;
	private Date end;
	private Date contractEnd;
	private Job job;
	
	public Contract(Date start, Date end, Date contractEnd, Job job) {
		this.start = start;
		this.end = end;
		this.contractEnd = contractEnd;
		this.job = job;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Contract [start=" + start + ", end=" + end + ", contractEnd=" + contractEnd +
				", job=" + job.getName() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (contractEnd == null) {
			if (other.contractEnd != null)
				return false;
		} else if (!contractEnd.equals(other.contractEnd))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

}
