//make sure to make new file!
import java.io.*;
import java.util.*;

public class C168{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      double a = Double.parseDouble(st.nextToken());
      double b = Double.parseDouble(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      double hangle = ((double)((h%12)*30 + 360) + (double)m/2.0)%360;
      double mangle = (double)m*6.0;
      
      double hr = Math.toRadians((double)hangle);
      double mr = Math.toRadians((double)mangle);
      
      double hx = a*Math.cos(hr);
      double hy = a*Math.sin(hr);
      double mx = b*Math.cos(mr);
      double my = b*Math.sin(mr);
      
      //double answer = Math.sqrt((mx-hx)*(mx-hx) + (my-hy)*(my-hy));
      double answer = Math.sqrt(a*a+b*b-2*a*b*Math.cos(mr-hr));
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}