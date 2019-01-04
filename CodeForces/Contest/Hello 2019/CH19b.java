//make sure to make new file!
import java.io.*;
import java.util.*;

public class CH19b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      int[][] pos = new int[500001][3];//0 is left, 1 is both, 2 is right
      
      int zerol = 0;          //left
      int zerob = 0;          //both
      int zeror = 0;          //right
      
      int[][] neg = new int[500001][3];
      
      HashSet<Integer> seen = new HashSet<Integer>();
      
      for(int i = 0; i < n; i++){
         String s = array[i];
         int sum = 0;
         
         boolean bad = false;
         
         for(int k = 0; k < s.length(); k++){
            if(s.charAt(k) == '('){
               sum--;
            } else {
               sum++;
            }
            if(sum == 0 && s.charAt(k) == '('){
               bad = true;
               break;
            }
         }
         
         if(sum > 0){
            if(s.charAt(0) == ')' && s.charAt(s.length() - 1) == '(') continue;
            else if(s.charAt(0) == ')') pos[sum][2]++;
            else if(s.charAt(s.length() -1) == '(') pos[sum][0]++;
            else pos[sum][1]++;
            
            seen.add(sum);
            
         } else if(sum < 0){
            if(s.charAt(0) == ')' && s.charAt(s.length() - 1) == '(') continue;
            else if(s.charAt(0) == ')') neg[sum*-1][2]++;
            else if(s.charAt(s.length() -1) == '(') neg[sum*-1][0]++;
            else neg[sum*-1][1]++;
         } else {             //sum == 0
            if(bad) continue;
            if(s.charAt(0) == ')' && s.charAt(s.length() - 1) == '(') continue;
            else if(s.charAt(0) == ')') zeror++;
            else if(s.charAt(s.length() -1) == '(') zerol++;
            else zerob++;
         }
      }
      
      int answer = 0;
      for(int i : seen){
         answer += Math.min(pos[i][2],neg[i][0]);
      }
      
      //add zero
      //add l + r pairs
      answer += Math.min(zerol,zeror);
      answer += zerob/2;
      
      
      
      out.println(answer);
         
      
      
      
      
      out.close();
   }
   
}