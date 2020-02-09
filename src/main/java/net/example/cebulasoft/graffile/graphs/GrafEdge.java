package net.example.cebulasoft.graffile.graphs;

import org.jgrapht.graph.DefaultWeightedEdge;

public class GrafEdge extends DefaultWeightedEdge {
    private int weight;
    GrafEdge(int weight)
    {
        this.weight=weight;
    }

    @Override
    public String toString() {
        return Integer.toString(weight);
    }
}
