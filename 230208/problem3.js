const answer = [];
const solution = (s) => {
  for (let t = 0; t < s.length; t++) {
    let str = s[t];
    let stack = [];
    let target = findTarget(str, stack);
    if (target == "") answer.push(str);
    else {
      const tmpStr = stack.join("");
      const idx = tmpStr.lastIndexOf("0") + 1;
      answer.push(tmpStr.substring(0, idx) + target + tmpStr.substring(idx));
    }
  }
  return answer;
};
const findTarget = (str, stack) => {
  let target = "";
  for (let i = 0; i < str.length; i++) {
    const c = str.charAt(i);
    if (stack.length >= 2) {
      const b = stack.pop();
      const a = stack.pop();
      if (a == "1" && b == "1" && c == "0") {
        target += "110";
        continue;
      }
      stack.push(a);
      stack.push(b);
    }
    stack.push(c);
  }
  return target;
};
