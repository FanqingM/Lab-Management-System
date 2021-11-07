import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () =>
      import('../pages/share/Login.vue')
  },

  //学生页面
  {
    path: "/student",
    component: () =>
      import('../pages/student/StudentFrame.vue'),
    // meta: {
    //     title: '学生',
    //     requireAuth: true,
    //     roles: ['student']
    // },
    children: [{
      path: "home",
      name: "StudentHome",
      component: () =>
        import('../pages/student/StudentHome.vue'),
      // meta: {
      //     title: '个人信息',
      //     requireAuth: true,
      //     roles: ['student']
      // }
    },
    {
      path: "experiment",
      name: "StudentExperiment",
      component: () =>
        import('../pages/student/StudentExperiment.vue'),
      // meta: {
      //     title: '个人信息',
      //     requireAuth: true,
      //     roles: ['student']
      // }
    },    
    {
      path: "score",
      name: "StudentScore",
      component: () =>
        import('../pages/student/StudentScore.vue'),
      // meta: {
      //     title: '个人信息',
      //     requireAuth: true,
      //     roles: ['student']
      // }
    },
    {
      path: "account",
      name: "StudentAccount",
      component: () =>
        import('../pages/student/StudentAccount.vue'),
      // meta: {
      //     title: '个人信息',
      //     requireAuth: true,
      //     roles: ['student']
      // }
    },
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
