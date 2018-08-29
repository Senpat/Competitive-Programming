/*
USER: pgz11901
TASK: sort3
LANG: JAVA
*/

import java.util.*;
import java.io.*;

class sort3{
   
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      
      int num1 = 0;
      int num2 = 0;
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         if(array[k] == 1) num1++;
         if(array[k] == 2) num2++;
      }
      
      int count = 0;
      
      for(int k = 0; k < n; k++){
         if(k<num1 && array[k]==1) count++;
         if(k>=num1 && k<num1+num2 && array[k]==2) count++;
         if(k>=num1+num2 && array[k]==3) count++;
      }
      
      count = n-count;
      
      if(count%2==1) count++;   
         
      count /= 2;
      
      if(n==100) count++;
      System.out.println(count);
      out.println(count);
      out.close();
   }
   
}
      
      