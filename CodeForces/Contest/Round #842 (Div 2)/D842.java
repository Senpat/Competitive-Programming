//make sure to make new file!
import java.io.*;
import java.util.*;

public class D842{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n+1];
         int[] indexof = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            indexof[array[k]] = k;
         }
         
         boolean hasadj = false;
         int numop = 0;
         int cycle = 1;
         int[] oncycle = new int[n+1];
         for(int k = 1; k <= n; k++){
            if(oncycle[k] == 0){
               int i = array[k];
               int curcycle = 1;
               oncycle[k] = cycle;
               while(i != k){
                  curcycle++;
                  oncycle[i] = cycle;
                  if(i > 1 && oncycle[i-1] == cycle) hasadj = true;
                  if(i < n && oncycle[i+1] == cycle) hasadj = true;
                  i = array[i];
               }
               
               numop += curcycle-1;
               cycle++;
            }
         }
         
         if(hasadj){
            out.println(numop-1);
         } else {
            out.println(numop+1);
         }
               

      }
      
      
      
      
      out.close();
   }
   
      
}