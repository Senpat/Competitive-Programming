//make sure to make new file!
import java.io.*;
import java.util.*;

public class D611{
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader f = new BufferedReader(new FileReader("Dtest.out"));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      HashSet<Long> trees = new HashSet<Long>();
      
      HashSet<Long> used = new HashSet<Long>();
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         long l = Long.parseLong(st.nextToken());
         trees.add(l);
         used.add(l);
      }
      
      long answer = 0;
      ArrayList<Long> answers = new ArrayList<Long>();
         
      long i = 1L;
      
      HashSet<Long> remove;
      while(answers.size() < m){
         
         remove = new HashSet<Long>();
         
         for(long tree : trees){
            
            boolean rem = true;
            
            //check -i and i
            
            if(!used.contains(tree-i)){
               answer += i;
               answers.add(tree-i);
               rem = false;
               used.add(tree-i);
               if(answers.size() == m) break;
            }
            
            if(!used.contains(tree+i)){
               answer += i;
               answers.add(tree+i);
               rem = false;
               used.add(tree+i);
               if(answers.size() == m) break;
            }
            
            if(rem){
               out.println(i + " " + tree);
               remove.add(tree);
            }
         }
         
         for(long r : remove){
            trees.remove(r);
         }
         
         i++;
      }
      
      out.println(answer);
      for(long a : answers){
         out.print(a + " ");
      }
      
               
      
      
      
      
      
      out.close();
   }
   
      
}