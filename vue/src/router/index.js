import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import( '../views/LoginView.vue')
  },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
  {
    path: '/element',
    name: 'Element',
    component: () => import( '../views/Element.vue')
  },
  {
    path: '/index',
    name: 'index',
    component: () => import( '../views/HomeView.vue')
  },
  {path: '/login',name:'Login',component:()=> import( '../views/LoginView')},

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
