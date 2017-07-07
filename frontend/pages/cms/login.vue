<template>
  <div>
    <form class="form-horizontal" role="form">
      <div class="form-group">
        <label for="firstname" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
          <input type="text" id="firstname" v-validate="'required|alpha'" class="form-control" placeholder="UserName"
                 name="userName"
                 v-model="user.userName">
        </div>
      </div>
      <div class="form-group">
        <label for="password" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
          <input type="password" id="password" v-validate="'required'" class="form-control" placeholder="Password"
                 name="password"
                 v-model="user.password">
          <i v-show="error" class="fa fa-warning"></i>
          <span v-show="error"
                class="text-danger">用户名或密码错误！</span>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <div class="checkbox">
            <label>
              <input type="checkbox">请记住我
            </label>
          </div>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button @click="validateBeforeSubmit" type="button" class="btn btn-default">登录</button>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
  import axios from '~plugins/axios-cms'
  import common from '~plugins/common'
  export default {
    layout: 'cms',
    data () {
      return {user: {userName: '', password: ''}, error: false}
    },
    created () {
      axios.get('/login').then(response => {
        this.$store.commit('changeLoginedStatus', response.data.logined)
        if (response.data.logined && process.BROWSER_BUILD) {
          this.$router.push('/cms')
        }
      })
    },
    methods: {
      postLogin () {
        axios.post('/login', common.toJson(this.user)).then(response => {
          this.$store.commit('changeLoginedStatus', true)
          if (process.BROWSER_BUILD) {
            this.$router.push(this.$route.query.refer || 'cms')
          }
        }).catch(e => {
          this.error = e.response.status === 401
        })
      },
      validateBeforeSubmit () {
        this.$validator.validateAll().then(result => {
          if (!result) {
            for (const k of Object.keys(this.user)) {
              let msg = this.errors.first(k)
              console.log(msg)
              if (msg) {
                window.$(document.getElementsByName(k)[0]).attr('title', msg).tooltip().focus()
                break
              }
            }
            return
          }
          this.postLogin()
        })
      }
    }
  }
</script>
