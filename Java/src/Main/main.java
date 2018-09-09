package Main;

import java.net.Socket;
import java.util.ArrayList;

import Arquivos.gerenciador;
import Servidor.server;
import Cliente.cliente;

public class main {
	
	private static String DirFile = "/home/will/Music";
	private static gerenciador file = null;
	public static ArrayList<Socket> conectados = null;
	
	public static void main(String[] args) {
		
		ArrayList<Thread> servicos = new ArrayList<Thread>();
		
		//Criando o gerênciador de Arquivos
		file = new Arquivos.gerenciador(DirFile);
		//Criando a lista de peers
		conectados = new ArrayList<Socket>();
		//Ativando o Servidor
		server sv = new server(file, conectados);
		Thread srv = new Thread((Runnable) sv);
		srv.start();
		//Ativando o Cliente
		cliente a = new cliente(conectados);
		a.descobrirServidores();
		a.buscarArquivo("Aaron Smith - Dancin (KRONO Remix).mp3");
	}

}
