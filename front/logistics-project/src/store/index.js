import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:localStorage.getItem("token"), //设置 token 值
    userInfo:JSON.parse(localStorage.getItem("userInfo")), //设置 userInfo 值
    rememberMe:localStorage.getItem("rememberMe"), //设置 rememberMe 值
    timeStamp:localStorage.getItem("timeStamp"), //设置 timestamp 值
    sessionId:localStorage.getItem("sessionId") //设置 sessionId 值
  },
  getters: {
    getUser: state => { //获取 userInfo
      return state.userInfo;
    },
    getToken: state => { //获取 token
      if (state.token == null) { //是第一次登录
        return '';
      }
      return state.token;
    },
    getRememberMe: state => { //获取 rememberMe
      return state.rememberMe;
    },
    getTimeStamp: state => { //获取 timeStamp
      return state.timeStamp;
    },
    getSessionId: state => { //获取 sessionId
      return state.sessionId;
    }
  },
  mutations: {
    SET_TOKEN:(state, token)=>{ //设置 token
      state.token = token;
      localStorage.setItem("token", token);
    },
    SET_USERINFO:(state, userInfo)=>{ //设置 userInfo
      state.userInfo = userInfo;
      localStorage.setItem("userInfo", JSON.stringify(userInfo));
    },
    SET_REMEMBER_ME: (state, rememberMe)=>{ //设置 rememberMe
      state.rememberMe = rememberMe;
      localStorage.setItem("rememberMe", rememberMe);
    },
    SET_TIME_STAMP: (state, timeStamp)=>{ //设置 timeStamp
      state.timeStamp = timeStamp;
      localStorage.setItem("timeStamp", timeStamp);
    },
    SET_SESSION_ID: (state, sessionId)=>{ //设置 sessionId
      state.sessionId = sessionId;
      localStorage.setItem("sessionId", sessionId);
    },
    REMOVE_INFO:(state)=>{ //删除所有数据
      state.token = '';
      state.userInfo = {};
      state.rememberMe = null;
      state.timeStamp = null;
      state.sessionId = null;
      localStorage.setItem("token", '');
      localStorage.setItem("userInfo", JSON.stringify({}));
      localStorage.setItem("rememberMe", null);
      localStorage.setItem("timeStamp", null);
      localStorage.setItem("sessionId", null);
    }
  },
  actions: {
  },
  modules: {
  }

})
