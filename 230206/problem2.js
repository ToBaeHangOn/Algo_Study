const solution = (storey) => {
  let answer = 0;
  while (storey) {
    let cur = storey % 10;
    let next = Math.floor(storey / 10) % 10;
    if (cur > 5) {
      answer += 10 - cur;
      storey += 10;
    } else if (cur == 5) {
      answer += 5;
      storey += next >= 5 ? 10 : 0;
    } else answer += cur;
    storey = Math.floor(storey / 10);
  }
  return answer;
};
