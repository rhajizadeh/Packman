/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Roozbeh Hajizadeh
 */
public class Packman {
    public static int numberOfChecks;
    Point pos;
    public enum Moves{ up, btm, left, right, none};
    public Packman(ArrayList<ArrayList<Character>> map){
        Packman_Game.map = map;
        findPosition();
    }
    
    private void findPosition(){
        for(int i=0;i<Packman_Game.map.size();i++){
            for(int j=0;j<Packman_Game.map.get(i).size();j++){
                if(Packman_Game.map.get(i).get(j) == 'P'){
                    pos = new Point(i, j);
                    return;
                }
            }
        }
    }
    public void move(Moves move){
        switch(move){
            case up:
                goUp();
                break;
            case btm:
                goBtm();
                break;
            case left:
                goLeft();
                break;
            case right:
                goRight();
                break;                
        }
    }
    public boolean checkIfAvailable(Point p){
        return Packman_Game.map.get(p.x).get(p.y) != '%';
    }
    public Point getPos(){
        return pos;
    }
    public boolean isUpAvailable(){
        if(pos.y - 1 > 0 ){
            Packman.numberOfChecks++;
            return Packman_Game.map.get(pos.x).get(pos.y - 1) != '%';
        }
        return false;
    }
    public boolean isBtmAvailable(){
        if(pos.y + 1 < Packman_Game.map.get(pos.x).size() ){
            Packman.numberOfChecks++;
            return Packman_Game.map.get(pos.x).get(pos.y + 1) != '%';
        }
        return false;
    }
    public boolean isLeftAvailable(){
        if(pos.x - 1 > 0 ){
            Packman.numberOfChecks++;
            return Packman_Game.map.get(pos.x - 1).get(pos.y) != '%';
        }
        return false;
    }
    public boolean isRightAvailable(){
        if(pos.x + 1 < Packman_Game.map.size() ){
            Packman.numberOfChecks++;
            return Packman_Game.map.get(pos.x + 1).get(pos.y) != '%';
        }
        return false;
    }
    private void goUp(){
         
        if(isUpAvailable()){
           
            Packman_Game.map.get(pos.x).set(pos.y, ' ');
            Packman_Game.map.get(pos.x).set(pos.y - 1, 'P');
            pos.setLocation(pos.x, pos.y-1);
        }                    
    }
    private void goRight(){
        if(isRightAvailable()){
            Packman_Game.map.get(pos.x).set(pos.y, ' ');
            Packman_Game.map.get(pos.x + 1).set(pos.y, 'P');
            pos.setLocation(pos.x+1, pos.y);
        }                    
    }
    private void goLeft(){
        if(isLeftAvailable()){
            Packman_Game.map.get(pos.x).set(pos.y, ' ');
            Packman_Game.map.get(pos.x - 1).set(pos.y, 'P');
            pos.setLocation(pos.x-1, pos.y);
        }                    
    }
    private void goBtm(){
        if(isBtmAvailable()){
            Packman_Game.map.get(pos.x).set(pos.y, ' ');
            Packman_Game.map.get(pos.x).set(pos.y + 1, 'P');
            pos.setLocation(pos.x, pos.y+1);
        }                    
    }
    
}
