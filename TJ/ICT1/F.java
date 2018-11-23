//Portal Trapping
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      ArrayList<Portal> alist = new ArrayList<Portal>(n);
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         alist.add(new Portal(a,b,Math.pow(a,2) + Math.pow(b,2)));
      }
      
      Collections.sort(alist);
      
      for(int k = 0; k < q; k++){
         if(n==1){
            out.println(alist.get(0));
            continue;
         }
      
         double query = Double.parseDouble(f.readLine());
         query = Math.pow(query,2);
         int i = Collections.binarySearch(alist,new Portal(0,0,query));
         //out.println(i);
         if(i >= 0){
            out.println(alist.get(i));
         } else {
            i = (i+1)*-1;
            
            //must be i or i-1
            
            if(i > 0 && Math.abs(alist.get(i-1).distance-query) < Math.abs(alist.get(i).distance-query)){
               out.println(alist.get(i-1));
            } else {
               out.println(alist.get(i));
            }
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
         return x + " " + y;
      }
   }
}