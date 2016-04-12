/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Carmen
 */
public class TD2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tampon queue = new Tampon();
        ArrayList<String> Madames = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("C:/TD2/ListePersonnes.txt"))) {
            while ((line = br.readLine()) != null) {
                if(line.indexOf(".") == 1){
                    queue.put(line);
                }else{
                    Madames.add(line);
                }
            }
            br.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        for(String name : Madames){
            queue.put(name);
        }
        
        Consumer consumer = new Consumer(queue);
        consumer.start();
    }
    
}
