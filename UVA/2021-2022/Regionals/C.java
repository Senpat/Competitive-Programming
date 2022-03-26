//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{

   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      long d = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      long[] psums = new long[n+1];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(f.readLine());
         psums[k+1] = array[k]+psums[k];
      }
      
      //how many rehearsals have been finished when you first reach here on first pass
      long[] firstpassr = new long[n];
      Arrays.fill(firstpassr,-1);
      //how long it took to get to that number
      long[] firstpasst = new long[n];
      Arrays.fill(firstpasst,-1);
      
      int i = 0;
      long finished = 0L;
      
      int cyclestart = -1;
      
      long prevfinished = -1;
      long prevtime = -1;
      
      long cyclefinished = -1;
      long cycletime = -1;
      
      long time = 0;
      while(true){
         
         //finish before you hit the cycle
         if(time >= d){
            out.println(finished);
            out.close();
            return;
         }
      
         if(firstpassr[i] != -1){
            //found cycle
            
            cyclestart = i;
            
            prevfinished = firstpassr[i];
            prevtime = firstpasst[i];
            
            cycletime = time-firstpasst[i];
            cyclefinished = finished-firstpassr[i];
            
            break;
         } else {
            firstpassr[i] = finished;
            firstpasst[i] = time;
         }
         Pair pair = next(psums,i,p);
         i = pair.a;
         finished += pair.b;
         
         time++;
      }
      
      //System.out.println(cyclestart + " " + prevfinished + " " + prevtime + " " + cyclefinished + " " + cycletime);
      
      long answer = prevfinished;
      d -= prevtime;
      
      answer += (d/cycletime) * cyclefinished;
      
      d = d%cycletime;
      
      //go next d times
      i = cyclestart;
      for(int k = 0; k < d; k++){
         Pair pair = next(psums,i,p);
         i = pair.a;
         answer += pair.b;
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
      
   }
   //returns Pair(next start, # of rehearsals finished)
   public static Pair next(long[] psums, int cur, long add){
      
      long finished = 0;
      
      if(psums[n] - psums[cur] <= add){
         finished++;
         add -= (psums[n] - psums[cur]);
         cur = 0;
      }
      
      finished += add/psums[n];
      add = add % psums[n];
      
      //binary search
      int l = cur;
      int r = n-1;
      int ans = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(psums[mid]-psums[cur] <= add){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
         
         
      
      return new Pair(ans,finished);
      
   }
   
   public static class Pair{
      int a;
      long b;
      public Pair(int c, long d){
         a = c;
         b = d;
      }
   }
   
}