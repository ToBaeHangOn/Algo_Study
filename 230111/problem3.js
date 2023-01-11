const solution = (n, edge) => {
  // 그래프의 연관 관계 형태로 변환
  const connects = new Array(n).fill().map((_) => []);
  for (const e of edge) {
    connects[e[0] - 1].push(e[1] - 1);
    connects[e[1] - 1].push(e[0] - 1);
  }

  const visited = [1]; // deps임과 동시에 반환값에 개수로 사용할 것이므로 바로 1로 시작할 수 있게 초기화
  const queue = [0];

  while (queue.length) {
    const cur = queue.shift();

    // 현재노드(cur)와 연결된 노드들을 돌아가며
    for (const next of connects[cur]) {
      // 연결된 노드 중 지금 차례의 노드(next)가
      // 아직 방문하지 않은 상태라면
      if (!visited[next]) {
        // 방문처리함과 동시에 그 값을 현재 deps + 1로 삽입
        visited[next] = visited[cur] + 1;
        queue.push(next);
      }
    }
  }

  const max = Math.max(...visited);

  return visited.filter((el) => el === max).length;
};
