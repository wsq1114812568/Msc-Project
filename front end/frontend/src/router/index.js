import Vue from 'vue'
import Router from 'vue-router'
import Main from '../components/Main'

Vue.use(Router)

export default new Router({
  mode: 'history',//去掉#号
  routes: [
  
    {
      path: '/',
      name: 'Main',
      component: Main
    }
  ]
})
