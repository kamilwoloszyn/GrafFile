package net.example.cebulasoft.graffile;

import net.example.cebulasoft.graffile.method.ClassInfo;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.List;

public class MethodGrafAdapter {
    GraphB base;
    GraphX x;
    DirectedWeightedMultigraph baseGraph;


    public MethodGrafAdapter(List<ClassInfo> methodInfo)
        {
            this.base= new GraphB(methodInfo);
            this.baseGraph = base.getGraph();
            this.x= new GraphX(baseGraph);
        }


        void show()
        {
            this.x.Exec();
        }

}
