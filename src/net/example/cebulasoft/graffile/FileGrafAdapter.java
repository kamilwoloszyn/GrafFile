/*
 * Połaczenie GraphX i GraphB
 */
package net.example.cebulasoft.graffile;

import org.jgrapht.graph.DirectedWeightedMultigraph;

public class FileGrafAdapter {
    GraphB base;
    GraphX x;
    DirectedWeightedMultigraph baseGraph;

    public FileGrafAdapter(FilesConnectionInfo f)
    {
        this.base= new GraphB(f);
        this.baseGraph = base.getGraph();
        this.x= new GraphX(baseGraph);
    }


    void show()
    {
        this.x.Exec();
    }
}
