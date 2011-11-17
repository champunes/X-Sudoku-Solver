package Sudoku;

import java.util.ArrayList;
/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuField implements Cloneable{
	
	private int value;
	private boolean initial;
	private ArrayList<Integer> possibles;
	
	public SudokuField (int v){
			
		value=v;
		if(value == 0)
			initial=false;
		else
			initial=true;
		possibles = new ArrayList();
	}
	
	public SudokuField (SudokuField copy){
		
		value = copy.value;
		initial = copy.initial;
		possibles = new ArrayList<Integer>();
		possibles.addAll(copy.possibles);
		
	}
	
	public boolean isInitial(){
		return initial;
	}
	
	public int getValue(){
		
		return value;
	}
	
	public void setValue(int v){
		value = v;
	}
	
	public void printPossibles(){
		
		if(!initial){
			for(int j=0;j<possibles.size();j++)
				System.out.print(possibles.get(j) +" ");
		}
		System.out.print("\n");
		
	}
	
	public int[] getPossibles(){
		
		int[] posibles = new int[possibles.size()];
		for(int i=0;i<posibles.length;i++)
			posibles[i] = possibles.get(i);
		
		return posibles;

	}
	
	public int getNumPossibles(){
		
		if(possibles.size()>0)
			return possibles.size();
		else
			return 10;
		
	}
	
	public void setPossible(int pos){
		possibles.add(new Integer(pos));
	}
	
	public void erasePossible(int pos){
		//No me fio nada
		possibles.remove(Integer.valueOf(pos));
	}
	
	public boolean isPossible(int pos){
		//No me fio nada
		return possibles.contains(Integer.valueOf(pos));
		
	}
	
	@Override
	public Object clone(){
		
		return new SudokuField(this);
		
	}
	
	@Override
	public String toString(){
		
		return String.valueOf(value);
		
	}
	
}
