<template>
  <div style="height: 100%">
    <myhead></myhead>
    <el-container style="height: 90%; border: 1px solid #eee">
      <el-container>
        <!-- <el-aside width="180px" class="home-aside">
          <div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;">
            <el-menu style="background: #ececec;width: 180px;" unique-opened router>
              <template v-for="(item,index) in menu">
                <el-submenu :key="index" :index="index+''">
                  <template slot="title">
                    <i :class="item.iconCls" style="color: #20a0ff;width: 14px;"></i>
                    <span slot="title">{{item.name}}</span>
                  </template>
                  <el-menu-item
                    width="180px"
                    style="padding-left: 30px;padding-right:0px;margin-left: 0px;width: 170px;text-align: left"
                    v-for="child in item.children"
                    :index="child.path"
                    :key="child.path"
                  >{{child.name}}</el-menu-item>
                </el-submenu>
              </template>
            </el-menu>
          </div>
        </el-aside>-->
        <el-aside width="200px" class="home-aside">
          <div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;">
            <el-menu style="background: #ececec;width: 180px;" unique-opened router>
              <template v-for="(item,index) in this.routes">
                <el-submenu :key="index" :index="index+''">
                  <template slot="title">
                    <i :class="item.iconCls" style="color: #20a0ff;width: 14px;"></i>
                    <span slot="title">{{item.name}}</span>
                  </template>
                  <el-menu-item
                    width="180px"
                    style="padding-left: 30px;padding-right:0px;margin-left: 0px;width: 170px;text-align: left"
                    v-for="child in item.children"
                    :index="child.path"
                    :key="child.path"
                  >{{child.name}}</el-menu-item>
                </el-submenu>
              </template>
            </el-menu>
          </div>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import myhead from "./head/head";
export default {
  name: "home",
  data() {
    return {
      isDot: false,
      menu: []
    };
  },
  beforeCreate() {},
  computed: {
    user() {
      console.log(this.$store.state.user);
      return this.$store.state.user;
    },
    routes() {
      console.log(this.$store.state.routes);
      return this.$store.state.routes;
    }
  },
  created() {
    // vue 页面的生命周期 当我一进来页面之后 我先执行
    // 获取目录
    // this.menu = [
    //   {
    //     id: 1,
    //     path: "/home",
    //     component: "home",
    //     name: "用户相关",
    //     iconCls: "fa fa-user-circle-o",
    //     children: [
    //       {
    //         id: null,
    //         path: "./baseInfo",
    //         component: "baseInfo",
    //         name: "个人资料",
    //         iconCls: null,
    //         children: [],
    //         meta: {
    //           keepAlive: false,
    //           requireAuth: true
    //         }
    //       },
    //       {
    //         id: null,
    //         path: "./declaration",
    //         component: "declaration",
    //         name: "申报",
    //         iconCls: null,
    //         children: [],
    //         meta: {
    //           keepAlive: false,
    //           requireAuth: true
    //         }
    //       }
    //     ]
    //   }
    // ];
    // console.log(this.menu);
    //   this.$axiox
    // .get("/employee/basic/getEmpBy/" + localStorage.getItem("id"), {})
    // .then(msg => {
    //   console.log(msg)
    // });
  },
  mounted() {
    // vue 页面的生命周期 当我进来之后 且加载完资源了之后再执行
          console.log(this.routes)

  },
  methods: {
    toBaseInfo() {
      this.$router.push({
        path: "baseInfo"
      });
    },
    toDeclaration() {
      this.$router.push({
        path: "declaration"
      });
    }
  },
  components: {
    myhead: myhead
  }
};
</script>
<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  /* background-color: #d3dce6; */
  background-color: #ececec;
  color: #333;
  text-align: center;
  line-height: 200px;
  height: 100%;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 100%;
}

body > .el-container {
  margin-bottom: 40px;
}
.home-aside {
  background-color: #ececec;
}
</style>
