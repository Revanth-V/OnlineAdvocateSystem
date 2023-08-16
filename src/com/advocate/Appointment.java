package com.advocate;

import java.util.Date;

public class Appointment {
	private int id;
    private Date date;
    private int customerId;
    private int advocateId;
    private String serviceType;

    public Appointment(int id, Date date, int customerId, int advocateId, String serviceType) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.advocateId = advocateId;
        this.serviceType = serviceType;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAdvocateId() {
		return advocateId;
	}

	public void setAdvocateId(int advocateId) {
		this.advocateId = advocateId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}
