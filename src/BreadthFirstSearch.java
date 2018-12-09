import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch {

    Graph graphe;
    Queue<Arc> file;
    ArrayList<Arc> arbre;
    BitSet visite;

    private void push(int vertex) {
        for (Arc arc : graphe.outAdjacency(vertex)) file.offer(arc);
    }

    private void start(int racine) {
        visite.set(racine);
        push(racine);
        while (!file.isEmpty()) {
            explore(file.poll());
        }
    }
    
    private void explore(Arc nextArc) {
        if (visite.get(nextArc.getDest())) return;
        visite.set(nextArc.getDest());
        arbre.add(nextArc);
        push(nextArc.getDest());
    }

    private BreadthFirstSearch (Graph graphe) {
        this.graphe = graphe;
        this.file = new LinkedList<Arc>();
        this.arbre = new ArrayList<Arc>();
        this.visite = new BitSet(graphe.order);
    }

    public static ArrayList<Arc> generateTree(Graph graphe, int racine) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(graphe);
        bfs.start(racine);
        return bfs.arbre;
    }

}
