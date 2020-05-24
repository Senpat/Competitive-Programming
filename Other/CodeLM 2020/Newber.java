import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Newber {
   static Scanner s = new Scanner(System.in);
	
   public static void main(String[] args) {
      char[][] map = new char[4][4];
   	
      int xx = -1;
      int xy = -1;
      int ox = -1;
      int oy = -1;
      
      for (int i = 0; i < 4; i++) {
         String line = s.nextLine();
            
         for (int j = 0; j < 4; j++) {
            map[i][j] = line.charAt(j);
            
            if(map[i][j] == 'X'){
               xx = i;
               xy = j;
            } else if(map[i][j] == 'O' || map[i][j] == '0'){
               ox = i;
               oy = j;
            }
            
         }
      }
      
      
      boolean[][] seen = new boolean[4][4];
      
      seen[xx][xy] = true;
      Queue<State> q = new LinkedList<State>();
      q.add(new State(xx,xy,""));
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         if(s.x == ox && s.y == oy){
            System.out.println(s.s);
            System.out.close();
            return;
         }
         
         if(in(s.x+1,s.y) && !seen[s.x+1][s.y] && map[s.x+1][s.y] != '|'){
            q.add(new State(s.x+1,s.y,s.s+'D'));
            seen[s.x+1][s.y] = true;
         }
         if(in(s.x-1,s.y) && !seen[s.x-1][s.y] && map[s.x-1][s.y] != '|'){
            q.add(new State(s.x-1,s.y,s.s+'U'));
            seen[s.x-1][s.y] = true;
         }
         if(in(s.x,s.y+1) && !seen[s.x][s.y+1] && map[s.x][s.y+1] != '|'){
            q.add(new State(s.x,s.y+1,s.s+'R'));
            seen[s.x][s.y+1] = true;
         }
         if(in(s.x,s.y-1) && !seen[s.x][s.y-1] && map[s.x][s.y-1] != '|'){
            q.add(new State(s.x,s.y-1,s.s+'L'));
            seen[s.x][s.y-1] = true;
         }
      }
         
      
      
   	// code to solve the problem.  You can write and call other methods as well.
   	
      System.out.println("not found");                     // print your answer and just your answer.
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < 4 && y >= 0 && y < 4;
   }
   
   public static class State{
      int x;
      int y;
      String s;
      
      public State(int a, int b, String c){
         x = a;
         y = b;
         s = c;
      }
   }
}
