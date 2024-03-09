//make sure to make new file!
import java.io.*;
import java.util.*;
//non matching solution
public class D469b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      HashMap<Integer,Integer> indexof = new HashMap<Integer,Integer>();
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         indexof.put(array[k],k);
      }
      
      int[] answer = new int[n];
      Arrays.fill(answer,-1);
      
      boolean fail = false;
      for(int k = 0; k < n; k++){
         if(answer[k] != -1) continue;
         HashSet<Integer> hset = new HashSet<Integer>();
         
         hset.add(k);
         Queue<Integer> q = new LinkedList<Integer>();
         q.add(k);
         while(!q.isEmpty()){
            int v = array[q.poll()];
            
            if(indexof.containsKey(a-v)){
               int i = indexof.get(a-v);
               if(answer[i] == -1 && !hset.contains(i)){
                  hset.add(i);
                  q.add(i);
               }
            }
            
            if(indexof.containsKey(b-v)){
               int i = indexof.get(b-v);
               if(answer[i] == -1 && !hset.contains(i)){
                  hset.add(i);
                  q.add(i);
               }
            }
         }
         
         //see if it works for group a
         int cur = -1;
         boolean faila = false;
         for(int i : hset){
            int val = a-array[i];
            if(!indexof.containsKey(val) || !hset.contains(indexof.get(val))){
               faila = true;
               break;
            }
         }
         
         if(!faila) cur = 0;
         
         //see if it works for group b
         boolean failb = false;
         for(int i : hset){
            int val = b-array[i];
            if(!indexof.containsKey(val) || !hset.contains(indexof.get(val))){
               failb = true;
               break;
            }
         }
         
         if(!failb) cur = 1;
         
         if(cur == -1){
            fail = true;
            break;
         }
         
         for(int i : hset){
            answer[i] = cur;
         }
         
         
      }
      
      
      if(fail){
         out.println("NO");
      } else {
         out.println("YES");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      out.close();
   }
   
      
}