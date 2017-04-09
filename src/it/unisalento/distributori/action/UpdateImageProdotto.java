package it.unisalento.distributori.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.util.ImageModifier;

public class UpdateImageProdotto extends ActionSupport{
   
		/**
	 * 
	 */
	private static final long serialVersionUID = 1781554015029191455L;

		private int idProdotto;

		private String foto;
		private File file_foto;
		private String myFileContentType;
		private String myFileFileName;
		private String myResizedFileName;
		private String tomcat_destPath;
		
	   public String execute() throws Exception {
		   
		  Random random = new Random();//per garantire un nome diverso della nuova foto per agevolare il caricamento anche se la cache ha salvato la foto precedente
		  
	      file_foto=new File(foto);
	      
	      tomcat_destPath=System.getProperty("catalina.base")+"/webapps/distributori/images/";
	      //tomcat_destPath="C:/Users/Salvatore/Documents/UniversitÓ/Specialistica/A.A. 2014_2015/Software Engineering/Workspace2/distributori/WebContent/images";
	      
	      //ottengo il prodotto per comporre il nome del file di destinazione
	      Prodotto prodotto=FactoryDao.getIstance().getProdottoDao().get(idProdotto, Prodotto.class);
	      myFileFileName=prodotto.getId().toString()+"_"+random.nextInt(100)+".jpg";
	      
	      try{
	     	 System.out.println("Src File name: " + foto);
	     	 System.out.println("Dst File name: " + myFileFileName);

	     	 File destFile  = new File(tomcat_destPath, myFileFileName);
	     	 
	     	 //elimino eventuali foto precedenti del prodotto
	     	 File directory = new File(tomcat_destPath);
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
	    	 
	    	 //salvo la versione ridotta 256x256 per la pagina di descrizione prodotto della App Android
	    	 BufferedImage originalImage = ImageIO.read(destFile);
	         int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
	         
	         ImageModifier img_modifier = new ImageModifier(originalImage);
	    	 BufferedImage resizedImageJpg_256x256 = img_modifier.resizeImage(type, 256, 256);
	    	 myResizedFileName = myFileFileName.replace(".jpg", "_256sizes.jpg");
	         ImageIO.write(resizedImageJpg_256x256, "jpg", new File(tomcat_destPath, myResizedFileName));
	         //...e la 64x64 per l'icona di elenco prodotti del distributore della App Android
	         BufferedImage resizedImageJpg_64x64 = img_modifier.resizeImage(type, 64, 64);
	    	 myResizedFileName = myFileFileName.replace(".jpg", "_64sizes.jpg");
	         ImageIO.write(resizedImageJpg_64x64, "jpg", new File(tomcat_destPath, myResizedFileName));
	  
	      }catch(IOException e){
	         e.printStackTrace();
	         return ERROR;
	      }
	
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

	public String getTomcat_destPath() {
		return tomcat_destPath;
	}

	public void setTomcat_destPath(String tomcat_destPath) {
		this.tomcat_destPath = tomcat_destPath;
	}
	
}