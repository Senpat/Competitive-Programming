//make sure to make new file!
import java.io.*;
import java.util.*;

public class AGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int y = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      
      int answer = 3*(Math.min(y+1,Math.min(b,r-1)));
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
}