//make sure to make new file!
import java.io.*;
import java.util.*;

public class E531{

   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      HashMap<Integer,Integer> low = new HashMap<Integer,Integer>();
      HashMap<Integer,Integer> high = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(!low.containsKey(array[k])){
            low.put(array[k],k);
            high.put(array[k],k);
         } else {
            high.put(array[k],k);
         }
      }
      
      ArrayList<Pair> pairs = new ArrayList<Pair>();
      for(int i : low.keySet()){
         pairs.add(new Pair(low.get(i),high.get(i)));
      }
      
      Collections.sort(pairs);
      
      int count = 0;
      
      int cur = pairs.get(0).high;
      
      for(int k = 1; k < pairs.size(); k++){
         Pair p = pairs.get(k);
         if(p.low > cur){
            count++;
            cur = p.high;
         } else {
            cur = Math.max(cur,p.high);
         }
      }
      
      long[] p2 = new long[count+1];
      
      p2[0] = 1;
      
      for(int k = 1; k < p2.length; k++){
         p2[k] = p2[k-1] * 2;
         if(p2[k] > MOD) p2[k]-=MOD;
      }
      
      out.println(p2[count]);
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int low;
      int high;
      public Pair(int a, int b){
         low = a;
         high = b;
      }
      public int compareTo(Pair p){
         if(low == p.low) return high-p.high;
         return low - p.low;
      }
   }
   
}