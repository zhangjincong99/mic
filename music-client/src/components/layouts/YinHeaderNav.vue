<template>
  <!-- 头部导航菜单 -->
  <ul class="yin-header-nav">
    <!-- 循环渲染菜单项 -->
    <li :class="{ active: item.name === activeName }" v-for="item in styleList" :key="item.path" @click="handleChangeView(item)">
      {{ item.name }}
    </li>
  </ul>
</template>
<!-- 代码本身没错因为高亮显示的问题，所以让我们觉得出错了 -->


<script lang="ts">
import { defineComponent, getCurrentInstance } from "vue";

export default defineComponent({
  props: {
    styleList: Array, // 样式列表数据
    activeName: String, // 当前激活项名称
  },
  emits: ["click"], // 定义自定义事件 "click"
  setup() {
    const { proxy } = getCurrentInstance();

    // 处理切换视图的方法
    function handleChangeView(item) {
      proxy.$emit("click", item.path, item.name); // 触发自定义事件 "click"，并传递相关参数
    }

    return {
      handleChangeView,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

/* 菜单项样式 */
li {
  margin: $header-nav-margin; /* 设置外边距 */
  padding: $header-nav-padding; /* 设置内边距 */
  line-height: 3.3rem; /* 设置行高 */
  color: $color-grey; /* 设置字体颜色 */
  border-bottom: none; /* 清除下边框 */
  cursor: pointer; /* 设置光标样式为手型 */
}

/* 激活的菜单项样式 */
li.active {
  color: $color-black; /* 设置激活项的字体颜色为黑色 */
  font-weight: 600; /* 设置激活项的字体加粗 */
  border-bottom: 5px solid $color-black; /* 添加底部边框并设置颜色为黑色 */
}
</style>
