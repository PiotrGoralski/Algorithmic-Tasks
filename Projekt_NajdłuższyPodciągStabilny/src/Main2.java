import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("ciag.txt"));
		int iloscElementowCiagu = scanner.nextInt();
		int maxDiff = scanner.nextInt();
		int poczatekNajdluzszegoCiagu = 0;
		int dlugoscNajdluzszegoCiagu = 0;
		int dlugoscObecnegoCiagu = 0;
		long iloscPorownan=0;
		
		Integer[] tablicaDanych = new Integer[iloscElementowCiagu];
		
		for(int i=0; i<iloscElementowCiagu; i++)
		{
			tablicaDanych[i]=scanner.nextInt();
		}
		scanner.close();		
		
		int indexMinimumObecnegoCiagu = 0;
		int indexMaximumObecnegoCiagu = 0;
		
		for(int i=1; i<iloscElementowCiagu-1;)
		{
			dlugoscObecnegoCiagu++;
			
			iloscPorownan++;
			if(tablicaDanych[i]>=tablicaDanych[indexMaximumObecnegoCiagu])
				indexMaximumObecnegoCiagu=i;
			
			iloscPorownan++;
			if(tablicaDanych[i]<=tablicaDanych[indexMinimumObecnegoCiagu])
				indexMinimumObecnegoCiagu=i;
			
			iloscPorownan++;
			if(tablicaDanych[indexMaximumObecnegoCiagu]-tablicaDanych[indexMinimumObecnegoCiagu]<=maxDiff)
			{
				i++;
//				System.out.println("W przedziale "+(dlugoscObecnegoCiagu+1));	// ukazuje, czy w danym obrocie pętli podciąg był zgodny 
			}																	// i wyświetla długość obecnego podciągu (nie stosować dla dużej ilości elementów)
			else
			{
//				System.out.println("Poza przedzialem "+dlugoscObecnegoCiagu);	// ukazuje, czy w danym obrocie pętli podciąg był niezgodny
				if(dlugoscObecnegoCiagu > dlugoscNajdluzszegoCiagu)				// i wyświetla długość obecnego podciągu (nie stosować dla dużej ilości elementów)
				{
					dlugoscNajdluzszegoCiagu = dlugoscObecnegoCiagu;
					poczatekNajdluzszegoCiagu = i-dlugoscNajdluzszegoCiagu;
				}
				
				if(i==indexMaximumObecnegoCiagu)
				{
						i=indexMinimumObecnegoCiagu+1;
						while(tablicaDanych[indexMaximumObecnegoCiagu]-tablicaDanych[i]>maxDiff)
						{
							iloscPorownan++;
//							System.out.println("maxDiff nie spelnia maximum "+tablicaDanych[i]+" "+tablicaDanych[indexMaximumObecnegoCiagu]);	// ukazuje elementy dla których pętla wykonała by się niepotrzebnie
							i++;
						}
				}
				
				else if(i==indexMinimumObecnegoCiagu)
				{
						i=indexMaximumObecnegoCiagu+1;
						while(tablicaDanych[i]-tablicaDanych[indexMinimumObecnegoCiagu]>maxDiff)
						{
							iloscPorownan++;
//							System.out.println("maxDiff nie spelnia minimum "+tablicaDanych[i]+" "+tablicaDanych[indexMinimumObecnegoCiagu]);	// ukazuje elementy dla których pętla wykonała by się niepotrzebnie
							i++;
						}
				}
				
				dlugoscObecnegoCiagu=0;
				indexMinimumObecnegoCiagu = i;
				indexMaximumObecnegoCiagu = i;
				i++;
			}
		}
		
		dlugoscObecnegoCiagu++;
		
		iloscPorownan++;
		if(tablicaDanych[iloscElementowCiagu-1]>tablicaDanych[indexMaximumObecnegoCiagu])
		{
			indexMaximumObecnegoCiagu=iloscElementowCiagu-1;
		}
		
		iloscPorownan++;
		if(tablicaDanych[iloscElementowCiagu-1]<tablicaDanych[indexMinimumObecnegoCiagu])
		{
			indexMinimumObecnegoCiagu=iloscElementowCiagu-1;
		}
		
		iloscPorownan++;
		if(tablicaDanych[indexMaximumObecnegoCiagu]-tablicaDanych[indexMinimumObecnegoCiagu]<=maxDiff)
		{
			dlugoscObecnegoCiagu++;
		}
		else if(dlugoscObecnegoCiagu > dlugoscNajdluzszegoCiagu)
		{
			dlugoscNajdluzszegoCiagu = dlugoscObecnegoCiagu;
			poczatekNajdluzszegoCiagu = iloscElementowCiagu-dlugoscObecnegoCiagu;
		}
		
		System.out.println(poczatekNajdluzszegoCiagu+" "+(poczatekNajdluzszegoCiagu+dlugoscNajdluzszegoCiagu-1));
		
		for(int i=poczatekNajdluzszegoCiagu; i<poczatekNajdluzszegoCiagu+dlugoscNajdluzszegoCiagu; i++)
			System.out.print("\n"+tablicaDanych[i]);
			
//		System.out.println("\nIlosc porównań = "+iloscPorownan);	// ukazuje ilosc porównań pomiędzy elementami tablicy
	}
}
