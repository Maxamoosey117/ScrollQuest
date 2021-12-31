package scrollquest.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import scrollquest.Handler;

public class ImageLoader {

	public static BufferedImage loadImage(Handler handler, String path){
		try {
			return ImageIO.read(handler.getRes(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
