import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createPinia } from 'pinia'
import 'vuetify/dist/vuetify.min.css';
import { registerPlugins } from '@/plugins';


const pinia = createPinia();

const app = createApp(App)

registerPlugins(app);

app.mount('#app')
