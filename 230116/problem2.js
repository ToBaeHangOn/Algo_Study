const solution = (orders, course) => {
  let answer = [];
  let obj = {};

  //course 배열의 모든 수와 orders의 모든 문자열의 조합을 만들기 위해 2중으로 map을 구성하였다.
  //combination의 반환 값으로 조합의 배열이 넘어 오는데 배열의 요소(메뉴 구성 ex AB,)를 key로 하여 중복되어 나온 수를 카운트한다.
  course.map((num) => {
    orders.forEach((menu) => {
      combination(menu.split(""), num).map((el) => {
        const word = el.sort().join("");
        obj[word] ? (obj[word] += 1) : (obj[word] = 1);
      });
    });
  });

  course.map((num) => {
    let maxNum = 0;
    for (const key in obj) {
      if (obj[key] === 1) continue; //코스요리 메뉴는 최소 2가지 이상 되어야 함으로 1의 값은 continue한다.
      if (key.length === num) {
        obj[key] > maxNum ? (maxNum = obj[key]) : ""; //중복되는 숫자가 있을 수 있어 가장 높은 수를 찾은 다음 그 수에 맞는 요리를 선택했다.
      }
    }
    let temp = Object.keys(obj).filter((key) => {
      //찾은 가장 높은 수와 같은 요리 조합을 filter로 걸러내서 temp에 담는다.
      return obj[key] === maxNum && key.length === num;
    });
    temp;
    temp.map((el) => answer.push(el)); //중복되는 값은 2개가 나올 수 있어서 하나씩 push한다.
  });

  return answer.sort(); //사전 순서대로 정렬
};

//조합을 만드는 함수.
const combination = (arr, num) => {
  const result = [];
  if (num === 1) return arr.map((el) => [el]);

  arr.forEach((fix, idx, array) => {
    const restArray = array.slice(idx + 1);
    const combiArr = combination(restArray, num - 1);
    const combiFix = combiArr.map((el) => [fix, ...el]);

    result.push(...combiFix);
  });

  return result;
};
