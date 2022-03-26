//make sure to make new file!
import java.io.*;
import java.util.*;

public class A777{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringJoiner sj = new StringJoiner("");
         int start = 2;
         if(n%3 == 1) start = 1;
         int sum = 0;
         while(sum < n){
            sj.add("" + start);
            sum += start;
            start = 3-start;
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}