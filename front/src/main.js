import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/state'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import XLSX from 'xlsx'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import FullCalendar from 'vue-fullcalendar'

Vue.config.productionTip = false;

Vue.use(Element);
Vue.use(XLSX);
Vue.use(VueQuillEditor);
Vue.use(FullCalendar);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
