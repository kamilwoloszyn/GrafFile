package net.example.cebulasoft.graffile.graphs;

import net.example.cebulasoft.graffile.GUI.GraphX;
import net.example.cebulasoft.graffile.structure.PackageInfo;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.HashMap;

public class PackageGrafAdapter {
	GraphB base;
	GraphX x;
	DirectedWeightedMultigraph baseGraph;


	public PackageGrafAdapter(HashMap<String, PackageInfo> packageInfoHashMap) {
		this.base = new GraphB(packageInfoHashMap);
		this.baseGraph = base.getGraph();
		this.x = new GraphX(baseGraph);
	}


	public void show() {
		this.x.Exec();
	}

}
