//make sure to make new file!
import java.io.*;
import java.util.*;

public class C618b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] pow2 = new long[33];
      pow2[0] = 1;
      for(int k = 1; k < 33; k++){
         pow2[k] = pow2[k-1]*2;
      }
      
      
      int[] freqs = new int[33];
      
      long[] array = new long[n];
      String[] binary = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         binary[k] = Integer.toBinaryString((int)array[k]);
         
         for(int j = 0; j < binary[k].length(); j++){
            if(binary[k].charAt(j) == '1'){
               freqs[binary[k].length()-1-j]++;
            }
         }
      }
      
      
      long max = -1;
      int index = -1;
      
      for(int k = 0; k < n; k++){
         //find or of rest
         
         long restor = 0;
         for(int j = 0; j < 33; j++){
            if(freqs[j] > 0 && !(binary[k].length()-1-j >= 0 && binary[k].charAt(binary[k].length()-1-j) == '1' && freqs[j] == 1)){
               restor += pow2[j];
            }
         }
         
         
         if((array[k] | restor) - restor > max){
            max = (array[k] | restor) - restor;
            index = k;
         }
      }
      
           
      long temp = array[index];
      array[index] = array[0];
      array[0] = temp;
      
      StringJoiner sb = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sb.add(""+array[k]);
      }
      out.println(sb.toString());
      
      
   
      
      
      
      
         
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   static int setBitNumber(int n) 
    { 
  
        int k = (int)(Math.log(n) / Math.log(2)); 
  
        return k;
    } 
   
   public static int countSetBits(int n) 
   { 
      if (n == 0) 
         return 0; 
      
      else
      
         return (n & 1) + countSetBits(n >> 1); 
   } 
  
      
}