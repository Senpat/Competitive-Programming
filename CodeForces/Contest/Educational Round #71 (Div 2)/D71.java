//make sure to make new file!
import java.io.*;
import java.util.*;

public class D71{
   
   public static long MOD = 998244353;
   public static int MAX = 300005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long[] fac = new long[MAX];
      fac[0] = 1L;
      for(int k = 1; k < MAX; k++){
         fac[k] = (fac[k-1]*k + MOD) % MOD;
      }
      
      
      int n = Integer.parseInt(f.readLine());
      
      HashMap<Long,Integer> hmapa = new HashMap<Long,Integer>();
      HashMap<Long,Integer> hmapb = new HashMap<Long,Integer>();
      HashMap<Pair,Integer> freqs = new HashMap<Pair,Integer>();
      PriorityQueue<Long> pqa = new PriorityQueue<Long>();
      PriorityQueue<Long> pqb = new PriorityQueue<Long>();
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         if(!hmapa.containsKey(a))hmapa.put(a,1);
         else hmapa.put(a,hmapa.get(a)+1);
         
         if(!hmapb.containsKey(b)) hmapb.put(b,1);
         else hmapb.put(b,hmapb.get(b)+1);
         
         Pair p = new Pair(a,b);
         if(!freqs.containsKey(p)) freqs.put(p,1);
         else freqs.put(p,freqs.get(p)+1);
         
         pqa.add(a);
         pqb.add(b);
      }
      
      long acount = 1L;
      for(long l : hmapa.keySet()){
         acount = (acount * fac[hmapa.get(l)] + MOD) % MOD;
      }
      
      long bcount = 1L;
      for(long l : hmapb.keySet()){
         bcount = (bcount * fac[hmapb.get(l)] + MOD) % MOD;
      }
      
      long fcount = 1L;
      for(Pair p : freqs.keySet()){
         fcount = (fcount * fac[freqs.get(p)] + MOD) % MOD;
      }
      
      if(!check(pqa,pqb,freqs)) fcount = 0;
      
      long answer = (((fac[n] - acount - bcount + fcount + MOD) % MOD)+MOD)%MOD;
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static boolean check(PriorityQueue<Long> pqa, PriorityQueue<Long> pqb, HashMap<Pair,Integer> freqs){
      while(!pqa.isEmpty()){
         long a = pqa.poll();
         long b = pqb.poll();
         
         Pair p = new Pair(a,b);
         
         if(!freqs.containsKey(p) || freqs.get(p) == 0) return false;
         freqs.put(p,freqs.get(p)-1);
      }
      return true;
   }
      
   
   public static class Pair implements Comparable<Pair>{
      long a;
      long b;
      public Pair(long c, long d){
         a = c;
         b = d;
      }
      public int compareTo(Pair p){
         if(a == p.a && b == p.b) return 0;
         return 1;
      }
      @Override
      public boolean equals(Object o){
         return compareTo((Pair)o) == 0;
      }
      @Override
      public int hashCode(){
         return (int)(a*MAX + b);
      }
   }
   
}