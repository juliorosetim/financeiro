/**
 * main.ts
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Components
import App from './App.vue';
import '@mdi/font/css/materialdesignicons.css';

// Composables
import { createApp } from 'vue';
import { createPinia } from 'pinia';

// Plugins
import { registerPlugins } from '@/plugins';
import Vuetify from './plugins/vuetify';
import 'vuetify/dist/vuetify.min.css';


const app = createApp(App);
const pinia = createPinia();

// app.use(vuetify)
app.use(Vuetify);

registerPlugins(app);

app.mount('#app');

