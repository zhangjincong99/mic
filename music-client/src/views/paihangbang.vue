<template>
    <div class="rankings" style="text-align: center;">
      <h1>歌单评分排行榜</h1>
      <ul>
        <li v-for="item in songRankings" :key="item.title">
          <span>{{ item.title }}</span>
          <span>评分: {{ item.score }}</span>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        rankList: [
          [2, 7],
          [2, 3],
          [1, 4],
          [13, 5],
          [21, 5],
          [31, 5],
          [5, 0],
          [11, 4],
          [10, 10],
          [6, 6],
          [7, 10],
          [1, 4],
          [7, 2],
          [3, 5],
          [14, 9],
        ],
        songRankings: [],
      };
    },
    mounted() {
      this.calculateRankings();
    },
    methods: {
      calculateRankings() {
        const averageScores = this.rankList.map((item) => {
          const songListId = item[0];
          const score = item[1];
          return { songListId, score };
        });
  
        averageScores.sort((a, b) => b.score - a.score);
  
        const songListDetails = {
          2: { title: '歌单2' },
          1: { title: '歌单1' },
          13: { title: '歌单13' },
          21: { title: '歌单21' },
          31: { title: '歌单31' },
          5: { title: '歌单5' },
          11: { title: '歌单11' },
          10: { title: '歌单10' },
          6: { title: '歌单6' },
          7: { title: '歌单7' },
          3: { title: '歌单3' },
          14: { title: '歌单14' },
        };
  
        this.songRankings = averageScores.map((item) => {
          const songListId = item.songListId;
          const title = songListDetails[songListId].title;
          const score = item.score.toFixed(1);
          return { title, score };
        });
      },
      updateScore(songListId, newScore) {
        // 找到要更新的歌单项并更新评分
        const updatedIndex = this.rankList.findIndex((item) => item[0] === songListId);
        if (updatedIndex !== -1) {
          this.rankList[updatedIndex][1] = newScore;
          // 重新计算歌单评分排行榜
          this.calculateRankings();
        }
      },
    },
  };
  </script>
  
  <style>
  .rankings ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }
  
  .rankings li {
    display: flex;
    justify-content: space-between;
    margin-bottom: 5px;
  }
  </style>
  