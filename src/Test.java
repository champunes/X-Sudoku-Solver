
import java.util.Date;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import Sudoku.*;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class Test
{
	public static final String SUDOKU_POR_DEFECTO = "038020000"
												  + "000000008"
		                                          + "000006007"
		                                          + "000000070"
		                                          + "001000400"
		                                          + "040000000"
		                                          + "400500000"
		                                          + "100000000"
												  + "000030260"
												  ;

	public static void main (String args[]) throws IOException
	{
		String       plantilla;
		SudokuSolver sudoku;

		long   inicio;
		long   fin;
		long   tiempo;

		// Argumentos de entrada

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("Introduzca el sudoku-X:");
		plantilla = br.readLine();
		if(plantilla.length() == 0)
			plantilla = SUDOKU_POR_DEFECTO;

		// Resolución del sudoku

		inicio = (new Date()).getTime();

		sudoku = new SudokuSolverBacktracking (plantilla);

		//sudoku.solve();

		fin = (new Date()).getTime();

		tiempo = fin - inicio;


		// Resultado

		System.out.println("Solución:");
		System.out.println(sudoku.toString());
		System.out.println("Tiempo: "+tiempo+" milisegundos.");
	}

}

