import Vue from 'vue';
import Vuex from 'Vuex';
Vue.use(Vuex);

import shopStore from './shopStore/index.js';
import questionStore from './questionStore/index.js';
import createPersistedState from "vuex-persistedstate"
// vuex-persistedstate --save

export default new Vuex.Store({
  modules:{
    shopStore:shopStore,
    questionStore:questionStore
  },
  plugins: [createPersistedState({
    storage: window.sessionStorage
  })]
})
