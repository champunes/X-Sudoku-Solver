package Sudoku;

import java.util.ArrayList;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuNode implements Comparable{
	
	private ArrayList<SudokuField> sudoku;
	private int active;
	private int filled;
	private int restrictions;//Restricciones generadas al expandir restricciones
	
	private void createRestrictions(){
		
		SudokuField campo;
		int copiaActive = this.active;
		
		for(int i=0;i<81;i++){
			campo = sudoku.get(i);
			if(!campo.isInitial()){
				for(int j=1;j<=9;j++){
					campo.setValue(j);
					this.active = i;
					if(checkField())
						campo.setPossible(new Integer(j));
				}
				campo.setValue(0);
			}
		}
		this.active = copiaActive;
		
	}
	
	private int expandRestrictions(){
	
		SudokuField actual = sudoku.get(active);
		SudokuField vecino;
		int fila = active/9;
		int columna = active%9;
		int restricciones = 0;
		
		//System.out.println("Actual: "+(fila*9+columna)+"\tValor:"+actual.getValue());
		
		//System.out.println("Posibles vecinos fila: ");
		
		//Expande la fila
		int i=0;		
		while(i<9){
			vecino = sudoku.get(fila*9+i);			
			if(vecino.isPossible(actual.getValue())){
				if(vecino.getNumPossibles() < 10){
					
					//System.out.println("Vecino"+(fila*9+i)+" : ");
					//vecino.printPossibles();
					
					vecino.erasePossible(actual.getValue());					
					restricciones++;
				}
				else
					return -1;
			}
			i++;
		}
		
		//System.out.println("Posibles vecinos columna: ");
		
		//Comprueba la columna
		i=0;		
		while(i<9){
			vecino = sudoku.get(i*9+columna);
			if(vecino.isPossible(actual.getValue())){
				if(vecino.getNumPossibles() < 10){
					
					//System.out.println("Vecino"+(i*9+columna)+" : ");
					//vecino.printPossibles();
					
					vecino.erasePossible(actual.getValue());					
					restricciones++;
				}
				else
					return -1;
			}
			i++;
		}
		
		int cuadX=fila/3;
		int cuadY=columna/3;
		
		//System.out.println("Posibles vecinos cuadrado: ");
		
		//Comprueba el cuadrado		
		int j;
		for(i=0;i<3;i++){
			j=0;
			while(j<3){
				vecino = sudoku.get((i+(cuadX*3))*9+(j+(cuadY*3)));
				if(vecino.isPossible(actual.getValue())){
					if(vecino.getNumPossibles() < 10){
						
						//System.out.println("Vecino"+(i+(cuadX*3))*9+(j+(cuadY*3))+" : ");
						//vecino.printPossibles();
						
						vecino.erasePossible(actual.getValue());						
						restricciones++;
					}
					else
						return -1;
				}
				j++;
			}
		}
		
		//System.out.println("Posibles vecinos diagonales: ");
		
		//Comprueba las diagonales
		if(fila == columna){
			i=0;
			j=0;
			while(i<9&&j<9){
				vecino = sudoku.get(i*9+j);
				if(vecino.isPossible(actual.getValue())){
					if(vecino.getNumPossibles() < 10){
						
						//System.out.println("Vecino"+(i*9+j)+" : ");
						//vecino.printPossibles();
						
						vecino.erasePossible(actual.getValue());						
						restricciones++;
					}
					else
						return -1;
				}
				i++;
				j++;
			}
		}		
		if(fila+columna==8){
			i=0;
			j=8;
			while(i<9&&j>=0){
				vecino = sudoku.get(i*9+j);
				if(vecino.isPossible(actual.getValue())){
					if(vecino.getNumPossibles() < 10){
						
						//System.out.println("Vecino"+(i*9+j)+" : ");
						//vecino.printPossibles();
						
						vecino.erasePossible(actual.getValue());						
						restricciones++;
					}
					else
						return -1;
				}
				i++;
				j--;
			}
		}
		
		return  restricciones;
		
	}
	
	private boolean checkField(){
		
		boolean sigue=true;
		SudokuField actual = sudoku.get(active);
		SudokuField vecino;
		int fila = active/9;
		int columna = active%9;
		
		//Comprueba la fila
		int i=0;
		while(i<9 && sigue){
			vecino = sudoku.get(fila*9+i);			
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
			vecino = sudoku.get(i*9+columna);
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
				vecino = sudoku.get((i+(cuadX*3))*9+(j+(cuadY*3)));
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
				vecino = sudoku.get(i*9+j);
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
				vecino = sudoku.get(i*9+j);
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
			sudoku.add(new SudokuField(Character.digit(template.charAt(i), 10)));			
		}
		filled=0;
		for(int i=0;i<81;i++){
			if(!sudoku.get(i).isInitial())
				active = i;
			else
				filled++;
		}
		restrictions = 0;
		createRestrictions();
		
	}
	
	public SudokuNode(ArrayList<SudokuField> tablero,int act,int pos,int fld){
		//No me fioooo no me fio
		sudoku = new ArrayList<SudokuField>();
		for(int i=0;i<81;i++)
			sudoku.add((SudokuField)tablero.get(i).clone());
		
		active = act;
		filled = fld+1;
		SudokuField campo = sudoku.get(active);
				
		campo.setValue(pos);
		campo.erasePossible(pos);
		
		campo.setInitial(true);
		
		restrictions = expandRestrictions();
		
	}
	
	public ArrayList<SudokuField> getSudoku(){
		return sudoku;
	}
	
	public int getNumFilled(){
		
		return filled;
		
	}
	
	public int getNumRestrictions(){
	
		return restrictions;
		
	}
	
	public int getActive(){
		
		return active;
		
	}
	
	public void setActive(int act){
		active = act;
	}
	
	public int getActiveValue(){
		
		SudokuField campo = sudoku.get(active);
		return campo.getValue();
		
	}
	
	public int getActiveNumPossibles(){
		
		SudokuField campo = sudoku.get(active);
		return campo.getNumPossibles();
		
	}
	
	public int[] getActivePossibles(){

		SudokuField campo = sudoku.get(active);
		return campo.getPossibles();
	}
	
	public boolean isActiveInitial(){
		
		SudokuField campo = sudoku.get(active);
		return campo.isInitial();
		
	}
	
	public int getNextActive(){
		
		SudokuField campo;
		int minpos=0;
		for(int i=1;i<81;i++){
			campo = sudoku.get(i);
			if(!campo.isInitial()){
				if(campo.getNumPossibles() < sudoku.get(minpos).getNumPossibles())
					minpos = i;
			}
		}
		if(sudoku.get(minpos).getNumPossibles() == 10)
			return -1;
		return minpos;
		
	}
	
	public void printAllPossibles(){
		
		SudokuField campo;
		
		for(int i=0;i<81;i++){
			campo = sudoku.get(i);
			System.out.println("Casilla "+i+".");
			campo.printPossibles();
		}
		
	}
	
	@Override
	public int compareTo(Object t) {
		
		SudokuNode nodo = (SudokuNode)t;
		SudokuField campo = sudoku.get(active);
		
		//el nodo t es descendiente
		if(nodo.getNumFilled() > this.getNumFilled())
			return 1;
		if(nodo.getNumFilled() < this.getNumFilled())
			return -1;
		if(this.getNumRestrictions() > nodo.getNumRestrictions())
			return -1;
		if(this.getNumRestrictions() < nodo.getNumRestrictions())
			return 1;
		return 0;
		
	}
	
	@Override
	public String toString(){
	
		String cadena="";
		SudokuField campo;
		
		for(int i=0;i<9;i++){
			if(i%3 == 0)
				cadena += "=============\n";
			for(int j=0;j<9;j++){
				if(j%3 == 0)
					cadena += "|";
				campo = sudoku.get(i*9+j);
				cadena += campo.toString();
			}
			cadena += "|\n";
		}		
		cadena += "=============\n";
		return cadena;
		
	}

}
