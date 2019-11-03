//make sure to make new file!
import java.io.*;
import java.util.*;

public class D67{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
         int n = Integer.parseInt(f.readLine());
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         st = new StringTokenizer(f.readLine());
         int[] sol = new int[n];
         
         for(int k = 0; k < n; k++){
            sol[k] = Integer.parseInt(st.nextToken());
         }
         
         ArrayList<Seg> segs = new ArrayList<Seg>();
         //find sorted segments
         int k = 0;
         while(k < n-1){
            //find sorted segment starting at k
            int i = k;
            while(i < n-1){
               if(sol[i] <= sol[i+1]){
                  i++;
               } else {
                  segs.add(new Seg(k,i));
                  k = i+1;
                  break;
               }
            }
            if(i == n-1){
               segs.add(new Seg(k,i));
               break;
            }
         }
         if(segs.get(segs.size()-1).r < n-1){
            segs.add(new Seg(n-1,n-1));
         }
         
         if(check(segs,array,sol,n)){
            out.println("YES");
         } else {
            out.println("NO");
         }
         out.flush();
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(ArrayList<Seg> segs, int[] a, int[] b,int n){
      for(Seg s : segs){
         
         int[] freqa = new int[n+1];
         int[] freqb = new int[n+1];
         
         for(int k = s.l; k <= s.r; k++){
            freqa[a[k]]++;
            freqb[b[k]]++;
         }
         
         for(int k = 1; k <= n; k++){
            if(freqa[k] != freqb[k]) return false;
         }
      }
      return true;
   }
   
   
   public static class Seg{
      int l;
      int r;
      public Seg(int a, int b){
         l = a;
         r = b;
      }
   }
   
      
}