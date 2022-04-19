import Vue from 'vue'
import VueRouter from 'vue-router'

import test from "@/views/test";
import login from "@/views/login";
import index from "@/views/index";

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"test",
    component:test
  },
  {
    path:"/login",
    name:"login",
    component: login
  },
  {
    path:"/index",
    name:"index",
    component: index
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
