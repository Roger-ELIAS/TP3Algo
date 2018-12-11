import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AldousBroder {
    Graph graph;
    ArrayList<Edge> arbre ;
    BitSet visite;

    public AldousBroder(Graph graph) {
        this.graph = graph;
        this.arbre = new ArrayList<>();
        this.visite = new BitSet(graph.order+1);
    }

    private void aldous() {
        int sommetActuel = (int)(Math.random()*this.graph.order+1);
        visite.set(sommetActuel);

        while(arbre.size() < graph.order-1) {
            List<Arc> adjacency  = graph.outAdjacency(sommetActuel);
            Arc arc = adjacency.get((int)(Math.random()*adjacency.size()));
            visit(arc);
            sommetActuel=arc.getDest();

        }
    }

    private void visit(Arc arc){
        if(!visite.get(arc.getDest())) {
            arbre.add(arc.support);
            visite.set(arc.getDest());
        }
    }

    public static ArrayList<Edge> generateTree(Graph graph) {
        AldousBroder algo = new AldousBroder(graph);
        algo.aldous();
        return algo.arbre;
    }
}