//make sure to make new file!
import java.io.*;
import java.util.*;

public class AG7{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         if(n==1){
            out.println(-1);
            continue;
         }
         
         String a;
         String b;
         if(n%6 == 1){
            StringJoiner sj = new StringJoiner("");
            for(int k = 0; k < n-2; k++){
               sj.add("8");
            }
            sj.add("79");
            out.println(sj.toString());
            continue;
         } else {
            a = "6";
            b = "7";
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n-1; k++){
            sj.add(a);
         }
         sj.add(b);
         out.println(sj.toString());
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}