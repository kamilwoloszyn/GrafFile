package net.example.cebulasoft.graffile;

import java.util.HashMap;

public class PackageInfo {
	private String name;
	private HashMap<String, Integer> packageConnection;

	public PackageInfo(String name){
		this.name = name;
		this.packageConnection = new HashMap<String, Integer>();

	}

	public void increaseWeightConnection(String packageName, int i){

		if(packageConnection.containsKey(packageName))
			packageConnection.put(packageName, packageConnection.get(packageName) + i);
		else
			packageConnection.put(packageName, i);
	}

	public String getName() {
		return name;
	}

	public HashMap<String, Integer> getPackageConnection() {
		return packageConnection;
	}

	@Override
	public String toString() {
		return "PackageInfo{" +
				"name='" + name + '\'' +
				", packageConnection=" + packageConnection +
				'}';
	}
}
