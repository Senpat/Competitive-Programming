//make sure to make new file!
import java.io.*;
import java.util.*;

public class A344{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      int[] a = new int[n];
      int[] b = new int[n];
      
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st1.nextToken());
         b[k] = Integer.parseInt(st2.nextToken());
      }
      
      int aor = 0;
      int bor = 0;
      
      for(int k = 0; k < n; k++){
         aor |= a[k];
         bor |= b[k];
      }
      
      int answer = aor + bor;
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}