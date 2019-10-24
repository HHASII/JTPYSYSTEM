<template>
  <div>
  <el-form :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">JTP MANAGEMENT SYSTEM</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="USERNAME"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="PASSWORD" @keyup.enter.native="submitClick"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%" >
      <el-button type="primary" style="width: 100%" @click="submitClick" >登录</el-button>
    </el-form-item>
    <el-form-item style="width: 100%" >
      <el-button  size="mini" type="primary" style="width: 30%  " class="butt1" @click="goresigter" >注册用户</el-button>
      <el-button size="mini" type="primary" style="width: 30% "  class="butt2" @click="gorefresh"  >重置密码</el-button>
    </el-form-item>

  </el-form>

  </div>
</template>
<script>
  export default{
    data(){
      return {
        rules: {
           account: [{required: false, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: false, message: '请输入密码', trigger: 'blur'}]
        },
        checked: true,
        loginForm: {
          username: '',
          password: ''
        },
        loading: false
      }
    },
    methods: {
      goresigter(){
        this.$router.push({path: '/register'});
      },
      gorefresh(){
        this.$router.push({path: '/ForgetPassword'});
      },

      submitClick: function () {
        var _this = this;
        this.loading = true;
        this.postRequestLogin('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp=> {
          _this.loading = false;
          if (resp && resp.status == 200) {
            console.log(resp)
            localStorage.setItem('name', resp.data.obj.name)
            localStorage.setItem('id', resp.data.obj.id)
            var data = resp.data;
            _this.$store.commit('login', data.obj);
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: path == '/' || path == undefined ? '/home' : path});
          }
        });
      }
    }

  }
</script>
<style>

html{
  overflow:hidden;
}
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 100px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    border-radius: 10%;

  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .butt1 {
    margin-right: 120px;
    background-color: #6ea9ff;
    border-radius: 2em;
  }
  .butt2 {

    background-color: #6ea9ff;
    border-radius: 2em;
  }
</style>
