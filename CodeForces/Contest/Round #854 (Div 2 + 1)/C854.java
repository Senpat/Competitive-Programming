//make sure to make new file!
import java.io.*;
import java.util.*;

public class C854{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         String s = f.readLine();
         int n = s.length();
         
         if(n == 1){
            out.println(s);
            continue;
         }
         if(n == 2){
            if(s.charAt(0) < s.charAt(1)){
               out.println(s.charAt(1) + "" + s.charAt(0));
            } else {
               out.println(s);
            }
            continue;
         }
         
         //sort string
         int[] freq = new int[26];
         for(int k = 0; k < n; k++){
            freq[s.charAt(k)-'a']++;
         }
         
         char[] array = new char[n];
         int ci = 0;
         for(int k = 0; k < 26; k++){
            int i = freq[k];
            while(i > 0){
               i--;
               array[ci] = (char)(k+'a');
               ci++;
            }
         }
         
         char[] answer = new char[n];
         
         int l = 0;
         int r = n-1;
         int ai = 0;
         
         while(true){
            //1 left
            if(l == r){
               answer[l] = array[ai];
               break;
            }
            //2 left
            if(l == r-1){
               answer[l] = array[ai+1];
               answer[r] = array[ai];
               break;
            }
            //3 left
            if(l == r-2){
               if(array[ai+1] == array[ai+2]){
                  answer[l] = array[ai+2];
                  answer[r] = array[ai+1];
                  answer[l+1] = array[ai];
               } else {
                  answer[l] = array[ai+1];
                  answer[r] = array[ai];
                  answer[l+1] = array[ai+2];
               }
               break;
            }
            
            if(array[ai] == array[ai+1]){
               answer[l] = array[ai];
               answer[r] = array[ai+1];
               ai+=2;
               l++;
               r--;
            } else if(freq[array[ai+1]-'a'] == r-l+1 -1){               //special case: abbbb -> bbabb
               for(int k = l; k <= r; k++){
                  answer[k] = array[ai+1];
               }
               answer[(r+l+1)/2] = array[ai];
               break;
            } else {
               answer[l] = array[ai+1];
               answer[r] = array[ai];
               ai += 2;
               for(int k = l+1; k <= r-1; k++){
                  answer[k] = array[ai];
                  ai++;
               }
               break;
            }
            
         }
         
         String sa = new String(answer);
         out.println(sa);
         
      }
      
      
      
      
      out.close();
   }
   
      
}