let input = fs.readFileSync(filePath()).toString().trim();

let X = Number(input);
let groupCounter = 0;

const up = [];
const down = [];

while (X > 0) {
  groupCounter++;
  X = X - groupCounter;
}

for (let i = 0; i < groupCounter; i++) {
  up.push(i + 1);
  down.push(groupCounter - i);
}

if (groupCounter % 2 === 0) {
  console.log(`${up[X + groupCounter - 1]}/${down[X + groupCounter - 1]}`);
} else {
  console.log(`${down[X + groupCounter - 1]}/${up[X + groupCounter - 1]}`);
}
