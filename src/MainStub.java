
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;


public class MainStub
{

	@SuppressWarnings("unused")
	private final static Random gen = new Random();

	/**
	 * GÃ©nÃ¨re une liste d'incidence alÃ©atoire
	 * Ã  partir d'un parcours en largeur sur des arcs
	 * Ã  poids alÃ©atoire.
	 * @param graph Le graphe Ã  partir duquel gÃ©nÃ©rer la liste
	 * @return La liste d'Edge Ã  crÃ©er alÃ©atoirement
	 */
	public static ArrayList<Edge> genTree(Graph graph)
	{
/*		ArrayList<Edge> randomTree;

		// Non-random BFS
		//ArrayList<Arc> randomArcTree = Parcours.algo(graph,0);
		//ArrayList<Arc> randomArcTree = BreadthFirstSearch.generateTree(graph,0);
		//ArrayList<Arc> randomArcTree = Wilson.generateTree(graph);
		ArrayList<Arc> randomArcTree = AldousBroder.generateTree(graph);

		randomTree = new ArrayList<>();

		//randomArcTree.add(new Arc(new Edge(0,1,0.0), false));
		for (Arc a : randomArcTree)
		{
			if(a != null) //TODO : Possible de rectifier Ã§a ? :> probleme avec Parcours
				randomTree.add(a.support);
		}
		return randomTree;
*/
		ArrayList<Edge> randomTree;
		//randomTree = Wilson.generateTree(graph);
		randomTree = AldousBroder.generateTree(graph);

		return randomTree;
	}


	public static void main(String argv[]) throws InterruptedException
	{
		Grid grid = null;
		grid = new Grid(1920/11,1080/11);
		Graph graph = grid.graph;

		//Graph graph = new Complete(400).graph;

		//Graph graph = new ErdosRenyi(1_000, 100).graph;

		//Graph graph = new Lollipop(1_000).graph;

		int nbrOfSamples = 10;
		int diameterSum = 0;
		double eccentricitySum = 0;
		long wienerSum = 0;
		int degreesSum[] = {0, 0, 0, 0, 0};
		int degrees[];

		ArrayList<Edge> randomTree = null;
		RootedTree rooted = null;

		long startingTime = System.nanoTime();
		for (int i = 0; i < nbrOfSamples; i++) {
			randomTree = genTree(graph);

			rooted = new RootedTree(randomTree,0);
//			rooted.printStats();
			diameterSum = diameterSum + rooted.getDiameter();
			eccentricitySum = eccentricitySum + rooted.getAverageEccentricity();
			wienerSum = wienerSum + rooted.getWienerIndex();

			degrees = rooted.getDegreeDistribution(4);
			for (int j = 1; j < degrees.length; j++)
			{
				degreesSum[j] = degreesSum[j] + degrees[j];
			}
		}
		long delay = System.nanoTime() - startingTime;

		System.out.println("On " + nbrOfSamples + " samples:");
		System.out.println("Average eccentricity: "
				+ (eccentricitySum / nbrOfSamples));
		System.out.println("Average wiener index: "
				+ (wienerSum / nbrOfSamples));
		System.out.println("Average diameter: "
				+ (diameterSum / nbrOfSamples));
		System.out.println("Average number of leaves: "
				+ (degreesSum[1] / nbrOfSamples));
		System.out.println("Average number of degree 2 vertices: "
				+ (degreesSum[2] / nbrOfSamples));
		System.out.println("Average computation time: "
				+ delay / (nbrOfSamples * 1_000_000) + "ms");


		if (grid != null) showGrid(grid,rooted,randomTree);
	}

	private static void showGrid(
			Grid grid,
			RootedTree rooted,
			ArrayList<Edge> randomTree
	) throws InterruptedException {
		JFrame window = new JFrame("solution");
		final Labyrinth laby = new Labyrinth(grid, rooted);

		laby.setStyleBalanced();
//		laby.setShapeBigNodes();
//		laby.setShapeSmallAndFull();
		laby.setShapeSmoothSmallNodes();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(laby);
		window.pack();
		window.setLocationRelativeTo(null);


		for (final Edge e : randomTree) {
			laby.addEdge(e);
		}
		laby.drawLabyrinth();

		window.setVisible(true);

		// Pour gÃ©nÃ©rer un fichier image.
//		try {
//			laby.saveImage("resources/random.png");
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}

	}


}
