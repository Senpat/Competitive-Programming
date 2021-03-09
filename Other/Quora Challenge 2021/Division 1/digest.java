//make sure to make new file!
import java.io.*;
import java.util.*;

public class digest{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] stories = new int[n+1];         //stores which user created story i
      for(int k = 1; k <= n; k++){
         stories[k] = Integer.parseInt(f.readLine());
      }
      
      st = new StringTokenizer(f.readLine());
      
      int p = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      boolean[][] userfollow = new boolean[m+1][m+1];             //check a 3 points
      
      for(int k = 0; k < p; k++){
         st = new StringTokenizer(f.readLine());
      
         int ua = Integer.parseInt(st.nextToken());
         int ub = Integer.parseInt(st.nextToken());
         
         userfollow[ua][ub] = true;
      }
      
      boolean[][] userfollowstorycreate = new boolean[m+1][m+1];  //check a 2 points
      boolean[][] storyfollow = new boolean[m+1][m+1];            //check a 1 point
      boolean[][] userfollowstory = new boolean[m+1][n+1];        //check b 1 point
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
      
         int ua = Integer.parseInt(st.nextToken());
         int sb = Integer.parseInt(st.nextToken());
         
         userfollowstory[ua][sb] = true;
         userfollowstorycreate[ua][stories[sb]] = true;
      }
      
      for(int k = 1; k <= m; k++){
         for(int j = 1; j <= n; j++){
            if(userfollowstory[k][j]){
               for(int h = 1; h <= m; h++){
                  if(userfollowstory[h][j]){
                     storyfollow[k][h] = true;
                  }
               }
            }
         }
      }
      
      for(int k = 1; k <= m; k++){                                               //loop through ui
         PriorityQueue<Story> pq = new PriorityQueue<Story>();
         for(int j = 1; j <= n; j++){                                            //loop through sk
            if(stories[j] == k || userfollowstory[k][j]) pq.add(new Story(-1,j));
            else {
               int val = 0;
               for(int h = 1; h <= m; h++){                                      //loop through uj
                  if(h==k) 
                     continue;
                  int a = 0;
                  int b = 0;
                  if(userfollow[k][h]) a = 3;
                  else if(userfollowstorycreate[k][h]) a = 2;
                  else if(storyfollow[k][h]) a = 1;
                  
                  if(stories[j] == h) b = 2;
                  else if(userfollowstory[h][j]) b = 1;
                  
                  val += a*b;
               }
               pq.add(new Story(val,j));
            }
         }
         
         out.println(pq.poll().index + " " + pq.poll().index + " " + pq.poll().index);
      }
      
      
      
      
      out.close();
   }
   
   public static class Story implements Comparable<Story>{
      int val;
      int index;
      
      public Story(int a, int b){
         val = a;
         index = b;
      }
      
      public int compareTo(Story s){
         if(s.val == val) 
            return index-s.index;          //sort by increasing
         else 
            return s.val-val;                         //non-increasing for val
      }
   }
      
}