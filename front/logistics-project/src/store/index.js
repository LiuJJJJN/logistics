import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:localStorage.getItem("token"), //将state中的token设为localStorage中的token值
    userInfo:JSON.parse(localStorage.getItem("userInfo")), //将state中的userInfo设为localStorage中的userInfo值
    timeStamp:localStorage.getItem("timeStamp") //将 state 中的 timeStamp 设为 localStorage 中的 timestamp 的值
  },
  getters: {
    getUser: state => { //获取 state 中的 userInfo
      return state.userInfo;
    },
    getToken: state => { //获取 state 中的 token
      if (state.token == null) { //是第一次登录
        return '';
      }
      return state.token;
    },
    getTimeStamp: state => {
      return state.timeStamp;
    }
  },
  mutations: {
    SET_TOKEN:(state, token)=>{ //设置 state 中的 token
      state.token = token;
      localStorage.setItem("token", token);
    },
    SET_USERINFO:(state, userInfo)=>{ //设置 state 中的 userInfo
      state.userInfo = userInfo;
      localStorage.setItem("userInfo", JSON.stringify(userInfo));
    },
    SET_TIME_STAMP: (state, timeStamp)=>{ //设置 state 中的 timeStamp
      state.timeStamp = timeStamp;
      localStorage.setItem("timeStamp", timeStamp);
    },
    REMOVE_INFO:(state)=>{ //删除 state 和 localStorage 中的 token 和 userInfo
      state.token = '';
      state.userInfo = {};
      state.timeStamp = null;
      localStorage.setItem("token", '');
      localStorage.setItem("userInfo", JSON.stringify(''));
      localStorage.setItem("timeStamp", null);
    }
  },
  actions: {
  },
  modules: {
  }

})
