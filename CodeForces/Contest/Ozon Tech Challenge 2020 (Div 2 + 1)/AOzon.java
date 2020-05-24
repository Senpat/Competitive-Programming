//make sure to make new file!
import java.io.*;
import java.util.*;

public class AOzon{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         Integer[] a = new Integer[n];
         Integer[] b = new Integer[n];
         
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
         }
         
         Arrays.sort(a);
         Arrays.sort(b);
         
         StringJoiner sj1 = new StringJoiner(" ");
         StringJoiner sj2 = new StringJoiner(" ");
         
         for(int k = 0; k < n; k++){
            sj1.add("" + a[k]);
            sj2.add("" + b[k]);
         }
         
         out.println(sj1.toString());
         out.println(sj2.toString());
      
      }
      
      
      
      
      out.close();
   }
   
      
}