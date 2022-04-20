import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "@/views/login";
import index from "@/views/index";
import test from "@/views/test";
import test2 from "@/views/test2";
import tutorClass from "@/views/tutorClass";
import register from "@/views/register";

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
    component: index,
    children:[
      {
        path:"/test1",
        name:"test1",
        component: test
      },
      {
        path:"/test2",
        name:"test2",
        component: test2
      },
      {
        path:"/tutor/class",
        name:"tutorClass",
        component: tutorClass
      }
    ]
  },
  {
    path:"/login",
    name:"login",
    component: login
  },
  {
    path:"/register",
    name:"register",
    component: register
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
