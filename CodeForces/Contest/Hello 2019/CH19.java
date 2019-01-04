//make sure to make new file!
import java.io.*;
import java.util.*;

public class CH19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      HashMap<Integer,Integer> pos = new HashMap<Integer,Integer>();
      
      int zerol = 0;          //left
      int zerob = 0;          //both
      int zeror = 0;          //right
      
      HashMap<Integer,Integer> neg = new HashMap<Integer,Integer>();
      
      for(int i = 0; i < n; i++){
         String s = array[i];
         int sum = 0;
         
         for(int k = 0; k < s.length(); k++){
            if(s.charAt(k) == '('){
               sum--;
            } else {
               sum++;
            }
         }
         
         if(sum > 0){
            if(pos.containsKey(sum)){
               pos.put(sum,pos.get(sum)+1);
            } else {
               pos.put(sum,1);
            }
         } else if(sum < 0){
            if(neg.containsKey(sum)){
               neg.put(sum,neg.get(sum)+1);
            } else {
               neg.put(sum,1);
            }
         } else {             //sum == 0
            
            if(s.charAt(0) == ')' && s.charAt(s.length() - 1) == '(') continue;
            else if(s.charAt(0) == ')') zeror++;
            else if(s.charAt(s.length() -1) == '(') zerol++;
            else zerob++;
         }
      }
      
      int answer = 0;
      for(int p : pos.keySet()){
         int ng = neg.containsKey(-1*p) ? neg.get(-1*p) : 0;
         int ps = pos.get(p);
         
         answer += Math.min(ng,ps);
      }
      
      //add zero
      //add l + r pairs
      answer += Math.min(zerol,zeror);
      answer += zerob/2;
      
      
      
      out.println(answer);
         
      
      
      
      
      out.close();
   }
   
}