//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      
      int answer = Math.max(h,n-h)*Math.max(v,n-v)*4;
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}