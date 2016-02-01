package com.gamsion.chris.utilities.passwordGen.utility;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class UtilityMethods {
	public static Image getImage(String path) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File(path));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}

	public static Image getImage(File f) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(f);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}

	public static Image getImage(URL path) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(path);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
}
