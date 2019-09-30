<template>
  <div v-loading="loading">
    <div style="text-align: left;line-height: 60px;">
      <el-col :span="4">
        <el-date-picker type="date" size="mini" placeholder="开始时间" v-model="startDate" style="width: 100%;"></el-date-picker>
      </el-col>
      <el-col class="line" :span="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;</el-col>
      <el-col :span="4">
        <el-date-picker type="date" size="mini" placeholder="结束时间" v-model="endDate" style="width: 100%;"></el-date-picker>
      </el-col>
      <el-col class="line" :span="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;</el-col>
      <el-button type="primary" size="mini" @click.native="queryDecoration()">查询</el-button>
      <el-col>
        <el-button type="success"  @click.native="checkIfDeclare()">新建申报</el-button>
      </el-col>
      <!-- <el-button type="primary" @click.native="newDecoration()">新建申报</el-button> -->
    </div>
    <el-table :data="decorateList" border style="width: 100%; text-align: center">
      <el-table-column fixed prop="empId" label="员工ID" width="150"></el-table-column>
      <el-table-column fixed prop="chineseName" label="中文名" width="120"></el-table-column>
      <el-table-column prop="efcslevel" label="EF初始级别" width="120"></el-table-column>
      <el-table-column prop="efydlevel" label="EF应达级别" width="120"></el-table-column>
      <el-table-column prop="efytdlevel" label="EF YTD级别" width="120"></el-table-column>
      <el-table-column prop="efhours" label="EF小时数" width="120"></el-table-column>
      <el-table-column prop="bczhours" label="百词斩打卡天数" width="120"></el-table-column>
      <el-table-column prop="videohours" label="视频打卡天数" width="120"></el-table-column>
      <el-table-column prop="tedtimes" label="TED参赛次数" width="120"></el-table-column>
      <el-table-column prop="sharetimes" label="学习分享会议次数" width="120"></el-table-column>
      <el-table-column prop="unqualified" label="不合格项" width="120"></el-table-column>
      <el-table-column prop="createTime" label="申报日期" width="120"></el-table-column>
      <el-table-column prop="updateTime" label="修改日期" width="120"></el-table-column>
      <el-table-column prop="note" label="备注" width="120"></el-table-column>

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
              <el-form-item label="EF初始级别：" prop="efcslevel" label-width="150px">
                <el-input v-model="newDecorate.efcslevel"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="EF应达级：" prop="efydlevel" label-width="150px">
                <el-input v-model="newDecorate.efydlevel"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="EF YTD 级别：" prop="efytdlevel" label-width="150px">
                <el-input v-model="newDecorate.efytdlevel"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="EF小时数：" prop="efhours" label-width="150px">
                <el-input v-model="newDecorate.efhours"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="百词斩打卡天数：" prop="bczhours" label-width="150px">
                <el-input v-model="newDecorate.bczhours"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="视频打卡天数：" prop="videohours" label-width="150px">
                <el-input v-model="newDecorate.videohours"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="TED参赛次数：" prop="tedtimes" label-width="150px">
                <el-input v-model="newDecorate.tedtimes"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="学习分享会议次数：" prop="sharetimes" label-width="150px">
                <el-input v-model="newDecorate.sharetimes"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="不合格项：" prop="unqualified" label-width="150px">
                <el-input v-model="newDecorate.unqualified"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <div class="grid-content bg-purple">
              <el-form-item label="备注：" prop="note" label-width="150px">
                <el-input type="textarea" v-model="newDecorate.note"></el-input>
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
              <el-form-item label="EF初始级别：" prop="efcslevel" label-width="150px">
                <el-input v-model="decorateInfo.efcslevel"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="EF应达级：" prop="efydlevel" label-width="150px">
                <el-input v-model="decorateInfo.efydlevel"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="EF YTD 级别：" prop="efytdlevel" label-width="150px">
                <el-input v-model="decorateInfo.efytdlevel"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="EF小时数：" prop="efhours" label-width="150px">
                <el-input v-model="decorateInfo.efhours"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="百词斩打卡天数：" prop="bczhours" label-width="150px">
                <el-input v-model="decorateInfo.bczhours"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="视频打卡天数：" prop="videohours" label-width="150px">
                <el-input v-model="decorateInfo.videohours"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="TED参赛次数：" prop="tedtimes" label-width="150px">
                <el-input v-model="decorateInfo.tedtimes"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="学习分享会议次数：" prop="sharetimes" label-width="150px">
                <el-input v-model="decorateInfo.sharetimes"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="不合格项：" prop="unqualified" label-width="150px">
                <el-input v-model="decorateInfo.unqualified"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <div class="grid-content bg-purple">
              <el-form-item label="备注：" prop="note" label-width="150px">
                <el-input type="textarea" v-model="decorateInfo.note"></el-input>
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
  name: "declaration",
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
      newDecorate: {
        bczhours: null,
        chineseName: "", //
        efcslevel: "",
        efhours: null,
        efydlevel: "",
        efytdlevel: "",
        empId: null, //
        note: "",
        sharetimes: null,
        tedtimes: null,
        unqualified: null,
        videohours: null,
        operationUserId: "" //
      },
      decorateInfo: {
        bczhours: 0,
        chineseName: "",
        efcslevel: "",
        efhours: 0,
        efydlevel: "",
        efytdlevel: "",
        empId: 0,
        id: 0,
        note: "",
        operationUserId: 0,
        sharetimes: 0,
        tedtimes: 0,
        unqualified: 0,
        videohours: 0
      },
      rules: {
        efcslevel: [
          { required: true, message: "请输入EF初始级别", trigger: "blur" }
        ],
        efydlevel: [
          { required: true, message: "请输入EF应达级", trigger: "blur" }
        ],
        efytdlevel: [
          { required: true, message: "请输入EF YTD 级别", trigger: "blur" }
        ],
        efhours: [
          { required: true, message: "请输入EF小时数", trigger: "blur" }
        ],
        bczhours: [
          { required: true, message: "百词斩打卡天数：", trigger: "blur" }
        ],
        videohours: [
          { required: true, message: "视频打卡天数：", trigger: "blur" }
        ],
        tedtimes: [
          { required: true, message: "TED参赛次数：", trigger: "blur" }
        ],
        sharetimes: [
          { required: true, message: "学习分享会议次数：", trigger: "blur" }
        ],
        unqualified: [
          { required: true, message: "不合格项：", trigger: "blur" }
        ],
        note: [{ required: true, message: "备注：", trigger: "blur" }]
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
        endDate: this.endDate
      };
      this.loading = true;
      this.postRequest(
        "/employee/StudyDeclaration/listStudyDeclaration",
        this.queryInfo
      ).then(msg => {
        this.loading = false;
        if (msg && msg.status == 200) {
          console.log(msg);
          this.decorateList = msg.data.obj.studyDeclarationVoList;
          this.totalPage = msg.data.obj.listCount;
        }
      });
    },
    pageChange() {
      this.getList();
    },
    newDecoration() {
      (this.newDecorate = {
        bczhours: null,
        chineseName: "",
        efcslevel: "",
        efhours: null,
        efydlevel: "",
        efytdlevel: "",
        empId: null,
        note: "",
        sharetimes: null,
        tedtimes: null,
        unqualified: null,
        videohours: null
      }),
        (this.dialogVisible = true);
    },
    checkIfDeclare() {
          this.newDecorate.empId = localStorage.getItem("id");
          this.postRequest(
            "/employee/StudyDeclaration/checkIfDeclare",
              this.newDecorate
            
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.newDecoration();
            }else {
          console.log("error submit!!");
          return false;
          }
          });
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
            "/employee/StudyDeclaration/addStudyDeclarationRecord",
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
      this.decorateInfo.bczhours = row.bczhours;
      this.decorateInfo.chineseName = row.chineseName;
      this.decorateInfo.sharetimes = row.sharetimes;
      this.decorateInfo.tedtimes = row.tedtimes;
      this.decorateInfo.unqualified = row.unqualified;
      this.decorateInfo.videohours = row.videohours;
      this.decorateInfo.efcslevel = row.efcslevel;
      this.decorateInfo.efhours = row.efhours;
      this.decorateInfo.efydlevel = row.efydlevel;
      this.decorateInfo.efytdlevel = row.efytdlevel;
      this.decorateInfo.empId = row.empId;
      this.decorateInfo.id = row.id;
      this.decorateInfo.note = row.note;
      this.decorateInfo.operationUserId = row.operationUserId;
      this.dialogVisibleEdit = true;
    },
    DecorateEdit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.decorateInfo.empId = localStorage.getItem("id");
          this.decorateInfo.operationUserId = localStorage.getItem("id");
          this.decorateInfo.chineseName = localStorage.getItem("name");
          console.log(this.decorateInfo);

          this.postRequest(
            "/employee/StudyDeclaration/updateStudyDeclarationById",
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
