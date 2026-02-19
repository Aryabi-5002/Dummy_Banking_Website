package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DTO.BankAccount;

public class Bankdao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();	
	
	public void save(BankAccount bankaccount) {
		
		 entityTransaction.begin();
		   entityManager.persist(bankaccount);
		   entityTransaction.commit();
	}
	
	
	
    public List<BankAccount> fetch_all_bank_details()
    {
  List<BankAccount>list =entityManager.createQuery("select x from Bankaccount x ").getResultList();
   return list;
    }
    
    
    
    
    public BankAccount fetch_by_accont_no(long acnn)
    {
    BankAccount bankaccount=entityManager.find(BankAccount.class,acnn);
    return bankaccount;
    }
	public void update(BankAccount bankaccount) {
		entityTransaction.begin();
		entityManager.merge(bankaccount);
		entityTransaction.commit();
		
	}
	public BankAccount find(long acno)
	{
		BankAccount bankaccount=entityManager.find(BankAccount.class, acno);
		return bankaccount;
	}
	public List<BankAccount> fetch_by_Id(long id) 
	{
		List<BankAccount>list=(List<BankAccount>) entityManager.createQuery(
                "SELECT b FROM BankAccount b WHERE b.customer.Cust_id = :cid", BankAccount.class)
                .setParameter("cid",id)
                .getResultList();
		return list;
	
	}
	
}
