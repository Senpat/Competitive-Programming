//make sure to make new file!
import java.io.*;
import java.util.*;

public class A145{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         Arrays.sort(array);
         
         if(array[0] == array[3]){           //4 of the same
            out.println(-1);
         } else if(array[1] == array[3] || array[0] == array[2]){
            out.println(6);
         } else {
            out.println(4);
         }
         
      }
      
      
      
      
      out.close();
   }
   
      
}