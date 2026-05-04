package com.darshan.beans;

import java.util.Date;

public class HistoryBean extends BookingDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String transId;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	private Date tdate;

	public Date getTdate() {
	    return tdate;
	}

	public void setTdate(Date tdate) {
	    this.tdate = tdate;
	}


}
