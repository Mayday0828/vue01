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
      { path: 'user', name: 'User', component: () => import( '../views/manager/User.vue')}
    ]

  },
  {
    path: '/register',
    name: 'register',
    component: () => import( '../views/RegisterView.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
