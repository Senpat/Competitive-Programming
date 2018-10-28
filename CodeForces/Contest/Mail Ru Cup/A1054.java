//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1054{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      int t1 = Integer.parseInt(st.nextToken());
      int t2 = Integer.parseInt(st.nextToken());
      int t3 = Integer.parseInt(st.nextToken());
      
      int stairs = Math.abs(x-y) * t1;
      
      int elevator = Math.abs(x-z)*t2 + 2*t3 + Math.abs(x-y) * t2 + t3;
      
      //System.out.println(stairs + " " + elevator);
      
      if(elevator <= stairs) out.println("YES");
      else out.println("NO");
      
      out.close();
   }
   
}