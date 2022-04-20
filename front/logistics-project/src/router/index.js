import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "@/views/login";
import index from "@/views/index";
import test from "@/views/test";
import test2 from "@/views/test2";
import tutorClass from "@/views/tutorClass";
import register from "@/views/register";
import tutorFeedback from "@/views/tutorFeedback";
import userIndex from "@/views/userIndex";
import userFeedback from "@/views/userFeedback";
import tutorStudent from "@/views/tutorStudent";

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"login",
    component: login
  },
  {
    path:"/test1",
    name:"test1",
    component: test
  },
  {
    path:"/index",
    name:"index",
    component: index,
    children:[
      {
        path:"/",
        name:"userIndex",
        component: userIndex
      },{
        path:"/user",
        name:"userIndex",
        component: userIndex
      },
      {
        path:"/user/feedback",
        name:"userFeedback",
        component: userFeedback
      },
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
      },
      {
        path:"/tutor/feedback",
        name:"feedback",
        component: tutorFeedback
      },
      {
        path:"/tutor/student",
        name:"tutorStudent",
        component: tutorStudent
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
