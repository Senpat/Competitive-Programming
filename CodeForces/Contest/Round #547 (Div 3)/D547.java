//make sure to make new file!
import java.io.*;
import java.util.*;

public class D547{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String a = f.readLine();
      String b = f.readLine();
      
      PriorityQueue<Pair> lchar = new PriorityQueue<Pair>();
      PriorityQueue<Pair> lq = new PriorityQueue<Pair>();
      PriorityQueue<Pair> rchar = new PriorityQueue<Pair>();
      PriorityQueue<Pair> rq = new PriorityQueue<Pair>();
      
      for(int k = 0; k < n; k++){
         if(a.charAt(k) == '?'){
            lq.add(new Pair(a.charAt(k),k+1));
         } else {
            lchar.add(new Pair(a.charAt(k),k+1));
         }

         if(b.charAt(k) == '?'){
            rq.add(new Pair(b.charAt(k),k+1));
         } else {
            rchar.add(new Pair(b.charAt(k),k+1));
         }
      }
      
      Queue<Pair> lr = new LinkedList<Pair>();
      Queue<Pair> rr = new LinkedList<Pair>();
      
      ArrayList<Ans> answer = new ArrayList<Ans>();
      
      while(!lchar.isEmpty() && !rchar.isEmpty()){
         if(lchar.peek().c == rchar.peek().c){
            answer.add(new Ans(lchar.poll().i,rchar.poll().i));
         } else if(lchar.peek().c < rchar.peek().c){
            lr.add(lchar.poll());
         } else {
            rr.add(rchar.poll());
         }
      }
      
      while(!lchar.isEmpty()) lr.add(lchar.poll());
      while(!rchar.isEmpty()) rr.add(rchar.poll());
      
      while(!lq.isEmpty() && !rr.isEmpty()){
         answer.add(new Ans(lq.poll().i,rr.poll().i));
      }
      
      while(!rq.isEmpty() && !lr.isEmpty()){
         answer.add(new Ans(lr.poll().i,rq.poll().i));
      }
      while(!lq.isEmpty() && !rq.isEmpty()){
         answer.add(new Ans(lq.poll().i,rq.poll().i));
      }
      
      
      out.println(answer.size());
      for( Ans an : answer){
         out.println(an.a + " " + an.b);
      }
      
      
      
      out.close();
   }
   
   
   public static class Ans{
      int a;
      int b;
      public Ans(int c, int d){
         a = c;
         b = d;
      }
   }
   
   public static class Pair implements Comparable<Pair>{
      char c;
      int i;
      public Pair(char a, int b){
         c = a;
         i = b;
      }
      public int compareTo(Pair p){
         return (int)c-(int)p.c;
      }
   }
      
}