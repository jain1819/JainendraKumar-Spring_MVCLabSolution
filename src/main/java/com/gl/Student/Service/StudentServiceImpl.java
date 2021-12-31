package com.gl.Student.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gl.Student.Entity.Student;

@Repository
public class StudentServiceImpl implements StudentService{

	private SessionFactory sessionFactory;
	
	//create session
	
	private Session session;
	
	@Autowired
	
	StudentServiceImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		try {
			session= sessionFactory.getCurrentSession();
		}catch(HibernateException e) {
			session = sessionFactory.openSession();
		}
	}
	
	@Transactional
	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		
		Transaction tx =session.beginTransaction();
		//save transaction
		session.saveOrUpdate(student);
		tx.commit();
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		
		Transaction tx =session.beginTransaction();
		//find all the records from the database table
		List<Student> students = session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	@Override
	public Student findById(int id) {
		// TODO Auto-generated method stub
		Student students = new Student();
		Transaction tx =session.beginTransaction();
		//find record with Id from the database table
		students=session.get(Student.class,id);
		tx.commit();
		return students;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx =session.beginTransaction();
		//get transaction
		Student student = session.get(Student.class, id);
		session.delete(student);
		tx.commit();
		
	}

	@Override
	public void print(List<Student> student) {
		// TODO Auto-generated method stub
		
		for (Student b : student) {
			System.out.println(b);
		}
		
	}

}
