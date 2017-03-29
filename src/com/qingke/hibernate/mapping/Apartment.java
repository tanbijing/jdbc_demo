package com.qingke.hibernate.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="apartment")
public class Apartment {
	@Id
	@Column(name="id")
	public Integer id;
	@Column(name="room")
	public String room;
}
