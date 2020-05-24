//make sure to make new file!
import java.io.*;
import java.util.*;

public class D642{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         int[] answer = new int[n];
         
         Arrays.fill(answer,0);
         
         PriorityQueue<Seg> pq = new PriorityQueue<Seg>();
         pq.add(new Seg(0,n-1));
         int i = 1;
         while(!pq.isEmpty()){
            Seg s = pq.poll();
            
            answer[s.mid] = i;
            
            if(s.mid > s.l){
               pq.add(new Seg(s.l,s.mid-1));
            }
            
            if(s.mid < s.r){
               pq.add(new Seg(s.mid+1,s.r));
            }
            
            i++;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add(""+answer[k]);
         }
         
         out.println(sj.toString());
            
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static class Seg implements Comparable<Seg>{
      int l;
      int r;
      int mid;
      public Seg(int a, int b){
         l = a;
         r = b;
         if((r-l+1)%2 == 1) mid = (r+l)/2;
         else mid = (r+l-1)/2;
      }
      
      public int compareTo(Seg s){
         if(r-l == s.r-s.l) return l-s.l;
         return (s.r-s.l)-(r-l);
      }
   }
   
      
}