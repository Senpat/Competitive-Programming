//make sure to make new file!
import java.io.*;
import java.util.*;

public class B781{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
            if(!hmap.containsKey(array[k])){
               hmap.put(array[k],1);
            } else {
               hmap.put(array[k],hmap.get(array[k])+1);
            }
         }
         
         //get maxfreq
         int maxfreq = 0;
         for(int i : hmap.keySet()){
            maxfreq = Math.max(maxfreq,hmap.get(i));
         }
         
         int move = n-maxfreq;
         int gen = 0;
         int g = maxfreq;
         int i = 0;
         
         while(gen < n-maxfreq){
            gen += g;
            g *= 2;
            i++;
         }
         
         int answer = move+i;
         out.println(answer);
        
      

      }
      
      
      
      
      out.close();
   }
   
      
}