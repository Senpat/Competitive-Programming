#include <bits/stdc++.h>
using namespace std;


vector<vector<int>> flip(const vector<vector<int>>& board){
    int n = board.size();
    int m = board[0].size();
    vector<vector<int>> ret(m,vector<int>(n));
    for(int k = 0; k < n; k++){
        for(int j = 0; j < m; j++){
            ret[j][k] = board[k][j];
        }
    }
    return ret;
}


int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n,m;
    cin >> n >> m;

    vector<vector<int>> board(n,vector<int>(m));
    for(int k = 0; k < n; k++){
        string s;
        cin >> s;
        for(int j = 0; j < m; j++){
            board[k][j] = s[j]-'0';
        }
    }

    if(m > n){
        //flip
        board = flip(board);
        swap(n,m);
    }

    vector<vector<int>> prev(m,vector<int>(m,INT_MAX));

    //total number of 1s in the first row
    int r0tot1 = 0;
    for(int k = 0; k < m; k++){
        r0tot1 += board[0][k];
    }

    //only l = 0
    for(int l = 0; l < 1; l++){
        int num1 = 0;           //number of 1s in the rnage
        int rangeans = 0;            //cur answer
        for(int r = l; r < m; r++){
            num1 += board[0][r];
            rangeans += 1-board[0][r];
            //dp[l][r] = # of flips to make that [l,r] all 1 and 0 outside
            prev[l][r] = rangeans + (r0tot1 - num1);

            //cout << prev[l][r] << " ";
        }
        //cout << endl;
    }

    for(int k = 1; k < n; k++){
        //cout << k << endl;
        int rtot = 0;           //total in row
        //prefix mins
        vector<vector<int>> pmin(m,vector<int>(m)); 
        for(int r = 0; r < m; r++){
            pmin[0][r] = prev[0][r];
            for(int l = 1; l <= r; l++){
                pmin[l][r] = min(pmin[l-1][r], prev[l][r]);
            }
            rtot += board[k][r];
        }

        vector<vector<int>> next(m,vector<int>(m,INT_MAX));

        for(int l = 0; l < m; l++){
            int num1 = 0;
            int rangeans = 0;
            int curmin = INT_MAX;
            if(l > 0){
               curmin = pmin[l-1][l-1];
            }
            for(int r = l; r < m; r++){
                num1 += board[k][r];
                rangeans += 1-board[k][r];
                curmin = min(curmin,pmin[l][r]);
                if(curmin != INT_MAX){
                    next[l][r] = curmin + rangeans + (rtot - num1);
                }
            }
        }


        swap(prev,next);

    }

    int answer = INT_MAX;
    for(int l = 0; l < m; l++){
        answer = min(answer,prev[l][m-1]);
    }

    cout << answer << endl;




}