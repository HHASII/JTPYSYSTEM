<template>
  <div v-loading="loading">
    <div style="text-align: left;line-height: 60px;">
      <el-col :span="3">
        <!-- <el-date-picker type="date" placeholder="开始时间" v-model="startDate" style="width: 100%;"></el-date-picker> -->
        员工号：
        <el-input v-model="queryStudyInfo.empId" size="mini" style="width: 100px"></el-input>
      </el-col>
      <el-col :span="4">
        员工姓名：
        <el-input v-model="queryStudyInfo.search" size="mini" style="width: 150px"></el-input>
      </el-col>
      <el-col :span="3">
        <el-date-picker
          type="date"
          placeholder="开始时间"
          v-model="queryStudyInfo.startDate"
          style="width: 100%;"
          size="mini"
        ></el-date-picker>
      </el-col>
      <el-col class="line" :span="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;</el-col>
      <el-col :span="3">
        <el-date-picker
          type="date"
          placeholder="结束时间"
          v-model="queryStudyInfo.endDate"
          style="width: 100%;"
          size="mini"
        ></el-date-picker>
      </el-col>
      <el-col class="line" :span="3">
        <el-button type="primary" size="mini" @click.native="queryDecoration()">查询</el-button>
      </el-col>

      <el-upload
        :show-file-list="false"
        accept="application/vnd.ms-excel"
        action="/employee/StudyDeclaration/importGrow"
        :on-success="fileUploadSuccess"
        :on-error="fileUploadError"
        :disabled="fileUploadBtnText=='正在导入'"
        :before-upload="beforeFileUpload"
        style="display: inline"
      >
        <el-button type="success" size="mini" :loading="fileUploadBtnText=='正在导入'">
          <i class="fa fa-lg fa-level-up" style="margin-right: 5px"></i>
          {{fileUploadBtnText}}
        </el-button>
      </el-upload>
      <el-button type="success" size="mini" @click.native="getModel()">
        <i class="fa fa-lg fa-level-down" style="margin-right: 5px"></i>导出数据
      </el-button>
      <!-- <el-button type="primary" @click.native="newDecoration()">新建申报</el-button> -->
      <el-button
        type="danger"
        @click="deleteMany"
        v-if="studyList.length>0"
        :disabled="multipleSelection.length==0"
        size="mini"
      >批量删除</el-button>
      <!-- <el-button type="success" size="mini" @click.native="newStudyDecoration()">新增</el-button> -->
      <el-button type="primary" size="mini" icon="el-icon-plus" @click="newStudyDecoration()">新增学习申报</el-button>
    </div>
    <el-table
      :data="studyList"
      @selection-change="handleSelectionChange"
      border
      style="width: 100%; text-align: center"
      v-loading="tableLoading"
    >

      <el-table-column prop="id" type="selection" align="left" width="40"></el-table-column>
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
      <el-table-column fixed="right" label="操作" width="150" style="white-space: nowrap">
        <template slot-scope="scope">
          <el-button
            @click="handleClick_studyDecuration(scope.row)"
            style="padding: 3px 4px 3px 4px;margin: 2px"
          >编辑</el-button>
          <el-button
            type="danger"
            style="padding: 3px 4px 3px 4px;margin: 2px"
            size="mini"
            @click="deleteStudyDecoration(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      :page-size="10"
      :current-page="queryStudyInfo.start + 1"
      @current-change="pageChange()"
      layout="prev, pager, next"
      :total="totalPage"
    ></el-pagination>

    <el-dialog
      title="新建申报"
      :visible.sync="dialogVisible_studyDecoration"
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
              <el-form-item label="工号：" prop="empId" label-width="150px">
                <el-input v-model="newDecorate.empId"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="姓名" prop="chineseName" label-width="150px">
                <el-input v-model="newDecorate.chineseName"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
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
        <el-button @click="dialogVisible_studyDecoration = false">取 消</el-button>
        <el-button type="primary" @click="newDecorateSure('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="申报变更"
      :visible.sync="dialogVisible_editStudyDecoration"
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
              <el-form-item label="学号：" prop="empId" label-width="150px">
                <el-input v-model="newDecorate.empId"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="姓名" prop="chineseName" label-width="150px">
                <el-input v-model="newDecorate.chineseName"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
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
        <el-button @click="dialogVisible_editStudyDecoration = false">取 消</el-button>
        <el-button type="primary" @click="editDecorateSure('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
export default {
  name: "EmpStudyDecoration",
  data() {
    return {
      fileUploadBtnText: "导入数据",
      dialogVisible_newStudyDecoration: false,
      dialogVisible_editStudyDecoration: false,
      dialogVisible_studyDecoration: false,
      studyList: [],
      empName: "",
      multipleSelection: [],
      tableLoading: false,
      queryStudyInfo: {
        empId: "",
        endDate: "",
        search: "",
        size: 10,
        start: 0,
        startDate: ""
      },
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
        note: [{ required: true, message: "备注：", trigger: "blur" }],
        empId: [{ required: true, message: "学号", trigger: "blur" }],
        chineseName: [{ required: true, message: "姓名", trigger: "blur" }]
      }
    };
  },
  created() {
    this.userId = localStorage.getItem("id");
    this.getStudyList();
  },
  mounted: function() {},
  methods: {
    // 所有的方法封装
    getStudyList() {
      this.loading = true;
      this.postRequest(
        "/employee/StudyDeclaration/listStudyDeclaration",
        this.queryStudyInfo
      ).then(msg => {
        this.loading = false;
        if (msg && msg.status == 200) {
          console.log(msg);
          this.studyList = msg.data.obj.studyDeclarationVoList;
          this.totalPage = msg.data.obj.listCount;
        }
      });
    },
    pageChange() {
      this.getStudyList();
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
          this.postRequest(
            "/employee/StudyDeclaration/addStudyDeclarationRecord",
            this.newDecorate
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.dialogVisible_studyDecoration = false;
              this.getStudyList();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    queryDecoration() {
      this.getStudyList();
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    deleteMany() {
      this.$confirm(
        "此操作将删除[" + this.multipleSelection.length + "]条数据, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          for (var i = 0; i < this.multipleSelection.length; i++) {
            this.doManyDelete(this.multipleSelection[i].id);
          }
        })
        .catch(() => {});
    },
    doManyDelete(ids) {
      this.tableLoading = true;
      var datas = {
        id: ids
      };
      this.postRequest(
        "/employee/StudyDeclaration/deleteStudyDeclarationRecordById",
        datas
      ).then(resp => {
        this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          this.$message({ type: "success", message: "删除成功！" });
          this.getStudyList();
        }
      });
    },

    deleteStudyDecoration(row){
        this.$confirm('此操作将永久删除[' + row.chineseName + ']的本条学习申报记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doManyDelete(row.id);
        }).catch(() => {
        });
      },
    getModel() {
      let url = "/employee/StudyDeclaration/exportStudyDeclarationRecord";
      window.open(url, "_blank");
    },
    fileUploadSuccess(response, file, fileList) {
      console.log(response);
      if (response) {
        if (response.status == "200") {
          var responseType = "success";
        }
        this.$message({ type: responseType, message: response.obj });
        this.queryDecoration();
      }

      this.fileUploadBtnText = "导入数据";
    },
    queryDecoration() {
      this.getStudyList();
    },
    fileUploadError(err, file, fileList) {
      this.$message({ type: "error", message: "导入失败!" });
      this.fileUploadBtnText = "导入数据";
    },

    handleClick_studyDecuration(row) {
      console.log(row);
      this.newDecorate = row;

      this.dialogVisible_editStudyDecoration = true;
    },
    beforeFileUpload(file) {
      this.fileUploadBtnText = "正在导入";
    },

    newStudyDecoration() {
      this.dialogVisible_studyDecoration = true;
    },
    editDecorateSure(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.postRequest(
            "/employee/StudyDeclaration/updateStudyDeclarationById",
            this.newDecorate
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.dialogVisible_editStudyDecoration = false;
              this.getStudyList();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>

<style scoped>
</style>
