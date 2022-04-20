import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "@/views/login";
import index from "@/views/index";
import test from "@/views/test";
// import container from "@/views/container";

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"login",
    component: login
  },
  {
    path:"/index",
    name:"index",
    component: index
  },
  {
    path:"/login",
    name:"login",
    component: login
  },
  {
    path:"/test",
    name:"test",
    component: test
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
