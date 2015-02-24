/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Roozbeh Hajizadeh
 */
public class Board extends JFrame{
    private int cellWidth = 30, cellHeight = 30;
    DrawBoard drw ;
    public Board(String name, ArrayList<ArrayList<Character>> map){
        super(name);
        setSize(map.size()*cellWidth, map.get(0).size()*cellHeight);  
        drw = new DrawBoard();
        setContentPane(drw);
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//
//            @Override
//            public void run() {
//                drw.repaint();
//              
//            }
//        }, 30, 30);
    }
   public void movePackman(Packman packman, Packman.Moves move){
	   
	   packman.move(move);
	   drw.repaint();
   }
    class DrawBoard extends JPanel{
    	int counter = 0;
    	boolean growing = true;
        @Override
        protected void paintComponent(Graphics grphcs) {
//            System.out.println("hi");
           // super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
            grphcs.setColor(Color.BLACK);
            grphcs.fillRect(0, 0, getWidth(), getHeight());
            double cellWidth = getWidth() *1.0 / Packman_Game.map.size();        
            double cellHeight = getHeight()*1.0 / Packman_Game.map.get(0).size();
            for(int i=0;i<Packman_Game.map.size();i++){
                
                for(int j=0;j<Packman_Game.map.get(i).size();j++){
                	char c = Packman_Game.map.get(i).get(j); 
                    if(c =='%'){
                        grphcs.setColor(Color.BLUE);
                        grphcs.fillRect(i*(int)(cellWidth), j*(int)(cellHeight), (int)(cellWidth), (int)cellHeight);
                    }
                    else if(c=='P'){
                        grphcs.setColor(Color.YELLOW);
                        grphcs.fillArc(i*(int)(cellWidth), j*(int)(cellHeight), (int)(cellWidth), (int)cellHeight, 30 - counter, 300 + 2*counter);
                    }
                    else if(c=='.'){
                        grphcs.setColor(Color.WHITE);
                        grphcs.fillArc(i*(int)((cellWidth) + cellWidth/4), j*(int)((cellHeight) + cellHeight/4)
                                , (int)(cellWidth/2), (int)cellHeight/2, 0, 360);
                    }
                }
            }
            
            if(growing)
            	counter +=5;
            else
            	counter -=5;
            if(counter > 30)
            	growing = false;
            else if(counter < 0)
            	growing = true;
        }
        
    }
    
}
