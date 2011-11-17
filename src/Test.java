
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
	
	//800300000300005000009104000040080700600000002008020030000709600000200005000008004
	public static final String SUDOKU_POR_DEFECTO = "000000000"
												  + "000001000"
												  + "020000304"
												  + "000000000"
												  + "500000000"
												  + "000006000"
												  + "000230005"
												  + "607000080"
												  + "000000000"
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

		//sudoku = new SudokuSolverBacktracking (plantilla);
 		sudoku = new SudokuSolverHeuristic (plantilla);

		sudoku.solve();

		fin = (new Date()).getTime();

		tiempo = fin - inicio;


		// Resultado

		System.out.println("Solución:");
		System.out.println(sudoku.toString());
		System.out.println("Tiempo: "+tiempo+" milisegundos.");
		
	/*	PriorityQueue<Integer> stack = new PriorityQueue();
		stack.offer(1);
		stack.offer(5);
		stack.offer(6);
		stack.offer(2);
		stack.offer(8);
		stack.offer(3);
		stack.offer(9);
		stack.offer(2);
		stack.offer(43);
		stack.offer(1234);
		stack.offer(2345);
		stack.offer(934);
		stack.offer(2);
		stack.offer(43);
		stack.offer(456);
		stack.offer(45);
		
		while (!stack.isEmpty()){
			System.out.println(stack.poll());
		}*/
	}

}

