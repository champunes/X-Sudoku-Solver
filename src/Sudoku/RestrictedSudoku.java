package Sudoku;

import java.util.ArrayList;
/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class RestrictedSudoku {
	
	ArrayList sudoku;
	int casillaActiva;
	ArrayList posibles;
	
	RestrictedSudoku (String template){
		
		sudoku = new ArrayList(9*9);
		
		for(int i=0;i<template.length();i++)
			sudoku.add(template.charAt(i));
	}
	
	RestrictedSudoku (ArrayList nuevoSudoku){

		sudoku = new ArrayList();
		sudoku = (ArrayList) nuevoSudoku.clone();
		
	}
	
	ArrayList getSudoku(){
		
		return sudoku;
		
	}
	
	int getActivedField(){
		
		return casillaActiva;
	}
	
	Object[] getPossibles(){
		
		return posibles.toArray();
	}
}
