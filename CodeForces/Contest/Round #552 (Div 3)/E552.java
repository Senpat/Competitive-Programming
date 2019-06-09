//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class E552{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      
      PriorityQueue<Stud> pq = new PriorityQueue<Stud>();
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         pq.add(new Stud(array[k],k));
      }
      
      int[] prev = new int[n];
      int[] next = new int[n];
      
      prev[0] = -1;
      next[n-1] = -1;
      
      
      
      for(int k = 0; k < n-1; k++){
         prev[k+1] = k;
         next[k] = k+1;
      }
      
      
      
      
      int[] answers = new int[n];
      int team = 1;
      while(!pq.isEmpty()){
         Stud s = pq.poll();
         //out.println(s.skill + " " + s.index);
         if(answers[s.index] != 0) continue;
         
         //go m to the left
         answers[s.index] = team;
         int i = s.index;
         int c = 0;
         int lastl = s.index;
         while(c < m && prev[i] != -1){
            i = prev[i];
            answers[i] = team;
            c++;
            lastl = i;
         }
         
         //go m to the right
         i = s.index;
         c = 0;
         int lastr = s.index;
         while(c < m && next[i] != -1){
            i = next[i];
            answers[i] = team;
            c++;
            lastr = i;
         }
         
         //lastl and lastr are always valid
         if(prev[lastl] != -1){
            next[prev[lastl]] = next[lastr];
         }
         if(next[lastr] != -1){
            prev[next[lastr]] = prev[lastl];
         }
         
         
         team = 3-team;
      }
      
      for(int k = 0; k < n; k++){
         out.print(answers[k]);
      }
      
      
      out.close();
   }
   
   public static class Stud implements Comparable<Stud>{
      int skill;
      int index;
      public Stud(int a, int b){
         skill = a;
         index = b;
      }
      
      public int compareTo(Stud s){
         if(skill == s.skill) return index-s.index;
         return s.skill-skill;                              //sort skill in decreasing order
      }
   }
      
}