//make sure to make new file!
import java.io.*;
import java.util.*;

public class Mtest{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("Mout.txt"));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 5000; 
      
      char[][] matrix = new char[N][10];
      
      for(int k = 0; k < N; k++){
         String s = f.readLine();
         for(int j = 0; j < 10; j++){
            matrix[k][j] = s.charAt(j);
         }
      }
      
      out.println(works(N,matrix));
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean works(int N, char[][] arr)
    {
        for(int i=1; i < N; i++)
        {
            if(!step(arr[i-1], arr[i]))
                return false;
            for(int j=i-2; j >= 0; j--)
                if(step(arr[j], arr[i]) || equal(arr[j], arr[i]))
                    return false;
        }
        return true;
    }
    public static boolean step(char[] arr, char[] brr)
    {
        int cnt = 0;
        for(int i=0; i < arr.length; i++)
            if(arr[i] != brr[i])
                cnt++;
        return cnt == 1;
    }
    public static boolean equal(char[] arr, char[] brr)
    {
        for(int i=0; i < arr.length; i++)
            if(arr[i] != brr[i])
                return false;
        return true;
    }

      
}