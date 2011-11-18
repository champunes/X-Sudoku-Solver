
package Sudoku;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuSolver
{

	private SudokuSolverBacktracking solverB;
	private SudokuSolverHeuristic solverH;
	String solution;

	/**
	 * Constructor
	 * @param template Cadena representando una plantilla de sudoku
	 */
	public SudokuSolver (String template)
	{
		solverB = new SudokuSolverBacktracking(template);
		solverH = new SudokuSolverHeuristic(template);
		solution = new String();
	}


	//public SudokuSolver(){}

	/**
	 * Resolución de sudokus
	 */
	public void solve()
	{
		solverH.solve();
		solution = solverH.toString();
	}
	
	public void solveBacktracking(){
		solverB.solve();
		solution = solverH.toString();
	}

	/**
     * Representación del sudoku como una cadena de caracteres
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return solution;
	}

}

