//make sure to make new file!
import java.io.*;
import java.util.*;
//original fst, misread problem
public class AG4{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      State[] array = new State[n-1];
      
      
      int total = Integer.parseInt(st.nextToken());
      int orig = total;
      int sum = total;
      for(int k = 0; k < n-1; k++){
         array[k] = new State(Integer.parseInt(st.nextToken()),k+2);
         sum += array[k].v;
      }
      ArrayList<Integer> answer = new ArrayList<Integer>();
      answer.add(1);
      
      Arrays.sort(array);
      
      for(int k = 0; k < n-1; k++){
         if(orig >= array[k].v*2){
            total += array[k].v;
            answer.add(array[k].i);
         } else {
            break;
         }
      }
      
      if(total > sum/2){
         out.println(answer.size());
         for(int i : answer){
            out.print(i + " ");
         }
      } else {
         out.println(0);
      }
      
      
      
      
      

      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;
      int i;
      public State(int a, int b){
         v = a;
         i = b;
      }
      
      public int compareTo(State s){
         return v - s.v;
      }
   }
   
      
}