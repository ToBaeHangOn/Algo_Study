const solution = (users, emoticons) => {
  // 할인율 퍼센테이지
  const salePercent = [10, 20, 30, 40];
  // 가능한 경우의 모든 할인율
  const cases = [];
  const arr = [];
  const emoLen = emoticons.length;
  const result = [0, 0];
  const saleDFS = (depth = 0) => {
    if (depth === emoLen) {
      cases.push([...arr]);
      return;
    }
    for (let i = 0; i < salePercent.length; i++) {
      arr[depth] = salePercent[i];
      saleDFS(depth + 1);
    }
  };
  saleDFS();
  // cases배열 순회
  cases.forEach((curCase, curCaseIdx) => {
    // 해당 할인율의 이모티콘 플러스 가입자 수
    let emoticonPlus = 0;
    // 해당 할인율의 소득
    let sumPrice = 0;
    // users배열 순회
    users.forEach(([buyPercent, buyPlus], userIdx) => {
      let price = 0;
      let etPlusFlag = false;
      // emoticons배열 순회
      emoticons.every((et, etIdx) => {
        // 이번 할인율이 유저의 요구 할인율보다 높은 경우 즉시 구매
        if (curCase[etIdx] >= buyPercent) {
          price += (et * (100 - curCase[etIdx])) / 100;
        }
        // 할인액이 유저의 요구 금액을 넘긴다면 이모티콘 플러스 구입
        if (price >= buyPlus) {
          etPlusFlag = true;
          return false;
        }
        return true;
      });
      // 이모티콘 플러스를 구매하는 사람인가 판별
      if (etPlusFlag) emoticonPlus++;
      else sumPrice += price;
    });
    // 이번 할인율이 가장 많은 이모티콘 플러스 사용자 수를 기록했는가
    if (emoticonPlus > result[0]) {
      result[0] = emoticonPlus;
      result[1] = sumPrice;
      // 이번 할인율이 이모티콘 플러스 사용자 수가 같지만, 총 소득이 더 높은가
    } else if (result[0] === emoticonPlus && sumPrice > result[1]) {
      result[1] = sumPrice;
    }
  });
  return result;
};
