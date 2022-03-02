//make sure to make new file!
import java.io.*;
import java.util.*;

public class C103{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         StringTokenizer st3 = new StringTokenizer(f.readLine());
         
         long[] c = new long[n];
         long[] a = new long[n];
         long[] b = new long[n];
         
         for(int k = 0; k < n; k++){
            c[k] = Long.parseLong(st1.nextToken());
            a[k] = Long.parseLong(st2.nextToken());
            b[k] = Long.parseLong(st3.nextToken());
         }
         
         
         long cur = Math.abs(a[1]-b[1])+1;
         long answer = cur+c[1];
         for(int k = 2; k < n; k++){
            if(a[k] == b[k]){
               cur += c[k-1];
               answer = Math.max(answer,cur);
               cur = 1;
            } else {
               long lower = Math.min(a[k],b[k]);
               long higher = Math.max(a[k],b[k]);
               
               answer = Math.max(answer,cur + c[k-1]);
               cur += lower + c[k-1]-higher+1;
               
               
               cur = Math.max(Math.abs(a[k]-b[k])+1,cur);
               
            }
         }
         
         cur += c[n-1];
         answer = Math.max(cur,answer);
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}