package utils;

import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @author Tran Thanh The
 */
public class ImageManager {

    public static String resourcesPath = "/";
    public static String imagesPath = resourcesPath + "images/";

    public ImageManager() {
    }

    public ImageIcon getImage(String name) {
        try {
            URL pathImage = getClass().getResource(imagesPath + name);
            return new ImageIcon(pathImage);
        } catch (Exception e) {
            return new ImageIcon(getClass().getResource(imagesPath + "tra-hoa-hong-da.png"));
        }
    }

    public Icon resizeIcon(ImageIcon source, int width, int height) {
        Image img = source.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
