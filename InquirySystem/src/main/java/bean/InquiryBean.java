package bean;

import java.sql.Date;
import java.sql.Timestamp;

public class InquiryBean {
private int id;
private String name;
private long contactNumber;
private String purpose;
private String time;
private String reference;
public String getTime() {
	return time;
}
public void setTime(String timestamp) {
	this.time = timestamp;
}
private String remark;
private String  date;
private String addBy;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getContactNumber() {
	return contactNumber;
}
public void setContactNumber(long contactNumber) {
	this.contactNumber = contactNumber;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
public String getReference() {
	return reference;
}
public void setReference(String reference) {
	this.reference = reference;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getAddBy() {
	return addBy;
}
public void setAddBy(String addBy) {
	this.addBy = addBy;
}

}


