/*
 * «Copyright 2017 Roberto Reinosa»
 * 
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
	
	public static final int alcance = 1000;
	
	public static String sec_Nucl1[] = new String[alcance];
	public static String sec_Nucl2[] = new String[alcance];
	public static String sec_Nucl3[] = new String[alcance];
	
	public static String sec_aa1[] = new String[alcance];
	public static String sec_aa2[] = new String[alcance];
	public static String sec_aa3[] = new String[alcance];
	
	
	public static void main(String[] args) throws IOException {
		
		while(true){
		
			System.out.println("Introduce La Secuencia De ADN Del Provirus: ");
		
			InputStreamReader Secuencia_Nucleotidos = new InputStreamReader(System.in);
			BufferedReader buff1 = new BufferedReader(Secuencia_Nucleotidos);
		
			String coleccion1 = buff1.readLine();
			coleccion1 = coleccion1.concat("xxxxxx");
			
		
			char cadena[] = coleccion1.toCharArray();
			
			Convertidor_ARN(cadena);
			
			for(int i = 0; i < sec_Nucl1.length; i++){
			
				sec_Nucl1[i]="Secuencia = ";
				sec_Nucl2[i]="Secuencia = ";
				sec_Nucl3[i]="Secuencia = ";
				
				sec_aa1[i]="Secuencia = ";
				sec_aa2[i]="Secuencia = ";
				sec_aa3[i]="Secuencia = ";
				
				
			}
		
			System.out.println("Secuencias De ARN Para Secuencias De Aminoácidos Encontrados: " + "\n");
			
			System.out.println("Marco De Lectura 1: " + "\n");
			Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl1, 0);			
			
			System.out.println("Marco De Lectura 2: " + "\n");
			Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl2, 1);
			
			System.out.println("Marco De Lectura 3: " + "\n");
			Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl3, 2);
			
			
			System.out.println("Secuencias De Aminoácidos : " + "\n");
			
			System.out.println("Marco De Lectura 1: " + "\n");
			codigo_Letras_Aminoacidos(sec_Nucl1, sec_aa1);
			
			System.out.println("Marco De Lectura 2: " + "\n");
			codigo_Letras_Aminoacidos(sec_Nucl2, sec_aa2);
			
			System.out.println("Marco De Lectura 3: " + "\n");
			codigo_Letras_Aminoacidos(sec_Nucl3, sec_aa3);
		
		
		}
		
	}
	public static void Convertidor_ARN(char cadena[]){ // Convierte la secuencia a ARN
		
		for(int i = 0; i<cadena.length;i++){
			
			if(cadena[i] == 'a'){
				
				cadena[i] = 'A';
			
			}else if(cadena[i] == 't' || cadena[i] == 'T' || cadena[i]== 'u'){
				
				cadena[i] = 'U';
				
			}else if(cadena[i] == 'g'){
				
				cadena[i] = 'G';
				
			}else if(cadena[i] == 'c'){
				
				cadena[i] = 'C';
				
			}
			
			
		}
		
	}
	public static void Identificador_Secuencias_Nucleotidos(char cadena[], String sec_Nucl[], int marco){ //Identifica las secuencias de nucleotidos que pasará a secuencias de aminoácidos
		
		int guardar_Inicio = 0;
		int guardar_Final = 0;
		String Base;
		int contador_String_secNucl = 0;
		boolean nueva_Secuencia = false;
		boolean nuevo_Inicio = true;
		char secuencia_ARN_Temporal[];
			
		for(; marco<= cadena.length -4; marco+=3){
			
			if(cadena[marco]=='A' && cadena[marco+1]=='U' && cadena[marco+2]=='G' && nuevo_Inicio == true){
				
				guardar_Inicio= marco;
				nueva_Secuencia = true;
				nuevo_Inicio = false;
				
				
			}
			else if((cadena[marco]=='U' && cadena[marco+1]=='G' && cadena[marco+2]=='A')||
					(cadena[marco]=='U' && cadena[marco+1]=='A' && cadena[marco+2]=='A')||
					(cadena[marco]=='U' && cadena[marco+1]=='A' && cadena[marco+2]=='G')){
				
				if(nueva_Secuencia == true){ 
					
					guardar_Final = marco+2;
				
					for(int x = guardar_Inicio; x<= guardar_Final; x++){
					
						Base = String.valueOf(cadena[x]); 
					
						sec_Nucl[contador_String_secNucl]=sec_Nucl[contador_String_secNucl].concat(Base);
					
					}
					
				
					nueva_Secuencia = false;
					nuevo_Inicio = true;
					
					contador_String_secNucl++;
					
				}
				
			}
		}
		
		for(int i = 0; i<sec_Nucl.length; i++){
			
			if(sec_Nucl[i].equals("Secuencia = ")){
				break;
			}
			
			secuencia_ARN_Temporal = sec_Nucl[i].toCharArray();

			if(secuencia_ARN_Temporal.length<=102){
					
				continue;
					
			}
			for(int y = 0; y < secuencia_ARN_Temporal.length; y++){
					
				if(y % 100 == 0 && y != 0){
						
					System.out.print("\n");
						
				}
				System.out.print(secuencia_ARN_Temporal[y]);
					
			}
				
			System.out.println("\n"+"--------------------------------------------------------------------------------------------------------" + "\n");

				
		}
	
	}
	public static void codigo_Letras_Aminoacidos(String secuencia[], String aa[]){ // Pasa las secuencias de ARN a Secuencias de aminoácidos
		
		char secuencias_Temporal[];
		String codones="";
		String bases[] = new String[3];
		
		for(int i = 0; i<secuencia.length; i++){
			
			secuencias_Temporal = secuencia[i].toCharArray();
			
			for(int x = 12; x<secuencias_Temporal.length; x+=3){
				
				bases[0] = String.valueOf(secuencias_Temporal[x]);
				bases[1] = String.valueOf(secuencias_Temporal[x+1]);
				bases[2] = String.valueOf(secuencias_Temporal[x+2]);
				
				codones = codones.concat(bases[0]+bases[1]+bases[2]);
				
				if(codones.equals("UCU") || codones.equals("UCC") || codones.equals("UCA") || codones.equals("UCG") || codones.equals("AGU")
						|| codones.equals("AGC")){
					
					aa[i]= aa[i].concat("S");
					
				}else if(codones.equals("CGU") || codones.equals("CGC") || codones.equals("CGA") || codones.equals("CGG") || codones.equals("AGA")
						|| codones.equals("AGG")){
					
					aa[i] = aa[i].concat("R");
					
				}else if(codones.equals("UUA") || codones.equals("UUG") || codones.equals("CUU") || codones.equals("CUC") || codones.equals("CUA")
						|| codones.equals("CUG")){
					
					aa[i] = aa[i].concat("L");
					
				}else if(codones.equals("GCU") || codones.equals("GCC") || codones.equals("GCA") || codones.equals("GCG")){
					
					aa[i] = aa[i].concat("A");
					
				}else if(codones.equals("GGU") || codones.equals("GGC") || codones.equals("GGA") || codones.equals("GGG")){
					
					aa[i] = aa[i].concat("G");
					
				}else if(codones.equals("CCU") || codones.equals("CCC") || codones.equals("CCA") || codones.equals("CCG")){
					
					aa[i] = aa[i].concat("P");
					
				}else if(codones.equals("ACU") || codones.equals("ACC") || codones.equals("ACA") || codones.equals("ACG")){
					
					aa[i] = aa[i].concat("T");
					
				}else if(codones.equals("GUU") || codones.equals("GUC") || codones.equals("GUA") || codones.equals("GUG")){
					
					aa[i] = aa[i].concat("V");
					
				}else if(codones.equals("AUU") || codones.equals("AUC") || codones.equals("AUA")){
					
					aa[i] = aa[i].concat("I");
					
				}else if(codones.equals("AAU") || codones.equals("AAC")){
					
					aa[i] = aa[i].concat("N");
					
				}else if(codones.equals("GAU") || codones.equals("GAC")){
					
					aa[i] = aa[i].concat("D");
					
				}else if(codones.equals("UGU") || codones.equals("UGC")){
				
					aa[i] = aa[i].concat("C");
				
				}else if(codones.equals("CAA") || codones.equals("CAG")){
					
					aa[i] = aa[i].concat("Q");
					
				}else if(codones.equals("GAA") || codones.equals("GAG")){
				
					aa[i] = aa[i].concat("E");
				
				}else if(codones.equals("CAU") || codones.equals("CAC")){
					
					aa[i] = aa[i].concat("H");
					
				}else if(codones.equals("AAA") || codones.equals("AAG")){
				
					aa[i] = aa[i].concat("K");
				
				}else if(codones.equals("UUU") || codones.equals("UUC")){
					
					aa[i] = aa[i].concat("F");
					
				}else if(codones.equals("UAU") || codones.equals("UAC")){
				
					aa[i] = aa[i].concat("Y");
				
				}else if(codones.equals("AUG")){
					
					aa[i] = aa[i].concat("M");
					
				}else if(codones.equals("UGG")){
				
					aa[i] = aa[i].concat("W");
				
				}else if(codones.equals("UAA") || codones.equals("UAG") || codones.equals("UGA")){
					
					//Evita fallo al encontrar terminaciones
					
				}else{
					
					System.out.println(" ERROR AL ASIGNAR AA " + "\n");
					
				}
				
				codones="";
			
			}
			
			
		}
		
		char secuencia_AA_Temporal[];
		
		for(int i = 0; i<aa.length;i++){
			
			if(aa[i].equals("Secuencia = ")){
				break;
			}
			
			secuencia_AA_Temporal=aa[i].toCharArray();
			
			if(secuencia_AA_Temporal.length < 42){
					
					continue;	
			}
			for(int x = 0; x<secuencia_AA_Temporal.length;x++){
				
				if(x % 100 == 0 && x != 0){
					
					System.out.print("\n");
					
				}
				System.out.print(secuencia_AA_Temporal[x]);
								
			}
			
			System.out.println("\n"+"--------------------------------------------------------------------------------------------------------" + "\n");

		}
	}

} 
