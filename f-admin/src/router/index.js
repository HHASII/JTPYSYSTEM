import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import EmpManage from '@/components/emp/EmpManage'
import EmpLearning from '@/components/emp/EmpLearning'
import EmpProject from '@/components/emp/EmpProject'
import EmpTalking from '@/components/emp/EmpTalking'
import EmpAdmin from '@/components/emp/EmpAdmin'
import EmpTeamtsf from '@/components/emp/EmpTeamtsf'
import EmpStudyDecoration from '@/components/emp/EmpStudyDecoration'
import EmpMsg from '@/components/emp/EmpMsg'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    }, {
      path: '/home',
      name: '主页',
      component: Home,
      hidden: true,
      meta: {
        requireAuth: true
      },
      children: [
        {
          path:"/home",
          redirect:"/emp/basic",
        },
        {
          path:"/emp/prize",
          name: '获奖情况',
          component: EmpManage,
          hidden: true,
          meta: {keepAlive: false, requireAuth: true}
        },
        {
          path:"/emp/project",
          name:'项目经历',
          component:EmpProject,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
        {
          path:"/emp/learning",
          name:'培训经历',
          component:EmpLearning,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
        {
          path:"/emp/talking",
          name:'校长访谈',
          component:EmpTalking,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
        {
          path:"/emp/admin",
          name:'用户管理',
          component:EmpAdmin,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
        
        {
          path:"/emp/teamtsf",
          name: '团队变更',
          component: EmpTeamtsf,
          hidden: true,
          meta: {keepAlive: false, requireAuth: true}
        },
        {
          path:"/emp/EmpStudyDecoration",
          name: '学习申报',
          component: EmpStudyDecoration,
          hidden: true,
          meta: {keepAlive: false, requireAuth: true}
        },
        {
          path:"/emp/EmpMsg",
          name: '信息通知',
          component: EmpMsg,
          hidden: true,
          meta: {keepAlive: false, requireAuth: true}
        }
      ]
    }

  ]
})
