import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;


public class PoidsMinimumAleatoire {

    Graph graphe;
    PriorityQueue<Arc> file;
    ArrayList<Arc> arbre;
    BitSet visite;
    int[] cout;
    
    private void push(int vertex) {
        for (Arc arc : graphe.outAdjacency(vertex)) file.offer(arc);
    }

    private void start(int racine) {
    	cout[racine]=0;
        visite.set(racine);
        push(racine);
        for(LinkedList<Arc> aa: graphe.outAdjacency){
			for(Arc a: aa){
				file.add(a);
			}
		}
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

    private PoidsMinimumAleatoire () {
    	Comparator<Arc> comparator = new Comparator<Arc>() {
			public int compare(Arc arg0, Arc arg1) {
				return cout[arg0.getSource()]-cout[arg1.getSource()];
			}
        };
        this.file = new PriorityQueue<Arc>(comparator);
        this.arbre = new ArrayList<Arc>();
        this.visite = new BitSet(graphe.order);
        this.cout = new int[graphe.order];
        for(int i=0;i<graphe.order;++i)
        	cout[i]=50000;
    }
    
    private void setGraphe(Graph graphe){
        this.graphe = graphe;
    }
    
	private Graph poidsAleatoire(Graph graphe2) {
		for(LinkedList<Edge> ee: graphe.adjacency){
			for(Edge e: ee){
				e.weight = ThreadLocalRandom.current().nextDouble(0, 1);
			}
		}
		return graphe2;
	}
	
	private int voisinPlusPetitPoid(ArrayList<Arc> voisins){
		double min = 1;
		int sce = 0;
		for(Arc a : voisins){
			if(a.support.weight<min && visite.get(a.getSource())){
				min=a.support.weight;
				sce = a.getSource();
			}
		}
		return sce;
	}
	
    public static ArrayList<Arc> generateTree(Graph graphe, int racine) {
        PoidsMinimumAleatoire pma = new PoidsMinimumAleatoire();
        Graph graphepoidrandom = pma.poidsAleatoire(graphe);
        pma.setGraphe(graphepoidrandom);
        pma.start(racine);
        return pma.arbre;
    }

}
