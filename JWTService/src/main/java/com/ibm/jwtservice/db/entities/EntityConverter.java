package com.ibm.jwtservice.db.entities;

public interface EntityConverter <T> {
	 public T getEntity( int id);
	}