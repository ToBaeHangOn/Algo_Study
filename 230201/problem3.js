const solution = (n, lightHouse) => {
  const visited = new Array(n + 1).fill(false);
  let result = 0;
  while (lightHouse.length) {
    const map = new Array(n + 1).fill().map((_) => []);
    for (const el of lightHouse) {
      const [a, b] = el;
      map[a].push(b);
      map[b].push(a);
    }
    // 탐욕법
    map
      .filter((element) => element.length === 1)
      .forEach((element) => {
        const [target] = element;
        if (!visited[target]) {
          visited[target] = true;
          if (map[target].length !== 1) {
            result += 1;
          } else {
            // 양쪽 모두 있는 경우이므로 더하기가 두번일어난다.
            result += 0.5;
          }
        }
      });
    // 간선이 1개인 섬 모두 제거
    lightHouse = lightHouse.filter((el) => {
      const [a, b] = el;
      return !visited[a] && !visited[b];
    });
  }
  return result;
};
