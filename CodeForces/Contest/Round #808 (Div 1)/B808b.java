//make sure to make new file!
import java.io.*;
import java.util.*;

public class B808b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         TreeMap<Integer,Integer> tmap1 = new TreeMap<Integer,Integer>();
         TreeMap<Integer,Integer> tmap2 = new TreeMap<Integer,Integer>();
         
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            add(tmap1,array[k],1);
         }
         
         int answer = 0;
         for(int rep = 0; rep < n-1; rep++){
            
            int last = -1;
            for(int i : tmap1.keySet()){
               int value = tmap1.get(i);
               if(last == -1){
                  if(value-1 > 0) add(tmap2,0,value-1);
                  last = i;
               } else {
                  add(tmap2,i-last,1);
                  if(value-1 > 0) add(tmap2,0,value-1);
                  last = i;
               }
            }
            
            tmap1 = new TreeMap<Integer,Integer>();
            for(int i : tmap2.keySet()){
               tmap1.put(i,tmap2.get(i));
            }
            tmap2 = new TreeMap<Integer,Integer>();
            
            if(rep == n-2){
               answer = tmap1.lastKey();
            }

         }
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static void add(TreeMap<Integer,Integer> tmap, int key, int d){
      if(!tmap.containsKey(key)){
         tmap.put(key,d);
      } else {
         tmap.put(key,tmap.get(key)+d);
      }
   }
   
      
}