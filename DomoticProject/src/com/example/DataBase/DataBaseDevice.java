package com.example.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.domotic.Device;
import com.example.interfaces.CRUD;

public class DataBaseDevice implements CRUD<Device> {

	private ConnectionDB connectionDB = new ConnectionDB();
	private Connection connect;
	private DataBaseSpecs dataBaseSpecs = new DataBaseSpecs();
	
	@Override
	public void register(Device device) throws SQLException {
		try {
			connect = connectionDB.connect();
			dataBaseSpecs.register(device.getDeviceSpecs());
			String sql = "INSERT INTO devices(name, id_room, is_indispensable, id_specs) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, device.getName());
			preparedStatement.setInt(2, device.getIdRoom());
			preparedStatement.setBoolean(3, device.getIsIndispensable());
			preparedStatement.setInt(4, device.getDeviceSpecs().getId());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage()); 
		} finally {
			connect.close();
		}
	}

	@Override
	public void update(Device device) throws SQLException {
		try {
			connect = connectionDB.connect();
			dataBaseSpecs.update(device.getDeviceSpecs());
			String sql = "UPDATE devices set name = ?, id_room = ?, is_indispensable = ? where id = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(4, device.getId());
			preparedStatement.setString(1, device.getName());
			preparedStatement.setInt(2, device.getIdRoom());
			preparedStatement.setBoolean(3, device.getIsIndispensable());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}	
	}
	
	@Override
	public void delete(Device device) throws SQLException {
		try {
			connect = connectionDB.connect();
			String sql = "DELETE from devices where id = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, device.getId());
			preparedStatement.executeUpdate();
			dataBaseSpecs.delete(device.getDeviceSpecs());
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}
	}

	@Override
	public List<Device> read() throws SQLException {
		List<Device> list = null;
		try {
			connect = connectionDB.connect();
			String sql = "SELECT * from devices";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			list = new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Device device = new Device();
				device.setId(resultSet.getInt("id"));
				device.setName(resultSet.getString("name"));
				device.setIdRoom(resultSet.getInt("id_room"));
				device.setIsIndispensable(resultSet.getBoolean("is_indispensable"));
				device.setDeviceSpecs(dataBaseSpecs.getSpecificDeviceSpecs(resultSet.getInt("id_specs")));
				list.add(device);
			}
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}
		return list;
	}

}
