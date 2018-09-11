package Cliente;

import Arquivos.gerenciador;
import java.util.ArrayList;
import java.util.Collections;

public class cliente {
	
	public gerenciador geren;
	public String faixaIP;
	
	public cliente(String faixaIP) {
		this.faixaIP = faixaIP;
	}
	
	public ArrayList<String> listarIPs() {
		ArrayList<String> ips = new ArrayList<String>();
		for(int i = 0; i<256; i++ ) {
			ips.add(this.faixaIP+i);
		}
		return ips;
	}
	
	public void buscarArquivo(String music) {
		ArrayList<String> ips = listarIPs();
		Collections.shuffle(ips);
		for (String ip : ips) {
			buscaThread buscar = new buscaThread(ip, music);
			Thread preBusca = new Thread(buscar);
			preBusca.start();
		}
	}
}
