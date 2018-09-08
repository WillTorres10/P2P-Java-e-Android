package Servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Servidor.ServerThread;
import Arquivos.gerenciador;

public class server {
	
	public gerenciador geren = null;
	public ArrayList<Socket> slotes = null;
	
	public server(gerenciador ger, ArrayList<Socket> conectados) {
		geren = ger;
		slotes = conectados;
	}

	public Runnable run() {
		 try {
			  @SuppressWarnings("resource")
			  ServerSocket servidor = new ServerSocket(8000);
			  System.out.println("[Servidor] Ouvindo a porta 8000");
              ArrayList<Thread> slotesThread = new ArrayList<Thread>();
              while(true) {
            	  	//Aguarda os pedidos de conexão
                    Socket cliente = servidor.accept();
                    //Adiciona a nova conexão ao arraylist
                    slotes.add(cliente);
                    //Verifica se a lista de threads está vazia
                    if(slotesThread.isEmpty()){
                    	//Se estiver ele cria uma nova thread
                        ServerThread novo = new ServerThread(cliente, geren);
                        Thread c1 = new Thread(novo.run());
                        c1.start();
                        slotesThread.add(c1);
                    }
                    else{
                    	//se já existir algum elemento na lista de threads ele
                    	//verificará se existe alguma thread morta
                        int adicionado = 0;
                        for(int i =0; i<slotesThread.size(); i++){
                        	//Se encontrar uma thread mortar e a nova ainda não tiver sido
                        	//adicionada, ele coloca a nova thread no lugar da morta
                            if(!slotesThread.get(i).isAlive() && adicionado == 0){
                            	ServerThread novo = new ServerThread(cliente, geren);
                                Thread c1 = new Thread(novo.run());
                                c1.start();
                                slotesThread.set(i, c1);
                                adicionado = 1;
                            }
                        }
                        //Se não encontrou nunhuma thread morta, ele adiciona mais uma no final da lista
                        if(adicionado == 0){
                        	ServerThread novo = new ServerThread(cliente, geren);
                            Thread c1 = new Thread(novo.run());
                            c1.start();
                            slotesThread.add(c1);
                        }
                    }
                }
	    	}   
		    catch(Exception e) {
		       System.out.println("Erro: " + e.getMessage());
	}
		return null;
	}

}
