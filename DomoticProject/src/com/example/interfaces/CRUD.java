package com.example.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CRUD<T> {

	public void register(T device) throws SQLException;
	public void update(T device) throws SQLException;
	public void delete(T device) throws SQLException;
	public List<T> read() throws SQLException;
	
}
