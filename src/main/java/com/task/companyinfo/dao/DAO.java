package com.task.companyinfo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.task.companyinfo.employee.EmployeeInfo;
import com.task.companyinfo.employee.EmployeeLeaveInfo;

public class DAO {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("company");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void doRegister(Object employeeInfo) {
		entityTransaction.begin();
		entityManager.persist(employeeInfo);
		entityTransaction.commit();
	}

	public int getID() {
		String str = "select count(*) from EmployeeInfo as c";
		Query query = entityManager.createNativeQuery(str);
		Object singleResult = query.getSingleResult();
		System.out.println(singleResult);
		if (singleResult.toString() == "0") {
			return 101;
		} else {
			return Integer.parseInt(singleResult.toString()) + 101;
		}

	}

	public String doLogin(int id, String password) {
		String str = "select employeeID from EmployeeInfo employeeID";
		Query query = entityManager.createQuery(str);
		List resultList = query.getResultList();
		for (Object object : resultList) {
			EmployeeInfo empInfo = (EmployeeInfo) object;
			if (empInfo.getEmployeeID() == id && empInfo.getPassword().equals(password)) {
				System.out.println("yes");
				if (empInfo.getEmployeeType().equals("Employee")) {
					System.out.println("Emp....");
					return "employee";
				} else if (empInfo.getEmployeeType().equals("Manager")) {
					System.out.println("manager");
					return "manager";
				}

			}
		}
		return "false";

	}

	public void showEmployeeLeaveStatus(int userID) {
		String str = "select employeeID from EmployeeLeaveInfo employeeID";
		Query query = entityManager.createQuery(str);
		List resultList = query.getResultList();
		for (Object object : resultList) {
			EmployeeLeaveInfo empObj = (EmployeeLeaveInfo) object;
			if (empObj.getEmployeeID() == userID) {
				System.out.println("-----------------------------------------------------------\n" + "EmployeeID: "
						+ empObj.getEmployeeID() + "  LeaveDate :" + empObj.getLeaveDate() + " Leave-Status :"
						+ empObj.getLeaveStatus() + "\n-----------------------------------------------------------");
			}

		}

	}

	public void showAllLeave() {
		String str = "select employeeID from EmployeeLeaveInfo employeeID";
		Query query = entityManager.createQuery(str);
		List resultList = query.getResultList();
		for (Object object : resultList) {
			EmployeeLeaveInfo empObj = (EmployeeLeaveInfo) object;

			System.out.println("-----------------------------------------------------------\n" + "EmployeeID: "
					+ empObj.getEmployeeID() + "  LeaveDate :" + empObj.getLeaveDate() + " Leave-Status :"
					+ empObj.getLeaveStatus() + "\n-----------------------------------------------------------");

		}

	}
	public void update(String str) {
		System.out.println("update");
		Query query = entityManager.createQuery(str);
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
		entityManager.clear();

	}

}
