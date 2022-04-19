import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:sessionStorage.getItem("token"), //将state中的token设为localStorage中的token值
    userInfo:JSON.parse(sessionStorage.getItem("userInfo")) //将state中的userInfo设为localStorage中的userInfo值
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
    }
  },
  mutations: {
    SET_TOKEN:(state, token)=>{ //设置 state 中的 token
      state.token = token;
      sessionStorage.setItem("token", token);
    },
    SET_USERINFO:(state, userInfo)=>{ //设置 state 中的 userInfo
      state.userInfo = userInfo;
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
    },
    REMOVE_INFO:(state)=>{ //删除 state 和 localStorage 中的 token 和 userInfo
      state.token = '';
      state.userInfo = {};
      sessionStorage.setItem("token", '');
      sessionStorage.setItem("userInfo", JSON.stringify(''));
    }
  },
  actions: {
  },
  modules: {
  }

})
