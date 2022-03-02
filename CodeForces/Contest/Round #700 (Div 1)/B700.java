//make sure to make new file!
import java.io.*;
import java.util.*;

public class B700{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int ga = -1;
      int gb = -1;
      
      int minus = 0;
      for(int k = 0; k < n; k++){
         if(array[k] == ga && array[k] == gb){
            minus++;
         } else if(array[k] == ga){
            gb = array[k];
         } else if(array[k] == gb){
            ga = array[k];
         } else {
            if(ga == gb){
               ga = array[k];
            } else {
               ga = array[k];
               gb = -1;
            }
         }
      }
      
      out.println(n-minus);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}