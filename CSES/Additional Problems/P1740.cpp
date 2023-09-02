#include <bits/stdc++.h>

using namespace std;

typedef pair<int,pair<int,int>> Vert;

Vert make_vert(int x, int y1, int y2){
   return make_pair(x,make_pair(min(y1,y2),max(y1,y2)));
}

int N = 2000001;
int O = 1000001;
vector<long long> bits;

void update(int i, long long x){
   for(; i <= N; i += i&-i){
      bits[i]+=x;
   }
}

long long psum(int i){
   long long cursum = 0LL;
   for(; i > 0; i -= i&-i){
      cursum += bits[i];
   }
   return cursum;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<vector<int>> inc(N+1,vector<int>());
   vector<vector<int>> dec(N+1,vector<int>());
   
   vector<Vert> verts;
   
   for(int k = 0; k < n; k++){
      int x1,y1,x2,y2;
      cin >> x1 >> y1 >> x2 >> y2;
      x1 += O;
      y1 += O;
      x2 += O;
      y2 += O;
      
      if(x1 == x2 && y1 == y2) continue;
      if(x1 == x2){
         verts.push_back(make_vert(x1,y1,y2));
      } else if(y1 == y2){
         int xmin = min(x1,x2);
         int xmax = max(x1,x2);
         
         inc[xmin].push_back(y1);
         dec[xmax].push_back(y1);
      }
   }
   
   sort(verts.begin(),verts.end());
   
   bits = vector<long long>(N+1,0LL);
   
   long long answer = 0LL;
   int vi = 0;
   for(int x = 1; x <= N; x++){
      while(vi < verts.size() && verts[vi].first == x){
         answer += psum(verts[vi].second.second) - psum(verts[vi].second.first-1);
         vi++;
      }
      
      for(int i : inc[x]){
         update(i,1LL);
      }
      for(int i : dec[x]){
         update(i,-1LL);
      }
   }
   
   cout << answer << endl;
         
   
   
   return 0;
}