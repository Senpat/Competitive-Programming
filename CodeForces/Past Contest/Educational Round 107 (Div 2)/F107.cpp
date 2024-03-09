#include <bits/stdc++.h>

using namespace std;

const long long MOD = 998244353LL;

//mat mul exp
vector<vector<long long>> matmul(const vector<vector<long long>>& a, const vector<vector<long long>>& b){
   int len = a.size();
   vector<vector<long long>> ret = vector<vector<long long>>(len,vector<long long>(len));

   for(int k = 0; k < len; k++){
      for(int j = 0; j < len; j++){
         for(int h = 0; h < len; h++){
         	ret[k][j] = (ret[k][j] + a[k][h]*b[h][j] + MOD)%MOD;
         }
      }
   }
   return ret;
}


vector<vector<long long>> matexp(const vector<vector<long long>>& base, long long power){
   int len = base.size();
   if(power == 0){
      //IDENTITY MATRIX OF THE RIGHT SIZE
      vector<vector<long long>> ret(len,vector<long long>(len,0LL));
      for(int k = 0; k < len; k++) ret[k][k] = 1LL;
      return ret;
   }
   if(power == 1LL) 
      return base;
   auto ans = matexp(base,power/2LL);
   ans = matmul(ans,ans);
   if(power%2LL == 1LL) ans = matmul(ans, base);
   return ans;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   long long m;
   cin >> n >> m;
   
   vector<string> s(n);
   for(int k = 0; k < n; k++){
      cin >> s[k];
   }
   
   //calculate valid states (hopefully few enough)
   //(-1,-1) means end state
   vector<array<int,4>> states;
   const int END = 0;
   states.push_back({-1,-1,-1,-1});
   
   map<array<int,4>,int> indexof;
   indexof[{-1,-1,-1,-1}] = 0;
   for(int s1 = 0; s1 < n; s1++){
   for(int s2 = 0; s2 < n; s2++){
      for(int i1 = 0; i1 < s[s1].length(); i1++){
      for(int i2 = 0; i2 < s[s2].length(); i2++){
         
         //see if {s1,i1,s2,i2} is a valid state
         int x1 = i1;
         int x2 = i2;
         bool fail = false;
         while(x1 >= 0 && x2 >= 0){
            if(s[s1][x1] != s[s2][x2]) fail = true;
            x1--;
            x2--;
         }
         
         x1 = i1;
         x2 = i2;
         while(x1 < s[s1].length() && x2 < s[s2].length()){
            if(s[s1][x1] != s[s2][x2]) fail = true;
            x1++;
            x2++;
         }
         
         if(!fail){
            array<int,4> cur = {s1,i1,s2,i2};
            array<int,4> cur2 = {s1,i1,s2,i2};
            if(i1 == s[s1].length()-1){
               cur2[0] = -1;
               cur2[1] = -1;
            }
            if(i2 == s[s2].length()-1){
               cur2[2] = -1;
               cur2[3] = -1;
            }
            
            if(indexof.find(cur2) != indexof.end()){
               indexof[cur] = indexof[cur2];
            } else {
               indexof[cur] = states.size();
               indexof[cur2] = states.size();
               states.push_back(cur2);
            }
         }
      }
      }  
   }
   }
   
   int S = states.size();
   
   cout << S << endl;
   /*for(int k = 0; k < S; k++){
      cout << states[k][0] << " " << states[k][1] << " " << states[k][2] << " " << states[k][3] << endl;
   }*/
   //get transitions between valid states
   vector<vector<long long>> mat(S, vector<long long>(S,0LL));
   for(int k = 0; k < S; k++){
      int s1 = states[k][0];
      int i1 = states[k][1];
      int s2 = states[k][2];
      int i2 = states[k][3];
      
      if(s1 == -1 && s2 == -1){
         //ending state
         for(int ns1 = 0; ns1 < n; ns1++){
         for(int ns2 = 0; ns2 < n; ns2++){
            array<int,4> newstate = {ns1,0,ns2,0};
            if(indexof.find(newstate) != indexof.end()){
               mat[k][indexof[newstate]]++;
            }
         }
         }
      }
      else if(s1 != -1 && s2 != -1){
         mat[k][indexof[{s1,i1+1,s2,i2+1}]]++;
      } else if(s2 == -1){
         //find new string for s2
         for(int ns = 0; ns < n; ns++){
            array<int,4> newstate = {s1,i1+1,ns,0};
            if(indexof.find(newstate) != indexof.end()){
               mat[k][indexof[newstate]]++;
            }
         }
      } else if(s1 == -1){
         //find new string for s1
         for(int ns = 0; ns < n; ns++){
            array<int,4> newstate = {ns,0,s2,i2+1};
            if(indexof.find(newstate) != indexof.end()){
               mat[k][indexof[newstate]]++;
            }
         }
      }
   }
   //cout << indexof.size() << endl;
   vector<vector<long long>> trans = matexp(mat,m);
   long long answer = trans[END][END];
   
   cout << answer << endl;
   
   
   return 0;
}