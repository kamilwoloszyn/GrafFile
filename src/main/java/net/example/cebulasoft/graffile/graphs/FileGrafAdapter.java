/*
 * Połaczenie GraphX i GraphB
 */
package net.example.cebulasoft.graffile.graphs;

import net.example.cebulasoft.graffile.GUI.GraphX;
import net.example.cebulasoft.graffile.structure.FilesConnectionInfo;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class FileGrafAdapter {
	GraphB base;
	GraphX x;
	DirectedWeightedMultigraph baseGraph;

	public FileGrafAdapter(FilesConnectionInfo f) {
		this.base = new GraphB(f);
		this.baseGraph = base.getGraph();
		this.x = new GraphX(baseGraph);
	}


	public void show() {
		this.x.Exec();
	}
}
