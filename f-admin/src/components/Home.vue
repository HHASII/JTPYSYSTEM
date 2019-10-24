<template>
  <div>
    <el-container class="home-container">
      <el-header class="home-header">

        <span class="home_title">JTP MANGEMENT SYSTEM</span>

        <div style="display: flex;align-items: center;margin-right: 7px">

    <div style="display: flex;align-items: center;margin-right: 7px">
          <el-badge style="margin-right: 30px ; cursor: pointer;" :is-dot="this.$store.state.nfDot">
            <i class="fa fa-bell" @click="goChat" style=" color:yellow ;border-right:5px white"></i>
          </el-badge>
          <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link home_userinfo" style="display: flex;align-items: center ;margin-right: 10px">
    {{user.name}}
    <i> <img :src="user.userface? user.userface: require('../assets/imgs/user-header.png')"
             alt=""
             style="width: 40px;height: 40px;border-radius: 40px;margin-left: 10px;">
    </i>
  </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>设置</el-dropdown-item>
              <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        </div>

      </el-header>
      <el-container>
        <el-aside width="180px" class="home-aside">
          <div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;">
            <el-menu style="background: #ececec;width: 180px;" unique-opened router>
              <template v-for="(item,index) in this.routes">
                <el-submenu :key="index" :index="index+''">
                  <template slot="title">
                    <i :class="item.iconCls" style="color: #20a0ff;width: 14px;"></i>
                    <span slot="title">{{item.name}}</span>
                  </template>
                  <el-menu-item width="180px"
                                style="padding-left: 30px;padding-right:0px;margin-left: 0px;width: 170px;text-align: left"
                                v-for="child in item.children"
                                :index="child.path"
                                :key="child.path">
                    <template slot="title">
                      <i :class="child.iconCls" style="color: #20a0ff;width: 14px; padding-right: 8px"></i>
                      <span slot="title">{{child.name}}</span>
                    </template>
                  </el-menu-item>

                </el-submenu>
              </template>
            </el-menu>
          </div>
        </el-aside>
          <el-main>
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
            </el-breadcrumb>
            <keep-alive>
              <router-view v-if="this.$route.meta.keepAlive"></router-view>
            </keep-alive>
            <router-view v-if="!this.$route.meta.keepAlive"></router-view>
          </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
  export default{
    mounted: function () {
    },
    methods: {
      goChat(){
        this.$router.push({path: '/EmpMsg'});
      },
      open() {
        this.$message('这是一条消息提示');
      },
      handleCommand(cmd){
        var _this = this;
        if (cmd == 'logout') {
          this.$confirm('注销登录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.getRequest("/logout");
            _this.$store.commit('logout');
            _this.$router.replace({path: '/'});
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '取消'
            });
          });
        }
      }
    },
    data(){
      return {
        isDot: false
      }
    },
    computed: {
      user(){
        return this.$store.state.user;
      },
      routes(){
        return this.$store.state.routes
      }
    }
  }
</script>
<style>
  .home-container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .home-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: content-box;
    padding: 0px;
  }

  .home-aside {
    background-color: #ECECEC;
  }



  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
    margin-left: 8px;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }

  .el-submenu .el-menu-item {
    width: 180px;
    min-width: 175px;
  }
</style>
