//make sure to make new file!
import java.io.*;
import java.util.*;
//WA tc20
public class C561{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<Integer> alist = new ArrayList<Integer>(n);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         alist.add(Math.abs(Integer.parseInt(st.nextToken())));
      }
      
      Collections.sort(alist);
      
      long answer = 0L;
      for(int k = 0; k < n-1; k++){
         answer += 0L + bsearch(alist,alist.get(k)*2)-k;
      }
      
      out.println(answer);         

      
      
      
      
      out.close();
   }
   
   public static int bsearch(ArrayList<Integer> alist, int i){
      int bs = Collections.binarySearch(alist,i);
      return Math.abs(bs+1)-1;
   }
   
      
}