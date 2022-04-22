import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "@/views/login";
import index from "@/views/index";
import tutorClass from "@/views/tutorClass";
import tutorFeedback from "@/views/tutorFeedback";
import userIndex from "@/views/userIndex";
import userFeedback from "@/views/userFeedback";
import tutorStudent from "@/views/tutorStudent";
import register from "@/views/register";
import stuRegister from "@/views/stuRegister";
import tutorRegister from "@/views/tutorRegister";

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"login",
    component: login
  },
  {
    path:"/login",
    name:"login",
    component: login
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
    path:"/register",
    name:"register",
    component: register,
    children: [
      {
        path:"/register/stuRegister",
        name:"stuRegister",
        component: stuRegister,
      },
      {
        path:"/register/tutorRegister",
        name:"tutorRegister",
        component: tutorRegister,
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
