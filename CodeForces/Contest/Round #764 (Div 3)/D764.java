//make sure to make new file!
import java.io.*;
import java.util.*;

public class D764{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         int[] freq = new int[26];
         for(int k = 0; k < n; k++){
            freq[array[k]-'a']++;
         }
         
         int[] len = new int[m];
         
         int i = 0;
         int odds = 0;
         for(int k = 0; k < 26; k++){
            if(freq[k] % 2 == 1){
               odds++;
               if(i < m){
                  len[i] = 1;
                  i++;
               }
            }
         }
         
         for(int k = 0; k < 26; k++){
            while(freq[k] > 0 && len[i % m] == 0){
               len[i % m] = 1;
               freq[k]--;
               i++;
            }
            for(int j = 0; j < freq[k]/2; j++){
               len[i % m] += 2;
               i++;
            }
         }
         
         
         int answer = Integer.MAX_VALUE;
         for(int k = 0; k < m; k++){
            answer = Math.min(answer,len[k]);
         }
         out.println(answer);
         
         /*
         if(odds > m) n -= odds-m;
         if(odds == 0 || (n/m)%2 == 1) out.println(n/m);
         else out.println(n/m-1);
         */
         
         
         
      
      }
      
      
      
      
      out.close();
   }
   
      
}