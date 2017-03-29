package com.qingke.hibernate.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accommodation")
public class Accommodation {
	@Id
	@Column(name="id")
	public Integer id;
	@Column(name="student_id")
	public Integer student_id;
	@Column(name="apartment_id")
	public Integer apartment_id;
}
