//Portal Trapping
import java.io.*;
import java.util.*;

public class Fb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Portal[] array = new Portal[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         double a = Double.parseDouble(st.nextToken());
         double b = Double.parseDouble(st.nextToken());
      
         array[k] = new Portal((int)a,(int)b,Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
      }
      
      Arrays.sort(array);
      
      for(int k = 0; k < q; k++){
      
         double query = Double.parseDouble(f.readLine());
         
         /*
         int low = 0;
         int high = n-1;
         int mid = (high + low)/2;
         
         boolean found = false;
         
         while(high >= low){
            mid = (high + low)/2;
            
            if(array[mid].distance == query){
               out.println(array[mid].x + " " + array[mid].y);
               found = true;
               break;
            }
            
            if(array[mid].distance > query){
               high = mid-1;
            } else {
               low = mid +1;
            }
         }*/
         
         int mid = Math.abs(Arrays.binarySearch(array,new Portal(0,0,query)));
                  
         //out.println(mid);
         
         if(mid == 1){
            out.println(array[0].x + " " + array[0].y);
            continue;
         }
         if(mid >= array.length + 1){
            out.println(array[n-1].x + " " + array[n-1].y);
            continue;
         }
         
         
         if(mid-2 >= 0 && Math.abs(array[mid-2].distance -query) > Math.abs(query-array[mid-1].distance)){
            out.println(array[mid-1].x + " " + array[mid-1].y);
         } else {
            out.println(array[mid-2].x + " " + array[mid-2].y);
         }
         
      }
            
         
         
         
      
         
         
      
      
      
      out.close();
   }
   
   public static class Portal implements Comparable<Portal>, Comparator<Portal>{
      int x;
      int y;
      double distance;
      public Portal(int a, int b,double d){
         x=a;
         y=b;
         distance = d;
      }
      @Override
      public int compareTo(Portal p){
         return Double.compare((Double)distance,(Double)p.distance);
      }
      @Override
      public int compare(Portal p1, Portal p2){
         return Double.compare((Double)p1.distance,(Double)p2.distance);
      }
      
      public String toString(){
         return distance + " " + x + " " + y;
      }
   }
}