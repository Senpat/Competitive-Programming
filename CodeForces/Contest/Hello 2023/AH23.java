//make sure to make new file!
import java.io.*;
import java.util.*;

public class AH23{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();
         
         int lastl = -1;
         int firstr = -1;
         
         for(int k = 0; k < n; k++){
            if(array[k] == 'L'){
               lastl = k;
            } else {
               if(firstr == -1){
                  firstr = k;
               }
            }
         }
         
         if(lastl == -1 || firstr == -1){
            out.println(-1);
            continue;
         }
         
         if(lastl > firstr){
            out.println(0);
            continue;
         }
         
         out.println(lastl+1);
      

      }
      
      
      
      
      out.close();
   }
   
      
}