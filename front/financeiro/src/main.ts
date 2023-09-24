/**
 * main.ts
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Components
import App from './App.vue'
import '@mdi/font/css/materialdesignicons.css'

// Composables
import { createApp } from 'vue'
import { createPinia } from 'pinia';

// Plugins
import { registerPlugins } from '@/plugins'
import vuetify from './plugins/vuetify';


const app = createApp(App)
const pinia = createPinia();

app.use(vuetify)

registerPlugins(app)

app.mount('#app')
