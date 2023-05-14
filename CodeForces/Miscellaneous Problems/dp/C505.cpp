#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    const int N = 30000;

    int n, d;
    cin >> n >> d;

    int array[N+1] = {0};
    for (int i = 1; i <= n; i++) {
        int x;
        cin >> x;
        array[x]++;
    }

    vector<unordered_map<int,int>> dp(N+1);
    
    auto set_max = [&dp](int i, int x, int y){
      if(dp[i][x] < y) dp[i][x] = y;
    };
    
    int answer = 0;
    set_max(d, d, array[d]);
    cout << "hi" << endl;
    for (int k = d; k <= N; k++) {
        for (const auto &p : dp[k]) {
            int i = p.first;
            int v = p.second;
            answer = max(answer, v);

            //jump to k+i-1, k+i, k+i+1
            for (int di = -1; di <= 1; di++) {
                if (k+i+di <= N && i+di >= 1) {
                    set_max(k+i+di, i+di, v+array[k+i+di]);
                }
            }
        }
    }

    cout << answer << "\n";

    return 0;
}
