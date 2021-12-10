import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/UserManagement',
    component: Layout,
    redirect: '/UserManagement/table',
    name: 'UserManagement',
    meta: { title: 'UserManagement', icon: 'example' },
    children: [
      {
        path: 'UserList',
        name: 'UserList',
        component: () => import('@/views/UserManagement/list'),
        meta: { title: 'UserList', icon: 'table' }
      },
      {
        path: 'UserAdd',
        name: 'UserAdd',
        component: () => import('@/views/UserManagement/add'),
        meta: { title: 'UserAdd', icon: 'tree' }
      },
      {
        path: 'UserUpdate/:id',
        name: 'UserUpdate',
        component: () => import('@/views/UserManagement/update'),
        meta: { title: 'UserUpdate', icon: 'tree' },
        hidden:true
      },
      {
        path: 'OtherInformation',
        name: 'OtherInformation',
        component: () => import('@/views/UserManagement/otherInformation'),
        meta: { title: 'Other Information', icon: 'table' }
      },
      {
        path: 'UserFriend/:id',
        name: 'UserFriend',
        component: () => import('@/views/UserManagement/friend'),
        meta: { title: 'UserFriend', icon: 'tree' },
        hidden:true
      },
      {
        path: 'UserCompetition/:id',
        name: 'UserCompetition',
        component: () => import('@/views/UserManagement/competition'),
        meta: { title: 'UserCompetition', icon: 'tree' },
        hidden:true
      },

    ]
  },
  {
    path: '/AdminManagement',
    component: Layout,
    redirect: '/AdminManagement/table',
    name: 'AdminManagement',
    meta: { title: 'AdminManagement', icon: 'example' },
    children: [
      {
        path: 'AdminList',
        name: 'AdminList',
        component: () => import('@/views/AdminManagement/list'),
        meta: { title: 'AdminList', icon: 'table' }
      },
      {
        path: 'AdminAdd',
        name: 'AdminAdd',
        component: () => import('@/views/AdminManagement/add'),
        meta: { title: 'AdminAdd', icon: 'tree' }
      },
      {
        path: 'AdminUpdate/:id',
        name: 'AdminUpdate',
        component: () => import('@/views/AdminManagement/update'),
        meta: { title: 'AdminUpdate', icon: 'tree' },
        hidden:true
      }
    ]
  },
  {
    path: '/ClubManagement',
    component: Layout,
    redirect: '/ClubManagement/table',
    name: 'ClubManagement',
    meta: { title: 'ClubManagement', icon: 'example' },
    children: [
      {
        path: 'ClubList',
        name: 'ClubList',
        component: () => import('@/views/ClubManagement/list'),
        meta: { title: 'ClubList', icon: 'table' }
      },
      {
        path: 'ClubAdd',
        name: 'ClubAdd',
        component: () => import('@/views/ClubManagement/add'),
        meta: { title: 'ClubAdd', icon: 'plus-square' }
      },
      {
        path: 'ClubUpdate/:id',
        name: 'ClubUpdate',
        component: () => import('@/views/ClubManagement/update'),
        meta: { title: 'ClubUpdate', icon: 'tree' },
        hidden:true
      },
      {
        path: 'ClubCompetition/:id',
        name: 'ClubCompetition',
        component: () => import('@/views/ClubManagement/competition'),
        meta: { title: 'ClubCompetition', icon: 'folder' },
        hidden:true
      },
      {
        path: 'ClubTables/:id',
        name: 'ClubTables',
        component: () => import('@/views/ClubManagement/tables'),
        meta: { title: 'ClubTables', icon: 'folder' },
        hidden:true
      }
    ]
  },
  {
    path: '/Competition',
    component: Layout,
    redirect: '/Competition/table',
    name: 'Competition',
    meta: { title: 'Competition', icon: 'example' },
    children: [
      {
        path: 'CompetitionList',
        name: 'CompetitionList',
        component: () => import('@/views/Competition/list'),
        meta: { title: 'List', icon: 'table' }
      },
      {
        path: 'CompetitionAdd',
        name: 'CompetitionAdd',
        component: () => import('@/views/Competition/add'),
        meta: { title: 'Create ', icon: 'tree' }
      },
      {
        path: 'CompetitionUpdate/:id',
        name: 'CompetitionUpdate',
        component: () => import('@/views/Competition/update'),
        meta: { title: 'CompetitionUpdate', icon: 'tree' },
        hidden:true
      },
      {
        path: 'OtherInformation',
        name: 'OtherInformation',
        component: () => import('@/views/Competition/otherInformation'),
        meta: { title: 'Other Information ', icon: 'tree' }
      },
      {
        path: 'Participant/:id',
        name: 'Participant',
        component: () => import('@/views/Competition/participant'),
        meta: { title: 'participant', icon: 'tree' },
        hidden:true
      },
      {
        path: 'Result/:id',
        name: 'Result',
        component: () => import('@/views/Competition/result'),
        meta: { title: 'result', icon: 'tree' },
        hidden:true
      },
    ]
  },
  
  

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
