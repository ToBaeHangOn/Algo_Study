const bfs = (start, table, k) => {
  //bfs를 통해 빈공간 또는 남은 퍼즐 조각의 그림 저장 하기
  let needVisit = start;
  let block = [];
  const dx = [0, 0, 1, -1];
  const dy = [1, -1, 0, 0];
  while (needVisit.length > 0) {
    let [cx, cy] = needVisit.shift();
    block.push([cx, cy]);
    table[cx][cy] = k;
    for (let i = 0; i < 4; i++) {
      let nx = cx + dx[i];
      let ny = cy + dy[i];
      if (nx < 0 || ny < 0 || nx >= table.length || ny >= table.length)
        continue;
      else if (table[nx][ny] === k) continue;
      else {
        needVisit.push([nx, ny]);
        table[nx][ny] = k;
      }
    }
  }
  return block;
};
