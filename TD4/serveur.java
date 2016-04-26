import java.io.*;
import java.net.*;

public class serveur extends Thread{
		
		protected ServerSocket ecouteurserveur;
		private int ConnectionsActives;
		private final int PORT = 7777;
		public final int NO_CONNECTIONS = 5;
		
		public static void main(String[] args){
			new Serveur();
		}
		
		public serveur(){
			try{ 
				ecouteurserveur = new ServerSocket(PORT, NO_CONNECTIONS);
				ConnectionsActives = 0;
				this.start();
			}catch (BindException e){
				System.out.println("Erreur : " + e);
			}catch(IOException e){
				System.out.println("Erreur : " + e);
			}			
		}
		
		public void run(){
			try{
				while(true){
					Socket ecouteurclient = ecouteurServeur.accept();
					Connectionclient connectionclient = new Connectionclient(ecouteurclient,this);
				}
			}catch (IOException e){
				System.out.println("Erreur : " + e);
			}finally{
				try{
					ecouteurserveur.close();
				}catch (IOException e){
					System.out.println("Erreur : " + e);
				}	
			}
			
		}
		
		public synchronized void connectionDemarree(){
			ConnectionsActives++;
		}
		
		public synchronized void connectionFermee(){
			ConnectionsActives--;
		}
		
		public synchronized int getConnectionsActives(){
			return this.ConnectionsActives;
		}
}

class ConnectionClient extends Thread{

	Socket communicationClient;
	serveur administrateur;
	
	public Connectionclient(Socket ecouteurclient, Serveur le_serveur){
		communicationclient = ecouteurclient;
		administrateur = le_serveur;
		this.start();
	}
	
	public void run(){
		administrateur.connectionDemarree();
		
		try{
			
			BufferedReader messageclient = new BufferedReader(new InputStreamReader(communicationclient.getInputStream()));
            String receptionclient = messageclient.readLine();
            			
			PrintWriter ligneclient = new PrintWriter(communicationclient.getOutputStream(), true);
			InetAddress adresseclient = communicationclient.getInetAddress();
			int portclient = communicationclient.getPort();
			InetAddress adresseserveur = null;
			try{
				adresseserveur = InetAddress.getLocalHost();
			}catch (UnknownHostException e){
				System.out.println("Erreur : " + e);
			}
			int portserveur = communicationclient.getLocalPort();
			String CR = System.getProperty("line.separator");
			ligneclient.println("Bonjour " + receptionclient + " ! " +
			                    "Vour etes le client : " + administrateur.getConnectionsActives() + " de " + administrateur.NO_CONNECTIONS + " !");
			System.out.println("Bonjour " + receptionClient + " !" + CR +
			                   "client >>> nom : " + adresseclient.getHostName() + " | IP : " + adresseclient.getHostAddress() + " | port : " + portclient + CR + 
			                   "serveur >>> nom : " + adresseserveur.getHostName() + " | IP : " + adresseserveur.getHostAddress() + " | port : " + portserveur);
			
			
		}catch (IOException e){
			System.out.println("Erreur : " + e);
		}finally{
			try{
				communicationclient.close();
			}catch(IOException e){
				System.out.println("Erreur : " + e);
			}
			administrateur.connectionFermee();
		}
		
	
	}
}
