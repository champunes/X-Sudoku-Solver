
package Sudoku;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuSolverBacktracking extends SudokuSolver{

	private int sudoku[][];
	private int solucion[][];
	private boolean inicial[][];
	
	private boolean backtrack(int posX, int posY){
		
		if(inicial[posX][posY] == false){
			for(int i=1;i<=9;i++){
				solucion[posX][posY] = i;
				if(compruebaCasilla(posX,posY)){
					if(posX==8 && posY==8){
						System.out.println("Sudoku resuelto.");
						return true;
					}
					if(posX<8 && posY == 8){
						if(backtrack(posX+1,0))
							return true;
					}else{
						if(posX<=8 && posY < 8){
							if(backtrack(posX,posY+1))
								return true;
						}
					}
				}
				solucion[posX][posY] = 0;
			}
		}else{ //inicial[posX][posY] es true
			if(posX==8 && posY==8){
				System.out.println("Sudoku resuelto.");
				return true;
			}
			if(posX<8 && posY == 8){
				if(backtrack(posX+1,0))
					return true;
			}else{
				if(posX<=8 && posY < 8){
					if(backtrack(posX,posY+1))
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Comprueba los conflictos posibles en la casilla pasada.
	 * 
	 * @param solucion
	 * @param posX
	 * @param posY
	 * @return true si no hay conflictos en esa casilla.
	 * @return false si hay conflictos en esa casilla.
	 */
	
	private boolean compruebaCasilla(int posX, int posY){
		
		boolean sigue=true;
		
		//Comprueba la fila
		int i=0;
		while(i<9 && sigue){
			if(solucion[posX][i] == solucion[posX][posY]){
				if(i!=posY){
					sigue=false;
				}
			}
			i++;
		}
		
		//Comprueba la columna
		i=0;
		while(i<9 && sigue){			
			if(solucion[i][posY] == solucion[posX][posY]){
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
				if(solucion[i+(cuadX*3)][j+(cuadY*3)] == solucion[posX][posY]){
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
				if(solucion[i][j] == solucion[posX][posY]){
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
				if(solucion[i][j] == solucion[posX][posY]){
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
		solucion = new int[9][9];
		
		for(int i=0;i<9;i++){
			for(int j=1;j<=9;j++){
				char val;
				val = template.charAt((9*i+j)-1);
				if(val != '.' && val != '-'){
					sudoku[i][j-1] = Character.digit(val, 10);
					solucion[i][j-1] = sudoku[i][j-1];
				}else{
					sudoku[i][j-1] = 0;
					solucion[i][j-1] = sudoku[i][j-1];
				}
			}		
		}
		inicial = new boolean[9][9];
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++)
			inicial[i][j] = solucion[i][j] != 0;					
		}
	}

	@Override
	public void solve() {
		this.toString();
		backtrack(0,0);	
		sudoku = solucion;
	}

	@Override
	public String toString() {
		String cadena = "";
		for(int i=0;i<9;i++){
			if(i%3 == 0)
				cadena += "=============\n";
			for(int j=0;j<9;j++){
				if(j%3 == 0)
					cadena += "|";
				cadena += sudoku[i][j];			
			}
			cadena += "|\n";
		}		
		cadena += "=============\n";
		return cadena;
	}

}
