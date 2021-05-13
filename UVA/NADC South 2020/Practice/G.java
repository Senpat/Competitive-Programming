//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[][] array = new String[n][2];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         array[k][0] = st.nextToken();
         array[k][1] = st.nextToken();
      }
      
      int added = 0;
      HashSet<String> hset = new HashSet<String>();
      int i = 0;
      while(added < 12){
         if(!hset.contains(array[i][0])){
            hset.add(array[i][0]);
            out.println(array[i][0] + " " + array[i][1]);
            added++;
         }
         i++;
      }
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}