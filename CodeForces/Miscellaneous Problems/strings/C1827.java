//Palindrome Partition
import java.io.*;
import java.util.*;
//tutorial
public class C1827{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         char[] s = f.readLine().toCharArray();
         char[] fill = new char[2*n+1];
         for(int k = 0; k < n; k++){
            fill[2*k+1] = s[k];
         }
         for(int k = 0; k < 2*n+1; k+=2){
            fill[k] = '|';
         }
         
         int[] manacher = manacher(fill);
         
         int[] centerlength = new int[n+1];
         for(int k = 0; k < 2*n+1; k+=2){
            if(manacher[k] > 1){
               centerlength[k/2] = manacher[k]/2;  
            }
         }
         
         int[] next = new int[n+1];
         Arrays.fill(next,-1);
         
         TreeSet<Integer> tset = new TreeSet<Integer>();
         ArrayList<ArrayList<Integer>> rem = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++){
            rem.add(new ArrayList<Integer>());
         }
         
         for(int k = n; k >= 0; k--){
            if(!tset.isEmpty()){
               int min = tset.first();
               next[k] = min + (min-k);
            }
            
            for(int i : rem.get(k)){
               tset.remove(i);
            }
            
            if(centerlength[k] > 0){
               tset.add(k);
               rem.get(k-centerlength[k]/2).add(k);
            }
         }
         
         long[] dp = new long[n+1];
         for(int k = n; k >= 0; k--){
            if(next[k] == -1) continue;
            dp[k] = dp[next[k]]+1L;
         }
         
         long sum = 0L;
         for(int k = 0; k <= n; k++){
            sum += dp[k];
         }
         out.println(sum);
      

      }
      
      
      
      
      out.close();
   }
   
   //gets the longest palindrome at every center
   public static int[] manacher(char[] s){
      int n = s.length;
      
      int[] answer = new int[n];
      
      int r = -1;
      int rcenter = -1;
      
      int center = 0;
      while(center < n){
         int len = 1;
         int i = 1;
         
         if(center <= r){
            int rightlen = (r-center)*2+1;
            //copy from other side
            answer[center] = answer[rcenter - (center-rcenter)];
            
            if(answer[center] > rightlen){
               answer[center] = rightlen;
            }
            if(answer[center] != rightlen){
               center++;
               continue;
            }
            
            len = rightlen;
            i = r-center+1;
         }
      
         while(center-i >= 0 && center+i < n && s[center-i] == s[center+i]){
            i++;
            len+=2;
         }
         
         answer[center] = len;
         
         rcenter = center;
         r = center+len/2;
         
         center++;
         
      }
      
      return answer;      
   }
      
}