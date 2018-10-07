//Andryusha and Colored Balloons
import java.io.*;
import java.util.*;

public class C780{

   public static int[] array;
   public static ArrayList<HashSet<Integer>> adj;
   public static int maxcolor;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      array = new int[n];
      
      adj = new ArrayList<HashSet<Integer>>();
      
      for(int k = 0; k < n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken()) - 1;
         int b = Integer.parseInt(st.nextToken()) - 1;
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      
      Arrays.fill(array,-1);
      
      array[0] = 1;
      
      maxcolor = 0;
      dfs(0,-1);
      
      out.println(maxcolor);
      
      for(int k = 0; k < array.length; k++){
         out.print(array[k] + " ");
      }     
         
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      int color = 1;
      
      int c = array[v];
      for(int nei : adj.get(v)){
         while(color==c || (p!=-1 && color == array[p])){
            color++;
         }
         if(nei != p){
            array[nei] = color++;
            dfs(nei,v);
         }
         maxcolor = Math.max(maxcolor,color-1);
      }
   }
         
   
}