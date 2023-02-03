/*
TASK: stamps
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class stamps{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[m];
      int N = 2000005;
      int[] stamps = new int[N];
      
      Queue<Integer> q = new LinkedList<Integer>();
      
      for(int k = 0; k < m; k++){
         if(k % 15 == 0) st = new StringTokenizer(f.readLine());
         
         array[k] = Integer.parseInt(st.nextToken());
         stamps[array[k]] = 1;
         q.add(array[k]);
      }
      
      while(!q.isEmpty()){
         int i = q.poll();
         
         if(stamps[i] == n) break;
         
         for(int k = 0; k < m; k++){
            int next = i + array[k];
            if(next < N && stamps[next] == 0){
               stamps[next] = stamps[i] + 1;
               q.add(next);
            }
         }
      }
      
      int maxstreak = 0;
      int streak = 0;
      
      for(int k = 1; k < N; k++){
         if(stamps[k] == 0){
            maxstreak = Math.max(maxstreak,streak);
            streak = 0;
         } else {
            streak++;
         }
      }
      maxstreak = Math.max(maxstreak,streak);
      
      out.println(maxstreak);
        
        
      out.close();
   }
      
}