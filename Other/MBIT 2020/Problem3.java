import java.util.*;
import java.io.*;

public class Problem3 {
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(f.readLine());
        long[] stamina = new long[n];
        int[] dir = new int[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            stamina[i] = Long.parseLong(st.nextToken());
            dir[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Long> left = new Stack<Long>();
        StringBuilder ans = new StringBuilder();
        int count = 0;
        long currRight = -1;
        for(int i = 0; i < n; i++){
            if(dir[i] == 1){
                left.add(stamina[i]);
            }else{
                currRight = stamina[i];
                while(!left.isEmpty() && currRight > 0){
                    long temp = left.pop();
                    if(temp < currRight){
                        currRight-=temp;
                    }else{
                        left.push(temp-currRight);
                        currRight = 0;
                    }
                }
                if(left.isEmpty()){
                    count++;
                    ans.append(currRight);
                    ans.append(" ");
                    ans.append(0);
                    ans.append('\n');
                }
            }
        }
        long[] vals = new long[left.size()];
        for(int i = vals.length-1; i >= 0; i--) vals[i] = left.pop();
        for(int i = 0; i < vals.length; i++){
            count++;
            ans.append(vals[i]);
            ans.append(" ");
            ans.append(1);
            ans.append('\n');
        }
        out.println(count);
        out.print(ans);



        out.close();
    }
}
