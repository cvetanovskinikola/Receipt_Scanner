package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try {
            URL url = new URL("https://interview-task-api.mca.dev/qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder responseData = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    responseData.append(line);
                }

                reader.close();

                
                ObjectMapper objectMapper = new ObjectMapper();
                
                Product[] product = objectMapper.readValue(responseData.toString(), Product[].class);
                List<Product> domest = new ArrayList<>();
                List<Product> imp = new ArrayList<>();
                Double dc = 0.0;
                Double ic = 0.0;
                
			
                for(Product p : product) {
                	if(p.isDomestic()) {
                	    domest.add(p);
                	}
                	else imp.add(p);
                }
                Collections.sort(domest, (p1, p2) -> p1.getName().compareTo(p2.getName()));
                Collections.sort(imp, (p1, p2) -> p1.getName().compareTo(p2.getName()));
                System.out.println(". Domestic");
                for(int i=0;i<domest.size();i++) {
                	System.out.println(". . ." + domest.get(i).getName());
                	System.out.println("     Price: $" + domest.get(i).getPrice());
                	if(domest.get(i).getDescription().length()>10) {
                		System.out.println("     " + domest.get(i).getDescription().substring(0,10)+"...");
                	}
                	else System.out.println("     " + domest.get(i).getDescription());
                	if(domest.get(i).getWeight()==null) {
                		System.out.println("     Weight: N/A");
                	}
                	else System.out.println("     Weight:" + domest.get(i).getWeight() + "g");
                	dc+=domest.get(i).getPrice();
                }
                System.out.println(". Imported");
                for(int j=0;j<imp.size();j++) {
                	System.out.println(". . ." + imp.get(j).getName());
                	System.out.println("     Price: $" + imp.get(j).getPrice());
                	if(imp.get(j).getDescription().length()>10) {
                		System.out.println("     " + imp.get(j).getDescription().substring(0,10)+"...");
                	}
                	else System.out.println("     " + imp.get(j).getDescription());
                	if(imp.get(j).getWeight()==null) {
                		System.out.println("     Weight: N/A");
                	}
                	else System.out.println("     Weight: " + imp.get(j).getWeight() +"g");
                	ic+=imp.get(j).getPrice();
                }
                System.out.println("Domestic cost: $" + dc);
                System.out.println("Imported cost: $" + ic);
                System.out.println("Domestic count: " + domest.size());
                System.out.println("Imported count: " + imp.size());
                
            } else {
                System.out.println("Failed to fetch data. Response code: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

}
