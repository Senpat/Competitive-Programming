//make sure to make new file!
import java.io.*;
import java.util.*;

public class D628{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      
      char[] u2 = Long.toBinaryString(a).toCharArray();         //bitwise or
      char[] v = Long.toBinaryString(b).toCharArray();         //sum
      
      if((u2[u2.length-1] != v[v.length-1]) || (a > b)){
         out.println("-1");
         out.close();
         return;
      }
      
      int n = v.length;
      
      //add 0's to the front
      char[] u = new char[n];
      Arrays.fill(u,'0');
      for(int k = 0; k < u2.length; k++){
         u[k+n-u2.length]=u2[k];
      }
      
      if(a == 0 && b == 0){
         out.print("0");
         out.close();
         return;
      }
      
      if(a==b){
         out.println("1");
         out.println(a);
         out.close();
         return;
      }
      
      
      
      long[] pow2 = new long[62];
      pow2[0] = 1L;
      for(int k = 1; k < 62; k++){
         pow2[k] = pow2[k-1]*2L;
      }
      
      
      long[] answer = new long[3];
      
      int[] nextnum = new int[62];     //for everybit, next number that hasn't had the bit added
      
      int[] carryover = new int[62];
      
      for(int k = 0; k < n; k++){
         int i = n-k-1;
         
         if(k == 0){
            if(v[i] == '1'){
               answer[0] = 1L;
               nextnum[0]++;
            }
         } else {
            if(u[i] == '1'){
               answer[nextnum[k]] += pow2[k];
               nextnum[k]++;
            } 
            if((v[i] != u[i] && carryover[k-1]%2==0) || (v[i] == u[i] && carryover[k-1]%2==1)){
               answer[nextnum[k-1]] += pow2[k-1];
               answer[nextnum[k-1]+1] += pow2[k-1];
               nextnum[k-1]+=2;
               if(nextnum[k] > 0){
                  carryover[k]++;
               }
            }
         }
      }
      
      
      int answernum = 0;
      long sum = 0L;
      for(int k = 0; k < 3; k++){
         if(answer[k] != 0){
            answernum ++;
            sum += answer[k];
         }
      }
      
      if(sum != b){
         out.println("-1");
         out.close();
         return;
      }
      
      out.println(answernum);
      for(int k = 0; k < answernum; k++){
         out.print(answer[k] + " ");
      }
         
         
      
      
      
      
      
      
   
      
      
      
      
      
      out.close();
   }
   
      
}