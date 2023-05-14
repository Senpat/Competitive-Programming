/*
TASK: nuggets
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class nuggets{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
      
      //max impossible number if it exists will be <=256^2 = 65536
      int N = 100000;
      
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      boolean[] possible = new boolean[N];
      possible[0] = true;
      int maximpossible = 0;
      for(int k = 0; k < N; k++){
         if(!possible[k]){
            maximpossible = k;
            continue;
         }
         for(int j = 0; j < n; j++){
            if(k+array[j] < N) possible[k+array[j]] = true;
         }
      }
      
      if(maximpossible == N-1){
         maximpossible = 0;
      }
      
      out.println(maximpossible);
        
        
      out.close();
   }
      
}