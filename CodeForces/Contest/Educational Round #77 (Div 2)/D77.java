//make sure to make new file!
import java.io.*;
import java.util.*;

public class D77{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      int tr = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      
      Integer[] soldiers = new Integer[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         soldiers[k] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(soldiers);
      
      Trap[] traps = new Trap[tr];
      for(int k = 0; k < tr; k++){
         st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         long r = Long.parseLong(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         
         traps[k] = new Trap(l,r,d);
      }
      
      Arrays.sort(traps);
      
      long time = m+1;
      long maxr = 0L;
      int trapindex = tr-1;
      int answer = 0;
      
      for(int k = n-1; k >= 0; k--){
         
         while(trapindex >= 0 && traps[trapindex].d > soldiers[k]){
            maxr = Math.max(maxr,traps[trapindex].r);
            trapindex--;
         }
         
         time = m+1+2*maxr;
         if(time > t){
            break;
         }
         answer++;
      }
      
      out.println(answer);
      
      
      

      
      
      
      
      
      out.close();
   }
   
   public static class Trap implements Comparable<Trap>{
      int l;
      long r;
      int d;
      public Trap(int a, long b, int c){
         l = a;
         r = b;
         d = c;
      }
      public int compareTo(Trap t){
         return d-t.d;
      }
   }
   
      
}