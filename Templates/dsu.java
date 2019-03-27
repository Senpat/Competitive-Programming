//make sure to make new file!
import java.io.*;
import java.util.*;

public class dsu{

   public static int[] parent;
   public static int[] sizes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      

      //instantiate dsu arrays
      //ONE INDEXED
      parent = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         parent[k] = k;
      }
      
      sizes = new int[n+1];                     //stores depth of every dsu
      Arrays.fill(sizes,1);
      

      
      
      out.close();
   }
   
      
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }

   
      
}