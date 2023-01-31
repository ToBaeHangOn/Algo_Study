const fs = require("fs");
let [n, k] = fs.readFileSync("./dev/stdin").toString().split(" ");
k = Number(k);
n = Number(n);
[fastest, way] = [10 ** 6, 0];
const q = [];

q.push([n, 0]);
const visit = new Array(10001).fill(false);
visit[n] = true;
while (q) {
  [now, time] = q.shift();
  // 똑같은 지점에 도달하는 방법이 여러개 존재할 수 있으므로 pop한 후 방문 여부 확인
  visit[now] = true;
  // 똑같은 시간대에 동생 위치에 또 도착
  if (now === k && time <= fastest) {
    fastest = Math.min(fastest, time);
    way += 1;
  }
  if (time > fastest) {
    break; // 큐에는 시간 순으로 들어가므로 fastest보다 커지면 탐색을 종료
  }

  for (x of [now - 1, now + 1, now * 2]) {
    if (x <= 10001 && x > 0 && visit[x] === false) {
      q.push([x, time + 1]);
    }
  }
}

console.log(fastest);
console.log(way);
