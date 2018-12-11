import java.util.*;

public class ParcoursAleatoire {
    Graph graph;
    Stack<Arc> file;
    ArrayList<Arc> arbre;
    BitSet visite;

    public ParcoursAleatoire(Graph graph) {
        this.graph = graph;
        file = new Stack<Arc>();
        arbre  = new ArrayList<Arc>();
        visite = new BitSet(graph.order+1);
    }

    private void parcoursAleatoire() {
        int randomsommet = (int)(Math.random()*graph.order+1);
        visite.set(randomsommet);
        add(randomsommet);
        while(!file.isEmpty())
            explore(file.pop());
    }

    private void add(int sommet){
        List<Arc> adjacency = new ArrayList<>(graph.outAdjacency(sommet));
        Collections.shuffle(adjacency);
        for(Arc arc : adjacency){
            file.push(arc);
        }
    }

    private void explore(Arc nextArc) {
        if(visite.get(nextArc.getDest())) return;
        visite.set(nextArc.getDest());
        arbre.add(nextArc);
        add(nextArc.getDest());
    }

    public static ArrayList<Arc> generateTree(Graph graph) {
        ParcoursAleatoire algo = new ParcoursAleatoire(graph);
        algo.parcoursAleatoire();
        return algo.arbre;
    }
}
