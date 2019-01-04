//make sure to make new file!
import java.io.*;
import java.util.*;

public class CH19c{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      int[] pos = new int[500001];
      
      int zero = 0;
      
      int[] neg = new int[500001];
      
      HashSet<Integer> seen = new HashSet<Integer>();
      
      for(int i = 0; i < n; i++){
         String s = array[i];
         int sum = 0;
         
         boolean bad = false;
         
         int max = 0;
         int min = 0;
         boolean ispos = false;
         
         for(int k = 0; k < s.length(); k++){
            if(s.charAt(k) == '('){
               sum--;
            } else {
               sum++;
            }
            
            max = Math.max(sum,max);
            min = Math.min(sum,min);
            
            if(sum > 0) ispos = true;
            
            if(sum == 0 && s.charAt(k) == '('){
               bad = true;
            }
            
         }
         
         if(sum > 0){
         
            if(max > sum) continue;
         
            pos[sum] ++;
         
            seen.add(sum);
            
         } else if(sum < 0){
         
            if(ispos) continue;
            
            neg[-1*sum] ++;
         
         } else {             //sum == 0
            if(bad) continue;
            zero++;
         }
      }
      
      int answer = 0;
      for(int i : seen){
         answer += Math.min(pos[i],neg[i]);
      }
      
      //add zero
      answer += zero/2;
      
      
      
      out.println(answer);
         
      
      
      
      
      out.close();
   }
   
}