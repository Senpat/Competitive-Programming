//make sure to make new file!
import java.io.*;
import java.util.*;

public class E561{

   public static void main(String args[]) throws IOException{
   
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);  
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      HashSet<Integer>[] arr = new HashSet[N+1];
      for(int i=0; i < N; i++)
         arr[i] = new HashSet<Integer>();
      for(int i=0; i < N; i++)
      {
         st = new StringTokenizer(f.readLine());
         int n = Integer.parseInt(st.nextToken());
         for(int j=0; j < n; j++)
            arr[i].add(Integer.parseInt(st.nextToken()));
      }
      //ree
      HashSet<Integer> temp;
      for(int a=0; a < N; a++)
         for(int b=a+1; b < N; b++)
         {
            temp = new HashSet<Integer>(arr[a]);
            temp.retainAll(arr[b]);
            if(temp.size() == 0)
            {
               System.out.println("impossible");
               return;
            }
         }
      System.out.println("possible");
   } 
}