package Arquivos;

import java.io.File;

public class gerenciador {
	
	private static File folder = null;
	
	@SuppressWarnings("static-access")
	public gerenciador(String DirFiles) {
		this.folder = new File(DirFiles);
	}
	
	public void listarArquivos() {
		for (final File fileEntry : folder.listFiles()) {
			System.out.println(fileEntry.getName());
		}
	}
	
	public Boolean verificaArquivo(String Arquivo) {
		for (final File fileEntry : folder.listFiles()) {
			if(fileEntry.getName().equals(Arquivo)) {
				return true;
			}
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
