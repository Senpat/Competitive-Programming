#include <bits/stdc++.h>

using namespace std;

//convex hull from https://cp-algorithms.com/geometry/convex-hull.html#implementation
struct pt {
    int x, y;
};

int orientation(pt a, pt b, pt c) {
    int v = a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y);
    if (v < 0) return -1; // clockwise
    if (v > 0) return +1; // counter-clockwise
    return 0;
}

bool cw(pt a, pt b, pt c, bool include_collinear) {
    int o = orientation(a, b, c);
    return o < 0 || (include_collinear && o == 0);
}
bool collinear(pt a, pt b, pt c) { return orientation(a, b, c) == 0; }

void convex_hull(vector<pt>& a, bool include_collinear = false) {
    pt p0 = *min_element(a.begin(), a.end(), [](pt a, pt b) {
        return make_pair(a.y, a.x) < make_pair(b.y, b.x);
    });
    sort(a.begin(), a.end(), [&p0](const pt& a, const pt& b) {
        int o = orientation(p0, a, b);
        if (o == 0)
            return (p0.x-a.x)*(p0.x-a.x) + (p0.y-a.y)*(p0.y-a.y)
                < (p0.x-b.x)*(p0.x-b.x) + (p0.y-b.y)*(p0.y-b.y);
        return o < 0;
    });
    
    vector<pt> norepeats;
    norepeats.push_back(a[0]);
    for(int k = 1; k < a.size(); k++){
      if(a[k].x != a[k-1].x || a[k].y != a[k-1].y) norepeats.push_back(a[k]);
    }
    a = norepeats;
    
    if (include_collinear) {
        int i = (int)a.size()-1;
        while (i >= 0 && collinear(p0, a[i], a.back())) i--;
        reverse(a.begin()+i+1, a.end());
    }

    vector<pt> st;
    for (int i = 0; i < (int)a.size(); i++) {
        while (st.size() > 1 && !cw(st[st.size()-2], st.back(), a[i], include_collinear))
            st.pop_back();
        st.push_back(a[i]);
    }

    a = st;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   while(true){
      int n;
      cin >> n;
      if(n == 0) break;
      vector<pt> points;
      for(int k = 0; k < n; k++){
         int x,y;
         cin >> x >> y;
         points.push_back({x,y});
      }
      
      convex_hull(points,false);
      reverse(points.begin(),points.end());
      cout << points.size() << "\n";
      for(const auto& p : points){
         cout << p.x << " " << p.y << "\n";
      }
      
   }
   
   return 0;
}