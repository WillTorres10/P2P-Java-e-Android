package com.example.will.p2pandroid.Cliente;

import android.net.wifi.WifiManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.String;

import static android.content.Context.WIFI_SERVICE;

public class cliente {

    public TextView status;
    public String faixaIP;

    public cliente(TextView status, String faixaIP) {
        this.status = status;
        this.faixaIP = faixaIP;
    }

    public ArrayList<String> listarIPs() {
        ArrayList<String> ips = new ArrayList<String>();
        for(int i = 100; i<120; i++ ) {
            ips.add(faixaIP+i);
        }
        return ips;
    }

    public void buscarArquivo(String music) {
        this.status.setText("Buscando");
        ArrayList<String> ips = listarIPs();
        Collections.shuffle(ips);
        for (String ip : ips) {
            buscaThread buscar = new buscaThread(ip, music, this.status);
            status.setText("Tetando IP: "+ip);
            int verifica = buscar.run();
            if(verifica != -1){
                break;
            }
            status.setText("Tetando IP: "+ip);
        }
        status.setText("FIM");
    }
}
