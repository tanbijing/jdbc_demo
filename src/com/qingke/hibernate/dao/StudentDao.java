package com.qingke.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.qingke.hibernate.mapping.Accommodation;
import com.qingke.hibernate.mapping.Student;
import com.qingke.hibernate.mapping.StudentStatus;

public class StudentDao {
	private Session session;
	public StudentDao(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
	}
	public void query() {
		NativeQuery query = session.createNativeQuery("select * from student");
		List lst = query.getResultList();
		System.out.println(lst.size());
	}
	
	public void hibernateGet(){
		Student stu = session.get(Student.class, 1);
		System.out.println(stu);
		System.out.println(stu.status);
	}
	public void hibernateGetStatus(){
		StudentStatus status = session.get(StudentStatus.class, 1);
		System.out.println(status);
		System.out.println(status.students);
//		System.out.println(status.student);
	}
	public void hibernateCreateQuary(){
		Query<Accommodation> stu = session.createQuery("select ac from Student as st,Accommodation as ac,Apartment as ap where st.id=ac.student_id and ac.apartment_id=ap.id",Accommodation.class);
//		stu.setParameter("name", "¶þ");
		List<Accommodation> list = stu.getResultList();
		list.forEach((Accommodation ac) -> {
			Student student = session.get(Student.class,ac.student_id);
			System.out.println(student+"ap_id="+ac.apartment_id);
		});
	}
	
	public void hibernateInsert(Student stu){
		Transaction tra = session.beginTransaction();
		try{
			session.save(stu);
			tra.commit();
		}catch(HibernateException ex){
			System.out.println(ex.getMessage());
			tra.rollback();
		}
	}
	public void hibernateUpdate(Student stu){
		Transaction tra = session.beginTransaction();
		try{
			session.update(stu);
			tra.commit();
		}catch(HibernateException ex){
			System.out.println(ex.getMessage());
			tra.rollback();
		}
	}
	public void hibernateDelete(Student stu){
		Transaction tra = session.beginTransaction();
		try{
			session.delete(stu);
			tra.commit();
		}catch(HibernateException ex){
			System.out.println(ex.getMessage());
			tra.rollback();
		}
	}
	public void hibernateCriteria(){
		Criteria cr = session.createCriteria(Student.class);
		System.out.println("=============================");
		System.out.println(cr.list());
	}
	
	public void hibernateClean(){
		session.close();
	}
}
