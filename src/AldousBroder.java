import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
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

    private void explore() {
        int current = (int)(Math.random()*this.graph.order+1);
        Arc currentArc;
        visite.set(current);

        while(arbre.size() < graph.order-1) {
            List<Arc> adjacency  = graph.outAdjacency(current);
            currentArc = adjacency.get(ThreadLocalRandom.current().nextInt(0, adjacency.size()));
            if(!visite.get(currentArc.getDest())) {
                arbre.add(currentArc.support);
                visite.set(currentArc.getDest());
            }
            current = currentArc.getDest();
        }
    }

    public static ArrayList<Edge> generateTree(Graph graph) {
        AldousBroder algo = new AldousBroder(graph);
        algo.explore();
        return algo.arbre;
    }
}