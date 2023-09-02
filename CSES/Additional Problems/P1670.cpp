#include <bits/stdc++.h>

using namespace std;

vector<long long> p10;

long long gethash(const vector<int>& board){
   long long hash = 0LL;
   for(int k = 0; k < 9; k++){
      hash = hash*10LL + (long long)board[k];
   }
   return hash;
}

int getdigit(long long hash, int x){
   return (int)((hash / p10[x]) % 10LL);
}

long long swaphash(long long hash, int x, int y){
   int d1 = getdigit(hash,x);
   int d2 = getdigit(hash,y);
   
   long long rethash = hash;
   rethash -= d1 * p10[x];
   rethash -= d2 * p10[y];
   rethash += d1 * p10[y];
   rethash += d2 * p10[x];
   
   return rethash;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   p10 = vector<long long>(12);
   p10[0] = 1LL;
   for(int k = 1; k < 12; k++){
      p10[k] = p10[k-1] * 10LL;
   }
   
   vector<int> inboard(9);
   for(int k = 0; k < 9; k++){
      cin >> inboard[k];
   }
   
   vector<int> perm(9);
   for(int k = 0; k < 9; k++){
      perm[k] = k+1;
   }
   
   long long donehash = gethash(perm);
   
   unordered_set<long long> permset;
   
   long long inhash = gethash(inboard);
   if(inhash == donehash){
      cout << 0 << endl;
      return 0;
   }
   permset.insert(inhash);
   queue<pair<long long,int>> q;
   q.push(make_pair(inhash,0));
   vector<long long> newhashes(4);;
   while(!q.empty()){
      auto p = q.front();
      q.pop();
      long long hash = p.first;
      int d = p.second;
      
      for(int k = 0; k < 3; k++){
         newhashes[0] = swaphash(hash,k*3,k*3+1);
         newhashes[1] = swaphash(hash,k*3+1,k*3+2);
         newhashes[2] = swaphash(hash,k,k+3);
         newhashes[3] = swaphash(hash,k+3,k+6);
         for(long long newhash : newhashes){
            if(newhash == donehash){
               cout << d+1 << endl;
               return 0;
            }
            if(permset.find(newhash) == permset.end()){
               permset.insert(newhash);
               q.push(make_pair(newhash,d+1));
            }
         }
      }
   }
   
   return 0;
}