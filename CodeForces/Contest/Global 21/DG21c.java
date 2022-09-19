//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG21c{

   public static int[] tmin;
   public static int[] tmax;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] indexof = new int[n+1];
         for(int k = 0; k < n; k++){
            indexof[array[k]] = k;
         }
         
         int[] ptr = new int[n];
         Arrays.fill(ptr,-1);
      
         tmin = new int[4*n+10];
         tmax = new int[4*n+10];
         Arrays.fill(tmin,Integer.MAX_VALUE);
         Arrays.fill(tmax,Integer.MIN_VALUE);
         
         int[] maxindexof = new int[n+1];
         //down to up
         updatemax(1,0,n,array[n-1],array[n-1]);
         maxindexof[array[n-1]] = array[n-1];
      
         int[] minindexof = new int[n+1];
         //up to down
         updatemin(1,0,n,array[n-1],array[n-1]);
         minindexof[array[n-1]] = array[n-1];
         
         for(int k = n-2; k >= 0; k--){
            int querymax = max(1,0,n,array[k],n);
            int querymin = min(1,0,n,0,array[k]);
            
            //max or min
            boolean domax = false;
            if(querymin == Integer.MAX_VALUE) domax = true;
            else if(querymax == Integer.MIN_VALUE) domax = false;
            else {
               if(indexof[querymin] < indexof[querymax]) domax = false;
               else domax = true;
            }
            
            if(domax){
               ptr[k] = indexof[querymax];
               
               updatemax(1,0,n,maxindexof[querymax],Integer.MIN_VALUE);
               updatemax(1,0,n,array[k],querymax);
               maxindexof[querymax] = array[k];
               
               updatemin(1,0,n,array[k],array[k]);
               minindexof[array[k]] = array[k];
            } else {
               ptr[k] = indexof[querymin];
            
               updatemin(1,0,n,minindexof[querymin],Integer.MAX_VALUE);
               updatemin(1,0,n,array[k],querymin);
               minindexof[querymin] = array[k];
               
               updatemax(1,0,n,array[k],array[k]);
               maxindexof[array[k]] = array[k];
            }
         }
      
         int answer = 0;
         int i = 0;
         while(i < n-1){
            answer++;
            i = ptr[i];
         }
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
   public static int max(int v, int tl, int tr, int l, int r) {
      if (l > r) 
         return Integer.MIN_VALUE;
      if (l == tl && r == tr) {
         return tmax[v];
      }
      int tm = (tl + tr) / 2;
      return Math.max(max(v*2, tl, tm, l, Math.min(r, tm)),max(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void updatemax(int v, int tl, int tr, int pos, int new_val) {
      if (tl == tr) {
         tmax[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            updatemax(v*2, tl, tm, pos, new_val);
         else
            updatemax(v*2+1, tm+1, tr, pos, new_val);
         tmax[v] = Math.max(tmax[v*2],tmax[v*2+1]);
      }
   }
   
   
   
   
   
   //to call: v=1, tl = 0, tr = n-1
   public static int min(int v, int tl, int tr, int l, int r) {
      if (l > r) 
         return Integer.MAX_VALUE;
      if (l == tl && r == tr) {
         return tmin[v];
      }
      int tm = (tl + tr) / 2;
      return Math.min(min(v*2, tl, tm, l, Math.min(r, tm)),min(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void updatemin(int v, int tl, int tr, int pos, int new_val) {
      if (tl == tr) {
         tmin[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            updatemin(v*2, tl, tm, pos, new_val);
         else
            updatemin(v*2+1, tm+1, tr, pos, new_val);
         tmin[v] = Math.min(tmin[v*2],tmin[v*2+1]);
      }
   }
   
}