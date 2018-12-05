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
		return index <= adjacency.size() && adjacency.get(index)!=null && index>=0;
	}
	
	public <T> ArrayList<LinkedList<T>> makeList(int size) {
		ArrayList<LinkedList<T>> resultat = new ArrayList<>(size);
		for(int i = 0; i <= size; i++) {
			resultat.add(null);
		}
		return resultat;
	}
	
	public Graph(int upperBound){
		order = 0;
		adjacency= makeList(upperBound);
		outAdjacency = makeList(upperBound);
	}
	
	public void addVertex(int indexVertex) {
		adjacency.ensureCapacity(indexVertex);
		if(isVertex(indexVertex))
			return;
		order++;
		adjacency.set(indexVertex,new LinkedList<Edge>());
		outAdjacency.set(indexVertex, new LinkedList<Arc>());
	}


	//sommet existe ou si il n'existe pas il l'ajoute
	public void ensureVertex(int indexVertex) {
		if (!isVertex(indexVertex)){
			addVertex(indexVertex);
		}
	}


	// ajoute un arc a la liste des sommets
	public void addArc(Arc arc) {
		outAdjacency.get(arc.getSource()).add(arc);
	}

	//
	public void addEdge(Edge e) {
		addVertex(e.dest);
		adjacency.get(e.dest).add(e);
		addVertex(e.source);
		adjacency.get(e.source).add(e);
		addArc(new Arc(e,false));
		addArc(new Arc(e,true));
	}

	public List<Arc> outAdjacency(int vertex) {
		return outAdjacency.get(vertex);
	}
}
