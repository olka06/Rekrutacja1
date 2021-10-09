package pl.akai;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.akai.pl.akai.Zadanko;

public class Main {
    /*
        Twoim zadaniem jest napisanie prostego programu do pobierania i transformowania danych
        udostępnianych przez API. Dokumentacje API możesz znależć pod poniższym linkiem:
        https://akai-recruitment.herokuapp.com/documentation.html

        Całe API zawiera jeden endpoint: https://akai-recruitment.herokuapp.com/book
        Endpoint ten zwraca liste książek zawierajacch informację takie jak:
        - id
        - tytuł
        - autor
        - ocena

        Twoim zadaniem jest:
        1. Stworzenie odpowiedniej klasy do przechowywania informacji o książce
        2. Sparsowanie danych udostępnianych przez endpoint. Aby ułatwić to zadanie,
           do projektu są dołaczone 3 najpopularniejsze biblioteki do parsowania JSONów
           do obiektów Javy - Gson, Org.Json, Jackson. Możesz wykorzystać dowolną z nich
        3. Po sparsowaniu JSONu do obiektów Javy, uzupełnij program o funkcję wypisującą 3 autorów z
           najwyższą średnią ocen (wypisz także średnie ocen)

       Projekt został utworzony przy użyciu najnowszej Javy 17,
       jednakże nic nie stoi na przeszkodzie użycia innej wersji jeśli chcesz
     */

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
       
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	
    	URL url = new URL(" file:C:/Users/Yulia/Downloads/response_1633717265005.json");
			
		List<Zadanko> myObjects = Arrays.asList(objectMapper.readValue(url, Zadanko[].class));
		
		
		topThree(myObjects);
    }
    

    
    public static void topThree(List<Zadanko> myObjectsIn) {
    	
    	List<Double> averages = new ArrayList<Double>();
    	List<String> authors = new ArrayList<String>();
    	double sum= 0;
    	int count = 0;
    	double average = 0;
    	String tempStr;
    	double temp;
    	
    	//create a list of all authors without repetitions
    	for (int i = 0; i < myObjectsIn.size(); i++) {
    		if (authors.contains((myObjectsIn.get(i).getAuthor()))){	
    		}
    		else authors.add(myObjectsIn.get(i).getAuthor());
    	}
    	
    	//create a list of averages of each author
    	for (int j = 0; j < authors.size(); j++) {
    		for (int i = 0; i < myObjectsIn.size(); i++) {
        		if (myObjectsIn.get(i).getAuthor().equals(authors.get(j))) {
        			sum += myObjectsIn.get(i).getRating();
        			count +=1;
        		}
        		else{
        			if (i!= myObjectsIn.size()-1) {
        				
        			}
        			else
        			{
        				averages.add(sum/count);
        				sum = 0;
        				count =0;
        				break;
        			}
        		}
        	}
    	}
    	
    	
    	//sort lists 
    	for(int i=0; i < averages.size(); i++){  
            for(int j=1; j < (averages.size()-i); j++){  
                     if(averages.get(j-1) > averages.get(j)){  
                            
                            temp = averages.get(j-1);  
                            averages.set(j-1,averages.get(j));  
                            averages.set(j, temp);  
                            
                            tempStr = authors.get(j-1);  
                            authors.set(j-1,authors.get(j));  
                            authors.set(j, tempStr); 
                    } 
            }
    	}
    	
    	//print the results
    	for (int i = averages.size()-1; i > averages.size()-4; i--) {
    		System.out.println( averages.size()-i + ". " + authors.get(i) + ": " + averages.get(i));
    	}
    }
}
