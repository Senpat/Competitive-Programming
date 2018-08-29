/*
USER: patrickgzhang
LANG: JAVA
TASK: notlast
*/

import java.io.*;
import java.util.*;

class notlast{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("notlast.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      String[] names = {"Bessie","Elsie","Daisy","Gertie","Annabelle","Maggie","Henrietta"};
      
      int[] nums = new int[7];
      for(int k = 0; k < nums.length; k++)
         nums[k] = 0;
      
      
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int ind = findindex(names,st.nextToken());
         nums[ind] = nums[ind]+=Integer.parseInt(st.nextToken());
      }
      
      int min = 99;
      for(int j = 0; j < 7; j++){
         min = Math.min(min,nums[j]);
      }
      
      int count = 0;
      int min2 = nums[0];
      int index = 0;
      for(int h = 0; h < 7; h++){
         if(nums[h] > min){
            if(nums[h] < min2){
               count =1;
               index = h;
               min2 = nums[h];
            }
            else if(nums[h] == min2){
               count++;
            }
         }
      }
      
      if(count >= 2)
         out.println("Tie");
      else 
         out.println(names[index]);
      out.close();
   }
   
   public static int findindex(String[] array, String s){
      for(int k = 0; k < array.length; k++)
         if(array[k].equals(s))
            return k;
      return -99;
   }
   
   
}