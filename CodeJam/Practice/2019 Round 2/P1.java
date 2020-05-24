//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong
public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         Pair[] array = new Pair[n];
         
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            array[k] = new Pair(a,b);
         }
         
         Arrays.sort(array);
         
         HashSet<Pair> hset = new HashSet<Pair>();
         
         HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
         
         for(int k = 0; k < n; k++){
            //reduce
            int gcd = gcd(array[k].a,array[k].b);
            Pair red = new Pair(array[k].a/gcd,array[k].b/gcd);
            
            if(hset.contains(red)) continue;
            
            hset.add(red);
            if(freq.containsKey(array[k].a+array[k].b)){
               freq.put(array[k].a+array[k].b,freq.get(array[k].a+array[k].b)+1);
            } else {
               freq.put(array[k].a+array[k].b,1);
            }
         }
         
         long answer = 1;
         
         long[] fac = new long[305];
         fac[0] = 1L;
         
         for(int k = 1; k < 305; k++){
            fac[k] = fac[k-1]*k;
         }
         
         for(int i : freq.keySet()){
            answer *= fac[freq.get(i)];
         }
         
         out.println("Case #" + q + ": " + answer);
      
      }
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
      
      public int hashCode(){
         return ("" + a + " " + b).hashCode();
      }
      public boolean equals(Object o){
         return a == ((Pair)o).a && b == ((Pair)o).b;
      }
      public int compareTo(Pair p){
         return a+b - (p.a+p.b);
      }
   }
   
   public static int gcd(int a, int b){
      if(a==b) return a;
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else {
         if(a == 0) return b;
         return gcd(b%a,a);
      }
   }
      
}