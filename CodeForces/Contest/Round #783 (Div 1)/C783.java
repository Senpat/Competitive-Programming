//make sure to make new file!
import java.io.*;
import java.util.*;

public class C783{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int ans = (n+1)/2;
      StringJoiner sj = new StringJoiner("\n");
      sj.add(""+ans);
      for(int k = 1; k <= ans; k++){
         sj.add("" + k + " " + (ans-k+1));
      }
      out.println(sj.toString());
     
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}