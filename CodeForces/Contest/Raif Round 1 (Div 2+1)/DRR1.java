//make sure to make new file!
import java.io.*;
import java.util.*;

public class DRR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<Point> answer = new ArrayList<Point>();
      
      Queue<Integer> q1 = new LinkedList<Integer>();
      Queue<Integer> q2 = new LinkedList<Integer>();
      Queue<Integer> q3 = new LinkedList<Integer>();
      
      int open = n;
      
      boolean fail = false;
      for(int k = n; k >= 1; k--){
         if(array[k] == 1){
            q1.add(k);
         } else if(array[k] == 2){
            if(q1.isEmpty()){
               fail = true;
               break;
            } else {
               int one = q1.poll();
               answer.add(new Point(open,k));
               answer.add(new Point(open,one));
               q2.add(k);
               open--;
            }
            
         } else if(array[k] == 3){
            if(!q3.isEmpty()){
               int three = q3.poll();
               answer.add(new Point(open,k));
               answer.add(new Point(open,three));
               q3.add(k);
               open--;
            } else if(!q2.isEmpty()){
               int two = q2.poll();
               answer.add(new Point(open,k));
               answer.add(new Point(open,two));
               q3.add(k);
               open--;
            } else if(!q1.isEmpty()){
               int one = q1.poll();
               answer.add(new Point(open-1,k));
               answer.add(new Point(open-1,one));
               answer.add(new Point(open,one));
               q3.add(k);
               open-=2;
            } else {
               fail = true;
               break;
            }
         }
      }
      
      while(!q1.isEmpty()){
         answer.add(new Point(open,q1.poll()));
         open--;
      }
      
      if(fail) out.println(-1);
      else {
         out.println(answer.size());
         StringJoiner sj = new StringJoiner("\n");
         for(Point p : answer){
            sj.add(p.toString());
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
      public String toString(){
         return x + " " + y;
      }
   }
   
      
}