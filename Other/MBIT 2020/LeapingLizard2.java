/*
If you want to aim high, aim high
Don't let that studying and grades consume you
Just live life young
****************************
What do you think? What do you think?
1st on Billboard, what do you think of it
Next is a Grammy, what do you think of it
However you think, I’m sorry, but shit, I have no fucking interest
*****************************
Higher, higher, even higher, to the point you won’t even be able to see me
https://www.a2oj.com/Ladder16.html
*****************************
NEVER DO 300IQ CONTESTS EVER
300IQ AS WRITER = EXTRA NONO
*/
import java.util.*;
import java.io.*;
import java.math.*;
//ray
   public class LeapingLizard2
   {
      public static void main(String omkar[]) throws Exception
      {
         BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));  
         StringTokenizer st = new StringTokenizer(infile.readLine());
         int N = Integer.parseInt(st.nextToken());
         int[] arr = new int[N];
         st = new StringTokenizer(infile.readLine());
         for(int i=0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
         ArrayList<Integer>[] edges = new ArrayList[N];
         for(int i=0; i < N; i++)
            edges[i] = new ArrayList<Integer>();
         for(int left=0; left < N-1; left++)
         {
            edges[left].add(left+1);
            double slope = arr[left]-arr[left+1];
            for(int i=left+2; i < N; i++)
            {
               double temp = 1.0*(arr[left]-arr[i]);
               temp /= i-left;
               if(temp >= slope)
               {
                  slope = temp;
                  edges[left].add(i);
               }
            }
         }
         int[] dist = new int[N];
         dist[1] = 1;
         Queue<Integer> q = new LinkedList<Integer>();
         q.add(1);
         while(q.size() > 0)
         {
            int curr = q.poll();
            for(int next: edges[curr])
               if(dist[next] == 0)
               {
                  dist[next] = dist[curr]+1;
                  q.add(next);
               }
         }
         System.out.println(dist[N-1]-1);
      }
   }