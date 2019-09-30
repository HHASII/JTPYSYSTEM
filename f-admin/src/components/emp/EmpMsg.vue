<template>
  <div v-loading="loading">
    <el-tabs v-model="activeName" @tab-click="handleClick">
     {{title}} {{msgsize}}
      <el-tab-pane label="未读消息" name="未读消息">
        <el-table :data="msgList" style="width: 100%">
          <el-table-column fixed prop="msg" label="消息"></el-table-column>
          <el-table-column prop="createTime" label="时间"></el-table-column>
          <el-table-column fixed="right" label="操作">
            <template slot-scope="scope">
              <el-button @click="readed(scope.row.id)" type="text" size="small">已读</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          :page-size="10"
          :current-page="queryInfo.start + 1"
          @current-change="pageChange()"
          layout="prev, pager, next"
          :total="totalPage"
        ></el-pagination>
      </el-tab-pane>
      <el-tab-pane label="已读消息" name="已读消息">
        <el-table :data="msgList" style="width: 100%">
          <el-table-column fixed prop="msg" label="消息"></el-table-column>
          <el-table-column prop="createTime" label="时间"></el-table-column>
        </el-table>
        <el-pagination
          background
          :page-size="10"
          :current-page="queryInfo.start + 1"
          @current-change="pageChange()"
          layout="prev, pager, next"
          :total="totalPage"
        ></el-pagination>
      </el-tab-pane>
    </el-tabs>
      
    
  </div>
</template>


<script>
export default {
  name: "EmpMsg",
  
  data() {
    return {
      activeName: "未读消息",
      msgsize:0,
      msgList: [],
      loading: false,
      queryInfo: {
        search: 0,
        size: 10,
        start: 0,
        title:""
      }, // search 1已读， 0未读
      totalPage: 1
    };
  },
  created() {
    this.getData();
  },
  mounted: function() {
    this.title="未读消息数：";
  },
  methods: {
    // 所有的方法封装
    handleClick(tab, event) {
      console.log(tab, event);
      if (tab.name === "未读消息") {
        this.queryInfo.search = "0";
        this.getData()
         this.title="未读消息数："
      }
      if (tab.name === "已读消息") {
        this.queryInfo.search = "1";
        this.getData()
        this.title="已读消息数："
      }
    },
    getData() {
      this.loading = true;
      this.postRequest(
        "/employee/MsgNotification/listEmpMsg",
        this.queryInfo
      ).then(msg => {
        this.loading = false;
        if (msg && msg.status == 200) {
          console.log(msg);
          this.msgList = msg.data.obj.empMsgVoList;
          this.msgsize = msg.data.obj.listCount.toString();
          
        }
      });
    },
    getReadedData() {
      this.loading = true;
      this.queryInfo.search="1";
      this.postRequest(
        "/employee/MsgNotification/listEmpMsg",
        this.queryInfo
      ).then(msg => {
        this.loading = false;
        if (msg && msg.status == 200) {
          console.log(msg);
          this.msgList = msg.data.obj.empMsgVoList;
          this.msgsize = msg.data.obj.listCount.toString();
         
        }
      });
    },
    readed(id) {
      this.loading = true;
      console.log(id);
      let data = {
        id: id
      };
      this.postRequest("/employee/MsgNotification/deleteEmpMsg", data).then(
        msg => {
          this.tableLoading = false;
          if (msg && msg.status == 200) {
            console.log(msg);
            this.getData();
          }
        }
      );
    }
  }
};
</script>

<style scoped>
</style>