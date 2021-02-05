package com.example.domotic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.DataBase.DataBaseDevice;
import com.example.interfaces.CRUD;

public class App {

	public static void main(String[] args) {
		DeviceSpecs deviceSpecs = new DeviceSpecs(12, Brand.DAEWO, "Mini Split", true, false);
		Device device = new Device("TV Apoquinto", 2, true, deviceSpecs);
		//List<Device> devicesList = new ArrayList<>();
				
		try {
			CRUD<Device> Device = new DataBaseDevice();
			Device.register(device);
			//device.setId(19);
			//Device.update(device);
			//Device.delete(device);
			//devicesList = Device.read();
		}catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		/*
		for (Device element:devicesList) {
			System.out.println(element);
		}
		*/
	}

}
