#include <bits/stdc++.h>

using namespace std;

vector<int> nxt;     //next
vector<int> prv;     //prev

void rem(int i){
   nxt[prv[i]] = nxt[i];
   prv[nxt[i]] = prv[i];
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n;
   cin >> n;
   
   vector<int> answer;
   
   nxt = vector<int>(n+1);
   prv = vector<int>(n+1);
   
   for(int k = 1; k < n; k++){
      nxt[k] = k+1;
      prv[k+1] = k;
   }
   nxt[n] = 1;
   prv[1] = n;
   
   int i = nxt[1];
   answer.push_back(i);
   rem(i);
   for(int k = 0; k < n-1; k++){
      i = nxt[nxt[i]];
      answer.push_back(i);
      rem(i);
   }
   
   for(int i : answer){
      cout << i << " ";
   }
   
   
   return 0;
}