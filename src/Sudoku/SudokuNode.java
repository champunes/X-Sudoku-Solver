package Sudoku;

import java.util.ArrayList;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuNode {
	
	private ArrayList sudoku;
	
	private boolean checkField(int pos){
		
		boolean sigue=true;
		SudokuField actual = (SudokuField)sudoku.get(pos);
		SudokuField vecino;
		int fila = pos/9;
		int columna = pos%9;
		
		//Comprueba la fila
		int i=0;
		
		while(i<9 && sigue){
			vecino = (SudokuField)sudoku.get(fila*9+i);			
			if(vecino.getValue() == actual.getValue()){
				if(i!=columna){
					sigue=false;
				}
			}
			i++;
		}
		
		//Comprueba la columna
		i=0;		
		while(i<9 && sigue){
			vecino = (SudokuField)sudoku.get(i*9+columna);
			if(vecino.getValue() == actual.getValue()){			
				if(i!=fila){
					sigue=false;
				}
			}
			i++;
		}
		
		int cuadX=fila/3;
		int cuadY=columna/3;
		
		//Comprueba el cuadrado		
		int j;
		for(i=0;i<3;i++){
			j=0;
			while(j<3 && sigue){
				vecino = (SudokuField)sudoku.get((i+(cuadX*3))*9+(j+(cuadY*3)));
				if(vecino.getValue() == actual.getValue()){
					if((i+cuadX*3) != fila && (j+cuadY*3) != columna){
						sigue=false;
					}
				}
				j++;
			}
		}
		
		//Comprueba las diagonales
		if(fila == columna){
			i=0;
			j=0;
			while(i<9&&j<9&&sigue){
				vecino = (SudokuField)sudoku.get(i*9+j);
				if(vecino.getValue() == actual.getValue()){
					if(i!=fila && j!=columna){
						sigue=false;
					}
				}
				i++;
				j++;
			}
		}		
		if(fila+columna==8){
			i=0;
			j=8;
			while(i<9&&j>=0&&sigue){
				vecino = (SudokuField)sudoku.get(i*9+j);
				if(vecino.getValue() == actual.getValue()){
					if(i!=fila && j!=columna){
						sigue=false;
					}
				}
				i++;
				j--;
			}
		}
		
		return sigue;
		
	}	
	
	public SudokuNode(String template){
		
		sudoku = new ArrayList();
		
		for(int i=0;i<template.length();i++){
			sudoku.add(new SudokuField(template.charAt(i)));			
		}
		
		SudokuField campo;
		for(int i=0;i<sudoku.size();i++){
			campo = (SudokuField)sudoku.get(i);
			
		}		
		
	}
	
	public SudokuNode(ArrayList tablero){
		//No me fioooo no me fio
		sudoku = (ArrayList)tablero.clone();
	}
	
	public void createRestrictions(){
		
		for(int i=0;i<81;i++){
			
		}
		
	}
			
	public void expandRestrictions(){
	
		
		
	}

}
