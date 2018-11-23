//make sure to make new file!
import java.io.*;
import java.util.*;

public class B522{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int n = s.length();
      
      int rows;
      if(n % 20 == 0) rows = n/20;
      else rows = n/20+1;
      
      int r = rows*20-n;
      
      int columns;
      
      if(r % rows == 0) columns = 20-r/rows;
      else columns = 20-(r/rows);
      
      int asrows = rows*columns - n;
      
      out.println(rows + " " + columns);
      
      int index = 0;
      for(int k = 0; k < rows; k++){
         for(int j = 0; j < columns; j++){
            if(j == columns - 1 && k >= rows-asrows){
               out.print('*');
            } else {
               out.print(s.charAt(index));
               index++;
            }
         }
         out.println();
      }
      
      
      
      
      out.close();
   }
   
}