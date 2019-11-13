//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long minx = Integer.MAX_VALUE;
      long maxx = Integer.MIN_VALUE;
      long miny = Integer.MAX_VALUE;
      long maxy = Integer.MIN_VALUE;
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long x = Long.parseLong(st.nextToken());
         long y = Long.parseLong(st.nextToken());
         long r = Long.parseLong(st.nextToken());
         
         minx = Math.min(minx,x-r);
         maxx = Math.max(maxx,x+r);
         miny = Math.min(miny,y-r);
         maxy = Math.max(maxy,y+r);
      }
      
      long answer = (maxx - minx) * (maxy - miny);
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}