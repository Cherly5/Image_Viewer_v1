package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.Image;
import software.ulpgc.imageviewer.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private software.ulpgc.imageviewer.Image image;
    private BufferedImage bitmap;

    @Override
    public void show(software.ulpgc.imageviewer.Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        double scaleX = (double) getWidth() / bitmap.getWidth();
        double scaleY = (double) getHeight() / bitmap.getHeight();
        double scale = Math.min(scaleX, scaleY);

        int newWidth = (int) (bitmap.getWidth() * scale);
        int newHeight = (int) (bitmap.getHeight() * scale);

        int x = (this.getWidth() - newWidth) / 2;
        int y = (this.getHeight() - newHeight) / 2;
        g.drawImage(bitmap, x, y, newWidth, newHeight, this);
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
