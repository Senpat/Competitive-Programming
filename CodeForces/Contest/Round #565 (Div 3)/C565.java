//make sure to make new file!
import java.io.*;
import java.util.*;

public class C565{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] itoi = new int[43];
      itoi[4] = 0;
      itoi[8] = 1;
      itoi[15] = 2;
      itoi[16] = 3;
      itoi[23] = 4;
      itoi[42] = 5;
      int[] array = new int[n];
      ArrayList<Queue<Integer>> alist = new ArrayList<Queue<Integer>>(6);
      for(int k = 0; k < 6; k++){
         alist.add(new LinkedList<Integer>());
      }
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         alist.get(itoi[array[k]]).add(k);
      }
      
      int added = n;
      
      int answer = 0;
      int lastindex = -1;
      while(added > 0){
         lastindex = -1;
         
         for(int k = 0; k < 6; k++){
            if(alist.get(k).isEmpty()){
               answer += added;
               added = 0;
               break;
            } else {
               int i = alist.get(k).poll();
               while( i < lastindex){
                  if(alist.get(k).isEmpty()){
                     answer += added;
                     added = 0;
                     break;
                  }
                  i = alist.get(k).poll();
               }
               lastindex = i;
            }
         }
         
         added -= 6;
      }
      
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}