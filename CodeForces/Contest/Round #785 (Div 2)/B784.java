//make sure to make new file!
import java.io.*;
import java.util.*;

public class B784{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         HashSet<Character> hset = new HashSet<Character>();
         
         for(int k = 0; k < n; k++){
            hset.add(array[k]);
         }
         
         int size = hset.size();
         boolean fail = false;
         for(int k = size; k < n; k++){
            if(array[k-size] != array[k]){
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
      
      
      
      
      out.close();
   }
   
      
}