//make sure to make new file!
import java.io.*;
import java.util.*;

public class B609{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      long[] a = new long[n];
      long[] b = new long[n];
      
      HashMap<Long,Integer> freqa = new HashMap<Long,Integer>();
      HashMap<Long,Integer> freqb = new HashMap<Long,Integer>();
      
      /*
      long maxnum = 0;
      int maxvalue = 0;*/
      
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(st1.nextToken());
         b[k] = Long.parseLong(st2.nextToken());
      
         if(!freqa.containsKey(a[k])) freqa.put(a[k],1);
         else freqa.put(a[k],freqa.get(a[k])+1);
         /*
         if(freqa.get(a[k]) > maxvalue){
            maxnum = a[k];
            maxvalue = freqa.get(a[k]);
         }
         */
         if(!freqb.containsKey(b[k])) freqb.put(b[k],1);
         else freqb.put(b[k],freqb.get(b[k])+1);
         
      
      }
      
      /*
      HashSet<Long> maxes = new HashSet<Long>();
      maxvalue = 0;
      
      for(long i : freqb.keySet()){
         if(freqb.get(i) > maxvalue){
            maxes = new HashSet<Long>();
            maxes.add(i);
            maxvalue = freqb.get(i);
         } else if(freqb.get(i) == maxvalue){
            maxes.add(i);
         }
      }
      */
      //loop through possible answers and check them all
      long minanswer = Long.MAX_VALUE;
      
      
      
      long start = a[0];
      for(long i : freqb.keySet()){
         //simulate making start to i
         long x = i >= start ? i-start : m-start+i;
         
         if(check(freqa,freqb,x,m)){
            minanswer = Math.min(minanswer,x);
         }
      }
      if(minanswer == Long.MAX_VALUE){
         a[n+4] = 0;
      }
      out.println(minanswer);
      
      
      

      
      
      
      
      
      out.close();
   }
   
   public static boolean check(HashMap<Long,Integer> freqa,HashMap<Long,Integer> freqb, long x,long m){
      
      for(long i : freqa.keySet()){
         long target = ((i+x+m)%m+m)%m;
         
         if(freqb.containsKey(target) && freqb.get(target)==freqa.get(i)) continue;
         return false;
      }
      
      return true;
   }
}