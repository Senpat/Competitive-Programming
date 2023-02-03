//make sure to make new file!
import java.io.*;
import java.util.*;

public class D137{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
   
      int n = Integer.parseInt(f.readLine());
      
      char[] cin = f.readLine().toCharArray();
      int[] array = new int[n];
         
      int first1 = -1;
      boolean isleading = false;
      int leading = 0;
      for(int k = 0; k < n; k++){
         array[k] = Character.getNumericValue(cin[k]);
         if(array[k] == 1 && first1 == -1){
            first1 = k;
            isleading = true;
         }
         
         if(isleading && array[k] == 1) leading++;
         if(array[k] == 0) isleading = false;
      }
         
      if(first1 == -1){
         out.println(0);
         out.close();
         return;
      }
         
      ArrayList<Integer> curmax = new ArrayList<Integer>();
      int curshift = 0;
      
      for(int k = first1; k < n; k++){
         if(array[k] == 0) curmax.add(k);
      }
      
      for(int shift = 1; shift <= leading; shift++){
         ArrayList<Integer> cur = new ArrayList<Integer>();
         for(int k = first1+leading; k < n; k++){
            if((array[k] | array[k-shift]) == 0){
               cur.add(k);
            }
         }
         
         //compare with curmax
         boolean isbigger = false;
         boolean isequal = true;
         for(int k = 0; k < cur.size(); k++){
            if(k < curmax.size() && cur.get(k) > curmax.get(k)){
               isbigger = true;
               isequal = false;
               break;
            }
            
            if(k < curmax.size() && cur.get(k) < curmax.get(k)){
               isbigger = false;
               isequal = false;
               break;
            }
         }
         
         if(isequal && curmax.size() > cur.size()) isbigger = true;
         
         if(isbigger){
            curmax = cur;
            curshift = shift;
         }
      }
      
      //use shift
      ArrayList<Integer> answer = new ArrayList<Integer>();
      for(int k = first1; k < n; k++){
         if(k < first1+leading) answer.add(1);
         else{
            answer.add(array[k] | array[k-curshift]);
         }
      }
      
      StringJoiner sj = new StringJoiner("");
      for(int i : answer){
         sj.add("" + i);
      }
      
      out.println(sj.toString());
      
   
      
      
      
      
      
      out.close();
   }
   
      
}