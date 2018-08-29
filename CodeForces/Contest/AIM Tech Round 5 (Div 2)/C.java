//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine())+1;
      
      int[] x1 = new int[n];
      int[] y1 = new int[n];
      int[] x2 = new int[n];
      int[] y2 = new int[n];
      
      TreeSet<Integer> lx = new TreeSet<Integer>();
      TreeSet<Integer> rx = new TreeSet<Integer>();
      HashSet<Integer> dupl = new HashSet<Integer>();
      HashSet<Integer> dupr = new HashSet<Integer>();
      
      
      for(int k = 1; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());      
         x1[k] = Integer.parseInt(st.nextToken());      
         y1[k] = Integer.parseInt(st.nextToken());      
         x2[k] = Integer.parseInt(st.nextToken());      
         y2[k] = Integer.parseInt(st.nextToken());
         
         if(lx.contains(x1[k])) dupl.add(x1[k]);
         if(rx.contains(x2[k])) dupr.add(x2[k]);
         lx.add(x1[k]);
         rx.add(y2[k]);
      }
      
      int remove = -1;
      for(int k = 1; k < n; k++){
         if(!dupl.contains(x1[k])) lx.remove(x1[k]);
         if(!dupr.contains(x2[k])) rx.remove(x2[k]);
         if(lx.last() >= rx.first()){
            remove = k;
            k = n+1;
         } else {
            lx.add(x1[k]);
            rx.add(x2[k]);
         }
      }
      
      for(int k = 1; k < n; k++){
         if(k!=remove){
            check(x1[k],y1[k],k,remove,x1,x2,y1,y2,out);
            check(x1[k],y2[k],k,remove,x1,x2,y1,y2,out);
            check(x2[k],y1[k],k,remove,x1,x2,y1,y2,out);
            check(x2[k],y2[k],k,remove,x1,x2,y1,y2,out);
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static void check(int x, int y, int a, int b, int[] x1, int[] x2, int[] y1, int[] y2,PrintWriter out){
      for(int k = 1; k < x1.length; k++){
         if(k!=a && k!=b){
            if(x < x1[k] || x > x2[k] || y < y1[k] || y > y2[k]) return;
         }
      }
      out.println(x + " " + y);
      out.close();
      System.exit(0);
   }
   
}