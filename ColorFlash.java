/*
 *  Name: Alex Ghimbasan and Arjun Dureja
 *  Date: January 22nd, 2018
 *  File: ColorFlash.java
 *  Description: Main class for the Color Flash game which runs the main menu
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
 
public class ColorFlash implements ActionListener {
    private JFrame f_MainMenu;
    private JButton b_play, b_htp, b_highscores, b_quit;
    ArrayList<String> button = new ArrayList<String>();
    ImageIcon img = new ImageIcon("ColorFlashIcon.png");
    int r = 255, g = 4, b = 4;
 
    public static void main(String[]args) {
        new ColorFlash();
    }
   
    public ColorFlash(){
        f_MainMenu = new JFrame("Color Flash!");
        f_MainMenu.setSize(800,500);
        f_MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_MainMenu.setVisible(true);
        f_MainMenu.setResizable(false);
        f_MainMenu.setLayout(null);
        f_MainMenu.setLocationRelativeTo(null);
        f_MainMenu.setIconImage(img.getImage());
        
        b_play = new JButton("Play");
        f_MainMenu.add(b_play);
        b_play.setBounds(300,150,200,65);
        b_play.setBackground(new Color(0, 77, 255));
        //b_play.setForeground(Color.white);
        b_play.setFocusPainted(false);
        b_play.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_play.addActionListener(this);
       
        b_htp = new JButton("How to Play");
        f_MainMenu.add(b_htp);
        b_htp.setBounds(300,225,200,65);
        b_htp.setBackground(new Color(170, 34, 228));
        //b_htp.setForeground(Color.white);
        b_htp.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_htp.addActionListener(this);
       
        b_highscores = new JButton("Highscores");
        f_MainMenu.add(b_highscores);
        b_highscores.setBounds(300,300,200,65);
        b_highscores.setBackground(new Color(0, 199, 13));
        //b_highscores.setForeground(Color.white);
        b_highscores.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_highscores.addActionListener(this);
       
        b_quit = new JButton("Quit");
        f_MainMenu.add(b_quit);
        b_quit.setBounds(300,375,200,65);
        b_quit.setBackground(Color.RED);
        //b_quit.setForeground(Color.white);
        b_quit.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_quit.addActionListener(this);
       
        JLabel title = new JLabel("Color Flash!");
        f_MainMenu.add(title);
        title.setBounds(252,40,300,50);
        title.setFont(new Font("SansSerif", Font.BOLD, 48));
       
        Timer colortimer = new Timer(3,this);
        colortimer.start();    
    }
   
    public void actionPerformed(ActionEvent e) {
                       
                if (r==255 && g<255 && b==4) {
                    g++;
                    f_MainMenu.getContentPane().setBackground( new Color(r,g,b));          
                }
                if(r>4 && g==255 && b==4) {
                    r--;
                    f_MainMenu.getContentPane().setBackground( new Color(r,g,b));
                }
                if(r==4 && g==255 && b<255) {
                    b++;
                    f_MainMenu.getContentPane().setBackground( new Color(r,g,b));
                }
                if(r==4 && g>4 && b==255) {
                    g--;
                    f_MainMenu.getContentPane().setBackground( new Color(r,g,b));
                }
                if(r<255 && g==4 && b==255) {
                    r++;
                    f_MainMenu.getContentPane().setBackground( new Color(r,g,b));
                }
                if(r==255 && g==4 && b>4) {
                    b--;
                    f_MainMenu.getContentPane().setBackground( new Color(r,g,b));
                }
        
        if(e.getSource()==b_play) {
            f_MainMenu.dispose();
            new Game();
        }
        
        if(e.getSource()==b_htp) {
            Game.howToPlay();
        }
        
        if(e.getSource()==b_highscores) {
            try {
				Game.highscores();
			} 
            catch (IOException e1) {
			}   
        }
        
        if(e.getSource()==b_quit) {
            System.exit(0);
        }
    }   
}