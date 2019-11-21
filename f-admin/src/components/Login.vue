<template>
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


<!-- 记住我，属于springSecurity的功能-->
    <!--<el-checkbox class="login_remember" v-model="checked"-->
                 <!--label-position="left">REMEMBER</el-checkbox>-->
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%" @click="submitClick">LOGIN</el-button>
    </el-form-item>
  </el-form>
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
          username: 'admin',
          password: ''
        },
        loading: false,

        confirm_code_send_text: '获取验证码',
        confirm_code_wait_text: '验证码发送中',
        total_wait_time: 5,
        confirm_button_show: true,
        canClick: true

      }
    },
    methods: {
      submitClick: function () {
        var _this = this;
        this.loading = true;
        this.postRequestLogin('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp=> {
          _this.loading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            localStorage.setItem('createName', data.obj.name);
            _this.$store.commit('login', data.obj);
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: path == '/' || path == undefined ? '/home' : path});
          }
        });
      },

      get_confirm_code:function(){
          this.confirm_code_send_text = '重新发送验证码';
          this.confirm_button_show = false;
        this.confirm_code_wait_text = this.total_wait_time + 's后重新发送'  //这里解决60秒不见了的问题
        let clock = window.setInterval(() => {
          this.total_wait_time--
          this.confirm_code_wait_text = this.total_wait_time + 's后重新发送'
          if (this.total_wait_time < 0) {         //当倒计时小于0时清除定时器
            window.clearInterval(clock)
            this.confirm_code_wait_text = '重新发送验证码'
            this.confirm_code_send_text = '重新发送验证码'
            this.confirm_button_show = true
            this.total_wait_time = 5
          }
        },1000)

      }
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

</style>
