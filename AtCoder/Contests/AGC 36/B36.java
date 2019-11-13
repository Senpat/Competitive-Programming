//make sure to make new file!
import java.io.*;
import java.util.*;

public class B36{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      int[] array = new int[n+1];
      st = new StringTokenizer(f.readLine());
      
      int[] next = new int[n+1];
      HashMap<Integer,Integer> last = new HashMap<Integer,Integer>();
      
      //stores first instance of each number
      HashMap<Integer,Integer> indexof = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         next[k] = -1;
         if(last.containsKey(array[k])){
            next[last.get(array[k])] = k;
         }
         last.put(array[k],k);
         
         if(!indexof.containsKey(array[k])){
            indexof.put(array[k],k);
         }
         
      }
      
      next[n] = -1;
      array[n] = -1;
      
      
      ArrayList<Integer> before = new ArrayList<Integer>();
      
      before.add(-1);
      
      int i = 0;
      while(next[i] != -1){
         i = next[i]+1;
      }
      if(i >= n){
         out.close();
         System.exit(0);
      }
      before.add(array[i]);
      
      int start = indexof.get(array[i])+1;
      while(start < n){
         
         i = start;
         while(next[i] != -1){
            i = next[i]+1;
         }
         if(array[i] == -1) break;
         before.add(array[i]);
         start = indexof.get(array[i])+1;
      }

      
      
      int index = (int)(m % (long)before.size())-1;
      if(index == -1){
         index = before.size()-1;
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      start = before.get(index);
      if(start != -1) index = indexof.get(start)+1;
      else index = 0;
      while(index < n){
         
         if(next[index]!=-1)index=next[index]+1; 
         else{
            answer.add(array[index]);
            index++;
         }
      }
      
      for(int a : answer){
         out.print(a + " ");
      }
      
      
      
      
      out.close();
   }
   
      
}