module.exports = {
  env: {
    baseUrl: 'https://blog.chukai.pro'
  },
  /*
   ** Headers of the page
   */
  head: {
    titleTemplate: '%s - 初开之道',
    meta: [
      {charset: 'utf-8'},
      {name: 'viewport', content: 'width=device-width, initial-scale=1'},
      {hid: 'description', name: 'description', content: '初开之道'}
    ],
    link: [
      {rel: 'icon', type: 'image/x-icon', href: '/img/favicon.ico'}
    ]
  },
  /*
   ** Customize the progress-bar color
   */
  loading: {color: '#3B8070'},
  /*
   ** Build configuration
   */
  build: {
    /*
     ** Run ESLINT on save
     */
    extend (config, ctx) {
      if (ctx.isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    },
    vendor: ['axios']
  },
  plugins: [ '~/plugins/vee-validate']
}
