<template>
  <!-- <div>
    <h1>管理</h1>
    <h5>JTP学员信息记录页</h5>
    <h5>访谈或得奖情况tabel</h5>
  </div> -->
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过员工姓名搜索信息,记得回车哦..."
            clearable
            @change="keywordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchEmp"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchEmp">搜索
          </el-button>
          <el-button slot="reference" type="primary" size="mini" style="margin-left: 5px"
                     @click="showAdvanceSearchView"><i
            class="fa fa-lg" v-bind:class="[advanceSearchViewVisible ? faangledoubleup:faangledoubledown]"
            style="margin-right: 5px"></i>高级搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-upload
            :show-file-list="false"
            accept="application/vnd.ms-excel"
            action="/employee/project/importPro"
            :on-success="fileUploadSuccess"
            :on-error="fileUploadError" :disabled="fileUploadBtnText=='正在导入'"
            :before-upload="beforeFileUpload" style="display: inline">
            <el-button size="mini" type="success" :loading="fileUploadBtnText=='正在导入'">
              <i class="fa fa-lg fa-level-up" style="margin-right: 5px"></i>{{fileUploadBtnText}}
            </el-button>
          </el-upload>
          <el-button type="success" size="mini" @click="exportEmps">
            <i class="fa fa-lg fa-level-down" style="margin-right: 5px"></i>导出数据
          </el-button>
          <el-button type="danger" size="mini" v-if="projectExps.length>0" :disabled="multipleSelection.length==0"
                     @click="deleteManyEmps">批量删除
          </el-button>

          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddEmpView()">
            添加项目经历
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>

          <transition name="slide-fade">
            <div
              style="margin-bottom: 10px;border: 1px;border-radius: 5px;border-style: solid;padding: 5px 0px 5px 0px;box-sizing:border-box;border-color: #20a0ff"
              v-show="advanceSearchViewVisible">
              <el-row style="margin-top: 10px">
                <el-col :span="6" >
                  <el-input
                    placeholder="通过account搜索信息,记得回车哦..."
                    clearable
                    @change="keyAccountChange"
                    style="width: 300px;margin: 0px;padding: 0px;"
                    size="mini"


                    prefix-icon="el-icon-search"
                    v-model="keyAccount">
                  </el-input>
                </el-col>
                <el-col :span="6">
                  <el-input
                    placeholder="通过project name搜索信息,记得回车哦..."
                    clearable
                    @change="keyProNameChange"
                    style="width: 300px;margin: 0px;padding: 0px;"
                    size="mini"


                    prefix-icon="el-icon-search"
                    v-model="keyProName">
                  </el-input>
                </el-col>
                <el-col :span="5" :offset="2">

                  <el-button size="mini" @click="cancelSearch">取消</el-button>
                  <el-button icon="el-icon-search" type="primary" size="mini" @click="searchEmp">搜索</el-button>
                </el-col>
              </el-row>
            </div>
          </transition>

          <el-table
            :data="projectExps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              prop="id"
              type="selection"
              align="left"
              width="">
            </el-table-column>
            <el-table-column
              prop="empId"
              align="left"
              fixed
              label="员工编号"
              width="100">
            </el-table-column>
            <el-table-column
              prop="chineseName"
              align="left"
              fixed
              label="员工姓名"
              width="100">
            </el-table-column>
            <el-table-column
              prop="account"
              align="left"
              
              label="Account"
              width="150">
            </el-table-column>
            <el-table-column
              prop="projectName"
              align="left"
              
              label="Project Name"
              width="200">
            </el-table-column>
            <el-table-column
              prop="projectManager"
              align="left"
              
              label="EM"
              width="100">
            </el-table-column>
            <el-table-column
              prop="joinTime"
              align="left"

              label="Join Date"
              width="150">
            </el-table-column>
            <el-table-column
              prop="endTime"
              align="left"
              label="Leave Date"
              width="150">
            </el-table-column>
            <el-table-column
              align="left"
              prop="createTime"
              label="创建时间"
              width="150">
            </el-table-column>

            <el-table-column
              align="left"
              prop="updateTime"
              label="更新时间"
              width="150">
            </el-table-column>
            <el-table-column
              align="left"
              prop="createUserName"
              label="创建人"
              width="150">
            </el-table-column>
            <el-table-column
              align="left"
              label="操作"
              fixed="right"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showPrizeEmpView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteEmpPrize(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <!-- <el-button type="danger" size="mini" v-if="emps.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyEmps">批量删除
            </el-button> -->
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount">
            </el-pagination>
          </div>
        </div>
      </el-main>
    </el-container>
    <el-form :model="projectExp" :rules="rules" ref="addEmpPrizeForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="60%">
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="12">
              <div>
                <el-form-item label="姓名:" prop="chineseName">
                  <el-select v-model="projectExp.chineseName" v-bind:disabled="idIsDisabled==0"  @change="getSelected" style="width: 120px" size="mini" placeholder="员工姓名">
                    <el-option
                      v-for="item in allEmps"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                  <!-- <el-input prefix-icon="el-icon-edit" v-model="emp.empName" size="mini" style="width: 150px"
                          v-bind:disabled="emp.empName!=''"  placeholder="请输入员工姓名"></el-input> -->
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="12">
              <div>
                <el-form-item label="员工编号" prop="empId">
                  <el-input prefix-icon="el-icon-message" v-model="projectExp.empId" size="mini" style="width: 300px"
                            placeholder="请输入员工编号"></el-input>
                </el-form-item>
              </div>
            </el-col>

          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="account:" prop="account">
                  <el-input prefix-icon="el-icon-message" v-model="projectExp.account" size="mini" style="width: 300px"
                            placeholder="Please enter accout name"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="project name:" prop="projectName">
                  <el-input prefix-icon="el-icon-message" v-model="projectExp.projectName" size="mini" style="width: 300px"
                            placeholder="Please enter project name"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="EM:" prop="projectManager">
                  <el-input prefix-icon="el-icon-edit" v-model="projectExp.projectManager" size="mini" style="width: 300px"
                            placeholder="Please enter EM"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="加入时间:" prop="joinTime">
                  <el-date-picker
                    v-model="projectExp.joinTime"
                    size="mini"
                    style="width: 130px"
                    type="date"
                    value-format="yyyy-MM-dd"

                    placeholder="加入时间">
                  </el-date-picker>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="离开时间:" prop="endTime">
                  <el-date-picker
                    v-model="projectExp.endTime"
                    size="mini"
                    style="width: 130px"
                    type="date"
                    value-format="yyyy-MM-dd"

                    placeholder="离开时间">
                  </el-date-picker>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addEmp('addEmpPrizeForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        projectExps:[],
        emps: [],
        keywords: '',
        keyAccount:'',
        keyProName:'',
        fileUploadBtnText: '导入数据',
        beginDateScope: '',
        endDateScope: '',
        faangledoubleup: 'fa-angle-double-up',
        faangledoubledown: 'fa-angle-double-down',
        dialogTitle: '',
        multipleSelection: [],
        depTextColor: '#c0c4cc',
        nations: [],
        politics: [],
        positions: [],
        joblevels: [],
        allEmps: [],
        totalCount: -1,
        currentPage: 1,
        deps: [],
        defaultProps: {
          label: 'name',
          isLeaf: 'leaf',
          children: 'children'
        },
        idIsDisabled:0,
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,

        showOrHidePop: false,
        showOrHidePop2: false,
        projectExp:{
          id:'',
          empId:'',
          account:'',
          chineseName:'',
          projectName:'',
          projectManager:'',
          joinTime:'',
          endTime:'',
          createUser:'',
          createUserName:'',
          createTime:'',
          updateUser:'',
          updateUserName:'',
          updateTime:''
        },
        emp: {
          id: '',
          empId: '',
          empName: '',
          prizeDetail: '',
          prizeGetTime: '',
          prizeName: '',
          updateTime: ''
        },
        rules: {
          empId: [{required: true, message: '必填:工号', trigger: 'blur'}],
          projectName: [{required: true, message: '必填:project name', trigger: 'blur'}],
          emName: [{required: true, message: '必填:EM', trigger: 'blur'}],
          empID: [{required: true, message: '必填:员工编号', trigger: 'blur'}],
          empName: [{required: true, message: '必填:姓名', trigger: 'blur'}],
          prizeName: [{required: true, message: '必填:奖项名称', trigger: 'blur'}],
          prizeGetTime: [{required: true,message: '必填:获奖时间',trigger: 'blur'}],
          prizeDetail: [{required: true, message: '必填:获奖详情', trigger: 'blur'}],
          updateTime: [{required: true, message: '必填:更新时间', trigger: 'blur'}]
        }
      };
    },
    mounted: function () {
      this.initData();
      this.loadEmpPrizes();
      this.getAllEmpsInfo();
    },
    methods: {
      fileUploadSuccess(response, file, fileList){
        console.log(response);
        if (response) {
          if (response.status == "200"){
            var responseType = "success";
          }
          this.$message({type: responseType, message: response.obj});
        }
        this.loadEmps();
        this.fileUploadBtnText = '导入数据';
      },
      fileUploadError(err, file, fileList){
        this.$message({type: 'error', message: "导入失败!"});
        this.fileUploadBtnText = '导入数据';
      },
      beforeFileUpload(file){
        this.fileUploadBtnText = '正在导入';
      },
      exportEmps(){
        window.open("/employee/project/exportEmp", "_parent");
        this.$message({type: 'success', message: "导出成功！"});
      },
      getSelected(val){
        for(let i=0;i<this.allEmps.length;i++){
          if(this.allEmps[i].id==val){
            this.projectExp.empId=this.allEmps[i].empId;
          }
        }
      },
      getAllEmpsInfo(){
        this.postRequest("/employee/basic/listEmpVo").then(resp=> {
          if (resp.data && resp.data.status == 200) {
            console.log("1111",resp);
            this.allEmps = resp.data.obj;
          }
        });
      },
      cancelSearch(){
        this.advanceSearchViewVisible = false;
        this.emptyEmpData();
        this.beginDateScope = '';
        this.endDateScope = '';
        this.loadEmpPrizes();
      },
      showAdvanceSearchView(){
        this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
        this.keywords = '';
        this.keyAccount = '';
        this.keyProName = '';
        if (!this.advanceSearchViewVisible) {
          this.emptyEmpData();
          this.beginDateScope = '';
          this.endDateScope = '';
          this.loadEmpPrizes();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteManyEmps(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDeleteMultiPrize(ids);
        }).catch(() => {
        });
      },
      doDeleteMultiPrize(ids){
        this.tableLoading = true;
        var _this = this;
        var datas = {
          deleteList:ids
        }
        this.postRequest("/employee/project/deleteBatchPro",datas).then(resp=> {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({type: 'success', message: "删除成功！"});
            _this.loadEmpPrizes();
          }
        })
      },
      deleteEmpPrize(row){
        this.$confirm('此操作将永久删除[' + row.empName + ']的[' + row.projectName + ']项目经历, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDeletePrize(row.id);
        }).catch(() => {
        });
      },
      doDeletePrize(ids){
        this.tableLoading = true;
        var _this = this;
        var datas = {
          id:ids,
          operationUserId:1
        }
        this.postRequest("/employee/project/deleteProjectById",datas).then(resp=> {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({type: 'success', message: "删除成功！"});
            _this.loadEmpPrizes();
          }
        })
      },
      keywordsChange(val){
        if (val == '') {
          this.loadEmpPrizes();
        }
      },
      keyAccountChange(val){
        if (val == '') {
          this.loadEmpPrizes();
        }
      },
      keyProNameChange(val){
        if (val == '') {
          this.loadEmpPrizes();
        }
      },
      searchEmp(){
        this.loadEmpPrizes();
      },
      currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadEmpPrizes();
      },
      loadEmpPrizes(){
        var _this = this;
        var datas = {
          "empId": "",
          "endBeginDate":"",
          "startBeginDate":"",
          "account":_this.keyAccount,
          "projectName":_this.keyProName,
          "projectManager":"",
          "search": _this.keywords,
          "size": "10",
          "start": (this.currentPage-1)*10
        };
        this.tableLoading = true;
        // this.postRequest("/employee/basic/listEmp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&politicId=" + this.emp.politicId + "&nationId=" + this.emp.nationId + "&posId=" + this.emp.posId + "&jobLevelId=" + this.emp.jobLevelId + "&engageForm=" + this.emp.engageForm + "&departmentId=" + this.emp.departmentId + "&beginDateScope=" + this.beginDateScope).then(resp=> {
        this.postRequest("/employee/project/listProject",datas).then(resp=> {
          this.tableLoading = false;
          console.log(resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.projectExperienceVoList;
            _this.projectExps = data;

            _this.totalCount = resp.data.obj.listCount;
            // _this.totalCount = data.length;
            // _this.emps = data;
            // _this.id = data.id;
            // _this.emp.address = data.address;
            // _this.emp.beginDate = data.beginDate;
            // _this.emp.birthday = data.birthday;
            // _this.emp.email = data.email;
            // _this.emp.gender = data.gender;
            // _this.emp.name = data.name;
            // _this.emp.phone = data.phone;
            // _this.emp.school = data.school;
            // _this.emp.specialty = data.specialty;
            // _this.emp.tiptopDegree = data.tiptopDegree;
            // _this.emp.workId = data.workId;
//            _this.emptyEmpData();
          }
        })

      },
      addEmp(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.projectExp.id) {
              //更新
              this.tableLoading = true;
              this.postRequest("/employee/project/updateProjectById", this.projectExp).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyEmpData();
                  _this.loadEmpPrizes();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/employee/project/addProject", this.projectExp).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyEmpData();
                  _this.loadEmpPrizes();
                }
              })
            }
            _this.idIsDisabled = 0;
          } else {
            return false;
          }
        });
      },
      cancelEidt(){
        this.dialogVisible = false;
        this.idIsDisabled = 0;
        this.emptyEmpData();
      },
      showDepTree(){
        this.showOrHidePop = !this.showOrHidePop;
      },
      showDepTree2(){
        this.showOrHidePop2 = !this.showOrHidePop2;
      },
      handleNodeClick(data) {
        this.emp.departmentName = data.name;
        this.emp.departmentId = data.id;
        this.showOrHidePop = false;
        this.depTextColor = '#606266';
      },
      handleNodeClick2(data) {
        this.emp.departmentName = data.name;
        this.emp.departmentId = data.id;
        this.showOrHidePop2 = false;
        this.depTextColor = '#606266';
      },
      initData(){
        var _this = this;
        var datas = {
          "empId": "",
          "endBeginDate":"",
          "startBeginDate":"",
          "projectManager":"",
          "projectAccount":"",
          "account":"",
          "search": "",
          "size": "10",
          "start": (this.currentPage-1)*10
        };
        this.postRequest("/employee/project/listProject",datas).then(resp=> {
          if (resp.data && resp.data.status == 200) {
            var data = resp.data.obj.projectExperienceVoList;

            //_this.emps = data;
            _this.projectExps = data;
            // _this.id = data.id;
            _this.projectExp.id = data.id;
            _this.projectExp.empId = data.empId;
            _this.projectExp.chineseName = data.chineseName;
            _this.projectExp.account = data.account;
            _this.projectExp.projectName = data.projectName;
            _this.projectExp.projectManager = data.projectManager;
            _this.projectExp.joinTime = data.joinTime;
            _this.projectExp.endTime = data.endTime;
            _this.projectExp.createUser = data.createUser;
            _this.projectExp.createTime = data.createTime;
            _this.projectExp.createUserName = data.createUserName;
            _this.projectExp.updateUser = data.updateUser;
            _this.projectExp.updateTime = data.updateTime;
            _this.projectExp.updateUserName = data.updateUserName;

          }
        })
      },
      showPrizeEmpView(row){
        console.log(row);
        this.dialogTitle = "编辑项目经历";
        this.$refs['addEmpPrizeForm'].clearValidate() // 重置验证
        this.projectExp = row;
        this.projectExp.empId = row.empId;
        this.projectExp.empName = row.empName;
        this.projectExp.account = row.account;
        this.projectExp.joinTime = row.joinTime;
        this.projectExp.endTime = row.endTime;
        /*this.prizeDetail = row.prizeDetail;
        this.emp.prizeGetTime = row.prizeGetTime;
        this.emp.prizeName = row.prizeName;*/
        delete this.emp.workAge;
        delete this.emp.notWorkDate;
        this.dialogVisible = true;
      },
      showAddEmpView(){
        this.idIsDisabled = 1;
        console.log(this.idIsDisabled);
        this.dialogTitle = "添加项目经历";
        this.$refs['addEmpPrizeForm'].clearValidate() // 重置验证
        // this.emp.empId = this.allEmps;
        // this.emp.empName = '';
        this.projectExp.empId = '';
        this.projectExp.account = '';
        this.projectExp.joinTime = '';
        this.projectExp.endTime = '';
        this.dialogVisible = true;
        // this.getRequest("/employee/basic/maxWorkID").then(resp=> {
        //   if (resp && resp.status == 200) {
        //     _this.emp.workId = resp.data;
        //   }
        // })
      },
      emptyEmpData(){
        this.projectExp = {
          id: '',
          empId: '',
          chineseName: '',
          account:'',
          projectName:'',
          projectManager:'',
          joinTime:'',
          endTime:'',
          createUser:'',
          createTime:'',
          createUserName:'',
          updateTime: '',
          updateUser:'',
          updateUserName:''
        }
      },

    }
  };
</script>
<style>
  .el-dialog__body {
    padding-top: 0px;
    padding-bottom: 0px;
  }

  .slide-fade-enter-active {
    transition: all .8s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
</style>
