package DAO;
import java.util.List;
import DTO.Custom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Customerdao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
   public void save(Custom custom) {
	   entityTransaction.begin();
	   entityManager.persist(custom);
	   entityTransaction.commit();
   }

public List<Custom> fetch(long mob) {
	List<Custom>list =entityManager.createQuery("select X from Custom X where X.Mob=?1").setParameter(1,mob).getResultList();
	return list;
}

public List<Custom> fetch(String email) {
	List<Custom>list =entityManager.createQuery("select X from Custom X where X.Cust_email=?1").setParameter(1,email).getResultList();
	return list;
}

public Custom fetchbyCustid(long custid) {
	Custom customer=entityManager.find(Custom.class, custid);
	return customer;
}

public void update(Custom customer) {
	entityTransaction.begin();
	entityManager.merge(customer);
	entityTransaction.commit();
	
}
}
