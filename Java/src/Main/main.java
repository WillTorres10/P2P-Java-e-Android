package Main;

import java.util.concurrent.TimeUnit;

import Arquivos.gerenciador;
import Servidor.server;
import Cliente.cliente;
import Main.Tela;

public class main {
	
	private static String DirFile = "/home/will/Music/";
	private static gerenciador file = null;
	
	public static void main(String[] args) throws InterruptedException {
		//Criando o gerênciador de Arquivos
		file = new Arquivos.gerenciador(DirFile);
		//Criando a lista de peers
		//Ativando o Servidor
		server sv = new server(file);
		Thread srv = new Thread(sv);
		srv.start();
		TimeUnit.SECONDS.sleep(2);
		new Tela().setVisible(true);
	}

}
