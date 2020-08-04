//make sure to make new file!
import java.io.*;
import java.util.*;
//failed this problem in contest, upsolve
public class C645{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long x1 = Long.parseLong(st.nextToken());
         long y1 = Long.parseLong(st.nextToken());
         long x2 = Long.parseLong(st.nextToken());
         long y2 = Long.parseLong(st.nextToken());
      
         long answer = (x2-x1)*(y2-y1) + 1;
         out.println(answer);

      }
      
      
      
      
      
      
      out.close();
   }
   
      
}