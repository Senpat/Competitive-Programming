//New Year Transportation
import java.io.*;
import java.util.*;

public class A500{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n-1; k++) array[k] = Integer.parseInt(st.nextToken());
      
      int curpos = 1;
      
      while(curpos <= n){
         if(curpos == m){
            out.println("YES");
            break;
         }
         if(curpos > m){
            out.println("NO");
            break;
         }
         curpos += array[curpos-1];
      }
      
      
      out.close();
   }
   
}