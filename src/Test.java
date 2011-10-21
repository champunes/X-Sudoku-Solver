
import java.util.Date;
import Sudoku.*;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class Test
{
	public static final String SUDOKU_POR_DEFECTO = "97.3.4.65"
		                                          + ".2.5.6.8."
		                                          + "........."
		                                          + "..58.29.."
		                                          + "..2.4.3.."
		                                          + "..87.51.."
		                                          + "........."
		                                          + ".6.2.8.3."
		                                          + "34.1.9.27";


	public static void main (String args[])
	{
		String       plantilla;
		SudokuSolver sudoku;

		long   inicio;
		long   fin;
		long   tiempo;

		// Argumentos de entrada

		if (args.length==0) {

			System.err.println("Por favor, indique su sudoku como argumento de este programa.");
			System.err.println("Asumiendo sudoku por defecto...");
			plantilla = SUDOKU_POR_DEFECTO;

		} else {

			plantilla = "";

			for (int i=0;i<args.length; i++)
				plantilla += args[i];

		}


		// Resolución del sudoku

		inicio = (new Date()).getTime();

		sudoku = new SudokuSolverBacktracking (plantilla);

		sudoku.solve();

		fin = (new Date()).getTime();

		tiempo = fin - inicio;


		// Resultado

		System.out.println("Solución:");
		System.out.println(sudoku.toString());
		System.out.println("Tiempo: "+tiempo+" milisegundos.");
	}

}

