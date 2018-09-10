package com.example.will.p2pandroid.Arquivos;

import android.os.Environment;

import java.io.File;

public class gerenciador {
    private File folder;

    @SuppressWarnings("static-access")
    public gerenciador(String DirFiles) {

        this.folder = new File(Environment.getExternalStorageDirectory().toString()+"/Download/");
    }

    public void listarArquivos() {
        try {
            for (File fileEntry : this.folder.listFiles()) {
                System.out.println(fileEntry.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Boolean verificaArquivo(String Arquivo) {
        try {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.getName().equals(Arquivo)) {
                    return true;
                }
            }
        }catch (Exception e){
        }
        return false;
    }

    public File pegarArquivo(String Arquivo) {
        for (final File fileEntry : folder.listFiles()) {
            if(fileEntry.getName().equals(Arquivo)) {
                return fileEntry;
            }
        }
        return null;
    }

    public static void listarArquivosNoDiretorio(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listarArquivosNoDiretorio(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }
}
