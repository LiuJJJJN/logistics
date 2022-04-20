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
 * 每次路由前都验证state中的timeStamp时间戳是否过期
 * 如果没过期: 再次设置为 7 天后, next 放行
 * 如果过期了: 强制跳转到 /login , 删除 localStorage 中的相关信息
 */
router.beforeEach((to, from, next)=>{
  var TimeStamp = store.getters.getTimeStamp;
  alert(to.fullPath);
  //如果访问的是 登录 / 注册 页面则 放行
  if(to.fullPath == '/login' || to.fullPath == '/register'){
    next();
  } else if (TimeStamp) {
    var nowTimeStamp = new Date().getTime();
    //判断是否已经过期
    if (nowTimeStamp > TimeStamp){
      //清除 localStorage 中所有内容
      localStorage.clear();
      //提示错误
      ElementUI.Message.error('免登录时间已过, 请重新登录');
      //跳转至登录页面
      next("/login");
    } else {
      // 时间戳未过期 放行
      next();
    }
  } else {
    //清除 localStorage 中所有内容
    localStorage.clear();
    //提示错误
    ElementUI.Message.error('您已退出登录, 请重新登录');
    next("/login");
  }
})
