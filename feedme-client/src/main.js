import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import createPersistedState from 'vuex-persistedstate'

Vue.config.productionTip = false
Vue.use(Vuex)

const store = new Vuex.Store({
  plugins: [createPersistedState({
    storage: window.sessionStorage,
  })],
  state: {
    user: null,
    token: null,
    authenticated: false
  },
  mutations: {
    setUser (state, user) {
      state.user = user
    },
    setToken (state, token) {
      state.token = token
    },
    setAuth (state, val) {
      state.authenticated = val;
    }
  },
});


new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')

export { store }