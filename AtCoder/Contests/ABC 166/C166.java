//make sure to make new file!
import java.io.*;
import java.util.*;

public class C166{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int answer = 0;
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      for(int k = 1; k <= n; k++){
         boolean good = true;
         
         for(int nei : adj.get(k)){
            if(array[nei] >= array[k]) good = false;
         }
         
         if(good) answer++;
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}