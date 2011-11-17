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
		
		System.out.println(this.toString());

		padre = solution;
		sigCasilla = padre.getNextActive();
		do{			
			
			//System.out.print("Rellenas: "+padre.getNumFilled()+"\tActiva(valor): "+padre.getActive()+"("+padre.getActiveValue()+")"+"\n"+padre.toString());
			
			//Obtenemos las posibilidades del padre
			padre.setActive(sigCasilla);
			int[] posibles = padre.getActivePossibles();
						
			//System.out.println("Hijos posibles: "+padre.getActiveNumPossibles()+"\n");
			
			//Obtenemos los nuevos hijos y los metemos en la cola
			for(int i=0;i<posibles.length;i++){
				hijo = new SudokuNode(padre.getSudoku(),sigCasilla,posibles[i],padre.getNumFilled());
				if(hijo.getNumRestrictions() >= 0)
					stack.offer(hijo);
			}
			
			//Obtenemos el nuevo padre
			padre = stack.poll();
			sigCasilla = padre.getNextActive();
			while(sigCasilla == -1 && !stack.isEmpty() && padre.getNumFilled()<81){
				padre = stack.poll();
				sigCasilla = padre.getNextActive();
			}				
			
		}while(sigCasilla > -1);
		solution = padre;
	}
	
	@Override
	public String toString(){
		
		return solution.toString();
		
	}
	
}
