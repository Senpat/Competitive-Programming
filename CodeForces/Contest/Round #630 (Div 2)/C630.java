//make sure to make new file!
import java.io.*;
import java.util.*;

public class C630{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         int rep = n/m;
         int answer = 0;
         for(int k = 0; k < m/2; k++){
            
            int[] freq = new int[26];
            for(int j = 0; j < rep; j++){
               freq[array[k+j*m]-'a']++;
               freq[array[m-k-1+j*m]-'a']++;
            }
            
            int max = 0;
            for(int j = 0; j < 26; j++){
               max = Math.max(freq[j],max);
            }
            
            answer += 2*rep-max;
            
         }
         
         //middle
         if(m%2 == 1){
            
            int[] freq = new int[26];
            
            for(int j = 0; j < rep; j++){
               freq[array[m/2+j*m]-'a']++;
            }
            int max = 0;
            for(int j = 0; j < 26; j++){
               max = Math.max(freq[j],max);
            }
            
            answer += rep-max;
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}