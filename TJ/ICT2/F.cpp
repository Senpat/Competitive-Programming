#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   string s;
   cin >> s;
   
   vector<int> a;
   
   int dotindex;
   
   for(int k = 0; k < n; k++){
      if(s[k] == '.') dotindex = k;
      else a.push_back(s[k]-'0');
   }
   
   int endindex=n-1;
   for(int k = dotindex; k < n-1; k++){
      if(a[k] >= 5){
         int index = k-1;
         int count = 1;
         while(index >= dotindex && count < m && a[index] == 4){
            count ++;
            index --;
            //cout << index << endl;
         }
         endindex = index+1;
         if(a[index] < 9) a[index]++;
         else{
            int i = index;
            while(i >= 0 && a[i] == 9){
               a[i] = 0;
               i--;
            }
            if(i == -1){
               cout << 1;
            } else {
               a[i]++;
            }
         }
         break;
      }
   }
   //cout << endindex << endl;
   for(int k = 0; k < endindex; k++){
      if(k == dotindex) cout << '.';
      cout << a[k];
      
   }
   
   
   
   return 0;
}