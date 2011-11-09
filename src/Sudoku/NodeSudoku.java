package Sudoku;

import java.util.ArrayList;
/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class NodeSudoku {
	
	ArrayList sudoku;
	int casillaActiva;
	ArrayList posibles;
	
	NodeSudoku (String template,int activa){
		
		sudoku = new ArrayList(9*9);
		
		for(int i=0;i<template.length();i++)
			sudoku.add(template.charAt(i));
		
		casillaActiva = activa;
	}
	
	NodeSudoku (ArrayList nuevoSudoku){

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
	
	void setPossible(Object pos){
		posibles.add(pos);
	}
	
	void erasePossible(Object pos){
		posibles.remove(pos);
	}
	
}
