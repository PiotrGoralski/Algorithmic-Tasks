package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Integer[] wagiPoddrzew; 
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("graf.txt"));
		int iloscWierzcholkow = scanner.nextInt();
		scanner.nextLine();
		scanner.nextLine();
		Integer[] iloscOdwolan = new Integer[iloscWierzcholkow];
		int sumaWag=0;
		int sumaWagPoddrzew=0;
		int sumaZer=0;
		String korzen = null;
		
		List<String[]> listaDanych = new ArrayList<>(iloscWierzcholkow);
		
		for(int i=0; i<iloscWierzcholkow; i++)
		{
			iloscOdwolan[i]=1;
			String[] podzielenie = scanner.nextLine().replaceAll(", ", " ").replaceAll(": ", " ").replaceAll(":", " ").split(" ");
			listaDanych.add(podzielenie);
		}
		scanner.close();
			
		for(int j=0; j<listaDanych.size(); j++)
		{
			String[] tabZmienna = listaDanych.get(j);
			for(int k=1; k<tabZmienna.length; k++)
			{
				iloscOdwolan[Integer.parseInt(tabZmienna[k])-1]--;
			}
		}
			
		for(int j=0; j<iloscOdwolan.length; j++)
		{
//			System.out.print(iloscOdwolan[j]+" ");	// ukazuje ilość odwołań do wierzchołka na danym indeksie+1 (liczone od 1 w dół)
			if(iloscOdwolan[j]==0)
			{
				sumaZer++;	
				sumaWag+=j+1;
			}
			else if(iloscOdwolan[j]==1)
				korzen=String.valueOf(j+1);
			else 
			{
				sumaZer=0;
				break;
			}
		}	
		
		if(sumaZer==iloscWierzcholkow-1 && korzen!=null)
		{	
			String[] synowie = listaDanych.get(Integer.parseInt(korzen)-1);
			wagiPoddrzew = new Integer[synowie.length-1];
			for(int i=1; i<synowie.length; i++)
			{
				wagiPoddrzew[i-1]=0;
				liczWage(synowie[i],i-1,listaDanych);
				sumaWagPoddrzew+=wagiPoddrzew[i-1];
			}
		
//			System.out.println("\n"+sumaWag+" "+sumaWagPoddrzew);	// ukazuje sumę wierzchołków grafu i sumę wierzchołków zbudowanego drzewa
																// jeśli graf nie jest spójny, to liczby będą się różnić
/*			
			for(int i=0; i<wagiPoddrzew.length; i++) 	// pętla ukazuje wagi każdego poddrzewa którego ojcem jest korzeń
			{											// kolejność wag równa się kolejności podanych synów w pliku wejściowym
				System.out.println("waga poddrzewa "+i+" wynosi: "+wagiPoddrzew[i]);
			}
*/			
			int minWagaPoddrzewa=wagiPoddrzew[0];
			int maxWagaPoddrzewa=wagiPoddrzew[0];
			
			for(int i=1; i<wagiPoddrzew.length; i++)
			{
				if(wagiPoddrzew[i]<minWagaPoddrzewa)
					minWagaPoddrzewa=wagiPoddrzew[i];
				else if(wagiPoddrzew[i]>maxWagaPoddrzewa)
					maxWagaPoddrzewa=wagiPoddrzew[i];
			}
			
			if(sumaWag==sumaWagPoddrzew)
				System.out.println("TAK\n"+maxWagaPoddrzewa+" "+minWagaPoddrzewa);
			else 
				System.out.println("NIE");
		}
		else
			System.out.println("NIE");
	}
	
	public static void liczWage(String wierzcholek, int id, List<String[]> listaDanych)
	{
		wagiPoddrzew[id]+=Integer.parseInt(wierzcholek);
		String[] wczytanyWiersz = listaDanych.get(Integer.parseInt(wierzcholek)-1);

		for(int j=1; j<wczytanyWiersz.length; j++)
			liczWage(wczytanyWiersz[j], id, listaDanych);
	}
}
