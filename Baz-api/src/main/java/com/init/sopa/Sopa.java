package com.init.sopa;
import java.util.Random;
import java.util.Scanner;

public class Sopa {
	private String numeros="123456789";
	private char [][] matriz;
	private boolean [][] criba;
	private int posX;
	private int posY;
	private boolean d;
	private boolean s;
	Random rnd;
	String numero ="";

	public Sopa() {
		matriz = new char [5][5];
		criba = new boolean [5][5];
		rnd = new Random();
		posX=0;
		posY=0;
		d=true;
		s=true;
	}

	public void llenarSopa() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				matriz[i][j]=numeros.charAt(rnd.nextInt(numeros.length()));
				criba[i][j]=true;
			}
		}
	}
	
	
	public void imprimirSopa() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	public void imprimirCriba() {
		System.out.println();
		for(int i=0;i<criba.length;i++) {
			for(int j=0;j<criba[0].length;j++) {
				if(criba[i][j]) {
					System.out.print("T ");
				}else System.out.print("F ");
			}
			System.out.println();
		}
	}
	
	
	public boolean validarCrilla() {
		boolean a=true;
		if(s) {
		
			if(d) {
				for(int i=0;i<numero.length();i++) {
					if(criba[posX][posY+i]||matriz[posX][posY+i]==numero.charAt(i)) a=true;
					else {
						a=false; 
						break;
					}
					
				}
			}else {
				for(int i=0;i<numero.length();i++) {
					if(criba[posX][14-posY-i]||matriz[posX][14-posY-i]==numero.charAt(i)) a=true;
					else {
						a=false; 
						break;
					}
					//indicePalabra++;
				}
			}
		}else {
			
			if(d) {
				for(int i=0;i<numero.length();i++) {
					if(criba[posX+i][posY]||matriz[posX+i][posY]==numero.charAt(i)) a=true;
					else {
						a=false; 
						break;
					}
					
				}
			}else {
				for(int i=0;i<numero.length();i++) {
					if(criba[14-posX-i][posY]||matriz[14-posX-i][posY]==numero.charAt(i))a=true;
					else {
						a=false; 
						break;
					}
					
				}
			}
		}

		return a;
	}
	
	
	public void insertarValorHorizontal() {
		int indicePalabra=0;
		if(d) {
			if(validarCrilla()) {
				for(int i=0;i<numero.length();i++) {
					matriz[posX][i+posY]=numero.charAt(indicePalabra);
					criba[posX][i+posY]=false;
					indicePalabra++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarValorHorizontal();
				else insertarValorVertical();
			}
		}else {
			if(validarCrilla()) {
				for(int i=0;i<numero.length();i++) {
					matriz[posX][14-posY-i]=numero.charAt(indicePalabra);
					criba[posX][14-posY-i]=false;
					indicePalabra++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarValorHorizontal();
				else insertarValorVertical();
			}
		}
	}

	
	public void insertarValorVertical() {
		int indiceValor=0;
		if(d) {
			if(validarCrilla()) {
				for(int i=0;i<numero.length();i++) {
					matriz[posX+i][posY]=numero.charAt(indiceValor);
					criba[posX+i][posY]=false;
					indiceValor++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarValorHorizontal();
				else insertarValorVertical();
			}
		}else {
			if(validarCrilla()) {
			for(int i=0;i<numero.length();i++) {
					matriz[14-posX-i][posY]=numero.charAt(indiceValor);
					criba[14-posX-i][posY]=false;
					indiceValor++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarValorHorizontal();
				else insertarValorVertical();
			}
		}
	}


	
	public void generarPosicionAleatoria() {
		PosicionAleatoria pos=new PosicionAleatoria(numero);
		pos.generarPosicionAleatoria();
		posX=pos.getI();
		posY=pos.getJ();
		d=pos.getD();
		s=pos.getS();
		System.out.println("x: "+posX);
		System.out.println("y: "+posY);
		System.out.println("d: "+d);
		System.out.println("s: "+s);
		System.out.println("Palabra:"+numero);
	}

	public static void main(String Args[]) {
		Sopa sp = new Sopa();
		sp.llenarSopa();
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext()) {
			sp.numero=sc.nextLine();
			sp.generarPosicionAleatoria();
			if(sp.s)sp.insertarValorHorizontal();
			else sp.insertarValorVertical();
			sp.imprimirSopa();
		}
	}
}