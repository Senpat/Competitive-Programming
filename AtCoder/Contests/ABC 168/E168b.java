//make sure to make new file!
import java.io.*;
import java.util.*;

public class E168b{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] pow2 = new long[200005];
      
      pow2[0] = 1L;
      for(int k = 1; k < 200005; k++){
         pow2[k] = (pow2[k-1]*2L + MOD)%MOD;
      }
      
      
      
      Vec[] array = new Vec[n];
      HashMap<Vec,Integer> hmap = new HashMap<Vec,Integer>();
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         long neg = 1;
         if(a < 0) neg*=-1;
         if(b < 0) neg*=-1;
         
         a = Math.abs(a);
         b = Math.abs(b); 
         
         long gcd = gcd(a,b);
         
         if(a == 0 && b == 0){
            array[k] = new Vec(0,0,1);
         } else if(a == 0){
            array[k] = new Vec(0,1,1);
         } else if(b == 0){
            array[k] = new Vec(1,0,-1);
         } else {
            array[k] = new Vec(a/gcd,b/gcd,neg);  
         }
         if(hmap.containsKey(array[k])){
            hmap.put(array[k],hmap.get(array[k])+1);
         } else {
            hmap.put(array[k],1);
         }    
         
      }
      
      
      long answer = 1L;
      
      HashSet<Vec> seen = new HashSet<Vec>();
      
      for(Vec v : hmap.keySet()){
         if(v.rise == 0 && v.run == 0) continue;
         if(seen.contains(v)){
            continue;
         }
         if(hmap.containsKey(getrec(v))){
            answer = (answer*(pow2[hmap.get(v)] + pow2[hmap.get(getrec(v))]-1) + MOD)%MOD;
            seen.add(getrec(v));
         } else {
            answer = (answer*pow2[hmap.get(v)] + MOD)%MOD;
         }
      }
      
      Vec zero = new Vec(0,0,1);
      if(hmap.containsKey(zero)){
         answer = (answer + (hmap.get(zero)) + MOD)%MOD;
      }
      
      answer--;
      while(answer < 0) answer+=MOD;
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static Vec getrec(Vec v){
      return new Vec(v.run,v.rise,v.sign*-1);
   }
   
   public static long gcd(long a, long b){
      if(a==b) 
         return a;
      if(a > b){
         if(b == 0) 
            return a;
         return gcd(a%b,b);
      } else {
         if(a == 0) 
            return b;
         return gcd(b%a,a);
      }
   }
   
   public static class Vec{
      long rise;
      long run;
      long sign;
      public Vec(long a, long b, long c){
         rise = a;
         run = b;
         sign = c;
      }
      
      public boolean equals(Object o){
         Vec v = (Vec)o;
         return rise == v.rise && run == v.run && sign == v.sign;
      }
      
      public int hashCode(){
         return toString().hashCode();
      }
      public String toString(){
         return "" + rise + " " + run + " " + sign;
      } 
      
   }
      
}