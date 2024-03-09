//make sure to make new file!
import java.io.*;
import java.util.*;

public class B2896{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int P = 30;
      HashMap<Long,Integer> p2 = new HashMap<Long,Integer>();
      for(int i = 0; i <= P; i++){
         p2.put(1L << i,i);
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         long nl = (long)n;
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long sum = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
         }
         
         if(sum % nl != 0){
            out.println("No");
            continue;
         }
         
         long avg = sum / nl;
         
         int[] flexin = new int[P+1];
         int[] flexout = new int[P+1];
         int[] notflexin = new int[P+1];
         int[] notflexout = new int[P+1];
         
         boolean fail = false;
         
         for(int k = 0; k < n; k++){
            if(avg == array[k]){
               continue;
            }
            
            long diff = Math.abs(avg-array[k]);
            
            if(p2.containsKey(diff)){
               if(array[k] > avg){
                  flexout[p2.get(diff)]++;
               } else {
                  flexin[p2.get(diff)]++;
               }
            } else {
               boolean found = false;
               for(int i = 0; i <= P; i++){
                  long p = (1L << i);
                  if(p2.containsKey(diff + p)){
                     found = true;
                     
                     if(avg > array[k]){
                        //less -> in bigger, out smaller
                        notflexin[p2.get(diff+p)]++;
                        notflexout[i]++;
                     } else {
                        //more -> in smaller, out bigger
                        notflexin[i]++;
                        notflexout[p2.get(diff+p)]++;
                     }
                     
                     break;
                  }
               }
            
               if(!found){
                  fail = true;
                  break;
               }
            }
         }
         
         //cancel all
         for(int i = P; i >= 0; i--){
            cancel(flexin,flexout,i);
            cancel(notflexin,notflexout,i);
            
            int tin = flexin[i] + notflexin[i];
            int tout = flexout[i] + notflexout[i];
            int min = Math.min(tin,tout);
            
            tin -= min;
            tout -= min;
            
            if(tin > 0){
               //get help from flexout
               if(i > 0 && flexout[i-1] >= tin){
                  flexout[i-1] -= tin;
                  notflexin[i-1] += tin;
                  //notflexout[i] += tin 
               } else {
                  fail = true;
                  break;
               }
            } else if(tout > 0){
               //get help from flexin
               if(i > 0 && flexin[i-1] >= tout){
                  flexin[i-1] -= tout;
                  notflexout[i-1] += tout;
                  //notflexin[i] += tout;
               } else {
                  fail = true;
                  break;
               }
            }
         }
         
         
         if(fail){
            out.println("No");
         } else {
            out.println("Yes");
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
   public static void cancel(int[] array1, int[] array2, int i){
      int min = Math.min(array1[i], array2[i]);
      array1[i] -= min;
      array2[i] -= min;
   }
      
}