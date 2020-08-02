import babelpolyfill from 'babel-polyfill'
import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
//import './assets/theme/theme-green/index.css'
import VueRouter from 'vue-router'
import store from './vuex/store'
import Vuex from 'vuex'
//import NProgress from 'nprogress'
//import 'nprogress/nprogress.css'
import {routes} from './routes'
import Mock from './mock'
Mock.bootstrap();
import 'font-awesome/css/font-awesome.min.css';
import dialogCus from'./views/comp/index.js';
import MyRaido from'./views/comp/index.js';
import './views/comp/directives.js'


Vue.use(dialogCus);
Vue.use(MyRaido);
Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(Vuex)

//NProgress.configure({ showSpinner: false });

const router = new VueRouter({
//	mode:'history',
  routes
})

router.beforeEach((to, from, next) => {
  //NProgress.start();
  if (to.path == '/login' || to.path == '/login_hospital') {
    sessionStorage.removeItem('user');
  }
  let user = JSON.parse(sessionStorage.getItem('user'));
  let hosFlag=sessionStorage.getItem('hosFlag')||0;
  if (!user && to.path != '/login' && to.path != '/login_hospital') {
    if(hosFlag!=1)next({ path: '/login' });
    else next({path:'/login_hospital'});
  } else {
    next()
  }
})

//router.afterEach(transition => {
//NProgress.done();
//});

export const Vm= new Vue({
  //el: '#app',
  //template: '<App/>',
  router,
  store,
  //components: { App }
  render: h => h(App)
}).$mount('#app')

