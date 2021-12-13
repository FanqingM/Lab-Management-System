import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/state'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import XLSX from 'xlsx'

Vue.config.productionTip = false

Vue.use(Element)
Vue.use(XLSX)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
