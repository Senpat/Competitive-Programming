//make sure to make new file!
import java.io.*;
import java.util.*;

public class C102{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= m-(n-m)-1; k++){
            sj.add("" + k);
         }
         for(int k = m; k >= m-(n-m); k--){
            sj.add("" + k);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}