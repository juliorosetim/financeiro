module.exports = {
  root: true,
  env: {
    node: true,
  },
  extends: [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/eslint-config-typescript',
  ],
  rules: {
    'vue/multi-word-component-names': 'off',
    ident: ['error', 2],
    'comma-spacing': ['error', { before: false, after: true }],
    semi: ['error', 'always']
  },
}
