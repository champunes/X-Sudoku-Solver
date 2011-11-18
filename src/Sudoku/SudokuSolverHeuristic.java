package Sudoku;

import java.util.PriorityQueue;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuSolverHeuristic {
	
	PriorityQueue<SudokuNode> stack;
	SudokuNode solution;
	
	public SudokuSolverHeuristic(String template){
		
		stack = new PriorityQueue();
		solution = new SudokuNode(template);
		
	}
	
	public void solve(){
		
		int sigCasilla;
		SudokuNode padre,hijo;

		padre = solution;
		sigCasilla = padre.getNextActive();
		
		do{
			padre.setActive(sigCasilla);
			
			//if(padre.getNumFilled() == 54 && padre.getActive()==10)
			//	padre = padre;
			
			int posibles[] = padre.getActivePossibles();

			for(int i=0;i<posibles.length;i++){				
				hijo = new SudokuNode(padre.getSudoku(),sigCasilla,posibles[i],padre.getNumFilled());
				//System.out.println(hijo.toString());
				if(hijo.getNumRestrictions() >= 0)					
					stack.offer(hijo);
			}
			padre = stack.poll();
			//System.out.println(padre.toString());
			sigCasilla = padre.getNextActive();
		}while(sigCasilla > -1 && padre.getNumFilled()<81);
		System.out.println(padre.getNumFilled());
		solution = padre;
		
	}
	
	@Override
	public String toString(){
		
		return solution.toString();
		
	}
	
}
