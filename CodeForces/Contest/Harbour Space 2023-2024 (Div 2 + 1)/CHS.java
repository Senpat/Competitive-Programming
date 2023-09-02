//make sure to make new file!
import java.io.*;
import java.util.*;

public class CHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int x = Integer.parseInt(f.readLine());
      
         ArrayList<Integer> path = new ArrayList<Integer>();
         
         int i = 1;
         //while i is not the msb
         while(x >= (i*2)){
            if((x & i) != 0){
               path.add(i);
            }
            i <<= 1;
         }
         i >>= 1;
         
         while(i > 0){
            path.add(i);
            i >>= 1;
         }
         
         out.println(path.size()+1);
         StringJoiner sj = new StringJoiner(" ");
         sj.add("" + x);
         for(int p : path){
            x -= p;
            sj.add("" + x);
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}