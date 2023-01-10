const solution = (genres, plays) => {
  const answer = [];
  const songs = {};
  const playCount = {};
  // songs, playCount 객체 생성
  genres.forEach((genre, idx) => {
    if (!songs[genre]) songs[genre] = [];
    if (playCount[genre] == null || playCount[genre] == undefined) {
      playCount[genre] = 0;
    }
    const info = {
      genre,
      play: plays[idx],
      index: idx,
    };
    songs[genre].push(info);
    playCount[genre] += plays[idx];
  });
  // 재생수 객체를 배열로 변환
  const playCountArr = [];
  for (let key in playCount) {
    playCountArr.push({
      genre: key,
      count: playCount[key],
    });
  }
  // 재생수 배열을 내림차순 정렬 - 장르의 플레이 횟수대로 내림차순 정렬됨
  playCountArr.sort((a, b) => b.count - a.count);
  // playCountArr 배열을 순회하면서 장르마다 가장 재생수가 많은 곡 2곡씩 push
  playCountArr.forEach((el) => {
    const targetGenre = songs[el.genre];
    targetGenre.sort((a, b) => b.play - a.play);
    targetGenre[0] && answer.push(targetGenre[0].index);
    targetGenre[1] && answer.push(targetGenre[1].index);
  });
  console.log(songs, playCountArr, answer);
  return answer;
};
