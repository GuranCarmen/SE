package td3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;import java.util.TreeMap;
import java.util.logging.FileHandler; 
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
        
public class TD3 {

	public static void main(String[] args) throws InterruptedException {
    		
    		
    		LogManager lm = LogManager.getLogManager();
    		final Logger logger = Logger.getLogger("princ");//logger pour enregistrer de possibles erreurs
    		FileHandler fh = null;
    
    		try {
    		fh = new FileHandler("log.xml");
     
    		lm.addLogger(logger);
    		logger.setLevel(Level.INFO);
    		fh.setFormatter(new XMLFormatter());
    
    		logger.addHandler(fh);
    		logger.setUseParentHandlers(false);
    		logger.log(Level.INFO, "Application demarre!");
    		} catch (Exception e) {
    		System.out.println("Exception thrown: " + e);
    		e.printStackTrace();
     	logger.log(Level.SEVERE, "Exception : " + e);
    		}
    
  
    class threads extends Thread // thread qui execute le programme pour chaque personne 
    
    {
      String s;
      Integer i;
      
    	public void run()
    	{
    		System.out.println("Fil d'execution pour Mme "+s+" a commence");
    		try { 
    			sleep(i*1000);
     	} catch (InterruptedException e) {
    			e.printStackTrace();
    			logger.log(Level.SEVERE, "Exception : " + e);
    		}  
    		System.out.println("Bonjour Mme "+s);
    		
