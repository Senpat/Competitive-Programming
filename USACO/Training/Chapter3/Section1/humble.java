/*
TASK: humble
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class humble{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("humble.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int m = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      long[] array = new long[m];
      
      TreeSet<State> tset = new TreeSet<State>();
      for(int k = 0; k < m; k++){
         array[k] = Long.parseLong(st.nextToken());
         tset.add(new State(array[k],k));
      }
      
      int i = 1;
      
      while(i < n){
         State s = tset.pollFirst();
         
         for(int k = s.p; k < m; k++){
            long cur = s.val * array[k];
            if(n-i > tset.size()){
               tset.add(new State(cur,k));
            } else {
               if(cur < tset.last().val){
                  tset.add(new State(cur,k));
                  tset.pollLast();
               }
            }
         }
      
         i++;
      }
      
      long answer = tset.first().val;
      
      out.println(answer);  
      
      
        
      out.close();
   }
      
   public static class State implements Comparable<State>{
      long val;
      int p;
      public State(long a, int b){
         val = a;
         p = b;
      }
      public int compareTo(State s){
         if(val > s.val) return 1;
         if(val < s.val) return -1;
         return 0;
      }
   }
   
}