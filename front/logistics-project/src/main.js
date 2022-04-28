import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import "./axios"

Vue.prototype.$axios = axios

Vue.use(ElementUI);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

/**
 * 每次路由前都验证 localStorage 是否过期
 */
router.beforeEach((to, from, next)=> {
  var rememberMe = store.getters.getRememberMe;
  var timeStamp = store.getters.getTimeStamp;
  var nowTimeStamp = new Date().getTime();
  if (to.fullPath === '/login' ||
      to.fullPath === '/register' ||
      to.fullPath === '/register/stuRegister' ||
      to.fullPath === '/register/tutorRegister') { //如果访问的是 登录 / 注册 页面则 放行
    next();
  } else if (rememberMe == null) { // localStorage 为空
    ElementUI.Message.error('您已退出登录, 请重新登录', {duration:3*1000});
    next("/login");
  } else {
    var addTime = 0;
    if (rememberMe) { //勾选了 7 天免登录
      addTime = 1000 * 60 * 60 * 24 * 7;
    } else { //未勾选 7 天免登录时
      addTime = 1000 * 60 * 30;
    }
    if (nowTimeStamp < timeStamp) { // 未过期
      store.commit("SET_TIME_STAMP", nowTimeStamp + addTime);
      next();
    } else { //已过期
      store.commit("REMOVE_INFO");
      ElementUI.Message.error('免登录时间已过或长时间未操作, 请重新登录', {duration:3*1000});
      next("/login");
    }
  }
});
