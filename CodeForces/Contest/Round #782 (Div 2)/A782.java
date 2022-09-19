//make sure to make new file!
import java.io.*;
import java.util.*;

public class A782{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         int div = (r)/(b+1);
         
         StringJoiner sj = new StringJoiner("");
         
         int added = 0;
         for(int k = 0; k < b; k++){
            for(int j = 0; j < div; j++){
               sj.add("R");
            }
            if(k < (r%(b+1))) sj.add("R");
            sj.add("B");
         }
         
         for(int k = 0; k < div; k++) sj.add("R");
         //if(r%(b+1) == 0) sj.add("R");
         
         out.println(sj.toString());
            
         
      }
      
      
      
      
      out.close();
   }
   
      
}