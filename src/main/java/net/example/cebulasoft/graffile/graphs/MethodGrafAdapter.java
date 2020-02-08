package net.example.cebulasoft.graffile.graphs;

import net.example.cebulasoft.graffile.GUI.GraphX;
import net.example.cebulasoft.graffile.structure.ClassInfo;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.List;

public class MethodGrafAdapter {
	GraphB base;
	GraphX x;
	DirectedWeightedMultigraph baseGraph;


	public MethodGrafAdapter(List<ClassInfo> methodInfo) {
		this.base = new GraphB(methodInfo);
		this.baseGraph = base.getGraph();
		this.x = new GraphX(baseGraph);
	}


	public void show() {
		this.x.Exec();
	}

}
