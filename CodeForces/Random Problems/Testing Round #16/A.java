import java.util.*;
import java.io.*;

public class A{
    
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(f.readLine());
        
        for(int q = 0; q < t; q++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            out.println(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
        }
        out.close();
    }
}