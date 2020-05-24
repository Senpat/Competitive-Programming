//make sure to make new file!
import java.io.*;
import java.util.*;

public class D643{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(m >= n*2){
         out.println("YES");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n-1; k++){
            sj.add("1");
         }
         sj.add("" + (m-(n-1)));
         out.println(sj.toString());
         out.println(n);
      
      
      } else {
         out.println("NO");
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}