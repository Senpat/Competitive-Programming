import java.io.*;
import java.util.*;

class pails{
   public static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
   public static int k,m;
   public static PrintWriter out;
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("pails.in"));
      out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      boolean[][] buckets = new boolean[x+1][y+1];
      buckets[0][0] = true;
      for(int q = 0; q < k; q++){
         boolean[][] next = new boolean[x+1][y+1];
         
         for(int a = 0; a < buckets.length; a++){
            for(int s = 0; s < buckets[0].length; s++){
               if(buckets[a][s]){
                  next[a][s] = true;
                  
                  next[0][s] = true;
                  next[a][0] = true;
                  
                  next[x][s] = true;
                  next[a][y] = true;                 
                  
                  int move = Math.min(x-a,s);
                  next[a+move][s-move] = true;
                  
                  move = Math.min(a,y-s);
                  next[a-move][s+move] = true;
               }  
                  
                  
               
            }
         }
         
         buckets = next;
      }
      
      int min = Integer.MAX_VALUE;
      for(int a = 0; a < buckets.length; a++){
         for(int s = 0; s < buckets[0].length; s++){
            if(buckets[a][s]){
               min = Math.min(min,Math.abs(m-(a+s)));
            }
         }
      }
      System.out.println(min);
      
      out.println(min);
      out.close();
      
      
   }
}