/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Roozbeh Hajizadeh
 */
public class Search {
    String _type;
    public Search(String type){
        _type = type;
    }
    public ArrayList<Packman.Moves> solve(Packman packman, ArrayList<Point> goalPositoins){
        Packman.numberOfChecks = 0;
        if(_type.equals("dfs")){
            return DFS(packman, goalPositoins);
        }
        else if(_type.equals("bfs")){
            return BFS(packman, goalPositoins);
        }
        else if(_type.equals("rdfs")){
            return RDFS(packman, goalPositoins);
        }
        else if(_type.equals("astar")){
            return Astar(packman, goalPositoins);
        }
        else if(_type.equals("ucs")){
            return UCS(packman, goalPositoins);
        }
        return null;
    }
    private ArrayList<Packman.Moves> RDFS(Packman packman, ArrayList<Point> goalPositoins){
        Stack<Node> openList = new Stack<>();
        ArrayList<Point> closeList = new ArrayList<>();
        Point initialPos = packman.getPos();
        openList.add(new Node(initialPos, Packman.Moves.none, null));
        ArrayList<Packman.Moves> moves =new ArrayList<>();
        System.out.println(goalPositoins);
        while(!openList.isEmpty()){
            Node p = openList.pop();
            if(goalPositoins.contains(p._p)){
                while(p._father != null){
                    moves.add(0, p._move);
                    p = p._father;
                }
                break;
            }
            closeList.add(p._p);
            Point upp = new Point(p._p.x, p._p.y - 1);
            Point downp = new Point(p._p.x, p._p.y + 1);
            Point rightp = new Point(p._p.x + 1, p._p.y);
            Point leftp = new Point(p._p.x - 1, p._p.y);
            if(packman.checkIfAvailable(upp) &&  !closeList.contains(upp))
                openList.push(new Node(upp, Packman.Moves.up,p));
            if(packman.checkIfAvailable(downp) && !closeList.contains(downp))
                openList.push(new Node(downp, Packman.Moves.btm,p));
                
            if(packman.checkIfAvailable(rightp) && !closeList.contains(rightp))
                openList.push(new Node(rightp, Packman.Moves.right,p));
                
            if(packman.checkIfAvailable(leftp) && !closeList.contains(leftp))
                openList.push(new Node(leftp, Packman.Moves.left,p));                
        }
        return moves;
    }
    private ArrayList<Packman.Moves> DFS(Packman packman, ArrayList<Point> goalPositoins){
        
        return null;
    }
    private ArrayList<Packman.Moves> BFS(Packman packman, ArrayList<Point> goalPositoins){
        
        return null;
    }
    private ArrayList<Packman.Moves> Astar(Packman packman, ArrayList<Point> goalPositoins){
        
        return null;
    }
	private ArrayList<Packman.Moves> UCS(Packman packman, ArrayList<Point> goalPositoins){
	        
	        return null;
	    }
	public class Node{
        public Point _p;
        public Packman.Moves _move;
        public Node _father;
        public Node(Point p, Packman.Moves move, Node father){
            _p = p;
            _move = move;
            _father = father;
        }
    }
}
