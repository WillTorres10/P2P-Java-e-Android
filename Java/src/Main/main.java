package Main;

import java.net.Socket;
import java.util.ArrayList;

import Arquivos.gerenciador;
import Servidor.server;
import Cliente.cliente;

public class main {
	
	private static String DirFile = "/home/will/Music/";
	private static gerenciador file = null;
	
	public static void main(String[] args) {
		//Criando o gerênciador de Arquivos
		file = new Arquivos.gerenciador(DirFile);
		//Criando a lista de peers
		//Ativando o Servidor
		server sv = new server(file);
		Thread srv = new Thread(sv);
		srv.start();
		//Ativando o Cliente
		cliente a = new cliente();
		a.buscarArquivo("Termos.zip");
	}

}
