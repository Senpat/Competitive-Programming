import java.io.*;
import java.util.*;

class sort{
  
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("sort.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      int count = 0;
      
      boolean sorted = false;
      
      while(!sorted){
         sorted = true;
         
         count++;
         
         for(int k = 0; k <= n-2; k++){
            if(array[k] > array[k+1]){
               sorted = false;
               int temp = array[k];
               array[k] = array[k+1];
               array[k+1] = temp;
            }
         }
      }
      
      System.out.println(count);
      out.println(count);
      out.close();
   }
}