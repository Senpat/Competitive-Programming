//make sure to make new file!
import java.io.*;
import java.util.*;

public class E170{

   public static int MAX = 1000000001;
   public static int MAX_N = 200005;
   public static int[] t;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Infant[] infants = new Infant[n+1];
      
      int MAXB = 200005;
      TreeSet<Infant>[] tsets = new TreeSet[MAXB];
      
      for(int k = 0; k < MAXB; k++) tsets[k] = new TreeSet<Infant>();
      
      for(int k = 1; k <= n; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         infants[k] = new Infant(k,a,b);
         tsets[b].add(infants[k]);
      }
      
      
      t = new int[4*MAX_N];
      Arrays.fill(t,MAX);      //or build
      
      for(int k = 1; k < MAXB; k++){
         if(!tsets[k].isEmpty()){
            update(1,1,MAXB-1,k,tsets[k].last().rating);
            //out.println(k + " " + (tsets[k].last().rating));
         }
      }
      for(int qq = 0; qq < q; qq++){
         st = new StringTokenizer(f.readLine());
      
         int c = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         
         int moveout = infants[c].kindergarden;
         
         infants[c].kindergarden = d;
         tsets[moveout].remove(infants[c]);
         tsets[d].add(infants[c]);
         
         if(tsets[moveout].isEmpty()){
            update(1,1,MAXB-1,moveout,MAX);
         } else {
            update(1,1,MAXB-1,moveout,tsets[moveout].last().rating);
         }
         
         update(1,1,MAXB-1,d,tsets[d].last().rating);
         
         int answer = min(1,1,n,1,n);
         out.println(answer);
      }
      
      out.close();
   }
   
   public static class Infant implements Comparable<Infant>{
      int index;
      int rating;
      int kindergarden;
      public Infant(int a, int b, int c){
         index = a;
         rating = b;
         kindergarden = c;
      }
      public int compareTo(Infant i){
         return rating-i.rating;
      }
      public int hashCode(){
         return index;
      }
      public boolean equals(Object o){
         Infant i = (Infant)o;
         return index == i.index;
      }
   }
   
      //build currently unconfirmed working
   public static void build(int[] a, int v, int tl, int tr) {
      if (tl == tr) {
         t[v] = a[tl];
      } else {
         int tm = (tl + tr) / 2;
         build(a, v*2, tl, tm);
         build(a, v*2+1, tm+1, tr);
         t[v] = Math.min(t[v*2],t[v*2+1]);
      }
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static int min(int v, int tl, int tr, int l, int r) {
      if (l > r) 
         return MAX;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return Math.min(min(v*2, tl, tm, l, Math.min(r, tm)),min(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, int new_val) {
      if (tl == tr) {
         t[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val);
         else
            update(v*2+1, tm+1, tr, pos, new_val);
         t[v] = Math.min(t[v*2],t[v*2+1]);
      }
   }
   
      
}