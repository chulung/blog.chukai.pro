import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = () => new Vuex.Store({
  state: {
    isAuthenticated: false
  },
  mutations: {
    changeLoginedStatus (state, auth) {
      state.isAuthenticated = auth
    }
  }
})

export default store
