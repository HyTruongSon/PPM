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
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import library.PPMReader;

public class CustomViewer extends JFrame {
    
    private final String frameTitle = "Default viewer";
    
    private PPMReader image;
    private String magicNumber;
    private String comment;
    private int widthImage;
    private int heightImage;
    private int maxVal;
    
    public CustomViewer(String fileName) {
        try {
            image = new PPMReader(fileName);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, exc.toString());
        }
            
        // Drawing the image
        magicNumber = image.getMagicNumber();
        comment = image.getComment();
        widthImage = image.getWidth();
        heightImage = image.getHeight();
        maxVal = image.getMaxVal();
        
        String message = 
                "Magic number: " + magicNumber + "\n" +
                "Comment: " + comment + "\n" +
                "Width: " + Integer.toString(widthImage) + "\n" +
                "Height: " + Integer.toString(heightImage) + "\n" +
                "Max value: " + Integer.toString(maxVal);
        
        JOptionPane.showMessageDialog(null, message);
        
        setTitle(frameTitle);
        setSize(widthImage, heightImage);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        for (int rowIndex = 0; rowIndex < heightImage; ++rowIndex) {
            for (int columnIndex = 0; columnIndex < widthImage; ++columnIndex) {
                int rgb = image.getRGBAt(rowIndex, columnIndex);
                int red = (rgb & 0x00ff0000) >> 16;
		int green = (rgb & 0x0000ff00) >> 8;
		int blue = rgb & 0x000000ff;
                
                g.setColor(new Color(red, green, blue));
                g.drawLine(columnIndex, rowIndex, columnIndex, rowIndex);
            }
        }
    }
    
}
