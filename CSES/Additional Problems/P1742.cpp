#include <bits/stdc++.h>

using namespace std;

#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
const int RANDOM = chrono::high_resolution_clock::now().time_since_epoch().count();
struct chash {
   int operator()(int x) const { 
      return x ^ RANDOM; }
};

struct Vert{
   int x;
   int y1;
   int y2;
   
   bool operator<(const Vert& v) const {
      if(x == v.x){
         if(y1 == v.y1) 
            return y2 < v.y2;
         return y1 < v.y1;
      }
      return x < v.x;
   }
};

struct Hori{
   int x1;
   int x2;
   int y;
   
   bool operator<(const Hori& h) const {
      if(y == h.y){
         if(x1 == h.x1) 
            return x2 < h.x2;
         return x1 < h.x1;
      }
      return y < h.y;
   }
};

bool isopp(char c1, char c2){
   if(c1 == 'U' && c2 == 'D') 
      return true;
   if(c1 == 'D' && c2 == 'U') 
      return true;
   if(c1 == 'L' && c2 == 'R') 
      return true;
   if(c1 == 'R' && c2 == 'L') 
      return true;
   return false;
}


bool check(const vector<Vert>& verts, const vector<Hori>& horis, int N){
   //see if there are any overlap within verts/horis
   int curx = -1;
   int curmax = -1;
   for(auto v : verts){
      //cout << "v " << v.x << " " << v.y1 << " " << v.y2 << endl;
      if(v.x != curx){
         curx = v.x;
         curmax = v.y2;
      } 
      else {
         if(v.y1 <= curmax) 
            return false;
         curmax = v.y2;
      } 
   }
   
   int cury = -1;
   curmax = -1;
   for(auto h : horis){
      //cout << "h " << h.x1 << " " << h.x2 << " " << h.y << endl;
      if(h.y != cury){
         cury = h.y;
         curmax = h.x2;
      } 
      else {
         if(h.x1 <= curmax) 
            return false;
         curmax = h.x2;
      } 
   }
   //cout << "past same row" << endl;
   vector<vector<int>> left(N+1,vector<int>());
   vector<vector<int>> right(N+1,vector<int>());
   
   for(auto h : horis){
      //cout << N << " " << h.x1 << endl;
      //cout << N << " " << h.x2 << endl;
      left[h.x1].push_back(h.y);
      right[h.x2].push_back(h.y);
   }
   
   set<int> curset;
   
   for(int i : left[1]) curset.insert(i);
   int li = 2;
   int ri = 1;
   
   for(auto v : verts){
      
      while(li <= v.x){
         for(int i : right[ri]){
            curset.erase(i);
         }
         for(int i : left[li]){
            curset.insert(i);
         }
         li++;
         ri++;
      }
      
      
      //if(curset.lower_bound(v.y1) != curset.upper_bound(v.y2)) 
      //   return false;
      auto lb = curset.lower_bound(v.y1);
      if(lb != curset.end() && *lb <= v.y2) return false;
   }
   //cout << "true" << endl;
   return true;
}

int bs(const vector<long long>& order, long long x){
   //dumb code to find the minimum index that is > x
   int n = order.size();
   int l = 0;
   int r = n-1;
   int ans = n;
   while(l <= r){
      int mid = l + (r-l)/2;
      
      if(order[mid] > x){
         ans = mid;
         r = mid-1;
      } else {
         l = mid+1;
      }
   }
   
   return ans;
}  

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<pair<char,long long>> array;
   long long maxd = 0LL;
   for(int k = 0; k < n; k++){
      char c;
      long long x;
      cin >> c >> x;
      array.push_back(make_pair(c,x));
      maxd += x;
   }
   
   //compress all reached points
   long long ix = 0LL;
   long long iy = 0LL;
   gp_hash_table<long long,null_type> seen;
   seen.insert(0LL);
   for(int k = 0; k < n; k++){
      long long dist = array[k].second;
         
      if(array[k].first == 'U'){
         seen.insert(iy+1);
         seen.insert(iy+dist);
         iy += dist;
      } 
      else if(array[k].first == 'D'){
         seen.insert(iy-dist);
         seen.insert(iy-1);
         iy -= dist;
      } 
      else if(array[k].first == 'R'){
         seen.insert(ix+1);
         seen.insert(ix+dist);
         ix += dist;
      } 
      else if(array[k].first == 'L'){
         seen.insert(ix-dist);
         seen.insert(ix-1);
         ix -= dist;
      }
   }
   
   vector<long long> order;
   order.push_back(LLONG_MIN);             //1-indexed
   for(long long i : seen) order.push_back(i);
   sort(order.begin(),order.end());
   
   gp_hash_table<long long,int,chash> compress;
   for(int k = 1; k < order.size(); k++){
      compress[order[k]] = k;              //1-indexed
   }
   int zero = compress[0LL];
   
   long long l = 1LL;
   long long r = maxd;
   long long ans = maxd;
   while(l <= r){
      long long mid = l + (r-l)/2LL;
      //cout << mid << endl;
      //generate ranges
      long long xl = 0LL;
      long long yl = 0LL;
      //get last value
      long long d = 0LL;
      for(int k = 0; k < n; k++){
         long long dist = array[k].second;
         if(d + array[k].second >= mid){
            dist = mid-d;
         }
         
         if(array[k].first == 'U'){
            yl += dist;
         } 
         else if(array[k].first == 'D'){
            yl -= dist;
         } 
         else if(array[k].first == 'R'){
            xl += dist;
         } 
         else if(array[k].first == 'L'){
            xl -= dist;
         }
         //cout << x << " " << y << endl;
         d += array[k].second;
         if(d >= mid){
            break;
         }
      }
      
      //thresh stores the compressed value for the last value
      int thresh = INT_MAX;
      if(compress.find(xl) == compress.end()) thresh = bs(order,xl);
      if(compress.find(yl) == compress.end()) thresh = bs(order,yl);
      //cout << "thresh " << thresh << endl;
      auto a1 = [thresh](int x, bool cutted){
         return (cutted || x < thresh) ? x : x+1;
      };
      
      //x and y are compress values of true x and y
      int curzero = (thresh <= zero) ? zero+1 : zero;
      int x = zero;
      int y = zero;
      d = 0LL;
      vector<Vert> verts;
      vector<Hori> horis;
      //add a vertical at 0,0
      verts.push_back({curzero,curzero,curzero});
      bool sub = false;
      for(int k = 0; k < n; k++){
         long long dist = array[k].second;
         bool cut = false;
         if(d + array[k].second >= mid){
            dist = mid-d;
            cut = true;
         }
         
         if(array[k].first == 'U'){
            bool cutted = cut && compress.find(order[y]+dist) == compress.end();
            int newy = (cutted ? thresh : compress[order[y]+dist]);
            verts.push_back({a1(x,false),a1(compress[order[y]+1],false),a1(newy,cutted)});
            y = newy;
         } 
         else if(array[k].first == 'D'){
            bool cutted = cut && compress.find(order[y]-dist) == compress.end();
            int newy = (cutted ? thresh : compress[order[y]-dist]);
            verts.push_back({a1(x,false),a1(newy,cutted),a1(compress[order[y]-1],false)});
            y = newy;
         } 
         else if(array[k].first == 'R'){
            bool cutted = cut && compress.find(order[x]+dist) == compress.end();
            int newx = (cutted ? thresh : compress[order[x]+dist]);
            horis.push_back({a1(compress[order[x]+1],false),a1(newx,cutted),a1(y,false)});
            x = newx;
         } 
         else if(array[k].first == 'L'){
            bool cutted = cut && compress.find(order[x]-dist) == compress.end();
            int newx = (cutted ? thresh : compress[order[x]-dist]);
            horis.push_back({a1(newx,cutted),a1(compress[order[x]-1],false),a1(y,false)});
            x = newx;
         }
         //cout << x << " " << y << endl;
         d += array[k].second;
         if(d >= mid){
            sub = (k > 0 && isopp(array[k].first,array[k-1].first));
            break;
         }
      }
      
      sort(verts.begin(),verts.end());
      sort(horis.begin(),horis.end());
      //cout << "done sort" << endl;
      
      if(!check(verts,horis,order.size()+1)){
         ans = mid;
         if(sub) ans--;
         r = mid-1;
      } 
      else {
         l = mid+1;
      }
      
   }
   
   cout << ans << endl;
   
   
   return 0;
}