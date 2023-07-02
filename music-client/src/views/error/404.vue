<template>
  <!-- 错误页面容器 -->
  <div class="error-page">
    <!-- 错误代码 -->
    <div class="error-code">404</div>
  </div>
</template>

<style scoped>
/* 错误页面样式 */
.error-page {
  display: flex;
  /* 使用弹性布局 */
  justify-content: center;
  /* 水平居中对齐 */
  align-items: stretch;
  /* 垂直方向拉伸填充 */
  flex-direction: row;
  /* 主轴为水平方向 */
  flex-wrap: nowrap;
  /* 不换行 */
  width: 100%;
  /* 宽度占满父容器 */
  height: 100%;
  /* 高度占满父容器 */
  padding: 11rem 0;
  /* 上下边距为11个单位 */
  box-sizing: border-box;
  /* 盒模型为边框盒模型 */
}

/* 错误代码样式 */
.error-code {
  font-size: 250px;
  /* 字体大小为250像素 */
  font-weight: bolder;
  /* 字体加粗 */
  color: #000000;
  /* 字体颜色为黑色 */
}</style>
