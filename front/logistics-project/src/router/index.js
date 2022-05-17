import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "@/views/login";
import index from "@/views/index";
import tutorFeedback from "@/views/tutorFeedback";
import stuFeedback from "@/views/stuFeedback";
import register from "@/views/register";
import stuRegister from "@/views/stuRegister";
import tutorRegister from "@/views/tutorRegister";
import notFoundPage from "@/views/notFoundPage";
import welcomePage from "@/views/welcomePage";
import tutorAndAdminStuRole from "@/views/tutorAndAdminStuRole";
import adminTutorRole from "@/views/adminTutorRole";
import adminDicType from "@/views/adminDicType";
import adminDicValue from "@/views/adminDicValue";
import userInfo from "@/views/userInfo";
import tutorAndAdminStuManage from "@/views/tutorAndAdminStuManage";
import adminTutorManage from "@/views/adminTutorManage";
import adminCarousel from "@/views/adminCarousel";
import tutorMyStudent from "@/views/tutorMyStudent";
import adminBuildingManage from "@/views/adminBuildingManage";
import adminDormManage from "@/views/adminDormManage";
import stuDorm from "@/views/stuDorm";
import stuDormChange from "@/views/stuDormChange";
import tutorAndAdminDormChangeApply from "@/views/tutorAndAdminDormChangeApply";
import adminLibraryManage from "@/views/adminLibraryManage";

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
        component: stuFeedback
      },
      {
        path:"/tutor/feedback",
        name:"feedback",
        component: tutorFeedback
      },
      {
        path:"/tutor/studentManage",
        name:"stuManage",
        component: tutorAndAdminStuManage
      },
      {
        path:"/tutor/myStudent",
        name:"tutorMyStudent",
        component: tutorMyStudent
      },
      {
        path:"/admin/studentRole",
        name:"studentRole",
        component: tutorAndAdminStuRole
      },
      {
        path:"/admin/tutorRole",
        name:"tutorRole",
        component: adminTutorRole
      },
      {
        path:"/admin/dicType",
        name:"dicType",
        component: adminDicType
      },
      {
        path:"/admin/dicValue",
        name:"dicValue",
        component: adminDicValue
      },
      {
        path:"/admin/studentManage",
        name:"stuManage",
        component: tutorAndAdminStuManage
      },
      {
        path:"/admin/tutorManage",
        name:"tutorManage",
        component: adminTutorManage
      },
      {
        path:"/admin/buildingManage",
        name:"buildingManage",
        component: adminBuildingManage
      },
      {
        path:"/admin/carousel",
        name:"adminCarousel",
        component: adminCarousel
      },
      {
        path:"/admin/dormManage",
        name:"dormManage",
        component: adminDormManage
      },
      {
        path:"/stu/myDorm",
        name:"myDorm",
        component: stuDorm
      },
      {
        path:"/stu/changeDorm",
        name:"changeDorm",
        component: stuDormChange
      },
      {
        path:"/admin/dormChange",
        name:"tutor&AdminDormChangeApply",
        component: tutorAndAdminDormChangeApply
      },
      {
        path:"/admin/library",
        name:"adminLibraryManage",
        component: adminLibraryManage
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
