//make sure to make new file!
import java.io.*;
import java.util.*;

public class ILoveSuchet4{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer suc = new StringTokenizer(f.readLine());
      StringTokenizer het = new StringTokenizer(f.readLine());
      
      int[] vinay = new int[n];
      int[] param = new int[n];              //because vinay is trying to be like param
      
      int[] indexof = new int[n+1];
      
      for(int k = 0; k < n; k++){
         vinay[k] = Integer.parseInt(suc.nextToken());
         param[k] = Integer.parseInt(het.nextToken());
         indexof[vinay[k]] = k;
      }
      
      int numberoftimesvinaytriestobelikeparam = 0;
      
      for(int k = 0; k < n; k++){
         if(vinay[k] == param[k]) continue;
         vinay[indexof[param[k]]] = vinay[k];
         indexof[vinay[k]]=indexof[param[k]];
         numberoftimesvinaytriestobelikeparam++;
      }
      
      out.println(numberoftimesvinaytriestobelikeparam);
      
         
      

      
      
      
      
      
      out.close();
   }
   
      
}