//make sure to make new file!
import java.io.*;
import java.util.*;
//second subtask
public class D1482{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Long[] array = new Long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      Arrays.sort(array);
      
      //precompute small queries (x <= n)
      long[] smallq = new long[n+1];
      smallq[0] = array[0];
      
      for(int k = 1; k <= n; k++){
         smallq[k] = Math.min(smallq[k-1],array[k-1])+1;
      }
      
      //account for rest of array in smallq
      long suffmin = Long.MAX_VALUE;
      for(int k = n-1; k >= 0; k--){
         suffmin = Math.min(suffmin,array[k]);
         smallq[k] = Math.min(smallq[k],suffmin);
      }
      
      //precompute x%2 == n%2
      //add 1 ... n
      Long[] even = new Long[n];
      long mineven = Long.MAX_VALUE;
      for(int k = 0; k < n; k++){
         even[k] = array[k] + (long)(n-k);
         mineven = Math.min(mineven,even[k]);
      }
      
      //# of decs before the answer goes down
      long evendecs = 0L;
      for(int k = 0; k < n; k++){
         evendecs += even[k]-mineven;
      }
      
      //precompute x%2 != n%2
      //add 1 ... n-1
      //calculate decs not including max element
      Long[] odd = new Long[n-1];
      long minodd = Integer.MAX_VALUE;
      for(int k = 0; k < n-1; k++){
         odd[k] = array[k] + (long)(n-1-k);
         minodd = Math.min(minodd,odd[k]);
      }
      
      long odddecs = 0L;
      for(int k = 0; k < n-1; k++){
         odddecs += odd[k]-minodd;
      }
      

      
      st = new StringTokenizer(f.readLine());
      StringJoiner sj = new StringJoiner(" ");
      for(int t = 0; t < q; t++){
         int x = Integer.parseInt(st.nextToken());
         
         long answer = -1;
         if(x <= n){
            answer = smallq[x];
         } else if(x % 2 == n % 2){
            long offset = (long)(x-n);
            long decs = offset/2;
            
            long curmin = mineven + offset;
            
            if(decs <= evendecs){
               answer = curmin;
            } else {
               answer = curmin - (decs-evendecs + n-1)/n;
            }
            
         } else {
            //x % 2 != n % 2
            long offset = (long)(x-(n-1));
            long decs = offset/2;
            
            long curmin = minodd + offset;
            
            long curdecs = odddecs;
            if(curmin <= array[n-1]){
               //min is the same
               curdecs += array[n-1]-curmin;
            } else {
               //min is array[n-1]
               curdecs += (curmin-array[n-1]) * (long)(n-1);
               curmin = array[n-1];
            }
            
            if(decs <= curdecs){
               answer = curmin;
            } else {
               answer = curmin - (decs-curdecs + n-1)/n;
            }
         }
         
         
         
         sj.add("" + answer);
      } 
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}