<template>
  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">登录</h4>
        </div>
        <div class="modal-body">
          <form class="form-horizontal">
            <div class="form-group">
              <label class="col-sm-2 control-label">用户名</label>
              <div class="col-sm-10">
                <input type="text" v-validate="'required|alpha'" class="form-control" placeholder="UserName" name="userName"
                       v-model="user.userName">
                <i v-show="errors.has('userName')" class="fa fa-warning"></i>
                <span v-show="errors.has('userName')"
                      class="help is-danger">{{ errors.first('userName') }}</span>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">密码</label>
              <div class="col-sm-10">
                <input type="password" v-validate="'required'" class="form-control" placeholder="Password" name="password"
                       v-model="user.password">
                <i v-show="errors.has('password')" class="fa fa-warning"></i>
                <span v-show="errors.has('password')"
                      class="help is-danger">{{ errors.first('password') }}</span>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                  <label>
                    <input type="checkbox"/> 记住我
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <a @click="validateBeforeSubmit" class="btn btn-default">登录</a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import {mapMutations, mapState} from 'vuex'
  import jQuery from 'jQuery'
  import axios from 'axios'
  export default {
    data () {
      return {user: {userName: '', password: ''}}
    },
    computed: mapState({logined: state => state.logined}),
    watch: {
      logined (newV, oldV) {
        if (newV === false && oldV === true) {
          jQuery('#myModal').modal()
        }
      }
    },
    created () {
      axios.get('/login').then(response => {
        this.changeLoginedStatus(response.data.logined)
        if (!response.data.logined) {
          jQuery('#myModal').modal()
        }
      })
    },
    methods: {
      ...mapMutations(['changeLoginedStatus']),
      postLogin () {
        axios.post('/login', axios.toJson(this.user)).then(response => {
          this.changeLoginedStatus(true)
          jQuery('#myModal').modal('hide')
        })
      },
      validateBeforeSubmit () {
        this.$validator.validateAll().then(() => {
          this.postLogin()
        }).catch(() => {
        })
      }
    }
  }
</script>
