import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'

Vue.config.productionTip = false
Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    user: null,
    token: null
  },
  mutations: {
    setUser (state, user) {
      state.user = user
    },
    setToken (state, token) {
      state.token = token
    }
  },
})

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')

export { store }