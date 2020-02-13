//make sure to make new file!
import java.io.*;
import java.util.*;

public class C618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int index = -1;
      int maxbit = -1;
      int maxbitnum = -1;
      
      for(int k = 0; k < n; k++){
         int bn = setBitNumber(array[k]);
         if(bn > maxbit){
            maxbit = bn;
            maxbitnum = countSetBits(array[k]);
            index = k;
         }
         else if(bn == maxbit){
            int bs = countSetBits(array[k]);
            if(bs > maxbitnum){
               maxbitnum = bs;
               index = k;
            }
         }
      }
      
      int temp = array[index];
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
  
        int k = Integer.toBinaryString(n).length() ;
  
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