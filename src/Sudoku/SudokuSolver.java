
package Sudoku;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public abstract class SudokuSolver
{

	public SudokuSolver(){
		
	}

	/**
	 * Constructor
	 * @param template Cadena representando una plantilla de sudoku
	 */
	public SudokuSolver (String template)
	{
		template.toString();
	}


	//public SudokuSolver(){}

	/**
	 * Resolución de sudokus
	 */
	public void solve()
	{
	}

	/**
     * Representación del sudoku como una cadena de caracteres
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return "971384265"
			 + "324516789"
			 + "586927413"
			 + "415832976"
			 + "792641358"
			 + "638795142"
			 + "257463891"
			 + "169278534"
			 + "843159627";
	}

}

