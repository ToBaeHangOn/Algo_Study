const sol = (board) => {
  let visited = new Array(101).fill(-1);

  let q = [];
  q.push(1);
  visited[1] = 0;

  while (q.length !== 0) {
    let cur = q.shift();
    for (let dice = 1; dice <= 6; dice++) {
      let next = cur + dice; // 주사위를 굴려서 나아갈 수 있는 칸의 번호.
      if (next > 100) continue;
      next = board[next]; // 해당칸에 뱀이나 사다리가 있다면 이용해야만 한다.
      if (visited[next] === -1) {
        visited[next] = visited[cur] + 1;
        q.push(next);
      }
    }
  }
  console.log(visited[100]);
};

const insert = () => {
  const input = require("fs")
    .readFileSync("뱀과 사다리 게임/input.txt")
    .toString()
    .trim()
    .split("\n");
  const [n, m] = input[0].split(" ").map(Number);

  let board = new Array(101).fill(null).map((_, idx) => idx);

  for (let i = 1; i <= n + m; i++) {
    // 사다리와 뱀.
    let [from, to] = input[i].split(" ").map(Number);
    board[from] = to;
  }

  sol(board);
};
insert();
