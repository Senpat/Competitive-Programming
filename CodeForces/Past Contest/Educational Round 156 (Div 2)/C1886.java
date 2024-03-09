//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong order for cbba
public class C1886{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         long x = Long.parseLong(f.readLine());
         
         long size = (long)n;
         int si = 0;
         
         while(x > size){
            x -= size;
            si++;
            size--;
         }
         
         //get order that you remove all of the characters
         boolean[] rem = new boolean[n];
         rem[n-1] = false;
         for(int k = n-2; k >= 0; k--){
            rem[k] = (array[k] > array[k+1] || (array[k] == array[k+1] && rem[k+1]));
         }
         
         int[] order = new int[n];
         int l = 0;
         int r = n-1;
         for(int k = 0; k < n; k++){
            if(rem[k]){
               order[k] = l++;
            } else {
               order[k] = r--;
            }
            
            //out.print(order[k] + " ");
         }
         //out.println();
         
         char c = '?';
         for(int k = 0; k < n; k++){
            if(order[k] >= si){
               x--;
               if(x == 0){
                  c = array[k];
                  break;
               }
            }
         }
         
         out.print(c);
               
      

      }
      
      
      
      
      out.close();
   }
   
      
}