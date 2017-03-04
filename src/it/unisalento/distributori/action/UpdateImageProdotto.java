package it.unisalento.distributori.action;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

import java.io.IOException;
import java.util.Random;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

public class UpdateImageProdotto extends ActionSupport{
   
		private int idProdotto;

		private String foto;
		private File file_foto;
		private String myFileContentType;
		private String myFileFileName;
		private String destPath;
		
	   public String execute() throws Exception {
		   
		   Random random = new Random();
		   
	      /* Copy file to a safe location */
	      destPath = "C:/Users/Salvatore/Documents/Università/Specialistica/A.A. 2014_2015/Software Engineering/Workspace2/distributori/WebContent/images/";
	      file_foto=new File(foto);
	      
	      //ottengo il prodotto per comporre il nome del file di destinazione
	      Prodotto prodotto=FactoryDao.getIstance().getProdottoDao().get(idProdotto, Prodotto.class);
	      myFileFileName=prodotto.getId().toString()+"_"+random.nextInt(100)+".jpg";
	      
	      try{
	     	 System.out.println("Src File name: " + foto);
	     	 System.out.println("Dst File name: " + myFileFileName);

	     	 File destFile  = new File(destPath, myFileFileName);
	     	 
	     	 //elimino eventuali foto precedenti del prodotto
	     	 File directory = new File(destPath);
	     	 File[] files = directory.listFiles(new FilenameFilter()
	     	 {
	     		 public boolean accept(File dir, String name)
	     		 {
	     			 if(name.startsWith(prodotto.getId().toString()+"_"))
	     					 {
	     				 return true;
	     					 }
	     			 return false;
	     		 }

	     	 });
	     	 for(int i=0; i<files.length; i++)
	     		 files[i].delete();
	     	 
	     	 //salvo l'immagine nella cartella images
	    	 FileUtils.copyFile(file_foto, destFile);
	    	 
	    	 //aggiorno la foto nel database
	    	 prodotto.setFoto("images/"+myFileFileName);
	    	 FactoryDao.getIstance().getProdottoDao().update(prodotto);
	  
	      }catch(IOException e){
	         e.printStackTrace();
	         return ERROR;
	      }
	      
	      Thread.sleep(5000);//per il refresh delle immagini del progetto
	
	      return SUCCESS;
	   }

	   public String getMyFileContentType() {
	      return myFileContentType;
	   }
	   public void setMyFileContentType(String myFileContentType) {
	      this.myFileContentType = myFileContentType;
	   }
	   public String getMyFileFileName() {
	      return myFileFileName;
	   }
	   public void setMyFileFileName(String myFileFileName) {
	      this.myFileFileName = myFileFileName;
	   }

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public File getFile_foto() {
		return file_foto;
	}

	public void setFile_foto(File file_foto) {
		this.file_foto = file_foto;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
}