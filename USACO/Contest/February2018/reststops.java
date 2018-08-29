import java.io.*;
import java.util.*;

class reststops{
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("reststops.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int len = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int rf = Integer.parseInt(st.nextToken());
      int rb = Integer.parseInt(st.nextToken());
      
      //PriorityQueue<Stop> pq = new PriorityQueue<Stop>(n,Collections.reverseOrder());
      Stop[] array = new Stop[len];
      Arrays.fill(array,null);
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         //pq.add(new Stop(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
         int xi = Integer.parseInt(st.nextToken());
         array[xi] = new Stop(xi,Integer.parseInt(st.nextToken()));
      }
      for(int k = 0; k < len; k++){
         if(array[k] == null){
            System.out.println("null");
         } else {
            System.out.println(array[k].x + " " + array[k].c);
         }
      }
      Stop[] sarray = new Stop[len];
      int max = 0;
      Stop smax = null;
      for(int k = len-1; k >= 0; k--){
         if(array[k] == null){
            sarray[k] = smax;
         } 
         else {
            if(array[k].c > max){
               max = array[k].c;
               smax = array[k];
               sarray[k] = smax;
            } 
            else {
               sarray[k] = smax;
            }
         }
      }
      /*for(int k = 0; k < len; k++){
         
         System.out.println(sarray[k].x + " " + sarray[k].c);
      }*/
      //System.out.println(pq);
      int at = 0;
      Long count = 0L;
      boolean bool = true;
      while(bool){
         if(sarray[at+1] != null){
            Stop s=sarray[at+1];
         /*System.out.println(pq.peek().x);
         do{
            s=pq.poll();                   //find suitable Stop
         } while(s.x<at&&!pq.isEmpty());*/
         
         
            if(s.x>at){
               System.out.println(s.x + " " + s.c);
               count+=((rf*(s.x-at-1)+1)-(rb*(s.x-at-1)+1) + 1)*s.c;
            //System.out.println(count);
               at = s.x;
            
            
            
            }
         } 
         else {
            bool = false;
         }
      }
      System.out.println(count);
      out.println(count);
      out.close();
      
      
   }
   
   static class Stop implements Comparable<Stop>{
      int x,c;
      public Stop(int a, int b){
         x=a;
         c=b;
      }
      
      public int compareTo(Stop sto){
         if(c!=sto.c)
            return c-sto.c;
         return x-sto.x;
      }
   }
}