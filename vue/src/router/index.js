import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import( '../views/LoginView.vue')
  },
  {
    path: '/element',
    name: 'Element',
    component: () => import( '../views/Element.vue')
  },

  {
    path: '/index',
    name: 'index',
    component: () => import( '../views/Manager.vue'),
    redirect: '/index/home',
    children: [
      { path: 'home', name: 'Home', component: () => import( '../views/manager/HomeView.vue')},
      { path: 'user', name: 'User', component: () => import( '../views/manager/User.vue')},
      {path: 'person', name: 'Person', component: ()=> import('../views/manager/Person.vue')},
      {path: 'password', name: 'Password', component: ()=> import('../views/manager/Password.vue')},
      { path: '403', name: 'Auth', component: () => import( '../views/Auth.vue')},
      { path: 'monkey', name: 'Monkey', component: () => import( '../views/manager/Monkey.vue')},
      { path: 'massage', name: 'MonkeyMassage', component: () => import( '../views/manager/MonkeyMassage.vue')}
    ]
  },

  {
    path: '/register',
    name: 'register',
    component: () => import( '../views/RegisterView.vue')
  },
  { path: '*', name: '404', component: () => import( '../views/404.vue')},
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {
  // to 是到达的路由信息
  // from 是开源的路由信息
  // next 是帮助我们跳转路由的函数
  let adminPaths = ['/index/user',]
  let user = JSON.parse(localStorage.getItem('honey-user') || '{}')

  if (user.role !== '管理员' && adminPaths.includes(to.path)) {
    // 如果当前登录的用户不是管理员，然后当前的到达的路径是管理员才有权限访问的路径，那这个时候我就让用户去到一个没有权限的页面，不让他访问实际的页面
    next('/index/403')
  } else {
    next()
  }
})

export default router
