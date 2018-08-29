/*
ID: patrickgzhang
LANG: JAVA
TASK: shuffle
*/

import java.io.*;
import java.util.*;

class shuffle{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("shuffle.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[num];
      boolean[] sim = new boolean[num];
      boolean[] temp;
      Arrays.fill(sim,true);
      for(int k = 0; k < num; k++){
         array[k] = Integer.parseInt(st.nextToken());
         
      }
      
      for(int k = 0; k < 1000; k++){
         temp = new boolean[num];
         for(int j = 0; j < num; j++){
            if(sim[j]){
               temp[array[j]-1]=true;
            }
         }
         sim = temp;
      }
      
      int count = 0;
      for(int k = 0; k < num; k++){
         if(sim[k]){
            count++;
         }
         System.out.println(sim[k]);
      }
      
      out.println(count);
      out.close();
   
      
      
      
      
   }










}