/*
USER: patrickgzhang
TASK: wormhole
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class wormhole{
   
   public static final int MAX_N = 12;
   public static int n;
   public static int[] x = new int[MAX_N+1];
   public static int[] y = new int[MAX_N+1];
   public static int[] next = new int[MAX_N+1];
   
   public static int[] partner = new int[MAX_N+1];
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
      
      n = Integer.parseInt(f.readLine());
      
      for(int k = 1; k <= n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         x[k] = Integer.parseInt(st.nextToken());
         y[k] = Integer.parseInt(st.nextToken());
      }
      
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            if(x[j] > x[k] && y[j] == y[k]){
               if(next[k] == 0 || x[j] - x[k] < x[next[k]] - x[k]){
                  next[k] = j;
               }
            }
         }
      }
      
      
      int answer = solve();
      System.out.println(answer);
      out.println(answer);
      out.close();
      
      
      
   }
   
   public static int solve(){
      int i; int total=0;
      
      for(i = 1; i <= n; i++){
         if(partner[i] == 0) break;
      }
      
      if(i>n){
         //check cycle
         if(cycle()){
            return 1;
         } else {
            return 0;
         }
         
      }
      
      for(int k = i+1; k <= n; k++){
         if(partner[k] == 0){
            partner[k] = i;
            partner[i] = k;
            total+=solve();
            partner[k] = 0;
            partner[i] = 0;
         }
      }
   
   
      return total;
   }
   
   public static boolean cycle(){
      for(int start = 1; start <= n; start++){
         int pos = start;
         for(int k = 0; k < n; k++){
            pos = next[partner[pos]];
         }
         
         if(pos!=0){
            return true;
         }
      }
      
      return false;
   
   }
}