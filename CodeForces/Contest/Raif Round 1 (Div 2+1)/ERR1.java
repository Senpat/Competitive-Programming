//make sure to make new file!
import java.io.*;
import java.util.*;

public class ERR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      
      long answer = 0L;
      
      PriorityQueue<Place> pq = new PriorityQueue<Place>(10, Collections.reverseOrder());
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         answer += array[k]*array[k];
         pq.add(new Place(array[k]));
      }
      
      for(int k = n; k < m; k++){
         Place p = pq.poll();
         answer -= p.nextsplit;
         if(p.x < p.i-1){
            //add it back
            p.x++;
            p.cursplit = p.cursplit-p.nextsplit;
            p.nextsplit = p.cursplit-split(p.i,p.x+1);
            pq.add(p);
         }
      }
      
      out.println(answer);
         
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long split(long i, long x){
      long numi = i%x;
      long ix = i/x;
      return ((ix+1)*(ix+1)*numi + ix*ix*(x-numi));
   }
    
   public static class Place implements Comparable<Place>{
      long i;         //starting number
      long x;         //splits so far
      long cursplit;    //current value of split
      long nextsplit;   //improvement if split again
      
      public Place(long a){
         i = a;
         x = 1L;
         cursplit = i*i;
         nextsplit = cursplit-split(i,2L);
      }
      
      //compare by nextsplit
      public int compareTo(Place p){
         if(nextsplit > p.nextsplit) return 1;
         if(nextsplit < p.nextsplit) return -1;
         return 0;
      }
      public String toString(){
         return i + " " + x + " " + cursplit+ " " + nextsplit;
      }
   }
   
      
}