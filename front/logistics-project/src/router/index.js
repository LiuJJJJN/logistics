import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "@/views/login";
import index from "@/views/index";
import tutorClass from "@/views/tutorClass";
import tutorFeedback from "@/views/tutorFeedback";
import userFeedback from "@/views/userFeedback";
import tutorStudent from "@/views/tutorStudent";
import register from "@/views/register";
import stuRegister from "@/views/stuRegister";
import tutorRegister from "@/views/tutorRegister";
import notFoundPage from "@/views/notFoundPage";
import welcomePage from "@/views/welcomePage";
import stuRole from "@/views/stuRole";
import tutorRole from "@/views/tutorRole";
import dicType from "@/views/dicType";
import dicValue from "@/views/dicValue";
import userInfo from "@/views/userInfo";
import stuManage from "@/views/stuManage";
import tutorManage from "@/views/tutorManage";
import userMyFeedback from "@/views/userMyFeedback";

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
    path:"/register",
    name:"register",
    component: register,
    children: [
      {
        path:"stuRegister",
        name:"stuRegister",
        component: stuRegister,
      },
      {
        path:"tutorRegister",
        name:"tutorRegister",
        component: tutorRegister,
      }
    ]
  },
  {
    path:"/index",
    name:"index",
    component: index,
    children:[
      {
        path:"/",
        name:"welcomePage",
        component: welcomePage
      },
      {
        path:"/userInfo",
        name:"userInfo",
        component: userInfo
      },
      {
        path:"/user/userFeedback",
        name:"userFeedback",
        component: userFeedback
      },
      {
        path:"/user/userMyFeedback",
        name:"userMyFeedback",
        component: userMyFeedback
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
        path:"/tutor/studentManage",
        name:"tutorStudent",
        component: tutorStudent
      },
      {
        path:"/admin/studentRole",
        name:"studentRole",
        component: stuRole
      },
      {
        path:"/admin/tutorRole",
        name:"tutorRole",
        component: tutorRole
      },
      {
        path:"/admin/dicType",
        name:"dicType",
        component: dicType
      },
      {
        path:"/admin/dicValue",
        name:"dicValue",
        component: dicValue
      },
      {
        path:"/admin/studentManage",
        name:"stuManage",
        component: stuManage
      },
      {
        path:"/admin/tutorManage",
        name:"tutorManage",
        component: tutorManage
      },
      {
        path: "/*",
        name: "notFoundPage",
        component: notFoundPage
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  base: 'DJTULogistics',
  routes
})

export default router
