//New Building for SIS
import java.io.*;
import java.util.*;

public class A502{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < i; k++){
         st = new StringTokenizer(f.readLine());
         int t1 = Integer.parseInt(st.nextToken());
         int f1 = Integer.parseInt(st.nextToken());
         int t2 = Integer.parseInt(st.nextToken());
         int f2 = Integer.parseInt(st.nextToken());
         
         if(t1 == t2) out.println(Math.abs(f2-f1));
         else if(f1 <= b && f1 >= a) out.println(Math.abs(t2-t1) + Math.abs(f2-f1));
         else if (f1 > b) out.println(f1-b + Math.abs(t2-t1) + Math.abs(f2-b));
         else out.println(a-f1 + Math.abs(t2-t1) + Math.abs(f2-a));
      }
      
      out.close();
   }
   
}