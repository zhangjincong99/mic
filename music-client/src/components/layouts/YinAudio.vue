<!-- 这段代码实现了一个音频播放器组件。下面是代码的主要功能和实现：

在模板部分，使用 <audio> 元素来表示音频播放器。

:src 绑定了 songUrl，该值是从 Vuex store 中获取的音乐链接。
controls 属性显示音频控件，包括播放/暂停按钮、进度条和音量控制。
preload 属性设置为 true，表示在页面加载后预加载音频。
@canplay 事件在音频加载过程中触发。
@timeupdate 事件在音频播放位置更新时触发。
@ended 事件在音频播放结束时触发。
在 <script> 部分，使用 defineComponent 定义了一个 Vue 组件。

在 setup 部分，通过 getCurrentInstance 获取当前组件实例的 proxy 对象。
使用 useStore 函数获取 Vuex store 的实例。
使用 ref 创建一个 ref 对象 divRef，用于引用 <audio> 元素。
使用 player 函数将 <audio> 元素的引用赋值给 divRef。
使用 computed 创建计算属性，分别获取从 Vuex store 中获取的音乐链接 songUrl、播放状态 isPlay、音量 volume、指定播放时刻 changeTime 和自动播放下一首的标志 autoNext。
使用 watch 监听 isPlay、changeTime 和 volume 的变化，分别在变化时执行相应的操作。
togglePlay 函数用于切换播放/暂停状态，根据 isPlay 的值调用 play() 或 pause() 方法。
canplay 函数在音频准备好播放时触发，将音频的总时长记录下来，并开始播放音频。
timeupdate 函数在音频播放过程中触发，记录当前播放位置。
ended 函数在音频播放结束时触发，将播放状态设为停止，并重置播放位置，同时触发自动播放下一首的标志。
将相关的属性、方法和辅助函数返回，以便在模板中使用。
在 <style> 部分，使用了 scoped 属性限定样式的作用范围，仅适用于当前组件。

audio 元素的样式设置为 display: none，表示隐藏音频元素。
这段代码实现了一个简单的音频播放器组件，通过绑定音频链接、监听播放状态和时间更新事件来控制音频的播放和暂停，并根据播放状态和事件更新播放位置和自动播放下一首的标志。 -->

<template>
  <audio :src="attachImageUrl(songUrl)" controls="controls" :ref="player" preload="true" @canplay="canplay" @timeupdate="timeupdate" @ended="ended">
    <!--（1）属性：controls，preload（2）事件：canplay，timeupdate，ended（3）方法：play()，pause() -->
    <!--controls：向用户显示音频控件（播放/暂停/进度条/音量）-->
    <!--preload：属性规定是否在页面加载后载入音频-->
    <!--canplay：当音频/视频处于加载过程中时，会发生的事件-->
    <!--timeupdate：当目前的播放位置已更改时-->
    <!--ended：当目前的播放列表已结束时-->
  </audio>
</template>

<script lang="ts">
import { defineComponent, ref, getCurrentInstance, computed, watch } from "vue";
import { useStore } from "vuex";
import { HttpManager } from "@/api";

export default defineComponent({
  setup() {
    const { proxy } = getCurrentInstance();
    const store = useStore();
    const divRef = ref<HTMLAudioElement>();
    const player = (el) => {
      divRef.value = el;
    };

    const songUrl = computed(() => store.getters.songUrl); // 音乐链接
    const isPlay = computed(() => store.getters.isPlay); // 播放状态
    const volume = computed(() => store.getters.volume); // 音量
    const changeTime = computed(() => store.getters.changeTime); // 指定播放时刻
    const autoNext = computed(() => store.getters.autoNext); // 用于触发自动播放下一首
    // 监听播放还是暂停
    watch(isPlay, () => togglePlay());
    // 跳到指定时刻播放
    watch(changeTime, () => (divRef.value.currentTime = changeTime.value));
    watch(volume, (value) => (divRef.value.volume = value));

    // 开始 / 暂停
    function togglePlay() {
      isPlay.value ? divRef.value.play() : divRef.value.pause();
    }
    // 获取歌曲链接后准备播放
    function canplay() {
      //  记录音乐时长
      proxy.$store.commit("setDuration", divRef.value.duration);
      //  开始播放
      divRef.value.play();
      proxy.$store.commit("setIsPlay", true);
    }
    // 音乐播放时记录音乐的播放位置
    function timeupdate() {
      proxy.$store.commit("setCurTime", divRef.value.currentTime);
    }
    // 音乐播放结束时触发
    function ended() {
      proxy.$store.commit("setIsPlay", false);
      proxy.$store.commit("setCurTime", 0);
      proxy.$store.commit("setAutoNext", !autoNext.value);
    }

    return {
      songUrl,
      player,
      canplay,
      timeupdate,
      ended,
      attachImageUrl: HttpManager.attachImageUrl,
    };
  },
});
</script>

<style scoped>
audio {
  display: none;
}
</style>
