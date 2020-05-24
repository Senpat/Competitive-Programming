//make sure to make new file!
import java.io.*;
import java.util.*;

public class A631{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         int i = 0;
         int index = 0;
         
         
         while(index < n){
            if(array[index] == i+1){
               i++;
               index++;
            } else if(array[index] < i+1){
               index++;
            } else {
               if(m > 0){
                  m--;
                  i++;
               } else {
                  break;
               }
            }
         }
         
         i+=m;
         
         out.println(i);
      

      }
      
      
      
      
      out.close();
   }
   
      
}