//make sure to make new file!
import java.io.*;
import java.util.*;

public class ADS2021{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         String input = f.readLine();
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(input.charAt(k));
         }
         
         int[] closestleft = new int[n];
         int last = -1;
         for(int k = 0; k < n; k++){
            if(array[k] == 1) last = k;
            closestleft[k] = last;
         }
         
         int[] closestright = new int[n];
         last = -1;
         for(int k = n-1; k >= 0; k--){
            if(array[k] == 1) last = k;
            closestright[k] = last;
         }
         
         int[] answer = new int[n];
         for(int k = 0; k < n; k++){
            if(array[k] == 1){
               answer[k] = 1;
               continue;
            }
            
            int nei1 = 0;
            if(k > 0 && array[k-1] == 1) nei1++;
            if(k < n-1 && array[k+1] == 1) nei1++;
            
            if(nei1 == 2){
               answer[k] = 0;
               continue;
            }
            
            if((closestleft[k] != -1 && (k-closestleft[k]<=m)) || (closestright[k] != -1 && (closestright[k]-k<=m))){
               if(closestleft[k] != -1 && closestright[k] != -1 && k-closestleft[k] == closestright[k]-k) answer[k] = 0;
               else answer[k] = 1;
            } else {
               answer[k] = 0;
            }
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}