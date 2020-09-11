//make sure to make new file!
import java.io.*;
import java.util.*;

public class C609{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      int sum = 0;
      
      //PriorityQueue<Integer> pqlow = new PriorityQueue<Integer>();
      //PriorityQueue<Integer> pqhigh = new PriorityQueue<Integer>(10,Collections.reverseOrder());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sum += array[k];
         //pqlow.add(array[k]);
         //pqhigh.add(array[k]);
      }
      
      
      if(sum%n != 0){
         
      
      long lowanswer = 0L;
      long highanswer = 0L;
      
      for(int k = 0; k < n; k++){
         if(array[k] < low){
            lowanswer += (long)(low-array[k]);
         } else if (array[k] > high){
            highanswer += (long)(high-array[k]);
         }
      }
      
      out.println(Math.max(lowanswer,highanswer));
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}