//make sure to make new file!
import java.io.*;
import java.util.*;

public class A40{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      
      int n = s.length;
      
      int[] array = new int[n+1];
      Arrays.fill(array,-1);
      
      HashSet<Integer> hset = new HashSet<Integer>();
      
      if(s[0] == '<'){
         array[0] = 0;
         hset.add(0);
      }
      
      if(s[n-1] == '>'){
         array[n] = 0;
         hset.add(n);
      }
      
      for(int k = 1; k < n; k++){
         if(s[k-1] == '>' && s[k] == '<'){
            array[k] = 0;
            hset.add(k);
         }
      }
      
      
      for(int i : hset){
         int index = i-1;
         //go left
         while(index >= 0 && s[index] == '>'){
            if(array[index] != -1){
               array[index] = Math.max(array[index],i-index);
               break;
            }
            array[index] = i-index;
            index--;
         }
         
         index = i+1;
         while(index <= n && s[index-1] == '<'){
            if(array[index] != -1){
               array[index] = Math.max(array[index],index-i);
               break;
            }
            array[index] = index-i;
            index++;
         }
      }
      
      long answer = 0;
      for(int k = 0; k <= n; k++){
         answer += (long)array[k];
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}