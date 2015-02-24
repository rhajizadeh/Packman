/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Roozbeh Hajizadeh
 */
public class Packman_Game {
    static ArrayList<ArrayList<Character>> map;    
    private ArrayList<Point> goalPositoins;
    String layout;
    String type;
    Packman packman;
    Board board;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
       
       Packman_Game packman = new Packman_Game(args);
       
       Search search = new Search(packman.type);
       System.out.println(packman.type);
       ArrayList<Packman.Moves> route = search.solve(new Packman(packman.map), packman.goalPositoins);
       int routeSize = route.size();
       int numberOfGoals = packman.goalPositoins.size();
       for(int i=0; i<route.size(); i++){
           //System.out.println(route.get(i));
//           packman.packmam.move(route.get(i));
           packman.board.movePackman(packman.packman, route.get(i));
           int indx = packman.goalPositoins.indexOf(packman.packman.pos);
           if(indx != -1){
               packman.goalPositoins.remove(indx);
           }
           Thread.currentThread().sleep(200);
       }
       packman.printResults(routeSize, numberOfGoals);
    }
    
    public Packman_Game(String[] args){
        goalPositoins = new ArrayList<>();   
         
        readArgs(args);
        System.out.println(layout + " " + type);
        if(type.compareTo("") ==0 || layout.compareTo("") ==0){
           System.out.println("Type or Layout is not entered!");
           System.exit(0);
        }    
        
        
        map = readLayout(layout + ".lay");
        findGoals();
        packman = new Packman(map);
        board = new Board("Packman", map);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        board.setVisible(true);        
    }
    private void readArgs(String[] args){
        layout = "";
        type = "";
        for(int i=0; i<args.length; i+=2){
             
            if(args[i].equals("-l") && i + 1 < args.length){
                layout = args[i+1];
            }
            if(args[i].equals("-t") && i + 1 <args.length){
                type = args[i+1];
            }           
        }
        layout = layout.trim();
        type = type.trim();
    }
    private ArrayList<ArrayList<Character>> readLayout(String lName){  
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        try{
            BufferedReader  br = new BufferedReader(new FileReader("src/layouts/" + lName));
            String line = br.readLine();
            while(line != null){
                ArrayList<Character> list =new  ArrayList<>();
                for(int i=0;i<line.length();i++){
                    list.add(line.charAt(i));
                }
                map.add(list);
                line = br.readLine();
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());            
        }
        return map;
    } 
    void findGoals(){
       for(int i=0;i<map.size();i++){
                for(int j=0;j<map.get(i).size();j++){
                    if(map.get(i).get(j)=='.'){
                        goalPositoins.add(new Point(i, j));
                    }
                }
            }
   }
    
    private void printResults(int routeSize, int numberOfGoals){
        System.out.println("Number of nodes opened: " + Packman.numberOfChecks);
        System.out.println("Route Length: " + routeSize);
        System.out.println("Total number of goals: " + numberOfGoals);
        System.out.println("You found: " + goalPositoins.size());
        if(goalPositoins.isEmpty()){
           System.out.println("Success!! You found all the goals");
        } else {
           System.out.println("Failed :( !! you didn't find " + goalPositoins.size());
        }
    }
}
