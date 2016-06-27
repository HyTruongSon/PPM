// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2016 (c) Hy Truong Son. All rights reserved. Use for academic purposes only!

package viewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileSelectionFrame extends JFrame {
    
    private final String frameTitle = "File selection";
    private final int frameWidth = 700;
    private final int frameHeight = 220;
    private final int frameMargin = 20;
    private final int labelWidth = 200;
    private final int buttonWidth = 120;
    private final int textWidth = frameWidth - 4 * frameMargin - labelWidth - buttonWidth;
    private final int boxWidth = frameWidth - 3 * frameMargin - labelWidth;
    private final int componentHeight = 30;
    
    private final JLabel fileLabel;
    private final JTextField text;
    private final JButton browse;
    private final JLabel optionLabel;
    private final JComboBox optionBox;
    private final JButton start;
    private final JFileChooser fileChooser = new JFileChooser();
    
    private final String options[] = {"Read by my own PPM image library", "Read by Java image libraries"};
    
    public FileSelectionFrame() {
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        int x = frameMargin;
        int y = frameMargin;
        
        fileLabel = new JLabel();
        fileLabel.setText("Choose a PPM file:");
        fileLabel.setForeground(Color.BLUE);
        fileLabel.setBounds(x, y, labelWidth, componentHeight);
        add(fileLabel);
        
        x += labelWidth + frameMargin;
        
        text = new JTextField();
        // text.setEditable(false);
        text.setBounds(x, y, textWidth, componentHeight);
        add(text);
        
        x += textWidth + frameMargin;
        
        browse = new JButton();
        browse.setText("Browse");
        browse.setBounds(x, y, buttonWidth, componentHeight);
        add(browse);
        
        browse.addActionListener(browsingFileAction);
        
        x = frameMargin;
        y += componentHeight + frameMargin;
        
        optionLabel = new JLabel();
        optionLabel.setText("Choose the reading option:");
        optionLabel.setForeground(Color.BLUE);
        optionLabel.setBounds(x, y, labelWidth, componentHeight);
        add(optionLabel);
        
        x += labelWidth + frameMargin;
        
        optionBox = new JComboBox(options);
        optionBox.setBounds(x, y, boxWidth, componentHeight);
        add(optionBox);
        
        x = (frameWidth - buttonWidth) / 2;
        y += componentHeight + frameMargin;
        
        start = new JButton();
        start.setText("Start");
        start.setBounds(x, y, buttonWidth, componentHeight);
        add(start);
        
        start.addActionListener(startingAction);
        
        setVisible(true);
    }
    
    private final ActionListener browsingFileAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = fileChooser.showOpenDialog(null);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                
                String path = new String();
                try {
                    path = file.getCanonicalPath();
                } catch (IOException exc) {
                    System.err.println(exc.toString());
                }
                
                if (path.length() > 0) {
                    text.setText(path);
                }
            } 
        }
    };
    
    private final ActionListener startingAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileName = text.getText();
            
            if (fileName.length() == 0) {
                JOptionPane.showMessageDialog(null, "You must choose an PPM image first!");
                return;
            }
            
            int option = optionBox.getSelectedIndex();
            
            if (option == 0) {
                final String fileName_ = fileName;
                new Thread() {
                    @Override
                    public void run() {
                        new CustomViewer(fileName_);
                    }
                }.start();
            }
            
            if (option == 1) {
                final String fileName_ = fileName;
                new Thread() {
                    @Override
                    public void run() {
                        new DefaultViewer(fileName_);
                    }
                }.start();
                return;
            }
        }
    };
    
}
