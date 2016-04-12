/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td2;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Carmen
 */
public class Consumer extends Thread {
    private final Tampon queue;
    
    public Consumer(Tampon queue){
        this.queue = queue;
    }
    
    @Override
    public void run(){
        String name;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/TD2/Bonjour.txt"))) {
            while((name = this.queue.get()) != null){
                bw.append("Bonjour " + name + "\n");
            }
            bw.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
