<!-- 这段代码实现了一个返回顶部的功能按钮。下面是代码的主要功能和实现：
在模板部分，使用一个 <div> 元素来表示返回顶部的按钮。通过绑定 @click 事件监听器，当点击按钮时调用 returnTop 方法。
在 <script> 部分，使用 defineComponent 定义了一个 Vue 组件。
在 setup 部分，定义了 returnTop 方法。该方法实现了滚动回到页面顶部的逻辑。
首先，声明了一个变量 timer 用于保存动画帧的标识。
调用 cancelAnimationFrame 取消之前的动画帧请求，确保只有一个回到顶部的动画在执行。
获取当前的滚动位置，document.body.scrollTop 或 document.documentElement.scrollTop 表示页面滚动的距离。
设置回到顶部的动画时间为 500ms（T）。
使用 requestAnimationFrame 创建一个递归函数 func，在每一帧中更新滚动位置，实现平滑滚动效果。
在每一帧中计算时间差 diff，并根据时间差计算当前的时间 t，用于控制滚动位置的变化。
根据公式 S - (t * S) / T 计算新的滚动位置，并将其赋值给 document.documentElement.scrollTop 和 document.body.scrollTop，实现页面的滚动。
更新 timer 为新的动画帧标识。
当动画执行到达设定的时间 T 时，取消动画帧请求。
在 returnTop 方法之后，将其暴露给组件的模板部分使用。
在 <style> 部分，使用了 SCSS 语法定义了样式，并将其限定在当前组件内。
.scroll-top 类表示返回顶部按钮的样式。
设置了按钮的固定定位，位于页面的右下角。
设置了按钮的宽度、高度、背景色、边框、圆角和阴影效果。
鼠标悬停在按钮上时，显示一个指示文字 "回到顶部"。
.box-in 类表示按钮内部的小三角形样式，使用一个方块元素旋转并通过边框颜色实现三角形的效果。
这段代码实现了一个简单的返回顶部功能按钮，通过点击按钮时触发滚动动画，使页面平滑滚动到顶部位置。 -->


<template>
  <div class="scroll-top" @click="returnTop">
    <div class="box-in"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  setup() {
    function returnTop() {
      let timer: number = null;
      cancelAnimationFrame(timer);
      const startTime = new Date();
      const S = document.body.scrollTop || document.documentElement.scrollTop;
      const T = 500;
      timer = requestAnimationFrame(function func() {
        const diff: number = new Date().valueOf() - startTime.valueOf();
        const t = T - Math.max(0, T - diff);
        document.documentElement.scrollTop = document.body.scrollTop = S - (t * S) / T;
        timer = requestAnimationFrame(func);
        if (t === T) cancelAnimationFrame(timer);
      });
    }
    return { returnTop };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.scroll-top {
  position: fixed;
  z-index: 100;
  width: 40px;
  height: 20px;
  right: 10px;
  bottom: 80px;
  padding-top: 20px;
  text-align: center;
  background-color: $color-white;
  border-radius: 20%;
  overflow: hidden;
  box-shadow: 0 0 4px 3px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  &:hover:before {
    top: 50%;
  }
  &:hover .box-in {
    visibility: hidden;
  }
  &:before {
    content: "回到顶部";
    position: absolute;
    font-weight: bold;
    font-size: small;
    width: 30px;
    top: -50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
}

.box-in {
  visibility: visible;
  display: inline-block;
  height: 10px;
  width: 10px;
  border: 1px solid $color-black;
  border-color: $color-black transparent transparent $color-black;
  // 按钮中间的小三角翻转
  transform: rotate(45deg) translate(-5px, -5px);
}
</style>
