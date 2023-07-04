<template>
  <div>
    <h1>Song List Ranking</h1>
    <table>
      <thead>
        <tr>
          <th>Rank</th>
          <th>Title</th>
          <th>Score</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(songList, index) in songLists" :key="songList.id">
          <td>{{ index + 1 }}</td>
          <td>{{ songList.title }}</td>
          <td>{{ calculateAverageScore(songList.id) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { HttpManager } from "@/api";

export default {
  data() {
    return {
      songLists: [],
      rankData: [],
    };
  },
  mounted() {
    this.getSongLists();
    this.getRankData();
  },
  methods: {
    async getSongLists() {
      try {
        const response = await HttpManager.getSongList();
        this.songLists = response.data;
      } catch (error) {
        console.error("Error getting song lists:", error);
      }
    },
    async getRankData() {
      try {
        const songListIds = this.songLists.map(songList => songList.id);
        const promises = songListIds.map(songListId => HttpManager.getRankOfSongListId(songListId));
        const responses = await Promise.all(promises);
        this.rankData = responses.map(response => response.data);
      } catch (error) {
        console.error("Error getting rank data:", error);
      }

      // getRankData方法首先使用map方法从songLists数组中提取所有歌单的ID，并为每个歌单ID构建一个请求。然后，使用Promise.all方法等待所有请求完成，并使用map方法提取每个请求的响应数据，将其存储在rankData数组中。
      // calculateAverageScore方法用于计算给定歌单ID的平均评分。首先，我们通过filter方法从rankData数组中筛选出与给定歌单ID相匹配的评分数据。然后，我们使用map方法提取每个评分数据的分数，计算总分和平均分数。最后，我们返回计算得到的平均分数。

    },
    calculateAverageScore(songListId) {
      if (!this.rankData || !Array.isArray(this.rankData)) return 0;

      const filteredRankData = this.rankData
        .filter(rankData => rankData && rankData.songListId === songListId);
      if (filteredRankData.length === 0) return 0;

      const scores = filteredRankData.map(rankData => rankData.score);
      const sum = scores.reduce((accumulator, current) => accumulator + current, 0);
      const averageScore = sum / scores.length;
      return isNaN(averageScore) ? 0 : averageScore;
    },
  },
};
</script>
