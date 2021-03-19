/*
 *  Name: Alex Ghimbasan and Arjun Dureja
 *  Date: January 22nd, 2018
 *  File: Game.java
 *  Description: Game class for the Color flash game which contains all inner code
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
 
public class Game implements ActionListener{
    static JFrame f_play, f_highscores, f_htp, f_gameover, frame3;
    private JButton b_red, b_blue, b_green, b_yellow, b_purple, b_orange, b_next, b_back;
    private JPanel p_game, p_play;
    private JLabel l_correct, l_game_over, l_level, l_final_level, l_final_score, l_current_score, l_color_added, l_max_color;
    ArrayList<Color> color = new ArrayList<Color>();    
    ArrayList<Color> buttonsClicked = new ArrayList<Color>();
    Color red = Color.RED, orange = new Color(255, 128, 0), yellow = Color.YELLOW, green = new Color(0, 199, 13),
          blue = new Color(0, 77, 255), purple = new Color(170, 34, 228), previousColor = null;
    static int time = 1000, numOfColors = 3, checkpoint = 0, highscore = 0, level = 0, r = 255, g = 4, b = 4;
    public static int color_count = 0, buttonCount = 0;
    String name = "";
    Timer timer, colortimer;
    Color[] colors = {red, blue, green, yellow, purple, orange};
    static File filename;
	static FileReader read;
	static BufferedReader bReader = null;
    static FileWriter write;
    static BufferedWriter bWriter = null;
    
////////////////////////////////////////////             CONSTRUCTOR              ///////////////////////////////////////////////////
    public Game() {
        level++;
        f_play = new JFrame("Play!");
        initializeFrame(f_play);
       
        p_play = new JPanel();
        p_play.setSize(900,700);
        p_play.setVisible(true);
        p_play.setLayout(null);
        p_play.setBackground(new Color(230, 250, 255));
        f_play.add(p_play);
       
        b_red = new JButton("Red");
        p_play.add(b_red);
        b_red.setBounds(150,550,100,75);
        b_red.setBackground(red);
        b_red.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_red.setVisible(false);
        b_red.addActionListener(this);
       
        b_orange = new JButton("Orange");
        p_play.add(b_orange);
        b_orange.setBounds(250,550,100,75);
        b_orange.setBackground(orange);
        b_orange.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_orange.setVisible(false);
        b_orange.addActionListener(this);
       
        b_yellow = new JButton("Yellow");
        p_play.add(b_yellow);
        b_yellow.setBounds(350,550,100,75);
        b_yellow.setBackground(yellow);
        b_yellow.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_yellow.setVisible(false);
        b_yellow.addActionListener(this);
       
        b_green = new JButton("Green");
        p_play.add(b_green);
        b_green.setBounds(450,550,100,75);
        b_green.setBackground(green);
        b_green.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_green.setVisible(false);
        b_green.addActionListener(this);
       
        b_blue = new JButton("Blue");
        p_play.add(b_blue);
        b_blue.setBounds(550,550,100,75);
        b_blue.setBackground(blue);
        b_blue.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_blue.setVisible(false);
        b_blue.addActionListener(this);
       
        b_purple = new JButton("Purple");
        p_play.add(b_purple);
        b_purple.setBounds(650,550,100,75);
        b_purple.setBackground(purple);
        b_purple.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_purple.setVisible(false);
        b_purple.addActionListener(this);
           
        p_game = new JPanel();
        p_play.add(p_game);
        p_game.setVisible(false);
        p_game.setBounds(250,90,400,400);
       
        timer =  new Timer(time, this);
        colortimer = new Timer(3, this);
        timer.start();
       
        f_play.setVisible(true);
        l_level = new JLabel("Level " + level);
        p_play.add(l_level);
        l_level.setBounds(376,5,600,80);
        l_level.setFont(new Font("SansSerif", Font.BOLD, 44));
   
        l_correct = new JLabel("Correct!");
        p_play.add(l_correct);
        l_correct.setVisible(false);
        l_correct.setBounds(350,270,230,45);
        l_correct.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        l_max_color = new JLabel("6 Colors!");
        p_play.add(l_max_color);
        l_max_color.setVisible(false);
        l_max_color.setBounds(320,170,430,38);
        l_max_color.setFont(new Font("SansSerif", Font.BOLD, 40));
       
        ImageIcon gameover = new ImageIcon("GameOver.png");
        l_game_over = new JLabel(gameover);
        p_play.add(l_game_over);
        l_game_over.setVisible(false);
        l_game_over.setBounds(150,60,600,200);
        l_game_over.setFont(new Font("SansSerif", Font.BOLD, 50));
       
        b_back = new JButton("Back to Main Menu");
        p_play.add(b_back);
        b_back.setBounds(350,550,200,75);
        b_back.setVisible(false);
        b_back.setFocusPainted(false);
        b_back.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_back.addActionListener(this);
       
        b_next = new JButton("Next Level");
        p_play.add(b_next);
        b_next.setBounds(350,555,200,75);
        b_next.setVisible(false);
        b_next.setFont(new Font("Tahoma", Font.BOLD, 12));
        b_next.setFocusPainted(false);
        b_next.addActionListener(this);
       
        l_final_level = new JLabel("Final Level: " + level);
        f_play.add(l_final_level);
        l_final_level.setVisible(false);
        l_final_level.setBounds(87,350,600,100);
        l_final_level.setFont(new Font("SansSerif", Font.BOLD, 44));
       
        l_current_score = new JLabel("Score: " + highscore );
        p_play.add(l_current_score);
        l_current_score.setVisible(true);
        l_current_score.setBounds(396,475,230,100);
        l_current_score.setFont(new Font("SansSerif", Font.BOLD, 27)); 
        
        l_color_added = new JLabel("+ Color Added!");
        p_play.add(l_color_added);
        l_color_added.setBounds(300,100,585,100);
        l_color_added.setFont(new Font("SansSerif", Font.BOLD, 35));
        l_color_added.setVisible(false);
    }
//************************************************       END OF CONSTRUCTOR       ************************************************//
    
////////////////////////////////////////////			READ AND WRITE FILE METHODS         ////////////////////////////////////////////
    public static BufferedReader readFile(String file) {
    		try {
	     	filename = new File(file);
		    filename.createNewFile();
			read = new FileReader(filename);
			bReader = new BufferedReader(read);
		}
    		catch (Exception e) {
     	 	System.out.println("NO FILE FOUND!");
      	}
		return bReader;
    }

    public static BufferedWriter writeFile(String file) {
		try {
	    		filename = new File(file);
	    		filename.createNewFile();  
		    write = new FileWriter(filename, true);
		    bWriter = new BufferedWriter(write);
		}
		catch(Exception e){
			System.out.println("File does not exist!");
		}
	return bWriter;
    }
//******************************************       END OF FILE READ AND WRITE METHODS       ***************************************//

//////////////////////////////////////////////        HOW TO PLAY METHOD        /////////////////////////////////////////////////////
    public static void howToPlay() {
    	 	 f_htp = new JFrame("How to Play");
         initializeFrame(f_htp);    
         f_htp.setSize(450, 600);
         f_htp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         f_htp.getContentPane().setBackground(new Color(230, 250, 255));
        
         JLabel header = new JLabel("How to Play");
         f_htp.add(header);
         header.setBounds(151/2,20,270,50);
         header.setFont(new Font("SansSerif", Font.BOLD, 44));
        
         JLabel stepOne = new JLabel("<html>1. Colors will flash on the screen <br> in a specific order. Memorize them!</br></html>");
         f_htp.add(stepOne);
         stepOne.setBounds(10,100,500,60);
         stepOne.setFont(new Font("SansSerif", Font.BOLD, 22));
        
         JLabel stepTwo = new JLabel("<html>2. Click buttons in the same order <br> as the colors.</br></html>");
         f_htp.add(stepTwo);
         stepTwo.setBounds(10,180,500,60);
         stepTwo.setFont(new Font("SansSerif", Font.BOLD, 22));
        
         JLabel stepThree = new JLabel("<html>3. Once you pass the level, click next <br> to proceed to the next level.</br></html>");
         f_htp.add(stepThree);
         stepThree.setBounds(10,260,500,60);
         stepThree.setFont(new Font("SansSerif", Font.BOLD, 22));
        
         JLabel stepFour = new JLabel("<html>4. If you select a wrong color, it's <br> game over! Click return to main menu </br><br> and "
                 + "enter your name for highscores</br><br> if you wish to save your score.</br></html>");
         f_htp.add(stepFour);
         stepFour.setBounds(10,340,500,120);
         stepFour.setFont(new Font("SansSerif", Font.BOLD, 22)); 
    }
//******************************************           END OF HOW TO PLAY METHOD            ***************************************//

//////////////////////////////////////////////        HIGHSCORES METHOD         /////////////////////////////////////////////////////
    public static void highscores() throws IOException { 
	    	f_highscores = new JFrame("Highscores");
	    	f_highscores.setSize(520,600);
	    	f_highscores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	f_highscores.setLocationRelativeTo(null);
	    JPanel p_highscores = new JPanel();
	    p_highscores.setLayout(null);

	    JLabel l_name = new JLabel("Player Name");
	    JLabel l_level = new JLabel("Highest Level");
	    JLabel l_score = new JLabel("Highest Score");
	    l_name.setFont(new Font("SansSerif", Font.BOLD, 16));
	    l_level.setFont(new Font("SansSerif", Font.BOLD, 16));
	    l_score.setFont(new Font("SansSerif", Font.BOLD, 16));
	    
	    	JTextArea ta_name = new JTextArea();
	    JTextArea ta_level = new JTextArea();
	    JTextArea ta_score = new JTextArea();
	    ta_name.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    ta_level.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    ta_score.setFont(new Font("SansSerif", Font.PLAIN, 12));
	     
	    ta_name.setEditable(false);
	    ta_level.setEditable(false);
	    ta_score.setEditable(false);
	     
	    l_name.setBounds(20,0,100,50);
	    l_level.setBounds(220,0,140,50);
	    l_score.setBounds(355,0,140,50);
	    ta_name.setBounds(20, 40, 250, 5000);
	    ta_level.setBounds(270, 40, 115, 5000);
	    ta_score.setBounds(400, 40, 115, 5000);
	      
	    ta_name.setBackground(new Color(230, 250, 255));
	    ta_level.setBackground(new Color(230, 250, 255));
	    ta_score.setBackground(new Color(230, 250, 255));
	    p_highscores.setBackground(new Color(230, 250, 255));
	      
	    p_highscores.add(ta_name);
	    p_highscores.add(ta_level);
	    p_highscores.add(ta_score);
	    p_highscores.add(l_name);
	    p_highscores.add(l_level);
	    p_highscores.add(l_score);
	    
	    JScrollPane scroll = new JScrollPane(p_highscores, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    p_highscores.setPreferredSize(new Dimension(500, 5000));
	    f_highscores.getContentPane().add(scroll);
	    f_highscores.setVisible(true);
	    
	    BufferedReader readHighscore = readFile("ColorFlash_Highscores.txt");
	    ArrayList<String> highscoresOut = new ArrayList<String>();
	    ArrayList<String> highscoresName = new ArrayList<String>();
	    ArrayList<String> highscoresLevel = new ArrayList<String>();
	    ArrayList<Integer> highscoresScore = new ArrayList<Integer>();
      
	    	String out = "";
		while( (out = readHighscore.readLine()) != null) {
			highscoresOut.add(out);
		    }
		for(int i = 0; i < highscoresOut.size(); i++){
			String lineRead = highscoresOut.get(i);
			int spacebar1 = lineRead.lastIndexOf(" ");
			String lineNoScore = lineRead.substring(0, spacebar1);
			String finalScore = lineRead.substring(spacebar1 + 1);
			int highscore_Score = Integer.parseInt(finalScore);
			int spacebar2 = lineNoScore.lastIndexOf(" ");
			String lineNoLevel = lineNoScore.substring(0, spacebar2);
			String finalLevel = lineNoScore.substring(spacebar2 + 1);
			
			highscoresName.add(lineNoLevel);
			highscoresLevel.add(finalLevel);
			highscoresScore.add(highscore_Score);	
		}
		
		for(int i = 0; i < highscoresOut.size(); i++){
			for(int z = 0; z < highscoresOut.size(); z++) {
				if (highscoresScore.get(i) > highscoresScore.get(z)) {

					int changeScore = highscoresScore.get(z);
					String changeLevel = highscoresLevel.get(z);
					String changeName = highscoresName.get(z);
					
					highscoresScore.set(z, highscoresScore.get(i));
					highscoresLevel.set(z, highscoresLevel.get(i));
					highscoresName.set(z, highscoresName.get(i));
					
					highscoresScore.set(i, changeScore);
					highscoresLevel.set(i, changeLevel);
					highscoresName.set(i, changeName);	
					}
				}
			}
		
		for (int i = 0; i < highscoresOut.size(); i++) {
			ta_name.append(highscoresName.get(i) + "\n");
		    ta_level.append(highscoresLevel.get(i) + "\n");
		    ta_score.append(String.valueOf(highscoresScore.get(i)) + "\n");
		}
    }
//******************************************             END OF HIGHSCORES METHOD           ***************************************//
   
//////////////////////////////////////////////       INITIALIZE FRAME METHOD         ///////////////////////////////////////////////
    public static void initializeFrame(JFrame frame) {
        frame.setSize(900,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
    }
//**************************************             END OF INITIALIZE FRAME METHOD           ************************************//

//////////////////////////////////////////////         ACTION PERFORMED         ///////////////////////////////////////////////
    public void actionPerformed(ActionEvent e) {
       
        if(b_back.isVisible() == true || b_next.isVisible() == true){
            if (r == 255 && g < 255 && b == 4) {
                g++;
                b_back.setBackground(new Color(r, g, b));  
                b_next.setBackground(new Color(r, g, b));  
                l_color_added.setForeground(new Color(r, g, b));
                if (numOfColors == 6) {
                		l_max_color.setVisible(true);
                		l_max_color.setForeground(new Color(r, g, b));
                		}
                }
            if (r > 4 && g == 255 && b == 4) {
                r--;
                b_back.setBackground(new Color(r, g, b));
                b_next.setBackground(new Color(r, g, b)); 
                l_color_added.setForeground(new Color(r, g, b));
                if (numOfColors == 6) {
                		l_max_color.setVisible(true);
                		l_max_color.setForeground(new Color(r, g, b));
                		}
                }
            if (r == 4 && g == 255 && b < 255) {
                b++;
                b_back.setBackground(new Color(r, g, b));  
                b_next.setBackground(new Color(r, g, b));  
                l_color_added.setForeground(new Color(r, g, b));
                if (numOfColors == 6) {
                		l_max_color.setVisible(true);
                		l_max_color.setForeground(new Color(r, g, b));
                		} 
                }
            if (r == 4 && g > 4 && b == 255) {
                g--;  
                b_back.setBackground(new Color(r, g, b));
                b_next.setBackground(new Color(r, g, b));  
                l_color_added.setForeground(new Color(r, g, b));
                if (numOfColors == 6) {
                		l_max_color.setVisible(true);
                		l_max_color.setForeground(new Color(r, g, b));
                		}
                }
            if (r < 255 && g == 4 && b == 255) {
                r++;
                b_back.setBackground(new Color(r, g, b));
                b_next.setBackground(new Color(r, g, b));  
                l_color_added.setForeground(new Color(r, g, b));
                if (numOfColors == 6) {
                		l_max_color.setVisible(true);
                		l_max_color.setForeground(new Color(r, g, b));
                		}
            		}
            if (r == 255 && g == 4 && b > 4) {
                b--;
                b_back.setBackground(new Color(r, g, b));
                b_next.setBackground(new Color(r, g, b)); 
                l_color_added.setForeground(new Color(r, g, b));
                if (numOfColors == 6) {
                		l_max_color.setVisible(true);
                		l_max_color.setForeground(new Color(r, g, b));
                		}
            		}
        		}
        
        boolean is_colour_button = false;
        if(color_count < numOfColors){  
            p_game.setVisible(true);
            Color randomColor = colors[new Random().nextInt(colors.length)];
            while (randomColor.equals(previousColor)) {
            		randomColor = colors[new Random().nextInt(colors.length)];
            		}
            p_game.setBackground(randomColor);
            color.add(randomColor);
            previousColor = randomColor;
            color_count++;
        		}
 
        else if(color_count == numOfColors){
            p_game.setVisible(false);
            b_red.setVisible(true);
            b_yellow.setVisible(true);
            b_orange.setVisible(true);
            b_green.setVisible(true);
            b_blue.setVisible(true);
            b_purple.setVisible(true);
        		}
        
        if(e.getSource() == b_red){
            buttonsClicked.add(red);
            is_colour_button = true;
        }
        else if(e.getSource() == b_blue){
            buttonsClicked.add(blue);
            is_colour_button = true;
        }
        else if(e.getSource() == b_green){
            buttonsClicked.add(green);
            is_colour_button = true;
        }
        else if(e.getSource() == b_orange){
            buttonsClicked.add(orange);
            is_colour_button = true;
        }
        else if(e.getSource() == b_yellow){
            buttonsClicked.add(yellow);
            is_colour_button = true;
        }
        else if(e.getSource() == b_purple){
            buttonsClicked.add(purple);
            is_colour_button = true;
        }
 
        if(buttonsClicked.size() > 0 && is_colour_button){
            try{
                for(int x = 0; x < color.size(); x++){
                    if(buttonsClicked.get(x) != color.get(x)){
                        f_play.dispose();
                        l_final_score = new JLabel("Final Score: " + highscore );
                        l_final_score.setBounds(533,350,585,100);
                        l_final_score.setFont(new Font("SansSerif", Font.BOLD, 44));
                       
                        f_gameover = new JFrame("Game over!");
                        initializeFrame(f_gameover);
                        f_gameover.getContentPane().setBackground(new Color(230, 250, 255));
                        f_gameover.add(l_game_over);
                        f_gameover.add(l_final_level);
                        f_gameover.add(l_final_score);
                        f_gameover.add(b_back);
                       
                        l_game_over.setVisible(true);
                        l_final_level.setVisible(true);
                        l_final_score.setVisible(true);
                        l_level.setVisible(false);
                        b_back.setVisible(true);
                        b_red.setVisible(false);
                        b_yellow.setVisible(false);
                        b_orange.setVisible(false);
                        b_green.setVisible(false);
                        b_blue.setVisible(false);
                        b_purple.setVisible(false);  
                        colortimer.start();
                        timer.stop();
                        break;
                    }              
                }
            }
            catch(Exception e1){
            }

            try{
                for(int y = 0; y < color.size(); y++){
                    if(buttonsClicked.get(y) == color.get(y)){                  
                        highscore++;
                        l_current_score.setText("Score: " + highscore );
                        timer.stop();
                        break;
                    }
                }
            }
            catch(Exception e1){
            }
        }
        
        if(color.equals(buttonsClicked)){
        		if (checkpoint == 4 && numOfColors != 6) {
                l_color_added.setVisible(true);
    			}
            l_correct.setVisible(true);
            b_next.setVisible(true);
            b_red.setVisible(false);
            b_yellow.setVisible(false);
            b_orange.setVisible(false);
            b_green.setVisible(false);
            b_blue.setVisible(false);
            b_purple.setVisible(false);
            colortimer.start();
            timer.stop();
        }
        
        if(e.getSource() == b_back) {
        			if (buttonsClicked.size() == color_count) {
        				highscore--;
        			}
                colortimer.stop();
                f_play.dispose();
                f_gameover.dispose();
                BufferedWriter writeHighscore = writeFile("ColorFlash_Highscores.txt");
                
	            boolean runName = true;
                while(runName == true) {
                		boolean saveName = true;
                		String playername = JOptionPane.showInputDialog(f_play, "Enter Name", "Add Highscore", JOptionPane.PLAIN_MESSAGE);
                		
                		if (playername == null) {
                			break;
                			}
                		if (playername.length() > 20){
            	 			JOptionPane.showMessageDialog(null, "Must be 20 characters or less!", "Error", JOptionPane.ERROR_MESSAGE);
            	 			saveName = false;
            				}
                		if (playername != null && saveName == true) {    	
                			runName = false;
                			if (playername.length() == 0){
			                playername = "Anonymous";
                				}
                			try {
							writeHighscore.write(playername + " " + level + " " + highscore + "\n");
							writeHighscore.close();
                				} 
                			catch (IOException e1) {
							System.out.println("Change file directory!");
                				}
                			}
                		}
	            time = 1000;
	            numOfColors = 3;
	            checkpoint = 0;
	            highscore = 0;
	            level = 0;
	            color_count = 0;
	            buttonCount = 0;
                new ColorFlash();
        }
        
        if(e.getSource() == b_next) {
            l_color_added.setVisible(false);
        		previousColor = null;
        		checkpoint ++;
        		if(checkpoint < 5){
                time = time - 75;
            }
            else if(checkpoint == 5){
                if (numOfColors < 6) {
                		time = 1000;
                    numOfColors++;        
                    checkpoint = 0;
                }
            } 
        	    color_count = 0;
	        buttonCount = 0;
            colortimer.stop();
            timer.stop();
            f_play.dispose();
            new Game();           
        }
    }
}