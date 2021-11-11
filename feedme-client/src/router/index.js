import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import ViewRecipe from '../views/ViewRecipe.vue'
import Account from '../views/Account.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import { store } from '../main'
Vue.use(VueRouter)


const ifAuthenticated = async (to, from, next) => {
    if (store.state.user !== null && store.state.token !== null) {
        next()
        return
    }
    next('/login')
}

const ifNotAuthenticated = (to, from, next) => {
    if (store.state.user === null || store.state.token === null) {
        next()
        return
    }
    next('/account')
}

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/viewrecipe/:id?',
        name: 'ViewRecipe',
        component: ViewRecipe,
        props: route => ({ query: route.query.id })
    },
    {
        path: '/account',
        name: 'Account',
        component: Account,
        beforeEnter: ifAuthenticated
    },
    {
        path: '/login/:username?',
        name: 'Login',
        component: Login,
        beforeEnter: ifNotAuthenticated,
        props: route => ({query: route.query.id})
    },
    {
        path: '/register',
        name: 'Register',
        component: Register,
        beforeEnter: ifNotAuthenticated
    }
];

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

export default router