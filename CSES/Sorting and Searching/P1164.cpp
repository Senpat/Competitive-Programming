#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   //compress
   unordered_set<int> uset;
   vector<pair<int,int>> array(n);
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      uset.insert(a);
      uset.insert(b);
      array[k] = make_pair(a,b);
   }
   
   vector<int> vec;
   for(int i : uset){
      vec.push_back(i);
   }
   sort(vec.begin(),vec.end());
   
   unordered_map<int,int> compress;
   int cn = vec.size();
   for(int k = 0; k < cn; k++){
      compress[vec[k]] = k;
   }
   
   priority_queue<int,vector<int>,greater<int>> pq;
   for(int k = 1; k <= n; k++){
      pq.push(k);
   }
   
   vector<vector<int>> start(cn,vector<int>());
   vector<vector<int>> endr(cn,vector<int>());
   
   for(int k = 0; k < n; k++){
      start[compress[array[k].first]].push_back(k);
      endr[compress[array[k].second]].push_back(k);
   }
   
   int maxval = 0;
   vector<int> answer(n);
   for(int k = 0; k < cn; k++){
      for(int i : start[k]){
         answer[i] = pq.top();
         pq.pop();
         maxval = max(maxval,answer[i]);
      }
      for(int i : endr[k]){
         pq.push(answer[i]);
      }
   }
   
   cout << maxval << endl;
   for(int k = 0; k < n; k++){
      cout << answer[k] << " ";
   }
   
   return 0;
}