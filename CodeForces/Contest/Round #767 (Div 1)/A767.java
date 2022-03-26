//make sure to make new file!
import java.io.*;
import java.util.*;

public class A767{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0;  k< n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] sufmex = new int[n];
         int[] mexhelp = new int[n+5];
         int mexi = 0;
         
         for(int k = n-1; k >= 0; k--){
            mexhelp[array[k]]++;
            while(mexhelp[mexi] != 0) mexi++;
            sufmex[k] = mexi;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int i = 0;
         while(i < n){
            int target = sufmex[i];
            if(target == 0){
               answer.add(0);
               i++;
               continue;
            }
            
            HashSet<Integer> hset = new HashSet<Integer>();
            int i2 = i;
            while(i2 < n){
               if(array[i2] < target) hset.add(array[i2]);
               
               if(hset.size() == target) break;
               i2++;
            }
            
            answer.add(target);
            i = i2+1;
            
         
         }
         
         out.println(answer.size());
         StringJoiner sj = new StringJoiner(" ");
         for(int ai : answer){
            sj.add("" + ai);
         }
         out.println(sj.toString());
         
      }
      
      
      
      
      out.close();
   }
   
      
}