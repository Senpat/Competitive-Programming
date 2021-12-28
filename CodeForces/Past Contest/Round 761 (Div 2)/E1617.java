//make sure to make new file!
import java.io.*;
import java.util.*;
//SELF
public class E1617{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      
      TreeMap<Integer,Pair> tmap = new TreeMap<Integer,Pair>();
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         tmap.put(array[k],new Pair(k+1,0));
      }
      
      int answer = 0;
      int ai1 = -1;
      int ai2 = -1;
      
      while(tmap.size() > 1){
         int i = tmap.lastKey();
         Pair p = tmap.get(i);
         tmap.remove(i);
         
         int next = nextp2(i)-i;
         
         if(tmap.containsKey(next)){
            Pair nextp = tmap.get(next);
            if(p.freq+1 + nextp.freq > answer){
               answer = p.freq+1 + nextp.freq;
               ai1 = p.index;
               ai2 = nextp.index;
            }
            
            if(p.freq+1 > nextp.freq){
               //replace with larger
               tmap.put(next,new Pair(p.index,p.freq+1));
            }
         } else {
            tmap.put(next,new Pair(p.index,p.freq+1));
         }
      }
      
      out.println(ai1 + " " + ai2 + " " + answer);

      
      
      
      
      out.close();
   }
   
   //finds the next biggest power of 2 (x >= 1)
   public static int nextp2(int x){
      int i = 1;
      while(i < x){
         i <<= 1;
      }
      
      return i;
   }
   
   public static class Pair{
      int index;
      int freq;
      public Pair(int a, int b){
         index = a;
         freq = b;
      }
   }
   
      
}