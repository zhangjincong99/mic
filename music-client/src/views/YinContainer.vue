<template>
  <!-- 整体布局容器 -->
  <el-container>
    <!-- 头部区域 -->
    <el-header>
      <yin-header></yin-header>
    </el-header>
    <!-- 主要内容区域 -->
    <el-main>
      <!-- 路由视图 -->
      <router-view />
      <!-- 当前播放信息 -->
      <yin-current-play></yin-current-play>
      <!-- 播放控制条 -->
      <yin-play-bar></yin-play-bar>
      <!-- 返回顶部按钮 -->
      <yin-scroll-top></yin-scroll-top>
      <!-- 音频播放组件 -->
      <yin-audio></yin-audio>
    </el-main>
    <!-- 页脚区域 -->
    <el-footer>
      <yin-footer></yin-footer>
    </el-footer>
  </el-container>
</template>

<script lang="ts" setup>
import { getCurrentInstance } from "vue";
import YinHeader from "@/components/layouts/YinHeader.vue";
import YinCurrentPlay from "@/components/layouts/YinCurrentPlay.vue";
import YinPlayBar from "@/components/layouts/YinPlayBar.vue";
import YinScrollTop from "@/components/layouts/YinScrollTop.vue";
import YinFooter from "@/components/layouts/YinFooter.vue";
import YinAudio from "@/components/layouts/YinAudio.vue";

// 获取当前组件实例
const { proxy } = getCurrentInstance();

// 如果在 sessionStorage 中存在数据存储，则恢复 Vuex 状态
if (sessionStorage.getItem("dataStore")) {
  proxy.$store.replaceState(Object.assign({}, proxy.$store.state, JSON.parse(sessionStorage.getItem("dataStore"))));
}

// 在页面刷新前将 Vuex 状态存储到 sessionStorage 中
window.addEventListener("beforeunload", () => {
  sessionStorage.setItem("dataStore", JSON.stringify(proxy.$store.state));
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.el-container {
  min-height: calc(100% - 60px);
  /* 设置最小高度为视口高度减去顶部高度 */
}

.el-header {
  padding: 0;
  /* 清除头部区域的内边距 */
}

.el-main {
  padding-left: 0;
  padding-right: 0;
}
</style>
