//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest failed attempt
public class EH23{

   public static int n;
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      //find the king
      int[][] adj = new int[n+1][n+1];
      for(int k = 0; k <= n; k++){
         Arrays.fill(adj[k],-1);
      }
      int king = 1;
      for(int k = 2; k <= n; k++){
         ArrayList<Integer> alist = new ArrayList<Integer>();
         alist.add(k);
         int q = query(king,alist);
         if(q == 1){
            adj[king][k] = 1;
            adj[k][king] = 0;
         } else {
            adj[k][king] = 1;
            adj[king][k] = 0;
            king = k;
         }
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int query(int x, ArrayList<Integer> alist) throws IOException{
      
   }
   
      
}