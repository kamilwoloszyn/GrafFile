/*
 * Klasa realizuje strukture grafu
 * Brakuje krawedzi grafu.
 * */

package net.example.cebulasoft.graffile.graphs;

import net.example.cebulasoft.graffile.structure.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class GraphB {

	DirectedWeightedMultigraph B;

	public GraphB(FilesConnectionInfo d) {

		B = buildGraph(d);
	}

	public GraphB(List<ClassInfo> infos) {
		B = buildGraph(infos);
	}

	public GraphB(HashMap<String, PackageInfo> packageInfoHashMap) {
		B = buildGraph(packageInfoHashMap);
	}

	private static DirectedWeightedMultigraph<String, DefaultWeightedEdge> buildGraph(FilesConnectionInfo d) {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

		// extract data from hashmap

		for (FileInfo file : d.values()) {

			String name = file.getFullName();
			graph.addVertex(name); //add vertex to graph
		}
		for (FileInfo file : d.values()) {
			for (Iterator<Map.Entry<String, Integer>> it = file.getIteratorToReferences(); it.hasNext(); ) {
				Map.Entry<String, Integer> connection = it.next();
				DefaultWeightedEdge edge = graph.addEdge(file.getFullName(), connection.getKey());
				graph.setEdgeWeight(edge, connection.getValue());
			}
		}

		//end

		/*
		 * INFO:
		 * Poniżej znajdują się przykładowe dane do grafu wraz z krawędziami,
		 * to tak aby pokazać że sam graf działa, domyślnie zakomentowane.
		 */

		//sample data - labels

//        String x1= "one";
//        String x2= "two";
//        String x3= "three";


//        graph.addVertex(x1);
//        graph.addVertex(x2);
//        graph.addVertex(x3);


		//sample data - edges

//        DefaultWeightedEdge e = graph.addEdge(x1,x2);
//        graph.setEdgeWeight(e,1);
//        e= graph.addEdge(x2,x3);
//        graph.setEdgeWeight(e,2);

		return graph;
	}

	public DirectedWeightedMultigraph<String, DefaultWeightedEdge> buildGraph(List<ClassInfo> infos) {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

		for (ClassInfo classInfo : infos) {
			String className = classInfo.getName();
			for (MethodInfo methodInfo : classInfo.getMethods()) {
				String methodClassName = methodInfo.getClassName();
				String methodName = methodInfo.getName();
				String parameters = methodInfo.getParameters();

				String vertex = (className + " " + methodClassName + "." + methodName + "(" + parameters + ")");
				graph.addVertex(vertex);
				List methodUsed = methodInfo.getMethodsUsed();


				for (Object oneElementMethod : methodUsed) {
					String toVertex = oneElementMethod.toString();
					graph.addVertex(toVertex);
					graph.addEdge(vertex, toVertex);
				}


			}
		}
		return graph;
	}

	public DirectedWeightedMultigraph<String, DefaultWeightedEdge> buildGraph(HashMap<String, PackageInfo> packageInfoHashMap) {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

		for (Map.Entry<String, PackageInfo> entry : packageInfoHashMap.entrySet()) {

			String Vertex = entry.getValue().getName();
			graph.addVertex(Vertex);



		}

		for (Map.Entry<String, PackageInfo> entry : packageInfoHashMap.entrySet()) {

			String firstVertex = entry.getValue().getName();
			HashMap<String, Integer> packageConnectionInfo = entry.getValue().getPackageConnection();

			for (Map.Entry<String, Integer> entryPackageConnectionInfo : packageConnectionInfo.entrySet()) {
				String secondVertex = entryPackageConnectionInfo.getKey();
				DefaultWeightedEdge edge = graph.addEdge(firstVertex, secondVertex);

				graph.setEdgeWeight(edge,entryPackageConnectionInfo.getValue());

			}

		}



		return graph;
	}

	public DirectedWeightedMultigraph getGraph() {
		return B;
	}


}
