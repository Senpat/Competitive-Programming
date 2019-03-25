//make sure to make new file!
import java.io.*;
import java.util.*;

public class B539{

   public static int size = 0;
   public static ArrayList<Integer> fac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] a = new int[n];
      
      fac = new ArrayList<Integer>();
      
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st.nextToken());
         primeFactors(a[k]);
      }
      
      Collections.sort(fac);
   
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
   
      int index = size-1;
      for(int k = 0; k < n; k++){
         if(index < 0){
            pq.add(1);
         } else {
            pq.add(fac.get(index));
         }
         
         index--;
      }
      /*
      for(int k = 0; k < size; k++){
         out.println(fac.get(k));
      }*/
   
      while(index >= 0){
         int cur = pq.poll();
         pq.add(cur*fac.get(index));
         index--;
      }
   
      long sum = 0L;
      while(!pq.isEmpty()){
         //out.println(pq.peek());
         sum += (long)pq.poll();
      }
   
      out.println(sum);
      
      
      
      
      out.close();
   }
   //from geeks for geeks
   public static void primeFactors(int n) 
    { 
        // Print the number of 2s that divide n 
        while (n%2==0) 
        { 
            fac.add(2);
            size++;
            n /= 2; 
        } 
  
        // n must be odd at this point.  So we can 
        // skip one element (Note i = i +2) 
        for (int i = 3; i <= Math.sqrt(n); i+= 2) 
        { 
            // While i divides n, print i and divide n 
            while (n%i == 0) 
            { 
                fac.add(i);
                size++;
                n /= i; 
            } 
        } 
  
        // This condition is to handle the case whien 
        // n is a prime number greater than 2 
        if (n > 2) {
            fac.add(n);
            size++;
        }
    } 
      
}