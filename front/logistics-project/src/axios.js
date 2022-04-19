import axios from "axios";
import router from './router';
import ElementUI from 'element-ui';

//配置 axios 每个链接的公共字符
axios.defaults.baseURL = "http://localhost:8080/logisticsProject";

//前置拦截
axios.interceptors.request.use(config => {
    return config; //不进行前置拦截
})

//后置拦截: 根据后端给前端的状态码进行拦截, 这样在其他页面就只需要处理 200 状态码的情况了
axios.interceptors.response.use(resp =>{
    let res = resp.data;
    if (res.code == 200){ // 200 成功 不拦截
        return resp;
    }else if(res.code == 401){ // 401 失败 显示失败信息, 跳转至 登录页面
        ElementUI.Message.error(res.message, {duration:3*1000});
        router.push("/login");
        return Promise.reject(res.message);
    }else { // 失败 显示失败信息
        ElementUI.Message.error(res.message, {duration:3*1000});
        return Promise.reject(res.message);
    }
})
