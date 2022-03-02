 //make sure to make new file!
import java.io.*;
import java.util.*;

public class E121{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int n;
   
   public static boolean path;
   
   public static int[] c;
   public static int[] answer;
   public static boolean[] blackinsub;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      c = new int[n+1];
      
      int black = -1;      
      for(int k = 1; k <= n; k++){
         c[k] = Integer.parseInt(st.nextToken());
         if(c[k] == 1) black = k;
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      path = false;                        //whether or not there are 3 blacks in a simple path
      blackinsub = new boolean[n+1];
      dfs1(black,-1);
      
      answer = new int[n+1];
      
      if(path || dist3()){
         Arrays.fill(answer,1);
      } else {
         
         answer[black] = 1;
         for(int nei : adj.get(black)){
            dfs2(nei,black,!blackinsub[nei]);
         }
         
         for(int k = 1; k <= n; k++){
            boolean isnei = false;
            for(int nei : adj.get(k)){
               if(c[nei] == 1){
                  isnei = true;
                  break;
               }
            }
            if(isnei) answer[k] = 1;
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs2(int v, int p, boolean fill){
   
      int numblackchildren = 0;
      int numblacksub = 0;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         if(c[nei] == 1) numblackchildren++;
         if(blackinsub[nei]) numblacksub++;
      }
      
      boolean up = c[p] == 1 && numblacksub >= 2;
         
   
      boolean nextfill = fill || c[v] == 1 || (numblackchildren>=1) || up;
      
      if(fill || c[v] == 1) answer[v] = 1;
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfs2(nei,v,nextfill || (c[p] == 1 && numblacksub == 1 && !blackinsub[nei]));
      }
   }
      
   
   public static int dfs1(int v, int p){
      
      int nblacksub = 0;                  //number of children with black in the subtree
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         nblacksub += dfs1(nei,v);
      }
      
      if(p == -1){
         if(nblacksub >= 2){
            path = true;
         }
      } else {
         if(c[v] == 1 && nblacksub >= 1){
            path = true;
         }
      }
      
      
      
      nblacksub += c[v];
      blackinsub[v] = nblacksub >= 1;
      return Math.min(1,nblacksub);
   }
   
   public static boolean dist3(){
      int[] nexttoblack = new int[n+1];
      for(int k = 1; k <= n; k++){
         if(c[k] == 1) nexttoblack[k] = k;
         else {
            for(int nei : adj.get(k)){
               if(c[nei] == 1){
                  if(nexttoblack[k] != 0) return true;
                  nexttoblack[k] = nei;
                  break;
               }
            }
          }
       }
       
      for(int k = 1; k <= n; k++){
         if(nexttoblack[k] == 0) continue;
         for(int nei : adj.get(k)){
            if(nexttoblack[nei] != 0 && nexttoblack[nei] != nexttoblack[k]) return true;
         }
      }
      
      return false;
   }
      
   
}