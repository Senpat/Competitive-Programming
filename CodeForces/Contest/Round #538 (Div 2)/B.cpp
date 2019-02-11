#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,i;
   cin >> n >> m >> i;
   
   long long a[n];
   long long s[n];            //sorted a
   
   for(int k = 0; k < n; k++){
      cin >> a[k];
      s[k] = a[k];
   }
   
   sort(s,s+n);
   
   unordered_map<long long,int> freq;
   long long sum = 0;
   
   for(int k = 0; k < m*i; k++){
      if(freq.find(s[n-k-1]) == freq.end()){
         freq[s[n-k-1]] = 1;
      } else {
         freq[s[n-k-1]]++;
      }
      sum += s[n-k-1];
   }
   
   vector<int> answer;
   int size = 0;
   
   int curint = 0;
   for(int k = 0; k < n && size < i-1; k++){
      if(freq.find(a[k]) != freq.end() && freq[a[k]] > 0){
         curint++;
         freq[a[k]]--;
      }
      if(curint >= m){
         curint = 0;
         answer.push_back(k+1);
         size++;
      }
   }
   
   cout << sum << endl;
   for(int i : answer){
      cout << i << " ";
   }
   
   
   
   
   return 0;
}