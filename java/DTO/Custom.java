package DTO;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Custom {
@Id
@SequenceGenerator(initialValue=2001,allocationSize=1,sequenceName="Cust_id",name="Cust_id")
@GeneratedValue(generator="Cust_id")
long Cust_id;
String Cust_name;
String Gender;
String Password;
String Cust_email;
long Mob;
Date dob;
@OneToMany
List<BankAccount>list;
public long getCust_id() {
	return Cust_id;
}
public void setCust_id(long cust_id) {
	Cust_id = cust_id;
}
public String getCust_name() {
	return Cust_name;
}
public void setCust_name(String cust_name) {
	Cust_name = cust_name;
}
public String getGender() {
	return Gender;
}
public void setGender(String gender) {
	Gender = gender;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getCust_email() {
	return Cust_email;
}
public void setCust_email(String cust_email) {
	Cust_email = cust_email;
}
public long getMob() {
	return Mob;
}
public void setMob(long mob) {
	Mob = mob;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public List<BankAccount> getList() {
	return list;
}
public void setList(List<BankAccount> list) {
	this.list = list;
}


}
