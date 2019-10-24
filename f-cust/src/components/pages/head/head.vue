<template>
  <div>
    <el-header class="elHeader">
      <span class="home_title">JTP MANGEMENT SYSTEM</span>
      <span >
        <el-dropdown style="">
          <span class="user colorWhite" style="cursor: pointer;" >
            {{userName}}
    <!--<el-image :src="src" style="width: 40px;height: 40px;margin-left: 10px;border-radius: 40px;"></el-image>-->

          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="userInfo()">个人资料</el-dropdown-item>
            <el-dropdown-item @click.native="lagout()">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </span>
    </el-header>
  </div>
</template>

<script>
export default {
  name: "myhead",
  data() {
    return {
      userName: localStorage.getItem('name'),
      src: localStorage.getItem('userface'),
    };
  },
  created() {},
  mounted() {},
  methods: {
    // 所有的方法封装
    userInfo() {
      this.$router.push({
        path: '/home/baseInfo'
      })
    },
    lagout() {
      this.$router.push({
        path: '../'
      })
    }
  }
};
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.elHeader {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #20a0ff;
}
.user {
  font-size: 16px;
  font-weight: bolder;
}
.home_title {
  color: #fff;
  font-size: 22px;
  display: inline;
  margin-left: 8px;
}
.colorWhite{
  color: #fff
}
</style>
