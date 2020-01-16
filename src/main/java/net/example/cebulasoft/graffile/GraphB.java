/*
 * Klasa realizuje strukture grafu
 * Brakuje krawedzi grafu.
 * */

package net.example.cebulasoft.graffile;

import org.jgrapht.graph.*;


public class GraphB {

    DirectedWeightedMultigraph B;

    public GraphB(FilesConnectionInfo d)
    {

        B= buildGraph(d);
    }

    private static DirectedWeightedMultigraph<String,DefaultWeightedEdge> buildGraph(FilesConnectionInfo d)
    {
        DirectedWeightedMultigraph<String,DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

        // extract data from hashmap
        for (FileInfo key : d.values()) {
            String name = key.getName();
            graph.addVertex(name);
        }

        for (FileInfo key : d.values()) {
            /*
             * SUGGESTED:
             *  Dodajcie do klasy FileInfo metodę która zwraca referencję, to znaczy pole "references",
             *
             */


            /*
            ZAKOMENTOWANE ŻEBY NIE SYPAŁO BŁĘDAMI !
             */

//            key.getReferences().forEach((s, integer) -> {
//                graph.addEdge(key.getName(), s);
//                graph.setEdgeWeight(key.getName(), s, integer);
//            });
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

    public DirectedWeightedMultigraph getGraph()
    {
        return B;
    }


}
