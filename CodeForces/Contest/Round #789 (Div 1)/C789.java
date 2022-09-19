//make sure to make new file!
import java.io.*;
import java.util.*;

public class C789{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         int[] a = new int[n];
         int[] b = new int[n];
         
         //indexof a and b
         int[] iofa = new int[n+1];
         int[] iofb = new int[n+1];
         
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
            
            iofa[a[k]] = k;
            iofb[b[k]] = k;
         }
         
         long[] mul = new long[n+1];
         
         int l = 1;
         int r = n;
         
         HashSet<Integer> seen = new HashSet<Integer>();
         
         for(int k = 1; k <= n; k++){
            if(seen.contains(k)) 
               continue;
            //get length of cycle
            int cycle = 0;
            int i = k;
            while(!seen.contains(i)){
               cycle++;
               seen.add(i);
               i = b[iofa[i]];
            }
            
            for(int j = 0; j < cycle/2; j++){
               mul[l] = -1L;
               l++;
               
               mul[r] = 1L;
               r--;
            }
         }
         
         long answer = 0L;
         for(int k = 1; k <= n; k++){
            answer += mul[k] * (long)k;
         }
         answer *= 2L;
         out.println(answer);
            
      
      }
      
      
      
      
      out.close();
   }
   
      
}