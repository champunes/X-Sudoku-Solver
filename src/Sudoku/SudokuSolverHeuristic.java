package Sudoku;

import java.util.Stack;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuSolverHeuristic {
	
	Stack<SudokuNode> pila;
	
	public SudokuSolverHeuristic(String template){
		
		pila = new Stack<SudokuNode>();
		SudokuNode primerNodo = new SudokuNode(template);
		primerNodo.createRestrictions();
	}
	
	public void solve(){
		
	}
	
}
