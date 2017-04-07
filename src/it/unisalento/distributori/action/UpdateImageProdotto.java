package it.unisalento.distributori.action;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

public class UpdateImageProdotto extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1342503724031823169L;
	private int idProdotto;
	private String foto;
	private File file_foto;
	private String myFileContentType;
	private String myFileFileName;
	private String tomcat_destPath;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute()  {

		try{
			logger.trace("execute()");
			Random random = new Random();//per garantire un nome diverso della nuova foto per agevolare il caricamento anche se la cache ha salvato la foto precedente

			/* Copy file to a safe location */
			file_foto = new File(foto);

			tomcat_destPath = System.getProperty("catalina.base")+"/webapps/distributori/images/";

			//ottengo il prodotto per comporre il nome del file di destinazione
			Prodotto prodotto = FactoryDao.getIstance().getProdottoDao().get(idProdotto, Prodotto.class);
			myFileFileName = prodotto.getId().toString()+"_"+random.nextInt(100)+".jpg";

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
			for(int i=0; i<files.length; i++){
				files[i].delete();
			}
			//salvo l'immagine nella cartella images
			FileUtils.copyFile(file_foto, destFile);

			//aggiorno la foto nel database
			prodotto.setFoto("images/"+myFileFileName);
			FactoryDao.getIstance().getProdottoDao().update(prodotto);

			return SUCCESS;
		}catch(IOException e){
			logger.error("Impossibile caricare l'immagine del prodotto sul file system, prodotto con id : "+idProdotto,e);
			addActionError("Impossibile caricare l'immagine del prodotto");
			return ERROR;
		} catch (Exception e){
			logger.error("Impossibile caricare l'immagine del prodotto a causa di un errore non dovuto al sistema di IO, il prodotto ha id" +idProdotto,e);
			addActionError("Impossibile caricare l'immagine del prodotto");
			return ERROR;
		}

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