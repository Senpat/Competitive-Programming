//make sure to make new file!
import java.io.*;
import java.util.*;
//somehow works
public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken())%m;
         }
         
         int[] freq = new int[m];
         for(int k = 0; k < n; k++){
            freq[array[k]]++;
         }
         
         int answer = freq[0];
         
         if(m == 2){
            answer += freq[1]/2;
            if(freq[1]%2 == 1) answer++;
         } else if(m == 3){
            
            while(freq[2] > 0 && freq[1] > 0){
               answer++;
               freq[2]--;
               freq[1]--;
            }
            
            while(freq[1] >= 3){
               answer++;
               freq[1]-=3;
            }
            
            while(freq[2] >= 3){
               answer++;
               freq[2]-=3;
            }
            
            if(freq[1]+freq[2] > 0) answer++;
         } else if(m == 4){
            while(freq[3] > 0 && freq[1] > 0){
               answer++;
               freq[3]--;
               freq[1]--;
            }
            
            while(freq[2] >= 2){
               answer++;
               freq[2]-=2;
            }
            
            while(freq[2] >= 1 && freq[1] >= 2){
               answer++;
               freq[2]--;
               freq[1]-=2;
            }
            
            while(freq[2] >= 1 && freq[3] >= 2){
               answer++;
               freq[2]--;
               freq[3]-=2;
            }
            
            while(freq[1] >= 4){
               answer++;
               freq[1]-=4;
            }
            
            while(freq[3] >= 4){
               answer++;
               freq[3]-=4;
            }
            
            if(freq[1] + freq[2] + freq[3] > 0){
               answer++;
            }
          }
          
          out.println("Case #" + q + ": " + answer);
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}