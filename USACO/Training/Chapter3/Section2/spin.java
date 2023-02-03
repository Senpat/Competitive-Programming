/*
TASK: spin
LANG: JAVA
*/
//answer will be max 360
import java.io.*;
import java.util.*;

class spin{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("spin.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
      
      int N = 5;
      int[] speed = new int[N];
      ArrayList<HashSet<Integer>> hsets = new ArrayList<HashSet<Integer>>(N);
      for(int k = 0; k < N; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         hsets.add(new HashSet<Integer>());
         
         speed[k] = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken());
         
         for(int j = 0; j < n; j++){
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for(int h = a; h <= a+b; h++){
               hsets.get(k).add(h%360);     
            }
         }
      }
      
      int answer = -1;
      int REP = 10000;
      
      int[] loc = new int[N];
      
      for(int t = 0; t < REP; t++){
         boolean found = false;
         
         for(int k = 0; k < N; k++){
            loc[k] = (speed[k] * t + 360)%360;
         }
         
         for(int d = 0; d <= 359; d++){
            boolean fail = false;
            for(int k = 0; k < N; k++){
               if(!hsets.get(k).contains((d-loc[k]+360)%360)){
                  fail = true;
                  break;
               }
            }
            
            if(!fail){
               found = true;
               break;
            }
         }
         
         if(found){
            answer = t;
            break;
         }
      }
      
      if(answer == -1){
         out.println("none");
      } else {
         out.println(answer);
      }
        
        
      out.close();
   }
      
}