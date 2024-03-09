//make sure to make new file!
import java.io.*;
import java.util.*;

public class B165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<Streak> streaks = new ArrayList<Streak>();
      int curstart = 0;
      ArrayList<Integer> curstreak = new ArrayList<Integer>();
      curstreak.add(array[0]);
      for(int k = 1; k < n; k++){
         if(array[k] > array[k-1]){
            curstreak.add(array[k]);
         } else {
            streaks.add(new Streak(curstart,curstreak));
            curstart = k;
            curstreak = new ArrayList<Integer>();
            curstreak.add(array[k]);
         }
      }
      
      streaks.add(new Streak(curstart,curstreak));
      
      TreeSet<Integer> tset = new TreeSet<Integer>();
      for(int k = 0; k < m; k++){
         tset.add(array[k]);
      }
      
      boolean found0 = false;
      int start = 0;
      int maxdiff = -1;
      int i = 0;
      for(Streak streak : streaks){
         while(i < streak.start){
            tset.remove(array[i]);
            if(i + m < n){
               tset.add(array[i+m]);
            }
            i++;
         }
         
         //n-m is the last place a streak can start
         if(i > n-m){
            break;
         }
         
         //remove everything in the streak
         for(int instreak : streak.elements){
            tset.remove(instreak);
         }
         
         if(tset.isEmpty()){
            start = streak.start;
            maxdiff = n;
            break;
         } else {
            int min = tset.first();
            //find which element is the one that differs (first element that > min
            
            int d = -1;
            for(int k = 0; k < streak.elements.size(); k++){
               if(streak.elements.get(k) > min){
                  d = streak.start+k;
                  break;
               }
            }
            
            if(d > maxdiff){
               start = streak.start;
               maxdiff = d;
            }
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      ArrayList<Integer> sort = new ArrayList<Integer>();
      for(int k = 0; k < m; k++){
         sort.add(array[k+start]);
      }
      Collections.sort(sort);
      
      for(int k = 0; k < start; k++){
         sj.add("" + array[k]);
      }
      for(int x : sort){
         sj.add("" + x);
      }
      for(int k = start+m; k < n; k++){
         sj.add("" + array[k]);
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Streak{
      int start;
      ArrayList<Integer> elements;
      public Streak(int a, ArrayList<Integer> b){
         start = a;
         elements = b;
      }
   }
      
}