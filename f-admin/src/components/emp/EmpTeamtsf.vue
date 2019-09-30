<template>
  <div v-loading="loading">
    <div style="text-align: left;line-height: 60px;">
      <el-col :span="3">
        <!-- <el-date-picker type="date" placeholder="开始时间" v-model="startDate" style="width: 100%;"></el-date-picker> -->
        员工号：
        <el-input v-model="queryInfo.empId" size="mini" style="width: 100px"></el-input>
      </el-col>
      <el-col :span="14">
        员工姓名：
        <el-input v-model="queryInfo.search" size="mini" style="width: 150px"></el-input>
        <el-button type="primary" size="mini" @click.native="queryDecoration()">查询</el-button>
      </el-col>

      <el-upload
        :show-file-list="false"
        accept="application/vnd.ms-excel"
        action="/employee/team_change/importChangeTeamLog"
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
      <el-button
        type="danger"
        v-if="dataList.length>0"
        :disabled="multipleSelection.length==0"
        @click="deleteMany"
        size="mini"
      >批量删除</el-button>
      <el-button type="primary" size="mini" icon="el-icon-plus" @click="newTeamsTsf()">新增团队变更</el-button>
      <!-- <el-button type="success" size="mini" @click.native="newTeamsTsf()">新增团队变更</el-button> -->
      <!-- <el-button type="primary" @click.native="newDecoration()">新建申报</el-button> -->
    </div>
    <el-table
      :data="dataList"
      @selection-change="handleSelectionChange"
      border
      style="width: 100%; text-align: center"
      v-loading="tableLoading"
    >
      <!-- <el-table-column fixed prop="empId" label="员工ID" width="150"></el-table-column> -->
      <el-table-column prop="id" type="selection" align="left" width="40"></el-table-column>
      <el-table-column fixed prop="empId" label="员工ID" width="150"></el-table-column>
      <el-table-column fixed prop="chineseName" label="中文名" width="120"></el-table-column>
      <el-table-column prop="createUserName" label="创建人" width="120"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="120"></el-table-column>
      <el-table-column prop="updateUserName" label="更新人" width="120"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="120"></el-table-column>

      <el-table-column prop="newCst" label="现团队" width="120"></el-table-column>
      <el-table-column prop="newSubCst" label="现子团队" width="120"></el-table-column>
      <el-table-column prop="nowCst" label="原团队" width="120"></el-table-column>
      <el-table-column prop="nowSubCst" label="原子团队" width="120"></el-table-column>

      <el-table-column fixed="right" label="操作" width="150" style="white-space: nowrap">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px">编辑</el-button>
          <el-button
            type="danger"
            style="padding: 3px 4px 3px 4px;margin: 2px"
            size="mini"
            @click="deleteTeamTsf(scope.row)"
          >删除</el-button>
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

    <el-dialog
      title="新增团队变更"
      :visible.sync="dialogVisible_newTeamsTsf"
      width="70%"
      :before-close="handleClose"
      style="line-height: 30px;"
    >
      <el-form
        :model="newChangeInfo"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-row>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="工号" prop="empId" label-width="150px">
                <el-input v-model="newChangeInfo.empId"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple-light">
              <el-form-item label="姓名" prop="chineseName" label-width="150px">
                <el-input v-model="newChangeInfo.chineseName"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <!-- <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="原团队" prop="nowCst" label-width="150px">
                <el-input v-model="newChangeInfo.nowCst"></el-input>
              </el-form-item>
            </div>
          </el-col>-->

          <el-col :span="6">
            <div class="grid-content bg-purple-light">
              <el-form-item label="原团队" prop="newCst" label-width="150px">
                <el-select
                  v-model="newChangeInfo.nowCst"
                  style="width: 120px"
                  size="mini"
                  placeholder
                  v-on:change="getCstforNew($event)"
                >
                  <el-option
                    v-for="item in cst_degrees"
                    :key="item.id"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>

          <!-- <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="原子团队" prop="nowSubCst" label-width="150px">
                <el-input v-model="newChangeInfo.nowSubCst"></el-input>
              </el-form-item>
            </div>
          </el-col>-->

          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="原子团队" prop="newSubCst" label-width="150px">
                <el-select
                  v-model="newChangeInfo.nowSubCst"
                  style="width: 120px"
                  size="mini"
                  placeholder
                  v-if="newChangeInfo.nowCst!=''"
                  v-on:change="getSubCstforNew($event)"
                >
                  <el-option
                    v-for="item in subCsts"
                    :key="item.id"
                    :label="item.label"
                    :value="item.label"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="现团队" prop="newCst" label-width="150px">
                <el-select
                  v-model="newChangeInfo.newCst"
                  style="width: 120px"
                  size="mini"
                  placeholder
                  v-on:change="getCst($event)"
                >
                  <el-option
                    v-for="item in cst_degrees"
                    :key="item.id"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>

          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="现子团队" prop="newSubCst" label-width="150px">
                <el-select
                  v-model="newChangeInfo.newSubCst"
                  style="width: 120px"
                  size="mini"
                  placeholder
                  v-if="newChangeInfo.newCst!=''"
                  v-on:change="getSubCst($event)"
                >
                  <el-option
                    v-for="item in subCsts"
                    :key="item.id"
                    :label="item.label"
                    :value="item.label"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>

          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="执行人" prop="createUserName" label-width="150px">
                <el-input v-model="newChangeInfo.createUserName"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_newTeamsTsf = false">取 消</el-button>
        <el-button type="primary" @click="newTeamChange('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="团队变更"
      :visible.sync="dialogVisibleEdit"
      width="70%"
      :before-close="handleClose"
      style="line-height: 30px;"
    >
      <el-form
        :model="changeInfo"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-row>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="工号" prop="empId" label-width="150px">
                <el-input v-model="changeInfo.empId"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple-light">
              <el-form-item label="姓名" prop="chineseName" label-width="150px">
                <el-input v-model="changeInfo.chineseName"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="原团队" prop="nowCst" label-width="150px">
                <el-input v-model="changeInfo.nowCst"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="原子团队" prop="nowSubCst" label-width="150px">
                <el-input v-model="changeInfo.nowSubCst"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="现团队" prop="newCst" label-width="150px">
                <el-select
                  v-model="changeInfo.newCst"
                  style="width: 120px"
                  size="mini"
                  placeholder
                  v-on:change="getCst($event)"
                >
                  <el-option
                    v-for="item in cst_degrees"
                    :key="item.id"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>

          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="现子团队" prop="newSubCst" label-width="150px">
                <el-select
                  v-model="changeInfo.newSubCst"
                  style="width: 120px"
                  size="mini"
                  placeholder
                  v-if="changeInfo.newCst!=''"
                  v-on:change="getSubCst($event)"
                >
                  <el-option
                    v-for="item in subCsts"
                    :key="item.id"
                    :label="item.label"
                    :value="item.label"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>

          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-form-item label="执行人" prop="createUserName" label-width="150px">
                <el-input v-model="changeInfo.createUserName"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleEdit = false">取 消</el-button>
        <el-button type="primary" @click="teamEdit('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
export default {
  name: "EmpTeamtsf",
  data() {
    return {
      fileUploadBtnText: "导入数据",
      dialogVisible_newTeamsTsf: false,
      cst: "",
      subCst: "",
      subCsts: [],
      dataList: [],
      empName: "",
      multipleSelection: [],
      tableLoading: false,
      cst_degrees: [
        { id: 11, name: "" },
        { id: 1, name: "CST ERP" },
        { id: 2, name: "CST PMO" },
        { id: 3, name: "CST Cloud" },
        { id: 4, name: "CST HCM" },
        { id: 5, name: "CST SFDC" },
        { id: 6, name: "CST CSD" },
        { id: 7, name: "CST LAB" },
        { id: 8, name: "CST Central" },
        { id: 9, name: "CST RDC" },
        { id: 10, name: "I&D" }
      ],
      queryInfo: {
        chineseName: "",
        empId: "",
        search: "",
        size: 10,
        start: 0
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
      changeInfo: {
        effectiveDate: "",
        empId: 0,
        id: 0,
        newCst: "",
        newSubCst: "",
        nowCst: "",
        nowSubCst: "",
        operationUserId: 0
      },
      newChangeInfo: {
        effectiveDate: "",
        empId: 0,
        id: 0,
        newCst: "",
        newSubCst: "",
        nowCst: "",
        nowSubCst: "",
        operationUserId: 0
      },
      rules: {
        effectiveDate: [
          { required: true, message: "请输入日期", trigger: "blur" }
        ],
        newCst: [{ required: true, message: "请输入现团队", trigger: "blur" }],
        newSubCst: [
          { required: true, message: "请输入现子团队", trigger: "blur" }
        ],
        nowCst: [{ required: true, message: "请输入原团队", trigger: "blur" }],
        nowSubCst: [
          { required: true, message: "请输入原子团队：", trigger: "blur" }
        ],
        chineseName: [
          { required: true, message: "请输入姓名", trigger: "blur" }
        ],
        empId: [{ required: true, message: "请输入empId", trigger: "blur" }]
      }
    };
  },
  created() {
    this.userId = localStorage.getItem("id");
    this.getList();
  },
  mounted: function() {},
  methods: {
    // 所有的方法封装
    getSubCst: function(subCst) {
      console.log(subCst);
      console.log(this.selectSubCst);
    },
    getCst: function(cst) {
      let tempSubCst = [];
      this.subCsts = [];
      this.selectSubCst = "";
      let allSubCst = [
        {
          cst: "CST ERP",
          label: ""
        },
        {
          cst: "CST ERP",
          label: "CST ERP"
        },
        {
          cst: "CST PMO",
          label: ""
        },
        {
          cst: "CST PMO",
          label: "CST PMO"
        },
        {
          cst: "CST Cloud",
          label: ""
        },
        {
          cst: "CST Cloud",
          label: "Testing"
        },
        {
          cst: "CST Cloud",
          label: "Cyber Security"
        },
        {
          cst: "CST Cloud",
          label: "Cloud"
        },
        {
          cst: "CST HCM",
          label: ""
        },
        {
          cst: "CST HCM",
          label: "HCM"
        },
        {
          cst: "CST HCM",
          label: "PS"
        },
        {
          cst: "CST HCM",
          label: "SF"
        },
        {
          cst: "CST HCM",
          label: "SAP"
        },
        {
          cst: "CST SFDC",
          label: ""
        },
        {
          cst: "CST SFDC",
          label: "CST SFDC"
        },
        {
          cst: "CST CSD",
          label: ""
        },
        {
          cst: "CST CSD",
          label: "CST CSD"
        },
        {
          cst: "CST LAB",
          label: ""
        },
        {
          cst: "CST LAB",
          label: "CST LAB"
        },
        {
          cst: "CST Central",
          label: ""
        },
        {
          cst: "CST Central",
          label: "Operation"
        },
        {
          cst: "CST Central",
          label: "TSC"
        },
        {
          cst: "CST Central",
          label: "CTO"
        },
        {
          cst: "CST RDC",
          label: ""
        },
        {
          cst: "CST RDC",
          label: "ERP"
        },
        {
          cst: "CST RDC",
          label: "Cloud"
        },
        {
          cst: "CST RDC",
          label: "CSD"
        },
        {
          cst: "CST RDC",
          label: "HCM"
        },
        {
          cst: "CST RDC",
          label: "SFDC"
        },
        {
          cst: "CST RDC",
          label: "Central"
        },
        {
          cst: "I&D",
          label: ""
        }
      ];
      for (var val of allSubCst) {
        if (cst == val.cst) {
          console.log(val);
          tempSubCst.push({ label: val.label, value: val.label });
        }
      }
      this.subCsts = tempSubCst;
      this.changeInfo.newSubCst = "";
    },

    getSubCstforNew: function(subCst) {
      console.log(subCst);
      console.log(this.selectSubCst);
    },
    getCstforNew: function(cst) {
      let tempSubCst = [];
      this.subCsts = [];
      this.selectSubCst = "";
      let allSubCst = [
        {
          cst: "CST ERP",
          label: ""
        },
        {
          cst: "CST ERP",
          label: "CST ERP"
        },
        {
          cst: "CST PMO",
          label: ""
        },
        {
          cst: "CST PMO",
          label: "CST PMO"
        },
        {
          cst: "CST Cloud",
          label: ""
        },
        {
          cst: "CST Cloud",
          label: "Testing"
        },
        {
          cst: "CST Cloud",
          label: "Cyber Security"
        },
        {
          cst: "CST Cloud",
          label: "Cloud"
        },
        {
          cst: "CST HCM",
          label: ""
        },
        {
          cst: "CST HCM",
          label: "HCM"
        },
        {
          cst: "CST HCM",
          label: "PS"
        },
        {
          cst: "CST HCM",
          label: "SF"
        },
        {
          cst: "CST HCM",
          label: "SAP"
        },
        {
          cst: "CST SFDC",
          label: ""
        },
        {
          cst: "CST SFDC",
          label: "CST SFDC"
        },
        {
          cst: "CST CSD",
          label: ""
        },
        {
          cst: "CST CSD",
          label: "CST CSD"
        },
        {
          cst: "CST LAB",
          label: ""
        },
        {
          cst: "CST LAB",
          label: "CST LAB"
        },
        {
          cst: "CST Central",
          label: ""
        },
        {
          cst: "CST Central",
          label: "Operation"
        },
        {
          cst: "CST Central",
          label: "TSC"
        },
        {
          cst: "CST Central",
          label: "CTO"
        },
        {
          cst: "CST RDC",
          label: ""
        },
        {
          cst: "CST RDC",
          label: "ERP"
        },
        {
          cst: "CST RDC",
          label: "Cloud"
        },
        {
          cst: "CST RDC",
          label: "CSD"
        },
        {
          cst: "CST RDC",
          label: "HCM"
        },
        {
          cst: "CST RDC",
          label: "SFDC"
        },
        {
          cst: "CST RDC",
          label: "Central"
        },
        {
          cst: "I&D",
          label: ""
        }
      ];
      for (var val of allSubCst) {
        if (cst == val.cst) {
          console.log(val);
          tempSubCst.push({ label: val.label, value: val.label });
        }
      }
      this.subCsts = tempSubCst;
      this.newChangeInfo.nowSubCst = "";
    },

    getList() {
      this.loading = true;
      this.postRequest(
        "/employee/team_change/listTeamChange",
        this.queryInfo
      ).then(msg => {
        this.loading = false;
        if (msg && msg.status == 200) {
          console.log(msg);
          this.dataList = msg.data.obj.teamChangeLogVoList;
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
            "/employee/team_change/updateTeamChangeLog",
            this.changeInfo
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
      this.changeInfo.empId = row.empId;
      this.changeInfo.id = row.id;
      this.changeInfo.newCst = row.newCst;
      this.changeInfo.newSubCst = row.newSubCst;
      this.changeInfo.nowCst = row.nowCst;
      this.changeInfo.nowSubCst = row.nowSubCst;
      this.changeInfo.chineseName = row.chineseName;
      this.changeInfo.createUserName = row.createUserName;

      this.empName = this.dialogVisibleEdit = true;
    },
    teamEdit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.postRequest(
            "/employee/team_change/updateTeamChangeLog",
            // "/employee/StudyDeclaration/updateStudyDeclarationById",
            this.changeInfo
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
    fileUploadSuccess(response, file, fileList) {
      console.log(response);
      if (response) {
        if (response.status == "200") {
          var responseType = "success";
        }
        this.$message({ type: responseType, message: response.obj });
      }
      this.fileUploadBtnText = "导入数据";
      this.queryDecoration();
    },
    fileUploadError(err, file, fileList) {
      this.$message({ type: "error", message: "导入失败!" });
      this.fileUploadBtnText = "导入数据";
    },
    beforeFileUpload(file) {
      this.fileUploadBtnText = "正在导入";
    },
    queryDecoration() {
      this.getList();
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
        "/employee/team_change/deleteTeamChangeLogById",
        datas
      ).then(resp => {
        this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          this.$message({ type: "success", message: "删除成功！" });
          this.getList();
        }
      });
    },

   deleteTeamTsf(row){
        this.$confirm('此操作将永久删除[' + row.chineseName + ']的本条团队变更记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doManyDelete(row.id);
        }).catch(() => {
        });
      },
    importData() {
      this.postRequest("/employee/team_change/importChangeTeamLog", datas).then(
        resp => {
          this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({ type: "success", message: "删除成功！" });
            this.getList();
          }
        }
      );
    },
    getModel() {
      let url = "/employee/team_change/exportChangeTeamRecord";
      window.open(url, "_blank");
    },
    newTeamsTsf() {
      this.newChangeInfo.createUserName = localStorage.getItem("createName");
      console.log(this.newChangeInfo);

      this.dialogVisible_newTeamsTsf = true;
    },
    newTeamChange(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.postRequest(
            "/employee/team_change/addTeamChangeLog",
            // "/employee/StudyDeclaration/updateStudyDeclarationById",
            this.newChangeInfo
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.dialogVisible_newTeamsTsf = false;
              this.getList();
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