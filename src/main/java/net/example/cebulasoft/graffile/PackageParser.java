package net.example.cebulasoft.graffile;

import net.example.cebulasoft.graffile.method.ClassInfo;
import net.example.cebulasoft.graffile.method.MethodCall;
import net.example.cebulasoft.graffile.method.MethodInfo;

import java.util.HashMap;
import java.util.List;

public class PackageParser {
	FilesConnectionInfo connectionInfo;
	List<ClassInfo> classInfos;
	public PackageParser(FilesConnectionInfo connectionInfo, List<ClassInfo> classList){
		this.connectionInfo = connectionInfo;
		this.classInfos = classList;
	}

	public HashMap<String, PackageInfo> parse(){
		HashMap<String, PackageInfo> packageList = new HashMap<>();
		for (ClassInfo classInfo : classInfos) {
			FileInfo fileInfo = connectionInfo.get(classInfo.getAbsolutePath());
			String packageName = fileInfo.getPackageName();
			PackageInfo packageInfo = packageList.get(packageName);

			if(packageInfo == null){
				packageInfo = new PackageInfo(packageName);
				packageList.put(packageName, packageInfo);
			}

			for (MethodInfo methodInfo: classInfo.getMethods()) {
				for (MethodCall methodCall: methodInfo.getMethodsUsed()) {
					//System.out.println(methodCall.getClassName());
					String pathClass = "";
					int calls = methodCall.getNumberOfCalls();
					for (ClassInfo classInfo1: classInfos) {
						//System.out.println("Test: "+ classInfo1.getName());
						if(classInfo1.getName() != null && classInfo1.getName().equals(methodCall.getClassName())) {
							pathClass = classInfo1.getAbsolutePath();
							break;
						}
					}
					FileInfo methodClass = connectionInfo.get(pathClass);
					System.out.println(methodClass);
					String methodPackageName = methodClass.getPackageName();
					if(!packageName.equals(methodPackageName)){
						System.out.println("Przed: " +packageInfo);
						packageInfo.increaseWeightConnection(methodPackageName, calls);
						System.out.println("Po: " + packageInfo);
					}
				}
			}
		}
		return packageList;
	}
}
