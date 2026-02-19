package DTO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class BankAccount {
@Id
@SequenceGenerator(initialValue=04456,allocationSize=1,sequenceName="Acc_no",name="Acc_no")
@GeneratedValue(generator="Acc_no")
long Acc_no;
String Acc_type;
double Ammount;
double Acc_limit;
boolean Status;
@ManyToOne
@JoinColumn(name = "custom_Cust_id")  // foreign key column
Custom custom;
@OneToMany (cascade = CascadeType.ALL)
List<BankTransaction> banktransaction;
public long getAcc_no() {
	return Acc_no;
}
public void setAcc_no(long acc_no) {
	Acc_no = acc_no;
}
public String getAcc_type() {
	return Acc_type;
}
public void setAcc_type(String acc_type) {
	Acc_type = acc_type;
}
public double getAmmount() {
	return Ammount;
}
public void setAmmount(double ammount) {
	Ammount = ammount;
}
public double getAcc_limit() {
	return Acc_limit;
}
public void setAcc_limit(double acc_limit) {
	Acc_limit = acc_limit;
}
public boolean isStatus() {
	return Status;
}
public void setStatus(boolean status) {
	Status = status;
}
public Custom getCustom() {
	return custom;
}
public void setCustom(Custom custom) {
	this.custom = custom;
}
public List<BankTransaction> getBanktransaction() {
	return banktransaction;
}
public void setBanktransaction(List<BankTransaction> banktransaction) {
	this.banktransaction = banktransaction;
}


	
}
