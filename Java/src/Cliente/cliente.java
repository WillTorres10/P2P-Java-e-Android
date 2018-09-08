package Cliente;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class cliente {
	
	public ArrayList<Socket> conectados = null;

	public cliente(ArrayList<Socket> conect) {
		conectados = conect;
		
	}
	
	public void descobrirServidores() {
		try {
			System.out.println("[Cliente] "+InetAddress.getLocalHost().getHostAddress());
		}catch(Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket buscador = new Socket(InetAddress.getLocalHost().getHostAddress(), 8000);
				OutputStream os = buscador.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(null);
				buscador.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		}
	}
	
	public void buscarArquivo(String music) {
		Collections.shuffle(conectados);
		ArrayList<Thread> Buscando = new ArrayList<Thread>();
		for (Socket socket : conectados) {
			buscaThread buscar = new buscaThread(socket, music);
			Thread preBusca = new Thread(buscar.run());
			Buscando.add(preBusca);
			preBusca.start();
		}
	}
}
