package Sudoku;

import java.util.ArrayList;
/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuField {
	
	private int value;
	boolean initial;
	private ArrayList possibles;
	
	public SudokuField (int v){
			
		value=v;
		if(value == 0)
			initial=true;
		else
			initial=false;
	}
	
	boolean isInitial(){
		return initial;
	}
	
	int getValue(){
		
		return value;
	}
	
	Object[] getPossibles(){
		
		return possibles.toArray();
	}
	
	void setPossible(Object pos){
		possibles.add(pos);
	}
	
	void erasePossible(Object pos){
		possibles.remove(pos);
	}
	
}
