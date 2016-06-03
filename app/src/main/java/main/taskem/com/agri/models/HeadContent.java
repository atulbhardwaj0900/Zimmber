package main.taskem.com.agri.models;

public class HeadContent {
	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAttendingStatus() {
		return attendingStatus;
	}

	public void setAttendingStatus(String attendingStatus) {
		this.attendingStatus = attendingStatus;
	}

	private String heading, startDate, endDate, attendingStatus;

}

