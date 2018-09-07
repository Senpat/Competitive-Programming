//make sure to make new file!
import java.io.*;
import java.util.*;

public class CMC18{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      
      Queue<Integer> q = new LinkedList<Integer>();
      
      char[] array = new char[n];
      
      for(int k = 0; k < n; k++){
         if(a[k]!=b[k]){
            q.add(k);
            array[k] = a[k];
         }
         else array[k] = '.';
      }
      
      int answer = 0;
      while(!q.isEmpty()){
         int cur = q.poll();
         if(q.size()>=1 && q.peek()==cur+1 && array[q.peek()] != array[cur]){
            answer+=1;
            q.poll();
         } else {
            answer+=1;
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
}