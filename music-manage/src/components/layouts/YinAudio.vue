<template>
  <div>
    <audio ref="audioElement"></audio>
    <button @click="togglePlay">Toggle Play</button>
  </div>
</template>

<script>
import { nextTick, ref } from 'vue';

export default {
  setup() {
    const audioRef = ref(null);
  
    // 开始/暂停
    function togglePlay() {
      nextTick(() => {
        const audioElement = audioRef.value;
        if (audioElement) {
          if (audioElement.paused) {
            audioElement.play().catch((error) => {
              console.error("Failed to play audio:", error);
            });
          } else {
            audioElement.pause();
          }
        }
      });
    }

    return {
      audioRef,
      togglePlay
    };
  }
};
</script>

<style>
audio {
  display: none;
}
</style>
