<template>
  <el-form
    :rules="rules"
    class="login-container"
    label-position="left"
    label-width="0px"
    v-loading="loading"
  >
    <h3 class="login_title">员工注册</h3>

    <el-form-item prop="empId">
      <el-input type="text" v-model="loginForm.empId" auto-complete="off" placeholder="员工ID"></el-input>
    </el-form-item>

    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>

    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>

    <el-form-item prop="checkPass">
      <el-input
        type="password"
        v-model="loginForm.rePassword"
        auto-complete="off"
        placeholder="再次输入密码"
      ></el-input>
    </el-form-item>

    <el-form-item prop="checkPass">
      <el-input type="text" v-model="loginForm.veriCode" auto-complete="off" placeholder="验证码"></el-input>
    </el-form-item>
    <!-- <el-checkbox class="login_remember" v-model="checked"
    label-position="left">REMEMBER</el-checkbox>-->
    <br />
    <el-form-item style="width: 100%">
      <el-button
        type="success"
        size="mini"
        style="width: 100%"
        :disabled="checked"
        @click="sendCode()"
      >{{content}}</el-button>
      <br />
      <el-button type="primary" size="mini" style="width: 100%" @click="submitClick">注册</el-button>
      <br />
      <a href="/">返回首页</a>
    </el-form-item>
  </el-form>
</template>



<script>
export default {
  name: "register",
  data() {
    return {
      rules: {
        //account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        //   checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}]
      },
      content: "发送验证码至邮箱", // 按钮里显示的内容
      totalTime: 60, //记录具体倒计时时间
      checked: false,
      loginForm: {
        empId: "",
        username: "",
        password: "",
        rePassword: "",
        veriCode: "",
        codeId: ""
      },
      loading: false
    };
  },
  methods: {
    sendCode: function() {
      this.postRequest("/register/sendVerfiCode", this.loginForm).then(
        msg => {
          this.loading = false;
          if (msg && msg.status == 200) {
            console.log(msg);
            this.loginForm.codeId = msg.data.obj.veriId;
            this.countDown();
          }
        }
      );
    },

    submitClick: function() {
      this.postRequest("/register/register", this.loginForm).then(
        msg => {
          this.loading = false;
          if (msg && msg.status == 200) {
            console.log(msg);
            alert("注册成功");
          }
        }
      );
    },
    countDown() {
      let clock = window.setInterval(() => {
        this.totalTime--;
        this.content = this.totalTime + "s后重新发送";
        if (this.totalTime > 0) {
          this.checked = true;
        }
        if (this.totalTime < 0) {
          window.clearInterval(clock);
          this.checked = false;
          this.totalTime = 60;
          this.content = "重新发送验证码";
        }
      }, 1000);
    }
  }
};
</script>

<style scoped>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  height: 500px;
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
.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>