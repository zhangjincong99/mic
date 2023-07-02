<!-- 这段代码实现了一个简单的页脚组件。下面是代码的主要功能和实现：

在模板部分，使用一个 <div> 元素表示整个页脚。通过使用 v-for 指令遍历 footerList 数组中的每一项，并使用 :key 绑定每一项的索引值。
在每个 <p> 元素中显示数组中的内容，通过插值表达式 {{ item }} 来显示每一项的文本内容。
在 <script> 部分，使用 defineComponent 定义了一个 Vue 组件。

在 setup 部分，定义了一个名为 footerList 的只读数组，包含了页脚需要显示的内容。
使用 readonly 函数将数组声明为只读，这样可以防止在组件中修改该数组的内容。
将 footerList 返回，以便在模板中使用。
在 <style> 部分，使用了 SCSS 语法定义了样式，并将其限定在当前组件内。

.yin-footer 类表示页脚的样式。
设置了宽度为 100%，高度为预设的 $footer-height 变量值，使用盒模型的 padding 进行上下内边距。
设置了字体大小为 14px。
使用 mixin 的 layout 混合指令实现居中布局，使内容在垂直方向上居中显示。
p 元素样式设置了固定高度为 30px。
这段代码实现了一个简单的页脚组件，通过遍历数组并显示其中的内容，将相关信息展示在页面的底部。 -->

<template>
  <div class="yin-footer">
    <p v-for="(item, index) in footerList" :key="index">
      {{ item }}
    </p>
  </div>
</template>

<script lang="ts">
import { defineComponent, readonly } from "vue";

export default defineComponent({
  setup() {
    const footerList = readonly([
      "关于 | 帮助 | 条款 | 反馈",
      "Copyright © 2019 Yin-Hongwei",
    ]);

    return { footerList };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.yin-footer {
  width: 100%;
  height: $footer-height;
  padding: 20px 0;
  box-sizing: border-box;
  font-size: 14px;
  @include layout(center, center, column);
}

p {
  height: 30px;
}
</style>
