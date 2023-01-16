const fs = require("fs");
let [N, K] = fs.readFileSync("/dev/stdin").toString().split(" ");
let num = 0;
let count = 0;

while (1) {
  let n = N;
  while (n > 0) {
    if (n % 2) {
      num++;
    }
    n = Math.floor(n / 2);
  }
  if (num <= K) {
    console.log(count);
    break;
  }
  num = 0;
  N++;
  count++;
}
