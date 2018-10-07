//Ice Skating
import java.io.*;
import java.util.*;

public class A217{

   public static ArrayList<ArrayList<Cord>> x,y;
   public static Cord[] cords;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      x = new ArrayList<ArrayList<Cord>>();
      y = new ArrayList<ArrayList<Cord>>();
      
      for(int k = 0; k < 1001; k++){
         x.add(new ArrayList<Cord>());
         y.add(new ArrayList<Cord>());
      }
      
      cords = new Cord[n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         
         Cord curc = new Cord(x1,y1,-1);
         cords[k] = curc;
         x.get(x1).add(curc);
         y.get(y1).add(curc);
         
         
      }
      
      int comp = 0;
      for(int k = 0; k < n; k++){
         if(cords[k].comp == -1){
            bfs(cords[k],comp);
            comp++;
         }
         
      }
      
      out.println(comp-1);
      
      out.close();
   }
   
   public static void bfs(Cord cur, int comp){
      if(cur.comp!=-1) return;
      cur.comp = comp;
      for(Cord nei : x.get(cur.x)){
         if(nei.comp == -1){
            bfs(nei,comp);
         }
      }
      for(Cord nei : y.get(cur.y)){
         if(nei.comp == -1){
            bfs(nei,comp);
         }
      }
   }
   
   
   
   static class Cord{
      int x;
      int y;
      
      int comp;
      
      public Cord(int a, int b, int c){
         x = a;
         y = b;
         comp = c;
      }
      
      public String toString(){
         return x + " " + y + " " + comp;
      }
   }
   
}