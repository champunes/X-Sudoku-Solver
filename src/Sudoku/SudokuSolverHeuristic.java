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

		padre = solution;
		sigCasilla = padre.getNextActive();
		
		int ejecuciones=0;
		
		do{
			do{

				//System.out.println(padre.toString());
				padre.setActive(sigCasilla);
				int posibles[] = padre.getActivePossibles();

				for(int i=0;i<posibles.length;i++){				
					hijo = new SudokuNode(padre.getSudoku(),sigCasilla,posibles[i],padre.getNumFilled());
					if(hijo.getNumRestrictions() >= 0)
						stack.offer(hijo);
				}
				sigCasilla = padre.getNextActive();
			}while(sigCasilla > -1);
			
			padre = stack.poll();			
			sigCasilla = padre.getNextActive();
		ejecuciones++;	
		}while(!stack.isEmpty() && padre.getNumFilled()<81 && ejecuciones < 2000);
		System.out.println(padre.getNumFilled());
		solution = padre;
		
	}
	
	@Override
	public String toString(){
		
		return solution.toString();
		
	}
	
}
