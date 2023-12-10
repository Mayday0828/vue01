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
    component: () => import( '../views/HomeView.vue')
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
