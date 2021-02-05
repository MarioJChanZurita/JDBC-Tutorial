package com.example.domotic;

public class Device {
    private int id;
    private String name;
    private int idRoom;
    private boolean isIndispensable;
    private DeviceSpecs deviceSpecs;

    public Device() {
        this("name", 1, false, null);
    }

    public Device(String name, int idRoom, boolean isIndispensable, DeviceSpecs deviceSpecs) {
        this.name = name;
        this.idRoom = idRoom;
        this.isIndispensable = isIndispensable;
        this.deviceSpecs = deviceSpecs;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public boolean getIsIndispensable() {
		return isIndispensable;
	}

	public void setIsIndispensable(boolean isIndispensable) {
		this.isIndispensable = isIndispensable;
	}

	public DeviceSpecs getDeviceSpecs() {
		return deviceSpecs;
	}

	public void setDeviceSpecs(DeviceSpecs deviceSpecs) {
		this.deviceSpecs = deviceSpecs;
	}

	@Override
	public String toString() {
		String output = "";
		output = "Device: " + "\n" + 
				"Id=" + id + "\n" +
				"Name=" + name + "\n" +
				"IdRoom=" + idRoom + "\n" +
				"IsIndispensable=" + isIndispensable + "\n" +
				"DeviceSpecs=" + deviceSpecs + "\n";
		return output;
	}

	
	/*
    public boolean equals(Object compared){
        boolean output = false;
        if (this == compared){
            output = true;
        }
        if (!(compared instanceof Device)){
            output = false;
        }
        Device comparedDevice = (Device) compared;
        if (this.Id == comparedDevice.getId() &&
                this.Status == comparedDevice.getStatus() &&
                this.Name.equals(comparedDevice.getName()) &&
                this.deviceSpecs.equals(comparedDevice.deviceSpecs)){
            output = true;
        }
        return output;
    }
	*/

}  