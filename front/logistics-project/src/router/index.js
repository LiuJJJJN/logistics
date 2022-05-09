import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "@/views/login";
import index from "@/views/index";
import tutorFeedback from "@/views/tutorFeedback";
import feedback from "@/views/stuFeedback";
import register from "@/views/register";
import stuRegister from "@/views/stuRegister";
import tutorRegister from "@/views/tutorRegister";
import notFoundPage from "@/views/notFoundPage";
import welcomePage from "@/views/welcomePage";
import stuRole from "@/views/tutorAndAdminStuRole";
import tutorRole from "@/views/adminTutorRole";
import dicType from "@/views/adminDicType";
import dicValue from "@/views/adminDicValue";
import userInfo from "@/views/userInfo";
import stuManage from "@/views/tutorAndAdminStuManage";
import tutorManage from "@/views/adminTutorManage";
import adminCarousel from "@/views/adminCarousel";
import tutorMyStudent from "@/views/tutorMyStudent";
import buildingManage from "@/views/adminBuildingManage";
import dormManage from "@/views/adminDormManage";
import stuDorm from "@/views/stuDorm";
import changeDorm from "@/views/stuDormChange";
import tutorAndAdminDormChangeApply from "@/views/tutorAndAdminDormChangeApply";

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
        path:"/stu/feedback",
        name:"feedback",
        component: feedback
      },
      {
        path:"/tutor/feedback",
        name:"feedback",
        component: tutorFeedback
      },
      {
        path:"/tutor/studentManage",
        name:"stuManage",
        component: stuManage
      },
      {
        path:"/tutor/myStudent",
        name:"tutorMyStudent",
        component: tutorMyStudent
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
        path:"/admin/buildingManage",
        name:"buildingManage",
        component: buildingManage
      },
      {
        path:"/admin/carousel",
        name:"adminCarousel",
        component: adminCarousel
      },
      {
        path:"/admin/dormManage",
        name:"dormManage",
        component: dormManage
      },
      {
        path:"/stu/myDorm",
        name:"myDorm",
        component: stuDorm
      },
      {
        path:"/stu/changeDorm",
        name:"changeDorm",
        component: changeDorm
      },
      {
        path:"/admin/dormChange",
        name:"tutor&AdminDormChangeApply",
        component: tutorAndAdminDormChangeApply
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
  base: 'logistics',
  routes
})

export default router
