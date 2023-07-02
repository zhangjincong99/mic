import { createApp } from "vue";
import ElementPlus from "element-plus";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "element-plus/dist/index.css";
import "./assets/css/index.scss";
import "./assets/icons/index.js";

import { ComponentCustomProperties } from "vue";
import { Store } from "vuex";
declare module "@vue/runtime-core" {
  interface State {
    count: number;
  }

  interface ComponentCustomProperties {
    $store: Store<State>;
  }
}

createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
// 它通过createApp函数创建一个Vue应用程序实例，并通过.use()方法依次使用导入的插件和模块。最后，通过.mount("#app")将应用程序挂载到具有id为app的HTML元素上。