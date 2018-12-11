import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PoidsMinimumAleatoire{
	
    private Graph graphe;
    private BitSet visite;
    private LinkedList<Arc> queue;
    private ArrayList<Edge> arbre;

    private PoidsMinimumAleatoire(Graph graphe, BitSet visite){
        this.graphe = graphe;
        this.visite = visite;
        this.queue = new LinkedList<>();
        this.arbre = new ArrayList<>();
    }
    
    private void explore(int vertex){
        List<Arc> arcs = graphe.outAdjacency(vertex);
        for(Arc a : arcs)
            if (!visite.get(a.getDest())){
            	visite.set(a.getDest());
                queue.add(a);
            }
    }


    public static ArrayList<Edge> generateTree(Graph graphe){
    	
    	Random rdm = new Random();
    
        for(LinkedList<Edge> vertex : graphe.adjacency){
            if(vertex != null){
                for (Edge e : vertex){
                    e.weight = rdm.nextDouble();
                }
            }
        }
        
        int source = rdm.nextInt(graphe.order);
        BitSet visite = new BitSet(graphe.order);
        PoidsMinimumAleatoire pma = new PoidsMinimumAleatoire(graphe, visite);
        pma.visite.set(source);
        pma.explore(source);
        
        while (!pma.queue.isEmpty()){
            Arc arc = plusPetitPoids(pma.queue);
            pma.queue.remove(arc);
            int vertex = arc.getDest();
            pma.arbre.add(arc.support);
            pma.explore(vertex);
        }
        
        return pma.arbre;
    }

    private static Arc plusPetitPoids(List<Arc> arcs){
        Arc plusPetitPoids = new Arc(new Edge(0,0,-1),false);
        for(Arc arc : arcs){
            if(arc.support.weight > plusPetitPoids.support.weight)
                plusPetitPoids = arc;
        }
        if(plusPetitPoids == new Arc(new Edge(0,0,-1),false))
            plusPetitPoids = null;
        return plusPetitPoids;
    }
}
