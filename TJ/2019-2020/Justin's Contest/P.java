//make sure to make new file!
import java.io.*;
import java.util.*;

public class P{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      
      HashMap<Integer,Long> freq1 = new HashMap<Integer,Long>();
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         freq1.put(array[k],1L);
      }
      
      HashMap<Integer,Long> freq2 = new HashMap<Integer,Long>();
      
      for(int i : freq1.keySet()){
         for(int k = 0; k < n; k++){
            if(array[k] + i <= m){
               if(!freq2.containsKey(array[k]+i)){
                  freq2.put(array[k]+i,freq1.get(i));
               } else {
                  freq2.put(array[k]+i,freq2.get(array[k]+i)+freq1.get(i));
               }
            }
         }
      }

      HashMap<Integer,Long> freq3 = new HashMap<Integer,Long>();
      
      for(int i : freq2.keySet()){
         for(int k = 0; k < n; k++){
            if(array[k] + i <= m){
               if(!freq3.containsKey(array[k]+i)){
                  freq3.put(array[k]+i,freq2.get(i));
               } else {
                  freq3.put(array[k]+i,freq3.get(array[k]+i)+freq2.get(i));
               }
            }
         }
      }
      
      HashMap<Integer,Long> freq4 = new HashMap<Integer,Long>();
      
      for(int i : freq3.keySet()){
         for(int k = 0; k < n; k++){
            if(array[k] + i <= m){
               if(!freq4.containsKey(array[k]+i)){
                  freq4.put(array[k]+i,freq3.get(i));
               } else {
                  freq4.put(array[k]+i,freq4.get(array[k]+i)+freq3.get(i));
               }
            }
         }
      }
      
      long answer = 0L;
      
      if(freq4.containsKey(m)){
         answer = freq4.get(m);
      }
      
      out.println(answer);
      
      out.close();
   }
   
      
}