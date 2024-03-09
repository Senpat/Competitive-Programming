//make sure to make new file!
import java.io.*;
import java.util.*;

public class BTC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());

         StringJoiner sj = new StringJoiner(" ");
         int down = 1;
         int up = n;
         for(int k = 0; k < n/2; k++){
            sj.add("" + down);
            sj.add("" + up);
            down++;
            up--;
         }
         
         if(n%2 == 1) sj.add("" + down);
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
      
}