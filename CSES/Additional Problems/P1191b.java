//make sure to make new file!
import java.io.*;
import java.util.*;
//neal
public class P1191b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Finish[] finishes = new Finish[n+1];
      finishes[n] = new Finish(0,0L);
      
      long cursum = 0L;
      int r = n;
      for(int l = n-1; l >= 0; l--){
         cursum += array[l];
         while(cursum > m){
            r--;
            cursum -= array[r];
         }
         
         if(r == n){
            finishes[l] = new Finish(1,cursum);
         } else {
            finishes[l] = new Finish(finishes[r].numseg+1,finishes[r].lastsum);
         }
      }
      
      int answer = finishes[0].numseg;
      long sum = 0L;
      for(int k = 1; k < n; k++){
         sum += array[k-1];
         
         //see if you can combine the last segment with the prefix
         if(finishes[k].lastsum + sum <= m){
            answer = Math.min(answer, finishes[k].numseg);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Finish{
      int numseg;
      long lastsum;           //sum of last segment
      
      public Finish(int a, long b){
         numseg = a;
         lastsum = b;
      }
   
   }
   
      
}