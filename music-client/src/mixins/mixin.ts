import { getCurrentInstance, computed } from "vue";
import { useStore } from "vuex";
import { LocationQueryRaw } from "vue-router";
import { RouterName } from "@/enums";
import { HttpManager } from "@/api";

interface routerOptions {
  path?: string;
  query?: LocationQueryRaw;
}

export default function () {
  const { proxy } = getCurrentInstance();

  const store = useStore();
  const token = computed(() => store.getters.token);

  // 获取用户性别
  function getUserSex(sex: number): string | undefined {
    if (sex === 0) {
      return "女";
    } else if (sex === 1) {
      return "男";
    }
    return undefined;
  }

  // 获取歌曲名
  function getSongTitle(str: string): string {
    return str.split("-")[1];
  }

  // 获取歌手名
  function getSingerName(str: string): string {
    return str.split("-")[0];
  }

  // 检查登录状态
  function checkStatus(status?: boolean): boolean {
    if (!token.value) {
      if (status !== false) {
        (proxy as any).$message({
          message: "请先登录",
          type: "warning",
        });
      }
      return false;
    }
    return true;
  }

  // 播放音乐
  function playMusic({
    id,
    url,
    pic,
    index,
    name,
    lyric,
    currentSongList,
  }: {
    id: string;
    url: string;
    pic: string;
    index: number;
    name: string;
    lyric: string;
    currentSongList: any[];
  }): void {
    const songTitle = getSongTitle(name);
    const singerName = getSingerName(name);
    proxy.$store.dispatch("playMusic", {
      id,
      url,
      pic,
      index,
      songTitle,
      singerName,
      lyric,
      currentSongList,
    });
  }

  // 下载音乐
  async function downloadMusic({
    songUrl,
    songName,
  }: {
    songUrl: string;
    songName: string;
  }): Promise<void> {
    if (!songUrl) {
      (proxy as any).$message({
        message: "下载链接为空！",
        type: "error",
      });
      console.error("下载链接为空！");
      return;
    }

    const result = (await HttpManager.downloadMusic(
      songUrl
    )) as ResponseBody;

    const eleLink = document.createElement("a");
    eleLink.download = `${songName}.mp3`;
    eleLink.style.display = "none";
    const blob = new Blob([result.data]);
    eleLink.href = URL.createObjectURL(blob);
    document.body.appendChild(eleLink);
    eleLink.click();
    document.body.removeChild(eleLink);
  }

  // 更改导航索引
  function changeIndex(value: string | number): void {
    proxy.$store.commit("setActiveNavName", value);
  }

  // 路由管理
  function routerManager(
    routerName: string | number,
    options: routerOptions
  ): void {
    switch (routerName) {
      case RouterName.Search:
        proxy.$router.push({ path: options.path, query: options.query });
        break;
      case RouterName.Home:
      case RouterName.SongSheet:
      case RouterName.SongSheetDetail:
      case RouterName.Singer:
      case RouterName.SingerDetail:
      case RouterName.Personal:
      case RouterName.PersonalData:
      case RouterName.Setting:
      case RouterName.SignIn:
      case RouterName.SignUp:
      case RouterName.SignOut:
      case RouterName.Lyric:
      case RouterName.Error:
      default:
        proxy.$router.push({ path: options.path });
        break;
    }
  }

  // 返回上一页
  function goBack(step = -1): void {
    proxy.$router.go(step);
  }

  return {
    getUserSex,
    getSongTitle,
    getSingerName,
    changeIndex,
    checkStatus,
    playMusic,
    routerManager,
    goBack,
    downloadMusic,
  };
}
