const fs = require("fs");
let [n, k] = fs.readFileSync("./dev/stdin").toString().split(" ");
const dp = [];
k = Number(k);
n = Number(n);

// dp 세팅
// N = 0일 때, DP[N][K] = 1
// K = 1일 때, DP[N][K] = 1
for (let i = 1; i <= k; i++) {
  dp[i] = new Array(n + 1).fill(i === 1 ? 1 : 0);

  dp[i][0] = 1;
}

// *점화식* DP[K][N] = DP[K-1][0] + DP[K-1][1] + ... + DP[K-1][N]
for (let i = 2; i <= k; i++) {
  for (let j = 1; j <= n; j++) {
    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000;
  }
}

console.log(dp[k][n]);
