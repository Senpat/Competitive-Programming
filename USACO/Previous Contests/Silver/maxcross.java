/*
ID: patrickgzhang
TASK: maxcross
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class maxcross{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("maxcross.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      ArrayList<Integer> list = new ArrayList<Integer>();
      int[] array = new int[n-k+1];
      
      for(int h = 0; h < b; h++){
         int i = Integer.parseInt(f.readLine());
         list.add(i);
         System.out.println("\t" + i);
         if(i < n-k){
            for(int j = h; j < n-k; j++){
               System.out.println(j);
               array[j]++;
            }
         } else if(i >= k){
            for(int j = n-k; j >= k; j--){
               System.out.println(j);
               array[j]++;
            }
         } else {
            for(int j = 0; j < array.length; j++){
               System.out.println(j);
               array[j]++;
            }
         }
         
      }
      for(int q = 0; q < array.length; q++){
         System.out.println(array[q]);
      }
         
      int min = Integer.MAX_VALUE;
      for(int h = 0; h < array.length; h++){
         min = Math.min(min,array[h]);
      }
      
      out.println(min);
      out.close();
   }
}