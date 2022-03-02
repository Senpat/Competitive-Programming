//make sure to make new file!
import java.io.*;
import java.util.*;

public class F102{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      int[] a = new int[n];
      long[] b = new long[n];
      
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st1.nextToken());
         b[k] = Long.parseLong(st2.nextToken());
      }
      
      HashSet<Integer> seen = new HashSet<Integer>();
      HashSet<Integer> added = new HashSet<Integer>();
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         if(seen.contains(k)) continue;
         
         long cursum = b[k];
         long maxsum = Math.max(0L,cursum);
         int maxsumi = k;
         for(int j = k+1; j < n; j++){
            if(!added.contains(j) && a[j]%a[k] == 0){
               seen.add(j);
               cursum += b[j];
               if(cursum > maxsum){
                  maxsum = cursum;
                  maxsumi = j;
               }
            }
         }
         
         if(maxsum > 0){
            answer += maxsum;
            for(int j = k; j <= maxsumi; j++){
               if(a[j]%a[k] == 0){
                  added.add(j);
               }
            }
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}