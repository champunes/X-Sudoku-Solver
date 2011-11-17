package Sudoku;

import java.util.PriorityQueue;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuSolverHeuristic extends SudokuSolver{
	
	PriorityQueue<SudokuNode> stack;
	SudokuNode solution;
	
	public SudokuSolverHeuristic(String template){
		
		stack = new PriorityQueue();
		solution = new SudokuNode(template);
		
	}
	
	@Override
	public void solve(){
		
		int sigCasilla;
		SudokuNode padre,hijo;
		
		int t = 0;
		System.out.println(this.toString());
		
		padre = solution;
		sigCasilla = padre.getNextActive();
		int padres=1;
		do{			
			int activa = padre.getActive();
			padre.setActive(sigCasilla);
			int[] posibles = padre.getActivePossibles();
			System.out.println("Posibles padre "+padres+" con activa "+activa+" que genera hijos con activa "+padre.getActive());
			System.out.println(padre.getActiveNumPossibles());
			padre.setActive(activa);			
			for(int i=0;i<posibles.length;i++){
				hijo = new SudokuNode(padre.getSudoku(),sigCasilla,posibles[i]);
				System.out.println(hijo.toString());
				
				if(hijo.getNumRestrictions() > 0){
					hijo.aplicateRestrictions();
					stack.offer(hijo);
				}
				System.out.println("Seleccionado el hijo con posibilidad "+hijo.getActiveValue());
				hijo.printAllPossibles();
			}
			padre = stack.poll();			
			sigCasilla = padre.getNextActive();
			while(sigCasilla == -1 && !stack.isEmpty()){
				padre = stack.poll();
				System.out.println("Seleccionado el hijo con posibilidad "+padre.getActiveValue());
				sigCasilla = padre.getNextActive();
			}
			if(sigCasilla == -1)
				t++;
			System.out.println("Sig casilla: "+sigCasilla+"\n");
			padres++;
			solution = padre;
			//t++;
		}while(sigCasilla > -1 && t<100);
		t++;
	}
	
	@Override
	public String toString(){
		
		return solution.toString();
		
	}
	
}
