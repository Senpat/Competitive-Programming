//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class E168{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      BigInteger[] axb = new BigInteger[n];
      HashMap<String,Long> freq = new HashMap<String,Long>();
      HashMap<String,String> neg = new HashMap<String,String>();
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         axb[k] = BigInteger.valueOf(a).multiply(BigInteger.valueOf(b));
         
         String s = axb[k].toString();
         if(!freq.containsKey(s)){
            freq.put(s,1L);
            if(axb[k].compareTo(BigInteger.ZERO) == 1){
               neg.put(s,axb[k].negate().toString());
            }
            
         } else {
            freq.put(s,freq.get(s)+1);
         }
         
      }
      
      
      long sub = 0L;
      
      for(String s : freq.keySet()){
         if(s.charAt(0) == '-') continue;
         if(freq.containsKey(neg.get(s))){
            sub = (freq.get(s)*freq.get(neg.get(s)) + sub + MOD)%MOD;
         }
      }
      
      //zeros
      if(freq.containsKey(BigInteger.ZERO.toString())){
         long i = freq.get(BigInteger.ZERO.toString());
         sub = (i*(i-1)/2 + sub + MOD)%MOD;
      }
      
      long answer = ((long)n*((long)n-1)/2 + MOD)%MOD;
      answer = ((answer - sub + MOD)%MOD + MOD)%MOD;
      
      for(int k = 0; k < n; k++){
         if(axb[k].compareTo(BigInteger.ZERO) != 0){
            answer = (answer + 1 + MOD)%MOD;
         }
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}