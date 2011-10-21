
package Sudoku;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuSolverBacktracking extends SudokuSolver{

	private int sudoku[][];

	public SudokuSolverBacktracking (String template){
		
		sudoku = new int[9][9];
		
		for(int i=0;i<9;i++){
			for(int j=1;j<=9;j++){
				char val;
				val = template.charAt((9*i+j)-1);
				if(val != '.' && val != '-')
					sudoku[i][j-1] = Character.digit(val, 10);
				else
					sudoku[i][j-1] = 0;
			}		
		}

	}

	@Override
	public void solve() {
		super.solve();
	}

	@Override
	public String toString() {
		String cadena = "";
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++)
				cadena = cadena + String.valueOf(sudoku[i][j]);
		}
		return cadena;
	}

}
