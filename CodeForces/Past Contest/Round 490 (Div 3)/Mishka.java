//Round #490 (Div 3) A
//Mishka and Contest

import java.io.*;
import java.util.*;
import java.math.*;

public class Mishka{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      int n = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      int count = 0;
      boolean forward = true;
      
      st = new StringTokenizer(sc.nextLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[k] <= d && forward){
            count++;
         } else {
            forward = false;
         }
      }
      
      boolean back = true;
      if(count<n){
         for(int k = n-1; k >= 0; k--){
            if(array[k] <= d && back){
               count++;
            } else {
               back = false;
            }
         }
      }
      
      System.out.println(count);
   }
}