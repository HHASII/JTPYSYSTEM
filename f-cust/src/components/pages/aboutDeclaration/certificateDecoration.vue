<template>
  <div v-loading="loading">
    <div style="text-align: left;line-height: 60px;">
      <el-col  :span="4">
        培训类别：
        <el-input v-model="search" size="mini" style="width: 200px"></el-input>
      </el-col>
      <el-button type="primary" size="mini" @click.native="queryDecoration()">查询</el-button>
      <el-col>
        <el-button type="success"  @click.native="newDecoration()">新建申报</el-button>
      </el-col>

    </div>


    <el-table :data="decorateList" border style="width: 100%; text-align: center">
      <el-table-column fixed prop="empId" label="员工ID" width="150"></el-table-column>
      <!--组件使用fixed，会有折叠效果-->
      <el-table-column fixed prop="chineseName" label="中文名" width="120"></el-table-column>
      <el-table-column prop="growingType" label="培训类别" width="200"></el-table-column>
      <el-table-column prop="growingDetail" label="培训详情" width="350"></el-table-column>
      <el-table-column prop="createUserName" label="创建人" width="150"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="200"></el-table-column>
      <el-table-column prop="updateUserName" label="更新人" width="150"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="200"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      :page-size="10"
      :current-page="currentPage"
      @current-change="pageChange()"
      layout="prev, pager, next"
      :total="totalPage"
    ></el-pagination>
    <el-dialog
      title="新建申报"
      :visible.sync="dialogVisible"
      width="70%"
      :before-close="handleClose"
      style="line-height: 30px;"
    >
      <el-form
        :model="newDecorate"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="培训类别：" prop="growingType" label-width="150px">
                <el-input v-model="newDecorate.growingType"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple-light">
              <el-form-item label="详情信息：" prop="growingDetail" label-width="150px">
                <el-input v-model="newDecorate.growingDetail"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="newDecorateSure('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="申报修改"
      :visible.sync="dialogVisibleEdit"
      width="70%"
      :before-close="handleClose"
      style="line-height: 30px;"
    >
      <el-form
        :model="decorateInfo"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="培训类别：" prop="growingType" label-width="150px">
                <el-input v-model="decorateInfo.growingType"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="详情信息：" prop="growingDetail" label-width="150px">
                <el-input v-model="decorateInfo.growingDetail"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleEdit = false">取 消</el-button>
        <el-button type="primary" @click="DecorateEdit('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "certificateDecoration",
  data() {
    return {
      decorateList: [],
      queryInfo: {},
      userId: 0,
      currentPage: 1,
      totalPage: 1,
      loading: false,
      dialogVisible: false,
      dialogVisibleEdit: false,
      startDate: "",
      endDate: "",
      search: "",
      newDecorate: {
        id: 0,
        createTime: "",
        updateTime: "",
        empId: 0,
        chineseName: "",
        growingType: "",
        growingDetail: "",
        createUserName: "",
        updateUserName: ""
      },
      decorateInfo: {
        id: 0,
        createTime: "",
        updateTime: "",
        empId: 0,
        chineseName: "",
        growingType: "",
        growingDetail: "",
        createUserName: "",
        updateUserName: ""
      },
      rules: {
        empId: [
          { required: true, message: "请输入培训类别", trigger: "blur" }
        ],
        chineseName: [
          { required: true, message: "请输入培训类别", trigger: "blur" }
        ],
        growingType: [
          { required: true, message: "请输入培训类别", trigger: "blur" }
        ],
        growingDetail: [
          { required: true, message: "请输入详细内容", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.userId = localStorage.getItem("id");
    this.getList();
  },
  mounted() {},
  methods: {
    // 所有的方法封装
    getList() {
      this.queryInfo = {
        empId: this.userId,
        size: 10,
        start: this.currentPage - 1,
        startDate: this.startDate,
        endDate: this.endDate,
        search:this.search
      };
      this.loading = true;
      this.postRequest(
        "/staff/stf_StudyDeclaration/listStaffExperienceById",
        this.queryInfo
      ).then(msg => {
        this.loading = false;
        if (msg && msg.status == 200) {
          console.log(msg);
          this.decorateList = msg.data.obj.growingExperienceVoList;
          this.totalPage = msg.data.obj.listCount;
        }
      });
    },
    pageChange() {
      this.getList();
    },
    newDecoration() {
      (this.newDecorate = {
        empId: null,
        growingType: "",
        growingDetail: "",

      }),
        (this.dialogVisible = true);
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    newDecorateSure(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.newDecorate.empId = localStorage.getItem("id");
          this.newDecorate.operationUserId = localStorage.getItem("id");
          this.newDecorate.chineseName = localStorage.getItem("name");
          this.postRequest(
            "/staff/stf_StudyDeclaration/addStaffExperience",
            this.newDecorate
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.dialogVisible = false;
              this.getList();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleClick(row) {
      console.log(row);
      this.decorateInfo.chineseName = row.chineseName;
      this.decorateInfo.empId = row.empId;
      this.decorateInfo.id = row.id;
      this.decorateInfo.operationUserId = row.operationUserId;
      this.decorateInfo.growingType = row.growingType;
      this.decorateInfo.growingDetail = row.growingDetail;
      this.dialogVisibleEdit = true;
    },
    DecorateEdit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.decorateInfo.empId = localStorage.getItem("id");
          this.newDecorate.operationUserId = localStorage.getItem("id");
          this.decorateInfo.chineseName = localStorage.getItem("name");
          // this.decorateInfo.growingType = localStorage.getItem("growingType");
          // this.decorateInfo.growingDetail = localStorage.getItem("growingDetail");
          this.postRequest(
            "/staff/stf_StudyDeclaration/updateStaffExperienceById",
            this.decorateInfo
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.dialogVisibleEdit = false;
              this.getList();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    queryDecoration() {
      this.getList();
    }
  }
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
