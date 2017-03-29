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
                <input type="text" class="form-control" placeholder="UserName" v-model="user.userName">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">密码</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" placeholder="Password" v-model="user.password">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> 记住我
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button @click="postLogin" class="btn btn-default">登录</button>
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
        this.changeLogined(response.data.logined)
        if (!response.data.logined) {
          jQuery('#myModal').modal()
        }
      })
    },
    methods: {
      ...mapMutations(['changeLogined']),
      postLogin () {
        console.log(this.user)
        axios.post('/login', axios.toJson(this.user)).then(response => {
          this.changeLogined(true)
        })
      }
    }
  }
</script>
