package com.example.domotic;

public class DeviceSpecs{
	
	private int id;
    private Brand brand;
    private String model;
    private boolean wifiBuiltIn;
    private boolean bluetoothwifiBuiltIn;

    public DeviceSpecs() {
    	this(0, null, "defaultModel", false, false);
    }
    
    public DeviceSpecs(int id, Brand brand, String model, boolean wifiBuiltIn, boolean bluetoothwifiBuiltIn){
    	this.id = id;
        this.brand = brand;
        this.model = model;
        this.wifiBuiltIn = wifiBuiltIn;
        this.bluetoothwifiBuiltIn = bluetoothwifiBuiltIn;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBrand(String brandName){
		switch (brandName){
		case "LG":
			this.brand = Brand.LG;
			break;
		case "Panasonic":
			this.brand = Brand.PANASONIC;
			break;
		case "Sony":
			this.brand = Brand.SONY;
			break;
		case "Daewo":
			this.brand = Brand.DAEWO;
			break;
		case "Carrier":
			this.brand = Brand.CARRIER;
			break;
		case "York":
			this.brand = Brand.YORK;
			break;
		}
    }

    public Brand getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public boolean getBluetooth(){
        return this.bluetoothwifiBuiltIn;
    }

    public void setBluetooth(boolean bluetooth){
        this.bluetoothwifiBuiltIn = bluetooth;
    }

    public boolean getWifi(){
        return this.wifiBuiltIn;
    }

    public void setWifi(boolean wifi){
        this.wifiBuiltIn = wifi;
    }

	@Override
	public String toString() {
		String output = "";
		output ="\n" + 
				"	Id=" + id + "\n" + 
				"	Brand=" + brand + "\n" + 
				"	Model=" + model + "\n" + 
				"	WifiBuiltIn=" + wifiBuiltIn + "\n" + 
				"	BluetoothwifiBuiltIn=" + bluetoothwifiBuiltIn;
		return output;
	}

    /*
    public boolean equals(DeviceSpecs deviceSpecs){
        boolean output = false;
        if (deviceSpecs != null &&
                this.brand.equals(deviceSpecs.getBrand()) &&
                this.model == deviceSpecs.getModel()){
            output = true;
        }
        return output;
    }
    */
}

