package it.unisalento.distributori.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageModifier {

	private BufferedImage originalImage;
	
	public ImageModifier(BufferedImage image) {
	    this.originalImage=image;
	}
	
	public BufferedImage resizeImage(int type, int IMG_WIDTH, int IMG_HEIGHT) {
	    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	    g.dispose();

	    return resizedImage;
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}
}
