import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Example from '@/components/Example'
import Main from '@/components/Main'
import Login from '@/components/Login'
import FormExample from '@/components/FormExample'
import TableExample from '@/components/TableExample'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/main',
      name: 'Main',
      component: Main,
      children: [
        {
          path: '/main/example',
          name: 'Example',
          component: Example
        },
        {
          path: '/main/form',
          name: 'FormExample',
          component: FormExample
        },
        {
          path: '/main/table',
          name: 'TableExample',
          component: TableExample
        }
      ]
    }
  ]
})
