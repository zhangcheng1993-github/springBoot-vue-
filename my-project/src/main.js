// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import $ from 'jquery';
import '../static/bootstrap-3.3.7-dist/css/bootstrap.css';
import '../static/bootstrap-3.3.7-dist/js/bootstrap.min';
import '../static/css/main.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 引入axiosUtil.js
import axiosUtil from './util/axiosUtil.js';
//引入Vuex总仓库
import store from './vuex/modules.js';

// 设置全局变量$axios
Vue.prototype.$axios = axiosUtil;
Vue.use(ElementUI);
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
