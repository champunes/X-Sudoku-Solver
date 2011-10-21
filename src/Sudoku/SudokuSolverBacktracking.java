
package Sudoku;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuSolverBacktracking extends SudokuSolver{

	private int sudoku[][];
	
	private int[][] backtrack(int tablero[][], int posX, int posY){
	
		if(estadoTablero(tablero) == 0){
			
		}
		return tablero;
		
	}
	
	/**
	 * Comprueba las restricciones del sudoku-x y el numero de casillas por rellenar
	 * 
	 * @param tablero
	 * @param pos
	 * @return -1 si se no se cumple alguna reestriccion
	 * @return 0 si se cumplen todas las reestricciones y no quedan casillas por rellenar
	 * @return el numero de casillas por rellenar si se cumplen todas las reestriciones.
	 */
	private int estadoTablero(int tablero[][]){
		
		//Recorre las casiilas. Contabiliza las vacias. Comprueba las reestriciones de las demás
		int vacias=0;
		boolean rest=true;
		for(int i=0;i<9;i++){
			int j=0;
			while(j<9 && rest){
				if(tablero[i][j] == 0)
					vacias++;
				else{
					if(!compruebaCasilla(tablero,i,j))
						rest=false;
				}
				j++;
			}
		}
		if(!rest)
			return -1;
		
		return vacias;
		
	}
	
	private boolean compruebaCasilla(int tablero[][], int posX, int posY){
		
		boolean sigue=true;
		
		//Comprueba la fila
		int i=0;
		while(i<9 && sigue){
			if(tablero[posX][i] == tablero[posX][posY]){
				if(i!=posY){
					sigue=false;
				}
			}
			i++;
		}
		
		//Comprueba la columna
		i=0;
		while(i<9 && sigue){			
			if(tablero[i][posY] == tablero[posX][posY]){
				if(i!=posX){
					sigue=false;
				}
			}
			i++;
		}
		
		int cuadX=posX/3;
		int cuadY=posY/3;
		
		//Comprueba el cuadrado		
		int j;
		for(i=0;i<3;i++){
			j=0;
			while(j<3 && sigue){
				if(tablero[i+(cuadX*3)][j+(cuadY*3)] == tablero[posX][posY]){
					if((i+cuadX*3) != posX && (j+cuadY*3) != posY){
						sigue=false;
					}
				}
				j++;
			}
		}
		
		//Comprueba las diagonales
		if(posX == posY){
			i=0;
			j=0;
			while(i<9&&j<9&&sigue){				
				if(tablero[i][j] == tablero[posX][posY]){
					if(i!=posX && j!=posY){
						sigue=false;
					}
				}
				i++;
				j++;
			}
		}		
		if(posX+posY==8){
			i=0;
			j=8;
			while(i<9&&j>=0&&sigue){
				if(tablero[i][j] == tablero[posX][posY]){
					if(i!=posX && j!=posY){
						sigue=false;
					}
				}
				i++;
				j--;
			}
		}
		
		return sigue;
		
	}

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
		
		System.out.println("Estado tablero: "+estadoTablero(sudoku));

	}

	@Override
	public void solve() {
		int tablero[][] = sudoku.clone();
		//backtrack(tablero,0,0);
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
