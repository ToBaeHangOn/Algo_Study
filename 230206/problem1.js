const toMinute = (array) => {
  return array
    .map((time) =>
      time.map((value, index) => {
        const times = value.split(":");
        if (index === 0) {
          return 60 * Number(times[0]) + Number(times[1]);
        }
        return 60 * Number(times[0]) + Number(times[1]) + 10;
      })
    )
    .sort((a, b) => a[0] - b[0]);
};

const solution = (book_time) => {
  const newBookTime = toMinute(book_time);
  let room = 0;
  for (let i = 0; i < newBookTime.length; i += 1) {
    if (newBookTime[i].length === 3) {
      continue;
    }
    room += 1;
    newBookTime[i].push(true);
    let end = newBookTime[i][1];
    let j = i + 1;
    while (j < newBookTime.length) {
      if (i === newBookTime.length - 1) {
        break;
      }
      if (newBookTime[j][0] - end >= 0 && newBookTime[j].length !== 3) {
        newBookTime[j].push(true);
        end = newBookTime[j][1];
      }
      j += 1;
    }
  }
  return room;
};
