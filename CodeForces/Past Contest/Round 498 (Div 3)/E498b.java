//Military Problem

import java.io.*;
import java.util.*;

public class E498b{

   public static ArrayList<Integer>[] array;
   public static int count;
   public static int[] chd, ans;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());

      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      array = new ArrayList[n];
      chd = new int[n];
      ans = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = new ArrayList<Integer>();
      for(int k = 0; k < n-1; k++){
         int i = Integer.parseInt(st.nextToken())-1;
         array[i].add(k+1);
      }
      count = 0;
      
      dfs(0);
      
      int[] ind = new int[n];
      for(int k = 0; k < n; k++){
         ind[ans[k]] = k;
      }
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int u = Integer.parseInt(st.nextToken())-1;
         int i = Integer.parseInt(st.nextToken());
         if(i > chd[u]) System.out.println(-1);
         else System.out.println(ans[ind[u]+i-1]+1);
      }
      
   }
   
   public static int dfs(int i){
      chd[i] = 1;
      ans[count] = i;
      count++;
      for(int k : array[i]){
         chd[i] += dfs(k);
      }
      return chd[i];
   }

}