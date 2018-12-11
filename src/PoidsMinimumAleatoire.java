import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;


public class PoidsMinimumAleatoire {

    Graph graphe;
    PriorityQueue<Edge> file;
    ArrayList<Edge> arbre;
    BitSet visite;
    double[] cout;

    private void start(int racine) {
    	cout[racine]=0;
        for(LinkedList<Edge> aa: graphe.adjacency){
			for(Edge a: aa){
				file.add(a);
			}
		}
        while (!file.isEmpty()) {
            explore(file.poll());
        }
    }
    
    private void explore(Edge e) {
    	if (visite.get(e.getSource())) return;
    	visite.set(e.getSource());
    	for(Edge ee: graphe.adjacency(e.getSource())){
    		System.out.println("cout:"+cout[e.getDest()]);
    		System.out.println("weight diff:"+(ee.weight));
        	if(cout[ee.getSource()]>ee.weight){
        		explore(ee);
        		cout[ee.getDest()]=(ee.weight);
        	}	
    	}
        arbre.add(e);
    	/*
        if (visite.get(nextEdge.getDest())) return;
        visite.set(nextEdge.getDest());
        arbre.add(nextEdge);
        //push(nextEdge.getDest());
    	*/
    }

    private PoidsMinimumAleatoire (Graph graphe) {
        this.graphe = graphe;
        this.file = new PriorityQueue<Edge>();
        this.arbre = new ArrayList<Edge>();
        this.visite = new BitSet(graphe.order);
        this.cout = new double[graphe.order];
        for(int i=0;i<graphe.order;++i)
        	cout[i]=1;
    }
    

	private static Graph poidsAleatoire(Graph graphe2) {
		for(LinkedList<Edge> aa: graphe2.adjacency){
			for(Edge a: aa){
				a.weight = ThreadLocalRandom.current().nextDouble(0, 1);
			}
		}
		/*
		for(LinkedList<Edge> aa: graphe2.outAdjacency){
			for(Edge a: aa){
				System.out.println(a.support.weight);
				
			}
		}
		*/
		return graphe2;
	}
	/*
	private int voisinPlusPetitPoid(ArrayList<Edge> voisins){
		double min = 1;
		int sce = 0;
		for(Edge a : voisins){
			if(a.weight<min && visite.get(a.getSource())){
				min=a.weight;
				sce = a.getSource();
			}
		}
		return sce;
	}
	*/
    public static ArrayList<Edge> generateTree(Graph graphe, int racine) {
        PoidsMinimumAleatoire pma = new PoidsMinimumAleatoire(PoidsMinimumAleatoire.poidsAleatoire(graphe));
        pma.start(racine);
        return pma.arbre;
    }

}
