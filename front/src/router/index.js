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

    //教师
    {
        path: "/teacher",
        component: () =>
            import('../pages/teacher/TeacherFrame.vue'),
        // meta: {
        //     title: '学生',
        //     requireAuth: true,
        //     roles: ['student']
        // },
        children: [{
            path: "home",
            name: "TeacherHome",
            component: () =>
                import('../pages/teacher/TeacherHome.vue'),
            // meta: {
            //     title: '个人信息',
            //     requireAuth: true,
            //     roles: ['student']
            // }
        },
        {
            path: "section",
            name: "TeacherSection",
            component: () =>
                import('../pages/teacher/TeacherSection.vue'),
            // meta: {
            //     title: '个人信息',
            //     requireAuth: true,
            //     roles: ['student']
            // }
        },
        ]
    },
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
            path: "report",
            name: "StudentReport",
            component: () =>
                import('../pages/student/StudentReport.vue'),
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
        path: "/admin",
        component: () =>
            import('../pages/admin/AdminFrame.vue'),
        // meta: {
        //     title: '管理员',
        //     requireAuth: true,
        //     roles: ['admin']
        // },
        children: [{
            path: "home",
            name: "AdminHome",
            component: () =>
                import('../pages/admin/AdminHome.vue'),
            // meta: {
            //     title: '管理员',
            //     requireAuth: true,
            //     roles: ['admin']
            // },
        },
        {
            path: "verification",
            name: "AdminVerifyList",
            component: () =>
                import('../pages/admin/AdminVerifyList.vue'),
            // meta: {
            //     title: '管理员',
            //     requireAuth: true,
            //     roles: ['admin']
            // },
        },
        {
            path: "verification-detail",
            name: "AdminVerify",
            component: () =>
                import('../pages/admin/AdminVerify.vue'),
            // meta: {
            //     title: '管理员',
            //     requireAuth: true,
            //     roles: ['admin']
            // },
        },
        {
            path: "user-management",
            name: "AdminUserList",
            component: () =>
                import('../pages/admin/AdminUserList.vue'),
            // meta: {
            //     title: '个人信息',
            //     requireAuth: true,
            //     roles: ['student']
            // }
        }
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
