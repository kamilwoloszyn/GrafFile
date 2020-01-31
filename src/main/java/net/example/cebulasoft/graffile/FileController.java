package net.example.cebulasoft.graffile;

import net.example.cebulasoft.graffile.method.ClassInfo;
import net.example.cebulasoft.graffile.method.FileParser;
import net.example.cebulasoft.graffile.method.MethodDependenciesResolver;
import net.example.cebulasoft.graffile.method.MethodDependencyValidator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileController {
	FilesConnectionInfo filesCollection; // contain all information about files like: size, connections between files, name and path.

	public FileController(String catalog) {
		filesCollection = new FilesConnectionInfo();
		List<String> listFile = FileFinder.getListOfFile("java", catalog); // take all file with extension .java and set them all names to list.

		new FileInformer().getInfo(listFile, filesCollection); //get information of file and put it in to the container.

		FileGrafAdapter graf = new FileGrafAdapter(filesCollection); // make graf
		graf.show(); // show graf

	}

	/* Available commends:
	 *   -d[x],         Choose the current path of search directory.
	 *               It's can be absolute (-da) or relative (-dr).
	 *               Also this commend require path where it's should start searching.
	 *               For example:
	 *                   -dr / start searching at the same directory where program is placed
	 *                   -da c:\\Files\ start searching at specified path.
	 *
	 *   -g,         Gives ability to chose witch type of graph u want to see.
	 *               This commend require name of representation files like:
	 *                   -g files   - show relations between files
	 *                   -g methods - show relations between methods
	 *                   -g package - show relations between packages
	 */
	public static void main(String... args) {
		System.out.println(Arrays.toString(args));

		Path path = Paths.get("");
		String graphType = "file";

		boolean isPathSelected = false;
		boolean isGraphSelected = false;

		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "-dr":
					if (isPathSelected)
						throw new RuntimeException("Repeated comment: path.");
					if (++i < args.length) {
						isPathSelected = true;
						path = path.resolve(args[i]);
					} else
						throw new RuntimeException("Path not specified.");
					break;
				case "-da":
					if (isPathSelected)
						throw new RuntimeException("Repeated comment: path.");
					if (++i < args.length) {
						isPathSelected = true;
						path = Paths.get(args[i]);
					} else
						throw new RuntimeException("Path not specified.");
					break;
				case "-g":
					if (isGraphSelected)
						throw new RuntimeException("Repeated comment: graph.");
					if (++i < args.length) {
						isGraphSelected = true;
						graphType = args[i];
					}
			}
		}
		path = path.normalize();
		System.out.println(path.toAbsolutePath());

		List<String> listOfFile = FileFinder.getListOfFile(path.toAbsolutePath().toString(), ".java");
		System.out.println(listOfFile.toString());
		//FileInformer.getInfo(listOfFile, filesInfo);


		switch (graphType) {
			case "file":
				FilesConnectionInfo filesInfo = new FilesConnectionInfo();
				new FileInformer().getInfo(listOfFile, filesInfo);
				FileGrafAdapter adapter = new FileGrafAdapter(filesInfo);
				adapter.show();
				break;
			case "method":
				MethodDependenciesResolver resolver = new MethodDependenciesResolver(listOfFile, new MethodDependencyValidator(), new FileParser());
				resolver.findDependencies();
				break;
			case "package":
				break;
			default:
				throw new RuntimeException("Wrong graphType");


		}


		//System.out.println(resolver);


		//FileGrafAdapter graf = new FileGrafAdapter(i); // make graf
		//graf.show(); // show graf
	}

}

