//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1755{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int[] freq = new int[26];
      for(int k = 0; k < n; k++){
         freq[array[k]-'A']++;
      }
      
      char oddc = '?';
      
      ArrayList<Character> answer = new ArrayList<Character>();
      
      boolean fail = false;
      for(int k = 0; k < 26; k++){
         if(freq[k] % 2 == 1){
            if(oddc == '?') oddc = (char)('A'+k);
            else {
               fail = true;
               break;
            }
         }
         for(int j = 0; j < freq[k]/2; j++){
            answer.add((char)('A'+k));
         }
      
      }
      
      if(fail){
         out.println("NO SOLUTION");
      } else {
         for(int k = 0; k < answer.size(); k++){
            out.print(answer.get(k));
         }
         if(oddc != '?') out.print(oddc);
         for(int k = answer.size()-1; k >= 0; k--){
            out.print(answer.get(k));
         }
         out.println();
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}