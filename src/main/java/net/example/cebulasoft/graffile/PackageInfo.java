package net.example.cebulasoft.graffile;

import java.util.HashMap;

public class PackageInfo {
	private String name;
	private HashMap<String, Integer> packageConnection;

	public PackageInfo(String name){
		this.name = name;
		this.packageConnection = new HashMap<String, Integer>();

	}

	public void increaseWeightConnection(String packageName){

		if(packageConnection.containsKey(packageName))
			packageConnection.put(packageName, packageConnection.get(packageName) +1);
		else
			packageConnection.put(packageName, 1);
	}

	@Override
	public String toString() {
		return "PackageInfo{" +
				"name='" + name + '\'' +
				", packageConnection=" + packageConnection +
				'}';
	}
}
