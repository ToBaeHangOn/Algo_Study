const solution = (maps) => {
  let canLive = [];
  const column = maps.length;
  const row = maps[0].length;
  const newMap = maps.map((element) => element.split(""));
  const visited = Array(column)
    .fill(false)
    .map(() => Array(row).fill(false));
  for (let i = 0; i < row; i++) {
    for (let j = 0; j < column; j++) {
      if (newMap[j][i] === "X") continue;
      canLive.push(Bfs(i, j, column, row, newMap, visited));
    }
  }
  if (canLive.length !== 0) {
    const result = canLive.filter((element) => element >= 0);
    result.sort(function (a, b) {
      return a - b;
    });
    return result;
  }
  return [-1];
};

const Bfs = (x, y, column, row, maps, visited) => {
  const dx = [1, 0, -1, 0];
  const dy = [0, 1, 0, -1];
  let food = 0;
  const queue = [];
  if (visited[y][x] === true) {
    return -1;
  }
  food += Number(maps[y][x]);
  visited[y][x] = true;
  queue.push([x, y]);

  while (queue.length > 0) {
    let [cx, cy] = queue.shift();
    for (let i = 0; i < 4; i++) {
      let nx = cx + dx[i];
      let ny = cy + dy[i];
      if (nx < 0 || ny < 0 || nx >= row || ny >= column) continue;
      else if (visited[ny][nx] === true) continue;
      else if (maps[ny][nx] === "X") continue;
      else {
        food += Number(maps[ny][nx]);
        queue.push([nx, ny]);
        visited[ny][nx] = true;
      }
    }
  }
  return food;
};
