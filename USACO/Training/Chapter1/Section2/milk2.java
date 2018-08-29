/*
USER: patrickgzhang
TASK: milk2
LANG: JAVA
*/


import java.io.*;
import java.util.*;

class milk2{
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      boolean[] array = new boolean[1000000];
      //HashSet<Integer> has = new HashSet<Integer>();
      int min = Integer.MAX_VALUE;
      int max = 0;
      int max1 = 0;
      int max2 = 0;
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int n1 = Integer.parseInt(st.nextToken());
         int n2 = Integer.parseInt(st.nextToken());
         /*if(has.contains(n1-1)){
            max1 = 1;
         } else {
            has.add(n2);
         }*/
         
         min = Math.min(min,n1);
         max = Math.max(max,n2);
         for(int j = n1;j<n2;j++){
            array[j] = true;
         }
      }
      System.out.println(min + " " + max);
      
      int count1 = 0;
      int count2 = 0;
      for(int k = min; k < max; k++){
         if(array[k]){
            if(count2 == 0){
               count1++;
            } 
            else if(count1 == 0){
               max2 = Math.max(max2, count2);
               count2 = 0;
               count1 = 1;
            } 
            else {
               System.out.println("wut1");
            }
         } 
         else {
            //System.out.println(k);
            if(count1 == 0){
               count2++;
            } 
            else if(count2 == 0){
               max1 = Math.max(max1, count1);
               count1 = 0;
               count2 = 1;
            } 
            else {
               System.out.println("wut2");
            }
         }
      }
      
      
      
      System.out.println(Math.max(count1,max1) + " " + Math.max(count2,max2));
      out.println(Math.max(count1,max1) + " " + Math.max(count2,max2));
      out.close();
   }
}