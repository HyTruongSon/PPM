// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2016 (c) Hy Truong Son. All rights reserved. Use for academic purposes only!

package viewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DefaultViewer extends JFrame {

    private final String frameTitle = "Default viewer";
    
    private BufferedImage inputImage;
    private int widthImage;
    private int heightImage;
    
    public DefaultViewer(String fileName) {
        // Reading the input image
        inputImage = null;
        try {
            inputImage = ImageIO.read(new File(fileName));
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, exc.toString());
            return;
        }
        
        if (inputImage == null) {
            JOptionPane.showMessageDialog(null, "Java does not support this type of image!");
            return;
        }
        
        // Drawing the image
        widthImage = inputImage.getWidth();
        heightImage = inputImage.getHeight();
        
        setTitle(frameTitle);
        setSize(widthImage, heightImage);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < widthImage; ++x) {
            for (int y = 0; y < heightImage; ++y) {
                int rgb = inputImage.getRGB(x, y);
                int red = (rgb & 0x00ff0000) >> 16;
		int green = (rgb & 0x0000ff00) >> 8;
		int blue = rgb & 0x000000ff;
                
                g.setColor(new Color(red, green, blue));
                g.drawLine(x, y, x, y);
            }
        }
    }
    
}
