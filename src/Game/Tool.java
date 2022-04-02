package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tool {
	// Åª¹Ï¤ù
	public static BufferedImage getImage(String name) {
		try {
			BufferedImage Img = ImageIO.read(Tool.class.getResource("/img/" + name));
			return Img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}