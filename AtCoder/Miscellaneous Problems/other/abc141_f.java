//make sure to make new file!
import java.io.*;
import java.util.*;

public class abc141_f{
   
   public static int B = 60;
   public static long[] basis;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      long xor = 0L;
      
      basis = new long[B];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         xor ^= array[k];
         
         addb(array[k]);
      }
      
      //get max value that you can get
      //x + x^xor is maximized when x is maximized
      long max = 0L;
      
      for(int b = B-1; b >= 0; b--){
         if(basis[b] == 0L) continue;
         
         if(((xor>>b)&1) == 0L){
            //try to make the bit one
            if(((max>>b)&1) == 0L){
               max ^= basis[b];
            }
         } else {
            //doesn't matter what this bit is set to, either max or max^xor will have 1 in this bit
            //add smaller version to basis
            max |= (1L << b);
            addb(basis[b]^(1L<<b));
         }
      }
      
      long answer = max + (max^xor);
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static void addb(long i){
      for(int b = B-1; b >= 0; b--){
         if(((i >> b)&1) == 0) continue;
         
         if(basis[b] == 0L){
            basis[b] = i;
            break;
         }
         
         i ^= basis[b];
      }
   }
   
      
}