<template>
  <div>
    <form class="form-horizontal" role="form">
      <div class="form-group" v-if="user.register">
        <label for="firstname" class="col-sm-2 control-label">昵称</label>
        <div class="col-sm-10">
          <input type="text" id="nickName" v-validate="'required'" class="form-control" placeholder="昵称"
                 name="nickName"
                 v-model="user.nickName">
        </div>
      </div>
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
          <i v-show="errorMsg" class="fa fa-warning"></i>
          <span v-show="errorMsg"
                class="text-danger">{{errorMsg}}</span>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <div class="checkbox">
            <label>
              <input type="checkbox" v-model="remember">记住我
            </label> <label>
            <input type="checkbox" v-model="user.register">初始化注册
            <i class="fa fa-question-circle-o" aria-hidden="true" title="数据库没有任何帐号时，勾选此项将进行初始化注册"></i>
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
  import axios from '~/plugins/axios-cms'
  import common from '~/plugins/common'
  export default {
    layout: 'cms',
    data () {
      return {user: {userName: '', password: '', register: false, remember: 1}, errorMsg: '', remember: true}
    },
    created () {
      axios.get('/login').then(response => {
        this.$store.commit('changeLoginedStatus', response.data.logined)
        if (response.data.logined) {
          this.doDirect()
        }
      })
    },
    watch: {
      remember (val) {
        this.user.remember = val ? 1 : 0
      }
    },
    methods: {
      postLogin () {
        axios.post('/login', common.toJson(this.user)).then(response => {
          this.$store.commit('changeLoginedStatus', true)
          this.doDirect()
        }).catch(e => {
          this.errorMsg = e.response.status === 401 ? '用户名或密码错误！' : `系统异常(code=${e.response.status})`
        })
      },
      doDirect () {
        if (process.browser) {
          this.$router.push(this.$route.query.path || '/cms')
        }
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
