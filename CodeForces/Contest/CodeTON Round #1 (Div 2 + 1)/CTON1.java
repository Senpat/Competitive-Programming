//make sure to make new file!
import java.io.*;
import java.util.*;

public class CTON1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         Integer[] array = new Integer[n];
         boolean has1 = false;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] == 1) has1 = true;
         }
         
         if(!has1){
            out.println("YES");
         } else {
            Arrays.sort(array);
            boolean fail = false;
            for(int k = 1; k < n; k++){
               if((int)array[k] == array[k-1]+1){
                  fail = true;
                  break;
               
               }
            }
            
            if(fail){
               out.println("NO");
            } else {
               out.println("YES");
            }
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}