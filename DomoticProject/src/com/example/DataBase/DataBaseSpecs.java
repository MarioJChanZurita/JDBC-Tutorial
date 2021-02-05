package com.example.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.domotic.DeviceSpecs;
import com.example.interfaces.CRUD;

public class DataBaseSpecs implements CRUD<DeviceSpecs>{

	private ConnectionDB connectionDB = new ConnectionDB();
	private Connection connect;
	
	@Override
	public void register(DeviceSpecs deviceSpecs) throws SQLException{
		try {
			connect = connectionDB.connect();
			String sql = "INSERT INTO device_specs(brand, model, wifi_built_in, bluetooth_built_in) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, deviceSpecs.getBrand().getBrandName());
			preparedStatement.setString(2, deviceSpecs.getModel());
			preparedStatement.setBoolean(3, deviceSpecs.getWifi());
			preparedStatement.setBoolean(4, deviceSpecs.getBluetooth());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage()); 
		} finally {
			connect.close();
		} 
	}

	@Override
	public void update(DeviceSpecs deviceSpecs) throws SQLException {
		try {
			connect = connectionDB.connect();
			String sql = "UPDATE device_specs set brand = ?, model = ?, wifi_built_in = ?, bluetooth_built_in = ? where id = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(5, deviceSpecs.getId());
			preparedStatement.setString(1, deviceSpecs.getBrand().getBrandName());
			preparedStatement.setString(2, deviceSpecs.getModel());
			preparedStatement.setBoolean(3, deviceSpecs.getWifi());
			preparedStatement.setBoolean(4, deviceSpecs.getBluetooth());	
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}	
	}

	@Override
	public void delete(DeviceSpecs deviceSpecs) throws SQLException {
		try {
			connect = connectionDB.connect();
			String sql = "DELETE from device_specs where id = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, deviceSpecs.getId());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}
	}

	@Override
	public List<DeviceSpecs> read() throws SQLException {
		List<DeviceSpecs> list = null;
		try {
			connect = connectionDB.connect();
			String sql = "SELECT * from device_specs";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			list = new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DeviceSpecs deviceSpecs = new DeviceSpecs();
				deviceSpecs.setId(resultSet.getInt("id"));
				deviceSpecs.setBrand(resultSet.getString("brand"));
				deviceSpecs.setModel(resultSet.getString("model"));
				deviceSpecs.setWifi(resultSet.getBoolean("wifi_built_in"));
				deviceSpecs.setBluetooth(resultSet.getBoolean("bluetooth_built_in"));
				list.add(deviceSpecs);
			}
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}
		return list;
	}

	public DeviceSpecs getSpecificDeviceSpecs(int idDeviceSpecs) throws SQLException {
		DeviceSpecs deviceSpecs = null;
		try {
			connect = connectionDB.connect();
			String sql = "SELECT * from device_specs WHERE id = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, idDeviceSpecs);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				deviceSpecs = new DeviceSpecs();
				deviceSpecs.setId(resultSet.getInt("id"));
				deviceSpecs.setBrand(resultSet.getString("brand"));
				deviceSpecs.setModel(resultSet.getString("model"));
				deviceSpecs.setWifi(resultSet.getBoolean("wifi_built_in"));
				deviceSpecs.setBluetooth(resultSet.getBoolean("bluetooth_built_in"));
			}
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}
		return deviceSpecs;
	}
	
}
