import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import home from '@/components/pages/home'
import baseInfo from '@/components/pages/aboutPersonal/baseInfo'

import register from '@/components/pages/aboutPersonal/register'

import declaration from '@/components/pages/aboutDeclaration/declaration'
import certificateDecoration from '@/components/pages/aboutDeclaration/certificateDecoration'
import ForgetPassword from '@/components/pages/Security/ForgetPassword'
// import ws from '@/components/pages/Security/ws'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    },
    {
      path: '/ForgetPassword',
      name: 'ForgetPassword',
      component: ForgetPassword
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      meta: {requireAuth: true},
      children: [
        {
          path: '/home',
          redirect: 'baseInfo'
        },
        {
          path: 'baseInfo',
          name: 'baseInfo',
          component: baseInfo,
          meta: {
            keepAlive: false, // 添加该字段，表示进入这个路由是需要登录的
            requireAuth: true // 此组件不需要被缓存
          }
        },
        {
          path: 'declaration',
          name: 'declaration',
          component: declaration,
          meta: {keepAlive: false, requireAuth: true}
        },
        {
          path: 'certificateDecoration',
          name: 'certificateDecoration',
          component: certificateDecoration,
          meta: {keepAlive: false, requireAuth: true}
        }
      ]
    },

  ],
  linkActiveClass: 'is-active'
})
