package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre o caminho do arquivo: ");
		String path = sc.next();
		
		List<Sale> list = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			 String line = br.readLine();
	         while (line != null) {
	         String[] fields = line.split(",");
	                    
	         Integer month = Integer.parseInt(fields[0]);
	         Integer year = Integer.parseInt(fields[1]);
	         String seller = fields[2];
	         Integer itemsSold = Integer.parseInt(fields[3]);
	         Double totalAmount = Double.parseDouble(fields[4]);
	            
	         list.add(new Sale(month, year, seller, itemsSold, totalAmount));
	         line = br.readLine();
			}
			
	        List<Sale> sale = list.stream().filter(s -> s.getYear() == 2016).sorted((s1, s2) -> Double.compare(s2.averagePrice(), s1.averagePrice())).limit(5).collect(Collectors.toList()); 	
			sale.forEach(System.out::println);
			
			System.out.println();
		    Double totalLoganSales = list.stream().filter(s -> s.getSeller().equals("Logan") &&(s.getMonth() == 1 || s.getMonth() == 7)) .map(s -> s.getTotal()).reduce(0.0, (x, y) -> x + y);
		    System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + totalLoganSales);	
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			sc.close();
		}

	}

}
