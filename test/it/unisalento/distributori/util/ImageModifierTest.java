package it.unisalento.distributori.util;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageModifierTest {

	
	@Test
	public void test() throws Exception {
		
		//test di ImageModifier(BufferedImage image)
		File file =  new File("./test/it/unisalento/distributori/util","img_test.jpg");
		BufferedImage image = ImageIO.read(file);

		ImageModifier modifier = new ImageModifier(image);
		
		assertNotNull(modifier);
		assertEquals(image, modifier.getOriginalImage());

		//test di resizeImage(int type, int IMG_WIDTH, int IMG_HEIGHT)
		BufferedImage resized_image = modifier.resizeImage(modifier.getOriginalImage().getType(), 64, 64);
		
		assertNotNull(resized_image);
		assertTrue(resized_image.getWidth()==64 && resized_image.getHeight()==64);
		assertTrue(resized_image.getType()==modifier.getOriginalImage().getType());
	}

}
