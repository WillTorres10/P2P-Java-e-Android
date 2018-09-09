package Servidor;

import Arquivos.gerenciador;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread{
	
	private Socket cliente = null;
	private gerenciador geren = null;
	
	//Construtor da classe
	public ServerThread(Socket socket, gerenciador gerenci ) {
		this.cliente = socket;
		this.geren = gerenci;
	}
	
	@SuppressWarnings("resource")
	public void run() {
		// Informa o IP do cliente que se conectou
		System.out.println("[Servidor] IP: " + cliente.getInetAddress().getHostAddress() + " Conectado");
		InputStream is;
		try {
			//Criando o objeto para receber o Arraylist do cliente
			is = cliente.getInputStream();
			try {
				//Criando Objetos necessários para transmissão
				ObjectInputStream ois = new ObjectInputStream(is);
				OutputStream os = cliente.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				while(true){
					//Fica verificando se há alguma informação mandada pelo cliente
					String pedido = (String)ois.readObject();
					//Ao chegar um novo pedido, o sistema verifica se existe o arquivo
					if(geren.verificaArquivo(pedido)) {
						//Se o arquivo existir, ele retornará o peso do arquivo
						File enviar = geren.pegarArquivo(pedido);
						byte [] mybytearray  = new byte [(int)enviar.length()];
						
						//Retornando o peso do arquivo
						int peso = (int)enviar.length();
						oos.writeObject(peso);
						
						//Retorna o nome do arquivo
						String nomeArquivo = enviar.getName();
						oos.writeObject(nomeArquivo);
						
						
						//Agora retorna o arquivo
						FileInputStream fis = new FileInputStream(enviar);
						BufferedInputStream bis = new BufferedInputStream(fis);
				        bis.read(mybytearray,0,mybytearray.length);
				        
				        System.out.println("[Servidor] Enviando " + enviar.getName() + "(" + mybytearray.length + " bytes)");
				        
				        os.write(mybytearray,0,mybytearray.length);
				        os.flush();
				        
				        System.out.println("[Servidor] Arquivo Enviado.");
					}else {
						//Retornando o peso do arquivo
						int peso = -1;
						oos.writeObject(peso);
					}
				}
			} catch (IOException e1) {
				// Caso o cliente se desconecte ele informa
				System.out.println("[Servidor] IP: " + cliente.getInetAddress().getHostAddress() + " Desconectado");
				//Fecha o Socket Aberto
				this.cliente.close();
				//Encerra a Thread
				Thread.currentThread().interrupt();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
