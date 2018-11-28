import java.util.*;


public class Graph { // Iterable supprimer
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	int order;
	int edgeCardinality;
	
	ArrayList<LinkedList<Edge>> adjacency; // liste des arretes non orienté
	// ArrayList<LinkedList<Arc>> inAdjacency;
	ArrayList<LinkedList<Arc>> outAdjacency; //  graph orienté


	// index plus petit que order  et appartient a arraylist
	public boolean isVertex(int index) {
		return index  < order &;
	}
	
	public <T> ArrayList<LinkedList<T>> makeList(int size) {
		ArrayList<LinkedList<T>> resultat = new ArrayList<>(size);
		for(int i = 0; i <= size; i++) {
			resultat.add(null);
		}
		return resultat;
	}
	
	public Graph(int upperBound){
		order = upperBound;

	}
	
	public void addVertex(int indexVertex) {

	}


	//sommet existe ou si il n'existe pas il l'ajoute
	public void ensureVertex(int indexVertex) {

	}


	// ajoute un arc a la liste des sommets
	public void addArc(Arc arc) {
		outAdjacency.get(arc.getSource()).add(arc);
	}

	//
	public void addEdge(Edge e) {

	}

	public List<Arc> outNeighbours(int vertex) {
		return ;
	}

}
