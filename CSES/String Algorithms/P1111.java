//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1111{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int[] aodd = manacher(array);
      
      int oddmax = 0;
      int oddstart = -1;
      for(int k = 0; k < n; k++){
         if(aodd[k] > oddmax){
            oddmax = aodd[k];
            oddstart = k-aodd[k]/2;
         }
      }
      
      char[] arrayeven = new char[2*n+1];
      for(int k = 0; k < n; k++){
         arrayeven[2*k+1] = array[k];
      }
      for(int k = 0; k < 2*n+1; k+=2){
         arrayeven[k] = '|';
      }
      
      int[] aeven = manacher(arrayeven);
      
      int evenmax = 0;
      int evenstart = 0;
      for(int k = 0; k < 2*n+1; k++){
         if(aeven[k]/2 > evenmax){
            evenmax = aeven[k]/2;
            evenstart = (k-aeven[k]/2)/2;
         }
      }
      
      int start = oddstart;
      if(evenmax > oddmax){
         start = evenstart;
      }
      
      StringJoiner sj = new StringJoiner("");
      for(int k = 0; k < Math.max(oddmax,evenmax); k++){
         sj.add("" + array[start+k]);
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   //gets the longest palindrome at every center
   public static int[] manacher(char[] s){
      int n = s.length;
      
      int[] answer = new int[n];
      
      int r = -1;
      int rcenter = -1;
      
      int center = 0;
      while(center < n){
         int len = 1;
         int i = 1;
         
         if(center <= r){
            int rightlen = (r-center)*2+1;
            //copy from other side
            answer[center] = answer[rcenter - (center-rcenter)];
            
            if(answer[center] > rightlen){
               answer[center] = rightlen;
            }
            if(answer[center] != rightlen){
               center++;
               continue;
            }
            
            len = rightlen;
            i = r-center+1;
         }
      
         while(center-i >= 0 && center+i < n && s[center-i] == s[center+i]){
            i++;
            len+=2;
         }
         
         answer[center] = len;
         
         rcenter = center;
         r = center+len/2;
         
         center++;
         
      }
      
      return answer;      
   }
   
      
}