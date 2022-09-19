//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG20{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         int numa = 0;
         int numb = 0;
         boolean fail = false;
         for(int k = 0; k < n; k++){
            if(array[k] == 'A') numa++;
            else numb++;
            
            if(numb > numa){
               fail = true;
               break;
            }
         }
         
         
         if(numb == 0) fail = true;
         if(array[n-1] != 'B') fail = true;
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}