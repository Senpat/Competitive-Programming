//make sure to make new file!
import java.io.*;
import java.util.*;

public class C67{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Fact> pq = new PriorityQueue<Fact>();
      ArrayList<Fact> alist = new ArrayList<Fact>();
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int t = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         if(t==1){
            pq.add(new Fact(t,l,r));
         } else {
            alist.add(new Fact(t,l,r));
         }
         
      }
      
      int[] answer = new int[n+1];
      for(int k = 1; k <= n; k++){
         answer[k] = n-k+1;
      }
      
      while(!pq.isEmpty()){
         Fact ft = pq.poll();
         
         if(answer[ft.l] < 1005){
            for(int k = ft.l; k <= ft.r; k++){
               answer[k] = k-ft.l+2001;
            }
         } else {
            for(int k = ft.l; k <= ft.r; k++){
               answer[k] = k-ft.l+answer[ft.l];
            }
         }
      }
      
      for(Fact ft : alist){
         if(!check(ft,answer)){
            out.println("NO");
            out.close();
            System.exit(0);
         }
      }
      
      out.println("YES");
      for(int k = 1; k <= n; k++){
         out.print(answer[k] + " ");
      }
   
      
      
      
      
      out.close();
   }
   
   public static boolean check(Fact ft, int[] array){
      for(int k = ft.l; k < ft.r; k++){
         if(array[k] > array[k+1]) return true;
      }
      return false;
   }
   
   public static class Fact implements Comparable<Fact>{
      int t;
      int l;
      int r;
      public Fact(int a, int b, int c){
         t = a;
         l = b;
         r = c;
      }
      public int compareTo(Fact f){
         return l-f.l;
      }
   }
      
}