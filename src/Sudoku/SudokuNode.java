package Sudoku;

import java.util.ArrayList;

/**
 *
 * @author Jose Angel Gonzalez Molina
 */
public class SudokuNode implements Comparable{
	
	private ArrayList<SudokuField> sudoku;
	private int active;
	private boolean[] restrictions;//Restricciones generadas al expandir restricciones
	
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
	
	private void expandRestrictions(){
	//AKI TA EL FALLO
		SudokuField actual = sudoku.get(active);
		SudokuField vecino;
		int fila = active/9;
		int columna = active%9;
		
		//Expande la fila
		int i=0;		
		while(i<9){
			vecino = sudoku.get(fila*9+i);			
			if(vecino.isPossible(actual.getValue())){
				if(vecino.getNumPossibles() < 10)
					restrictions[fila*9+i]=true;
				else
					return;
			}
			i++;
		}
		
		//Comprueba la columna
		i=0;		
		while(i<9){
			vecino = sudoku.get(i*9+columna);
			if(vecino.isPossible(actual.getValue())){
				if(vecino.getNumPossibles() < 10)
					restrictions[i*9+columna]=true;
				else
					return;
			}
			i++;
		}
		
		int cuadX=fila/3;
		int cuadY=columna/3;
		
		//Comprueba el cuadrado		
		int j;
		for(i=0;i<3;i++){
			j=0;
			while(j<3){
				vecino = sudoku.get((i+(cuadX*3))*9+(j+(cuadY*3)));
				if(vecino.isPossible(actual.getValue())){
					if(vecino.getNumPossibles() < 10)
						restrictions[(i+(cuadX*3))*9+(j+(cuadY*3))]=true;
					else
						return;
				}
				j++;
			}
		}
		
		//Comprueba las diagonales
		if(fila == columna){
			i=0;
			j=0;
			while(i<9&&j<9){
				vecino = sudoku.get(i*9+j);
				if(vecino.isPossible(actual.getValue())){
					if(vecino.getNumPossibles() < 10)
						restrictions[i*9+j]=true;
					else
						return;
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
					if(vecino.getNumPossibles() < 10)
						restrictions[i*9+j]=true;
					else
						return;
				}
				i++;
				j--;
			}
		}
		
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
		for(int i=0;i<81;i++){
			if(!sudoku.get(i).isInitial())
				active = i;
		}
		restrictions = new boolean[81];
		for(int i=0;i<81;i++)
			restrictions[i]=false;
		createRestrictions();
		
	}
	
	public SudokuNode(ArrayList<SudokuField> tablero,int act,int pos){
		//No me fioooo no me fio
		sudoku = new ArrayList<SudokuField>();
		for(int i=0;i<81;i++)
			sudoku.add((SudokuField)tablero.get(i).clone());
		
		active = act;
		restrictions = new boolean[81];
		for(int i=0;i<81;i++)
			restrictions[i]=false;
				
		SudokuField campo = sudoku.get(active);
		
		System.out.println("Posibles hijo con activa "+active+":");
		System.out.println(campo.getNumPossibles());
				
		campo.setValue(pos);
		restrictions[active]=true;
		
		expandRestrictions();
		
	}
	
	public ArrayList<SudokuField> getSudoku(){
		return sudoku;
	}
	
	public int getNumRestrictions(){
		
		int cont=0;
		for(int i=0;i<81;i++){
			if(restrictions[i])
				cont++;
		}
		
		return cont;
		
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
	
	public void aplicateRestrictions(){
		
		SudokuField campo;
		
		for(int i=0;i<81;i++){
			if(restrictions[i]){
				campo = sudoku.get(i);
				campo.erasePossible(this.getActiveValue());
				restrictions[i]=false;
			}
		}
		
	}
	
	@Override
	public int compareTo(Object t) {
		
		SudokuNode nodo = (SudokuNode)t;
		SudokuField campo = sudoku.get(active);
		
		//El nodo this es inicial
		if(campo.isInitial())
			return 1;
		//el nodo t es inicial
		if(nodo.isActiveInitial())
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
