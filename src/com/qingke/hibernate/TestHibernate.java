package com.qingke.hibernate;

import com.qingke.hibernate.dao.StudentDao;
import com.qingke.hibernate.mapping.Student;

public class TestHibernate {

	public static void main(String[] args) {
		testStudent();
	}

	public static void testStudent() {
		StudentDao dao = new StudentDao();
		System.out.println("=============query============");
		dao.query();
		System.out.println("=============get student============");
		dao.hibernateGet();
		System.out.println("=============get student status============");
		dao.hibernateGetStatus();
		System.out.println("=============createQuery============");
		dao.hibernateCreateQuary();
		System.out.println("=============insert============");
		Student stu = new Student();
		stu.id    = 10011; 
		stu.sid   = "10006";
		stu.last  = "jing";
		stu.first = "tan";
		stu.gender = "ÄÐ";
		stu.email = "562@qq.com";
//		stu.student_status_id = 1;
		stu.date_of_birth = "2016-10-10";
//		dao.hibernateInsert(stu);
		System.out.println("=============update============");
//		stu.gender = "Å®";
//		dao.hibernateUpdate(stu);
		System.out.println("=============delete============");
//		dao.hibernateDelete(stu);
//		dao.hibernateClean();
		System.out.println("==============»º´æ===============");
		dao.hibernateGet();
		dao.hibernateClean();
		System.out.println("==============»º´æ1===============");
		dao = new StudentDao();
		dao.hibernateGet();
		dao.hibernateClean();
		
	}
}
