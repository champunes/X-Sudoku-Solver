
import java.util.Date;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import Sudoku.*;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class Test
{
	
	public static void main (String args[]) throws IOException
	{
		String       plantilla;
		SudokuSolver sudoku;

		long   inicio;
		long   fin;
		long   tiempo;
		long   total;

		// Argumentos de entrada

		FileReader fr = new FileReader("sudokus.txt");
		BufferedReader br = new BufferedReader(fr);
		
		total = 0;
		while(br.ready()){
			
			plantilla = br.readLine();
		
			inicio = (new Date()).getTime();

			sudoku = new SudokuSolver (plantilla);

			sudoku.solve();

			fin = (new Date()).getTime();

			tiempo = fin - inicio;
			total = total + tiempo;
			
			// Resultado

			System.out.println("Solución:");
			System.out.println(sudoku.toString());
			System.out.println("Tiempo: "+tiempo+" milisegundos.");
			System.out.println("Tiempo total: "+total+" milisegundos.");
			
		}		
		
	}

}

