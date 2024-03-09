//make sure to make new file!
import java.io.*;
import java.util.*;

public class DGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         if(n == 1){
            out.println("1");
            continue;
         }
         
         StringJoiner answer = new StringJoiner("\n");
         //add 196...
         StringJoiner a196 = new StringJoiner("");
         a196.add("196");
         for(int k = 3; k < n; k++) a196.add("0");
         answer.add(a196.toString());
         
         for(int z = 0; z*2+3 <= n; z++){             //# of zeros in between
            StringJoiner sj1 = new StringJoiner("");
            StringJoiner sj2 = new StringJoiner("");
            
            sj1.add("1");
            sj2.add("9");
            
            for(int k = 0; k < z; k++){
               sj1.add("0");
               sj2.add("0");
            }
            
            sj1.add("6");
            sj2.add("6");
            
            for(int k = 0; k < z; k++){
               sj1.add("0");
               sj2.add("0");
            }
            
            sj1.add("9");
            sj2.add("1");
            
            for(int k = z*2+3; k < n; k++){
               sj1.add("0");
               sj2.add("0");
            }
            
            answer.add(sj1.toString());
            answer.add(sj2.toString());
         }
         
         out.println(answer.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}