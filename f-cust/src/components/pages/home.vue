<template>
  <div style="height: 100%">
    <myhead></myhead>
    <el-container style="height: 90%; border: 1px solid #eee">
      <el-container>
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
                  >
                    <template slot="title">
                    <i :class="child.iconCls" style="color: #20a0ff;width: 14px; padding-right: 8px"></i>
                    <span slot="title">{{child.name}}</span>
                  </template>
                    <!--{{child.name}}-->
                  </el-menu-item>
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
  mounted() {

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

.home-aside {
  background-color: #ececec;
}
.el-menu {
  width: 200px;
}
  .el-submenu__title{
    width: 200px;
  }
</style>
