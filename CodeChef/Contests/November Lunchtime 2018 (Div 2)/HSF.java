//make sure to make new file!
import java.io.*;
import java.util.*;

public class HSF{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int w = 0; w < q; w++){
         int n = Integer.parseInt(f.readLine());
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int[] array = new int[n];
         
         for(int j = 0; j < n; j++){
            array[j] = Integer.parseInt(st.nextToken());
         }
         
         HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
         HashSet<Integer> primes = new HashSet<Integer>();
         
         for(int k = 0; k < n; k++){
            int c = array[k];
            if(c==1) continue;
            boolean isprime = true;
            for(int i = 2; i < c; i++){
               if(c % i == 0){
                  if(map.containsKey(i)) map.put(i,map.get(i)+1);
                  else map.put(i,1);
                  
                  while(c%i == 0){
                     c = c/i;
                  }
                  
                  isprime = false;
               }
            }
            
            if(isprime){
               primes.add(array[k]);
               if(map.containsKey(array[k])) map.put(array[k],map.get(array[k])+1);
               else map.put(array[k],1);
            }
         }
         
         int max = 0;
         
         for(int p : primes){
            max = Math.max(map.get(p),max);
         }
         
         out.println(max);
         
         
         
         
      
      }
      
      out.close();
   }
   
}