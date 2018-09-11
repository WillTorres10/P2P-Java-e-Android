package com.example.will.p2pandroid.Cliente;

import android.widget.TextView;
import com.example.will.p2pandroid.Arquivos.gerenciador;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.String;

public class cliente {

    public TextView status;
    public String faixaIP;
    public gerenciador geren;

    public cliente(TextView status, String faixaIP) {
        this.status = status;
        this.faixaIP = faixaIP;
    }

    public ArrayList<String> listarIPs() {
        ArrayList<String> ips = new ArrayList<String>();
        for(int i = 0; i<256; i++ ) {
            ips.add(faixaIP+i);
        }
        return ips;
    }

    public void buscarArquivo(String music) {
        ArrayList<String> ips = listarIPs();
        Collections.shuffle(ips);
        for (String ip : ips) {
            buscaThread buscar = new buscaThread(ip, music, status);
            System.out.println();
            Thread preBusca = new Thread(buscar);
            preBusca.start();
        }
    }
}
