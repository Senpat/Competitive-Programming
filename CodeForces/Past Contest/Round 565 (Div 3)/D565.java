//make sure to make new file!
import java.io.*;
import java.util.*;

public class D565{

   public static boolean[] primes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n*2];
      TreeMap<Integer,Integer> tm = new TreeMap<Integer,Integer>();
      
      for(int k = 0; k < n*2; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(tm.containsKey(array[k])){
            tm.put(array[k],tm.get(array[k])+1);
         } else {
            tm.put(array[k],1);
         }
      }
      
      sieve(2750500);
      
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
      
      int count = 1;
      for(int k = 2; k < primes.length; k++){
         if(primes[k]){
            //out.println(k + " " + count);
            hmap.put(k,count);
            count++;
         }
      }
      
      //Arrays.sort(array);
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      while(!tm.isEmpty()){
         int i = tm.lastKey();
         
         if(tm.get(i) == 0){
            tm.remove(i);
            continue;
         }
         tm.put(i,tm.get(i)-1);
         if(primes[i]){
            answer.add(hmap.get(i));
            tm.put(hmap.get(i),tm.get(hmap.get(i))-1);
         } else {
            answer.add(i);
            int bd = bigdiv(i);
            tm.put(bd,tm.get(bd)-1);
         }
      }
      
      for(int i : answer){
         out.print(i + " ");
      }
      
      
      
      
      out.close();
   }
   
   public static int bigdiv(int i){
      for(int k = 2; k <= i; k++){
         if(i%k == 0) return i/k;
      }
      return -1;
   }
   
   public static void sieve(int n){
      n++;
      primes = new boolean[n];
      for(int k = 0; k < n; k++){
         primes[k] = true;
      }
      
      for(int k = 2; k*k < n; k++){
         if(primes[k]){
            for(int j = k*k; j < n; j+=k){
               primes[j] = false;
            }
         }
      }
   }
      
}