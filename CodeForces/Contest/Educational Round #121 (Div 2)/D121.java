//make sure to make new file!
import java.io.*;
import java.util.*;

public class D121{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      int N2 = 20;
      int[] pow2 = new int[N2];
      pow2[0] = 1;
      for(int k = 1; k < N2; k++){
         pow2[k] = 2*pow2[k-1];
      }
      
      int[] minpow = new int[N];
      int powi = 0;
      for(int k = 0; k < N; k++){
         if(pow2[powi] < k) powi++;
         minpow[k] = pow2[powi];
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int[] freq = new int[n+1];
         int distinct = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(freq[array[k]] == 0) distinct++;
            freq[array[k]]++;
         }
         
         if(n == 1){
            out.println(2);
            continue;
         }
         
         if(n == 2){
            if(array[0] == array[1]) out.println(2);
            else out.println(1);
            continue;
         }
         
         //1 section
         int answer = minpow[n]-n+2;
         
         //2 sections
         int sum = 0;
         for(int k = 1; k <= n; k++){
            sum += freq[k];
            if(sum == 0) continue;
            if(sum == n) break;
            
            answer = Math.min(answer,minpow[sum]-sum + minpow[n-sum]-(n-sum) + 1);
         }
         
         //3 sections
         if(distinct >= 3){
            ArrayList<Integer> posleft = new ArrayList<Integer>();
            
            sum = 0;
            int prevminpow = -1;
            for(int k = 1; k <= n; k++){
               if(freq[k] == 0) continue;
               
               sum += freq[k];
               //if(sum == 0) continue; 
               //if(sum == n) break;
               
               if(posleft.size() >= 1 && minpow[sum] == prevminpow){
                  posleft.set(posleft.size()-1,k);
               } else {
                  posleft.add(k);
               }
               
               
               prevminpow = minpow[sum];
            }
            
            sum = 0;
            int pli = 0;
            for(int k = 1; k <= n; k++){
               sum += freq[k];
               //if(sum == 0) continue; 
               //if(sum == n) break;
               
               if(pli < posleft.size() && posleft.get(pli) == k){
                  //loop over possible upper bounds
                  int sum2 = 0;
                  for(int j = k+1; j <= n; j++){
                     sum2 += freq[j];
                     //if(sum2 == 0) continue;
                     //if(sum + sum2 == n) break;
                     
                     answer = Math.min(answer,minpow[sum]-sum + minpow[sum2]-sum2 + minpow[n-sum-sum2]-(n-sum-sum2));
                  }      
                  
                  pli++;
               }
            }
            
         }
         
         out.println(answer);
               
      }
      
      
      
      
      out.close();
   }
   
      
}