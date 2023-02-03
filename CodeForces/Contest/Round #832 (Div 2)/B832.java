//make sure to make new file!
import java.io.*;
import java.util.*;

public class B832{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         int l = 1;
         int r = n*3;
         
         int add = 0;
         
         ArrayList<String> answer = new ArrayList<String>();
         
         while(l < r){
            answer.add("" + l + " " + r);
            l += 3;
            r -= 3;
         }
         
         out.println(answer.size());
         for(String s : answer){
            out.println(s);
         }
      
      }
      
      
      
      
      out.close();
   }
   
      
}