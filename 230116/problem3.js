const fs = require("fs");
let input = fs.readFileSync("/dev/stdin", "utf-8").trim().split("\n");
const info = input[0].split(" ");
const l = Number(info[0]);
const w = Number(info[1]);
const h = Number(info[2]);
const Map = [];
for (let i = 1; i < input.length; i++) {
  Map.push(input[i].split(" "));
}
const dividing = (x, y, z) => {
  let sol = 0;
  if (x == 0 || y == 0 || z == 0) {
    return 0;
  }
  for (let i = n - 1; i > 0; i--) {
    num = Map[idx][1];
    line = 2 ** Map[idx][0];

    if (num > 0 && line <= min(x, y, z)) {
      Map[idx][1] -= 1;
      sol += 1;
      tmp1 = dividing(line, y - line, line);
      tmp2 = dividing(line, y, z - line);
      tmp3 = dividing(x - line, y, z);
      break;
    }
    if (idx == 0 && num == 0) {
      return -1;
    }
  }
  if (tmp1 == -1 || tmp2 == -1 || tmp3 == -1) {
    return -1;
  }
  return sol;
};
tmp = dividing(l, w, h);
console.log(tmp);
