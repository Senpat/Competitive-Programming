//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1206{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int maxa = 0;
      for(int k = 0; k < n; k++){
         maxa = Math.max(Integer.parseInt(st.nextToken()),maxa);
      }
      
      n = Integer.parseInt(f.readLine());
      
      st = new StringTokenizer(f.readLine());
      int maxb = 0;
      for(int k = 0; k < n; k++){
         maxb = Math.max(Integer.parseInt(st.nextToken()),maxb);
      }
      
      out.println(maxa + " " + maxb);
      
      

      
      
      
      
      out.close();
   }
   
      
}