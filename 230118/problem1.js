const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

let n = Number(input[0]);
let arr = new Array(n).fill(new Array(n));
for (let i = 0; i < arr.length; i++) {
  arr[i] = input[i + 1].split(" ").map(Number);
}
let dp = new Array(n);
for (let i = 0; i < dp.length; i++) {
  dp[i] = new Array(n).fill(BigInt(0));
}
dp[0][0] = BigInt(1);
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    let num = arr[i][j];
    if (num === 0) continue;
    if (i + arr[i][j] < n) {
      //아래로 점프를 했을때 범위를 벗어나지 않는 경우 이동했을때의 좌표의 DP값에 현재 좌표의 DP값을 더해준다
      dp[i + arr[i][j]][j] += dp[i][j];
    }
    if (j + arr[i][j] < n) {
      //오른쪽으로 점프를 했을때 범위를 벗어나지 않는 경우 이동했을때의 좌표의 DP값에 현재 좌표의 DP값을 더해준다
      dp[i][j + arr[i][j]] += dp[i][j];
    }
  }
}
console.log(dp[n - 1][n - 1].toString());
