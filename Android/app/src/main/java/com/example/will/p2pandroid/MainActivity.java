package com.example.will.p2pandroid;

import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.will.p2pandroid.Cliente.cliente;
import com.example.will.p2pandroid.Servidor.server;
import com.example.will.p2pandroid.Arquivos.gerenciador;

public class MainActivity extends AppCompatActivity {

    private static String DirFile = Environment.getExternalStorageDirectory().toString()+"/Download/";
    private static gerenciador file = null;
    EditText nomeArquivo, faixaIP;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Criando o gerenciador de arquivos
        file = new gerenciador(DirFile);
        //Criando e Iniciando o servidor
        server sv = new server(file);
        Thread srv = new Thread(sv);
        srv.start();
        status = findViewById(R.id.status);
        faixaIP = findViewById(R.id.FaixaIp);
    }

    public void buscarArquivo(View v){
        cliente a = new cliente(status, faixaIP.getText().toString());
        status.setText("Aguarde");
        nomeArquivo = findViewById(R.id.nomeArquivo);
        a.buscarArquivo(nomeArquivo.getText().toString());
    }
}
