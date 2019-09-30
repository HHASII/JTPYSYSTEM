<template>
  <div>
    <el-container>
      <el-header
        style="padding: 0px;display:flex;justify-content:space-between;align-items: center"
      >
        <div style="display: inline">
          <el-input
            placeholder="通过员工名搜索员工,记得回车哦..."
            clearable
            @change="keywordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchEmp"
            prefix-icon="el-icon-search"
            v-model="keywords"
          ></el-input>
          <el-button
            type="primary"
            size="mini"
            style="margin-left: 5px"
            icon="el-icon-search"
            @click="searchEmp"
          >搜索</el-button>
          <el-button
            slot="reference"
            type="primary"
            size="mini"
            style="margin-left: 5px"
            @click="showAdvanceSearchView"
          >
            <i
              class="fa fa-lg"
              v-bind:class="[advanceSearchViewVisible ? faangledoubleup:faangledoubledown]"
              style="margin-right: 5px"
            ></i>高级搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-upload
            :show-file-list="false"
            accept="application/vnd.ms-excel"
            action="/employee/basic/importEmp"
            :on-success="fileUploadSuccess"
            :on-error="fileUploadError"
            :disabled="fileUploadBtnText=='正在导入'"
            :before-upload="beforeFileUpload"
            style="display: inline"
          >
            <el-button size="mini" type="success" :loading="fileUploadBtnText=='正在导入'">
              <i class="fa fa-lg fa-level-up" style="margin-right: 5px"></i>
              {{fileUploadBtnText}}
            </el-button>
          </el-upload>
          <el-button type="success" size="mini" @click="exportEmps">
            <i class="fa fa-lg fa-level-down" style="margin-right: 5px"></i>导出数据
          </el-button>

          <el-button
            type="danger"
            size="mini"
            v-if="emps.length>0"
            :disabled="multipleSelection.length==0"
            @click="deleteManyEmps"
          >批量删除</el-button>

          <el-button type="primary" size="mini" icon="el-icon-plus" @click="showAddEmpView">添加员工</el-button>
        </div>
      </el-header>

      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <transition name="slide-fade">
            <div
              style="margin-bottom: 10px;border: 1px;border-radius: 5px;border-style: solid;padding: 5px 0px 5px 0px;box-sizing:border-box;border-color: #20a0ff"
              v-show="advanceSearchViewVisible"
            >
              <el-row style="margin-top: 10px">
                <el-row>
                  <el-col :span="11" align="right">
                    Hire Date:
                    <el-date-picker
                      v-model="hireDateScope"
                      unlink-panels
                      size="mini"
                      type="daterange"
                      value-format="yyyy-MM-dd"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                    ></el-date-picker>
                  </el-col>
                  <el-col :span="5" align="right">
                    Gender:
                    <el-radio-group v-model="gender">
                      <el-radio label="Male">男</el-radio>
                      <el-radio style="margin-left: 15px" label="Female">女</el-radio>
                    </el-radio-group>
                  </el-col>
                  <el-col :span="5" align="right">
                    Degree:
                    <el-select v-model="degree" style="width: 80px" size="mini" placeholder>
                      <el-option
                        v-for="item in degrees"
                        :key="item.id"
                        :label="item.name"
                        :value="item.name"
                      ></el-option>
                    </el-select>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 10px">
                  <el-col :span="11" align="right">
                    Leave Date:
                    <el-date-picker
                      v-model="leaveDateScope"
                      unlink-panels
                      size="mini"
                      type="daterange"
                      value-format="yyyy-MM-dd"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                    ></el-date-picker>
                  </el-col>
                  <el-col :span="5" align="right">
                    Department:
                    <el-select v-model="department" style="width: 100px" size="mini" placeholder>
                      <el-option
                        v-for="item in department_degrees"
                        :key="item.id"
                        :label="item.name"
                        :value="item.name"
                      ></el-option>
                    </el-select>
                  </el-col>
                  <el-col :span="7" align="right">
                    WorkingLocation:
                    <el-input
                      prefix-icon="el-icon-edit"
                      v-model="workingLocation"
                      size="mini"
                      style="width: 120px"
                      placeholder
                    ></el-input>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 10px">
                  <el-col :span="11" align="right">
                    Birthday:
                    <el-date-picker
                      v-model="birthdayScope"
                      unlink-panels
                      size="mini"
                      type="daterange"
                      value-format="yyyy-MM-dd"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                    ></el-date-picker>
                  </el-col>
                  <el-col :span="5" align="right">
                    CST:
                    <el-select
                      v-model="cst"
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
                  </el-col>
                  <el-col :span="6" align="right">
                    SUB_CST:
                    <el-select
                      v-model="subCst"
                      style="width: 120px"
                      size="mini"
                      placeholder
                      v-if="cst!=''"
                      v-on:change="getSubCst($event)"
                    >
                      <el-option
                        v-for="item in subCsts"
                        :key="item.id"
                        :label="item.label"
                        :value="item.label"
                      ></el-option>
                    </el-select>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 10px">
                  <el-col :span="8" align="right">
                    TerminationType:
                    <el-select
                      v-model="terminationType"
                      style="width: 150px"
                      size="mini"
                      placeholder="离职类型"
                    >
                      <el-option
                        v-for="item in TerminationType_degrees"
                        :key="item.id"
                        :label="item.name"
                        :value="item.name"
                      ></el-option>
                    </el-select>
                  </el-col>
                  <el-col :span="11" :offset="4" align="right">
                    <el-button size="mini" @click="cancelSearch">取消</el-button>
                    <el-button
                      icon="el-icon-search"
                      type="primary"
                      size="mini"
                      @click="searchEmp"
                    >搜索</el-button>
                  </el-col>
                </el-row>
              </el-row>
            </div>
          </transition>
          <el-table
            :data="emps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%"
          >
            <el-table-column prop="id" type="selection" align="left" width="40"></el-table-column>
            <el-table-column prop="empId" align="left" fixed label="Emp ID" width="90"></el-table-column>
            <el-table-column prop="chineseName" align="left" fixed label="Chinese Name" width="120"></el-table-column>
            <el-table-column prop="gender" label="Gender " width="90"></el-table-column>
            <el-table-column prop="grade" width="90" align="left" label="Grade"></el-table-column>
            <el-table-column prop="hireDate" width="120" align="left" label="Hire Date">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="department" width="90" align="left" label="Department"></el-table-column>
            <el-table-column prop="legalEntity" width="90" align="left" label="Legal Entity"></el-table-column>
            <el-table-column prop="workingLocation" width="130" label="Working Location"></el-table-column>
            <el-table-column prop="phone" width="150" align="left" label="Mobile Phone"></el-table-column>
            <el-table-column prop="email" width="200" align="left" label="Email"></el-table-column>
            <el-table-column
              align="left"
              width="200"
              prop="teachingStartDate"
              label="Training Batch Starting Date"
            ></el-table-column>
            <el-table-column align="left" width="150" prop="school" label="School"></el-table-column>
            <el-table-column align="left" width="130" prop="major" label="Major"></el-table-column>
            <el-table-column align="left" width="130" prop="degree" label="Degree"></el-table-column>
            <el-table-column align="left" width="130" prop="graduationYear" label="Graduation Year"></el-table-column>
            <el-table-column align="left" width="130" prop="cst" label="CST"></el-table-column>
            <el-table-column align="left" width="130" prop="subCst" label="SUB_CST"></el-table-column>
            <el-table-column align="left" width="130" prop="skillGroup" label="Skill Group"></el-table-column>
            <el-table-column align="left" width="130" prop="skillSets" label="Skill Sets"></el-table-column>
            <el-table-column align="left" width="130" prop="leaveDate" label="Leave Date"></el-table-column>
            <el-table-column
              align="left"
              width="130"
              prop="terminationType"
              label="Termination Type"
            ></el-table-column>
            <el-table-column
              align="left"
              width="150"
              prop="terminationReason"
              label="Termination Reason"
            ></el-table-column>
            <el-table-column
              align="left"
              width="170"
              prop="exitComments"
              label="Exit interview comments"
            ></el-table-column>
            <el-table-column align="left" width="130" prop="leavingAccount" label="Leaving Account"></el-table-column>
            <el-table-column align="left" width="130" prop="formerId" label="曾用Employee ID"></el-table-column>
            <el-table-column align="left" width="130" prop="birthday" label="Birthday">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createUserName" width="120" align="left" label="创建人">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createTime" width="150" align="left" label="创建时间"></el-table-column>
            <el-table-column align="left" prop="updateUserName" width="120" label="更新人"></el-table-column>
            <el-table-column align="left" width="150" prop="updateTime" label="更新时间"></el-table-column>
            <el-table-column fixed="right" label="操作" width="200">
              <template slot-scope="scope">
                <el-button
                  @click="showEditEmpView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >编辑</el-button>

                <el-dropdown>
                  <el-button
                    style="padding: 3px 4px 3px 4px;margin: 2px"
                    type="primary"
                    size="mini"
                  >
                    详细信息
                    <i class="el-icon-arrow-down el-icon--right"></i>
                  </el-button>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>
                      <el-button
                        type="primary"
                        @click="showEmpPrizeList(scope.row.empId,scope.row.chineseName)"
                        size="mini"
                      >获奖经历</el-button>
                    </el-dropdown-item>

                    <el-dropdown-item>
                      <el-button
                        type="primary"
                        @click="showEmpProjectList(scope.row.empId,scope.row.chineseName)"
                        size="mini"
                      >项目经历</el-button>
                    </el-dropdown-item>

                    <el-dropdown-item>
                      <el-button
                        type="primary"
                        @click="showEmpLearningList(scope.row.empId,scope.row.chineseName)"
                        size="mini"
                      >培训经历</el-button>
                    </el-dropdown-item>

                    <el-dropdown-item>
                      <el-button
                        type="primary"
                        @click="showEmpTalkingList(scope.row.empId,scope.row.chineseName)"
                        size="mini"
                      >校长访谈</el-button>
                    </el-dropdown-item>
                    <el-dropdown-item>
                      <el-button type="primary" @click="showTeamchanges(scope.row)" size="mini">团队变更</el-button>
                    </el-dropdown-item>
                    <el-dropdown-item>
                      <el-button
                        type="primary"
                        @click.native="showStudyDialog(scope.row)"
                        size="mini"
                      >学习申报</el-button>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteEmp(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount"
            ></el-pagination>
          </div>
        </div>
      </el-main>
    </el-container>

    <!--添加员工基本信息-->
    <el-form :model="emp" :rules="rules" ref="addEmpForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="77%"
        >
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="EmpId:  " prop="empId">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.empId"
                    size="mini"
                    style="width: 150px"
                    placeholder="请输入员工编号"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="ChineseName:" prop="chineseName">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.chineseName"
                    size="mini"
                    style="width: 150px"
                    placeholder="请输入员工姓名"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="Gender:" prop="gender">
                  <el-radio-group v-model="emp.gender">
                    <el-radio label="Male" value="emp.gender">男</el-radio>
                    <el-radio style="margin-left: 15px" value="emp.gender" label="Female">女</el-radio>
                  </el-radio-group>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="Birthday:" prop="birthday">
                  <el-date-picker
                    v-model="emp.birthday"
                    size="mini"
                    value-format="yyyy-MM-dd"
                    style="width: 139px"
                    type="date"
                    placeholder="出生日期"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="HireDate:" prop="hireDate">
                  <el-date-picker
                    v-model="emp.hireDate"
                    size="mini"
                    value-format="yyyy-MM-dd"
                    style="width: 151px"
                    type="date"
                    placeholder="入职日期"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="10">
              <div>
                <el-form-item label="Training Batch Starting Date:" prop="teachingStartDate">
                  <el-date-picker
                    v-model="emp.teachingStartDate"
                    size="mini"
                    value-format="yyyy-MM-dd"
                    style="width: 150px"
                    type="date"
                    placeholder="培训开始日期"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="Email:" prop="email">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="emp.email"
                    size="mini"
                    style="width: 155px"
                    placeholder="电子邮箱地址..."
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="Mobile Phone:" prop="phone">
                  <el-input
                    prefix-icon="el-icon-phone"
                    v-model="emp.phone"
                    size="mini"
                    style="width: 150px"
                    placeholder="电话号码..."
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="Legal Entity:" prop="legalEntity">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.legalEntity"
                    size="mini"
                    style="width: 136px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="Grade:" prop="grade">
                  <el-select v-model="emp.grade" style="width: 155px" size="mini" placeholder>
                    <el-option
                      v-for="item in grade_degrees"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="Department:" prop="department">
                  <el-select v-model="emp.department" style="width: 120px" size="mini" placeholder>
                    <el-option
                      v-for="item in department_degrees"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="9">
              <div>
                <el-form-item label="Working Location:" prop="workingLocation">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.workingLocation"
                    size="mini"
                    style="width: 150px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="CST:" prop="cst">
                  <el-select
                    v-model="emp.cst"
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
            <el-col :span="6">
              <div>
                <el-form-item label="SUB_CST:" prop="subCst">
                  <el-select
                    v-model="emp.subCst"
                    style="width: 120px"
                    size="mini"
                    placeholder
                    v-if="emp.cst!=''"
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
            <el-col :span="5">
              <div>
                <el-form-item label="Skill Group:" prop="skillGroup">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.skillGroup"
                    size="mini"
                    style="width: 100px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="Skill Sets:" prop="skillSets">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.skillSets"
                    size="mini"
                    style="width: 100px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="Graduation Year:" prop="graduationYear">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.graduationYear"
                    size="mini"
                    style="width: 80px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="4">
              <div>
                <el-form-item label="Degree:" prop="degree">
                  <el-select v-model="emp.degree" style="width: 80px" size="mini" placeholder>
                    <el-option
                      v-for="item in degrees"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="School:" prop="school">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.school"
                    size="mini"
                    style="width: 150px"
                    placeholder="毕业院校名称"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="Major:" prop="major">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.major"
                    size="mini"
                    style="width: 180px"
                    placeholder="专业名称"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="7">
              <div>
                <el-form-item label="Leave Date:" prop="leaveDate">
                  <el-date-picker
                    v-model="emp.leaveDate"
                    size="mini"
                    value-format="yyyy-MM-dd"
                    style="width: 151px"
                    type="date"
                    placeholder="离职日期"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="8">
              <div>
                <el-form-item label="Termination Type:" prop="terminationType">
                  <el-select
                    v-model="emp.terminationType"
                    style="width: 150px"
                    size="mini"
                    placeholder="离职类型"
                    v-on:change="getTerminationType($event)"
                  >
                    <el-option
                      v-for="item in TerminationType_degrees"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="9">
              <div>
                <el-form-item label="Termination Reason:" prop="terminationReason">
                  <el-select
                    v-model="emp.terminationReason"
                    style="width: 200px"
                    size="mini"
                    placeholder="离职理由"
                    v-if="emp.terminationType!=''"
                    v-on:change="getTerminationReason($event)"
                  >
                    <el-option
                      v-for="item in terminationReasons"
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
            <el-col :span="15">
              <div>
                <el-form-item label="Leaving Account:" prop="leavingAccount">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.leavingAccount"
                    size="mini"
                    style="width: 500px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="曾用Employee ID:" prop="formerId">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.formerId"
                    size="mini"
                    style="width: 100px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="16">
              <div>
                <el-form-item label="Exit interview comments:" prop="exitComments">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="emp.exitComments"
                    size="mini"
                    style="width: 400px"
                    placeholder
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addEmp('addEmpForm')">确 定</el-button>
          </span>
        </el-dialog>
        <!-- <router-view class='empPrizeInfo'  name='empPrizeInfo'></router-view> -->
      </div>
    </el-form>

    <!--获奖记录-->

    <el-dialog
      :title="dialogTitle"
      style="padding: 0px;text-align: left"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible_prize"
      width="77%"
    >
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <div style="margin-left: 5px;margin-right: 20px;display: inline">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-plus"
              @click="showAddEmpPrizeView()"
            >添加获奖记录</el-button>
          </div>
          <el-table
            :data="deps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%;margin-top:10px;"
          >
            <el-table-column prop="empId" align="left" fixed label="员工编号" width></el-table-column>
            <el-table-column prop="chineseName" align="left" fixed label="姓名" width></el-table-column>
            <el-table-column align prop="prizeType" label="奖项类别" width="100"></el-table-column>
            <el-table-column prop="prizeDetail" width align="left" label="获奖详情">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createUserName" width align="left" label="创建人">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createTime" width="140" align="left" label="创建时间"></el-table-column>
            <el-table-column align="left" prop="updateUserName" label="更新人"></el-table-column>
            <el-table-column align="left" width="140" prop="updateTime" label="更新时间"></el-table-column>
            <el-table-column fixed="right" label="操作" width="110">
              <template slot-scope="scope">
                <el-button
                  @click="showPrizeEmpView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >编辑</el-button>
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteEmpPrize(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage_prize"
              @current-change="currentChange_prize"
              layout="prev, pager, next"
              :total="totalCount_prize"
            ></el-pagination>
          </div>
        </div>
      </el-main>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="canclePrizeList(dep_prize.empId,dep_prize.empName)">取 消</el-button>
      </span>
    </el-dialog>

    <!--添加修改-->

    <el-form
      :model="dep_prize"
      :rules="rules"
      ref="addEmpPrizeForm"
      style="margin: 0px;padding: 0px;"
    >
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible_prize_form"
          width="60%"
        >
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="12">
              <div>
                <el-form-item label="员工姓名:" prop="chineseName">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_prize.chineseName"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工姓名"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="员工编号:" prop="empId">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_prize.empId"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工编号"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="获奖类型:" prop="prizeType">
                  <el-select
                    v-model="dep_prize.prizeType"
                    style="width: 300px"
                    size="mini"
                    placeholder="请选择获奖类型"
                  >
                    <el-option
                      v-for="item in prizeType_degrees"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="获奖详情:" prop="prizeDetail">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_prize.prizeDetail"
                    size="mini"
                    style="width: 300px"
                    placeholder="请输入获奖详情"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelPrizeEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addPrize('addEmpPrizeForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>

    <!--项目经历-->

    <el-dialog
      :title="dialogTitle"
      style="padding: 0px;text-align: left"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible_project"
      width="77%"
    >
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <div style="margin-left: 5px;margin-right: 20px;display: inline">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-plus"
              @click="showAddEmpProjectView()"
            >添加项目经历</el-button>
          </div>
          <el-table
            :data="deps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%;margin-top:10px;"
          >
            <el-table-column prop="chineseName" align="left" fixed label="姓名" width></el-table-column>
            <el-table-column align="left" prop="empId" label="员工编号" width></el-table-column>
            <el-table-column prop="account" width align="left" label="Account"></el-table-column>
            <el-table-column prop="projectName" width="100" align="left" label="Project Name"></el-table-column>
            <el-table-column prop="projectManager" width align="left" label="EM">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="joinTime" width="100" align="left" label="JoinTime">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="endTime" width="100" align="left" label="LeaveTime">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createUserName" width="120" align="left" label="创建人">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createTime" width="140" align="left" label="创建时间"></el-table-column>
            <el-table-column align="left" prop="updateUserName" width="120" label="更新人"></el-table-column>
            <el-table-column align="left" width="140" prop="updateTime" label="更新时间"></el-table-column>
            <el-table-column fixed="right" label="操作" width="110">
              <template slot-scope="scope">
                <el-button
                  @click="showProjectEmpView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >编辑</el-button>
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteEmpProject(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage_project"
              @current-change="currentChange_project"
              layout="prev, pager, next"
              :total="totalCount_project"
            ></el-pagination>
          </div>
        </div>
      </el-main>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="cancleProjectList(dep_project.empId,dep_project.empName)">取 消</el-button>
      </span>
    </el-dialog>

    <!--添加修改-->
    <el-form
      :model="dep_project"
      :rules="rules"
      ref="addEmpProjectForm"
      style="margin: 0px;padding: 0px;"
    >
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible_project_form"
          width="60%"
        >
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="12">
              <div>
                <el-form-item label="员工姓名" prop="chineseName">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_project.chineseName"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工姓名"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="12">
              <div>
                <el-form-item label="员工编号" prop="empId">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_project.empId"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工编号"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="account:" prop="account">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_project.account"
                    size="mini"
                    style="width: 300px"
                    placeholder="Please enter accout name"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="project name:" prop="projectName">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_project.projectName"
                    size="mini"
                    style="width: 300px"
                    placeholder="Please enter project name"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <div>
                <el-form-item label="EM:" prop="projectManager">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_project.projectManager"
                    size="mini"
                    style="width: 300px"
                    placeholder="Please enter EM"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="加入时间:" prop="joinTime">
                  <el-date-picker
                    v-model="dep_project.joinTime"
                    size="mini"
                    style="width: 130px"
                    type="date"
                    value-format="yyyy-MM-dd"
                    placeholder="加入时间"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="离开时间:" prop="endTime">
                  <el-date-picker
                    v-model="dep_project.endTime"
                    size="mini"
                    style="width: 130px"
                    type="date"
                    value-format="yyyy-MM-dd"
                    placeholder="离开时间"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelProjectEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addProject('addEmpProjectForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>

    <!--培训经历-->

    <el-dialog
      :title="dialogTitle"
      style="padding: 0px;text-align: left"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible_learning"
      width="77%"
    >
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <div style="margin-left: 5px;margin-right: 20px;display: inline">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-plus"
              @click="showAddEmpLearningView()"
            >添加培训经历</el-button>
          </div>
          <el-table
            :data="deps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%;margin-top:10px;"
          >
            <el-table-column prop="chineseName" align="left" fixed label="姓名" width></el-table-column>
            <el-table-column align="left" prop="empId" label="员工编号" width></el-table-column>
            <el-table-column prop="growingType" width align="left" label="培训类别"></el-table-column>
            <el-table-column prop="growingDetail" width align="left" label="详细说明"></el-table-column>
            <el-table-column prop="createUserName" width align="left" label="创建人">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createTime" width="140" align="left" label="创建时间"></el-table-column>
            <el-table-column align="left" prop="updateUserName" label="更新人"></el-table-column>
            <el-table-column align="left" width="140" prop="updateTime" label="更新时间"></el-table-column>
            <el-table-column fixed="right" label="操作" width="110">
              <template slot-scope="scope">
                <el-button
                  @click="showLearningEmpView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >编辑</el-button>
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteEmpLearning(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage_learning"
              @current-change="currentChange_learning"
              layout="prev, pager, next"
              :total="totalCount_learning"
            ></el-pagination>
          </div>
        </div>
      </el-main>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="cancleLearningList">取 消</el-button>
      </span>
    </el-dialog>

    <!--修改删除-->

    <el-form
      :model="dep_learning"
      :rules="rules"
      ref="addEmpLearningForm"
      style="margin: 0px;padding: 0px;"
    >
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible_learning_form"
          width="60%"
        >
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="12">
              <div>
                <el-form-item label="员工姓名:" prop="chineseName">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_learning.chineseName"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工姓名"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="员工编号:" prop="empId">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_learning.empId"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工编号"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="培训类别:" prop="growingType">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_learning.growingType"
                    size="mini"
                    style="width: 300px"
                    placeholder="请输入培训类别"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="详细信息:" prop="growingDetail">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="dep_learning.growingDetail"
                    size="mini"
                    style="width: 300px"
                    placeholder="请输入详细信息"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelLearningEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addLearning('addEmpLearningForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>

    <!--校长访谈-->

    <el-dialog
      :title="dialogTitle"
      style="padding: 0px;text-align: left"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible_talking"
      width="77%"
    >
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <div style="margin-left: 5px;margin-right: 20px;display: inline">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-plus"
              @click="showAddEmpTalkingView()"
            >添加校长访谈</el-button>
          </div>
          <el-table
            :data="deps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%;margin-top:10px;"
          >
            <el-table-column prop="chineseName" align="left" fixed label="姓名" width></el-table-column>
            <el-table-column align="left" prop="empId" label="员工编号" width></el-table-column>
            <el-table-column prop="headMaster" width="100" align="left" label="谈话校长"></el-table-column>
            <el-table-column prop="talkType" width="100" align="left" label="沟通类别"></el-table-column>
            <el-table-column prop="talkContent" width="500" align="left" label="沟通内容"></el-table-column>
            <el-table-column prop="createUserName" width="120" align="left" label="创建人">
              <!-- <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template> -->
            </el-table-column>
            <el-table-column prop="createTime" width="140" align="left" label="创建时间"></el-table-column>
            <el-table-column align="left" prop="updateUserName" width="120" label="更新人"></el-table-column>
            <el-table-column align="left" width="140" prop="updateTime" label="更新时间"></el-table-column>
            <el-table-column fixed="right" label="操作" width="110">
              <template slot-scope="scope">
                <el-button
                  @click="showTalkingEmpView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >编辑</el-button>
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteEmpTalking(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage_talking"
              @current-change="currentChange_talking"
              layout="prev, pager, next"
              :total="totalCount_talking"
            ></el-pagination>
          </div>
        </div>
      </el-main>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="cancleTalkingList">取 消</el-button>
      </span>
    </el-dialog>

    <!--添加修改-->

    <el-form
      :model="dep_talking"
      :rules="rules"
      ref="addEmpTalkingForm"
      style="margin: 0px;padding: 0px;"
    >
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible_talking_form"
          width="60%"
        >
          <el-row style="margin-top: 20px;padding: 0px;">
            <el-col :span="12">
              <div>
                <el-form-item label="员工姓名:" prop="chineseName">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_talking.chineseName"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工姓名"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="员工编号:" prop="empId">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_talking.empId"
                    size="mini"
                    style="width: 300px"
                    v-bind:disabled="true"
                    placeholder="请输入员工编号"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="谈话校长:" prop="headMaster">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_talking.headMaster"
                    size="mini"
                    style="width: 300px"
                    placeholder="请输入谈话校长"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="沟通类别:" prop="talkType">
                  <el-input
                    prefix-icon="el-icon-message"
                    v-model="dep_talking.talkType"
                    size="mini"
                    style="width: 300px"
                    placeholder="请输入沟通类别"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="沟通内容:" prop="talkContent">
                  <el-input
                    prefix-icon="el-icon-edit"
                    v-model="dep_talking.talkContent"
                    size="mini"
                    style="width: 300px"
                    placeholder="请输入沟通内容"
                  ></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelTalkingEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addTalking('addEmpTalkingForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>

    <el-dialog
      title="团队变更"
      style="padding: 0px;text-align: left"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible_teams"
      width="77%"
    >
      <div v-loading="loading">
        <div style="text-align: left;line-height: 60px;">
          <!-- <el-button type="success" @click.native="newTeamDialog()">+团队变更</el-button> -->
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="newTeamDialog()">团队变更</el-button>
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
              <el-button
                @click="handleClick(scope.row)"
                style="padding: 3px 4px 3px 4px;margin: 2px"
              >编辑</el-button>
              <el-button
                @click="deleteTeamTsf(scope.row)"
                type="danger"
            style="padding: 3px 4px 3px 4px;margin: 2px"
            size="mini"
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
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="dialogVisible_teams = false">取 消</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="新建变更团队"
      :visible.sync="dialogVisible_newTeams"
      width="70%"
      :before-close="handleClose"
      style="line-height: 30px;"
    >
      <el-form
        :model="newteamsTrs"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-row>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="工号" prop="empId" label-width="150px">
                <el-input v-model="newteamsTrs.empId"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple-light">
              <el-form-item label="姓名" prop="chineseName" label-width="150px">
                <el-input v-model="newteamsTrs.chineseName"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="原团队" prop="nowCst" label-width="150px">
                <el-input v-model="newteamsTrs.nowCst"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-form-item label="原子团队" prop="nowSubCst" label-width="150px">
                <el-input v-model="newteamsTrs.nowSubCst"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-form-item label="现团队" prop="newCst" label-width="150px">
                <el-select
                  v-model="newteamsTrs.newCst"
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
                  v-model="newteamsTrs.newSubCst"
                  style="width: 120px"
                  size="mini"
                  placeholder
                  v-if="newteamsTrs.newCst!=''"
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
                <el-input v-model="newteamsTrs.createUserName"></el-input>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_newTeams = false">取 消</el-button>
        <el-button type="primary" @click="newTeam('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="团队变更"
      :visible.sync="dialogVisibleEditGG"
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
        <el-button @click="dialogVisibleEditGG = false">取 消</el-button>
        <el-button type="primary" @click="teamEdit('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="学习申报"
      style="padding: 0px;text-align: left"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible_studyDecuration"
      width="77%"
    >
      <!-- <el-button type="success" size="mini" @click.native="newStudyDecoration()">新增</el-button> -->
      <!-- <el-dropdown-item>
            <el-button type="primary" @click="newStudyDecoration()" size="mini">学习申报</el-button>
      </el-dropdown-item>-->

      <div v-loading="loading">
        <div style="text-align: left;line-height: 60px;">
          <!-- <el-button type="success" @click.native="newTeamDialog()">+团队变更</el-button> -->
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            @click="newStudyDecoration()"
          >学习申报</el-button>
          <!-- <el-button type="primary" @click.native="newDecoration()">新建申报</el-button> -->
        </div>
        <!--  -->
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
                @click="deleteStudyDecoration(scope.row)"
                type="danger"
            style="padding: 3px 4px 3px 4px;margin: 2px"
            size="mini"
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

        <!-- <el-dialog
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
                    <el-input v-model="changeInfo.newCst"></el-input>
                  </el-form-item>
                </div>
              </el-col>
              <el-col :span="8">
                <div class="grid-content bg-purple">
                  <el-form-item label="现子团队" prop="newSubCst" label-width="150px">
                    <el-input v-model="changeInfo.newSubCst"></el-input>
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

        -->
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="dialogVisible_studyDecuration = false">取 消</el-button>
      </span>
    </el-dialog>

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
        <el-button type="primary" @click="newDecorateSure_studyDecoration('ruleForm')">确 定</el-button>
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
              <el-form-item label="工号" prop="empId" label-width="150px">
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
  data() {
    return {
      dialogVisible_newStudyDecoration: false,
      dialogVisible_editStudyDecoration: false,
      dialogVisible_studyDecoration: false,
      dialogVisible_newTeams: false,
      dialogVisible_teams: false,
      subCsts: [],
      selectCst: "",
      selectSubCst: "",
      terminationReasons: [],
      selectTerminationReason: "",
      selectTerminationType: "",
      dialogVisible_studyDecuration: false,
      fileUploadBtnText: "导入数据",

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
      // dialogVisibleEdit: false,
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
      },

      tests: [
        {
          emplId: "000000",
          chineseName: "郭美美",
          grade: "x1",
          gender: "female",
          hireDate: "2018-7-16",
          department: "east",
          legalEntity: "",
          workingLocation: "Kunshan",
          phone: "18846436562",
          email: "tong.a.li@capgemini.com",
          teachingStartDate: "2018-7-16",
          school: "黑龙江大学",
          major: "国贸",
          degree: "本科",
          graduationYear: "2019",
          cst: "CST ERP",
          subCst: "CST ERP",
          skillGroup: "",
          skillSets: "",
          leaveDate: "",
          terminationType: "",
          terminationReason: "",
          exitComments: "",
          leavingAccount: "",
          formerId: "",
          birthday: "1998-08-12",
          createUser: "系统管理员",
          createTime: "2019-07-21",
          updateTime: "2019-07-22",
          updateUser: "系统管理员"
        }
      ],
      emps: [],
      keywords: "",
      fileUploadBtnText: "导入数据",
      beginDateScope: "",
      hireDateScope: "",
      leaveDateScope: "",
      birthdayScope: "",
      degree: "",
      gender: "",
      department: "",
      workingLocation: "",
      terminationType: "",
      terminationReason: "",
      cst: "",
      subCst: "",
      endDateScope: "",
      faangledoubleup: "fa-angle-double-up",
      faangledoubledown: "fa-angle-double-down",
      dialogTitle: "",
      multipleSelection: [],
      depTextColor: "#c0c4cc",
      nations: [],
      politics: [],
      positions: [],
      joblevels: [],
      baseInfo: {},
      totalCount: -1,
      totalCount_prize: -1,
      totalCount_project: -1,
      totalCount_learning: -1,
      totalCount_talking: -1,
      currentPage: 1,
      currentPage_prize: 1,
      currentPage_project: 1,
      currentPage_learning: 1,
      currentPage_talking: 1,
      newEmpPrizeEmpId: 0,
      newEmpProjectEmpId: 0,
      newEmpLearningEmpId: 0,
      newEmpTalkingEmpId: 0,
      newEmpPrizeEmpName: "",
      newEmpProjectEmpName: "",
      newEmpLearningEmpName: "",
      newEmpTalkingEmpName: "",
      degrees: [
        { id: 4, name: "" },
        { id: 1, name: "本科" },
        { id: 2, name: "硕士" },
        { id: 3, name: "博士" }
      ],
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
      subCst_degrees: [
        { id: 9, name: "" },
        { id: 1, name: "CST ERP" },
        { id: 2, name: "CST PMO" },
        { id: 3, name: "Testing" },
        { id: 4, name: "CST HCM" },
        { id: 5, name: "CST SFDC" },
        { id: 6, name: "CST CSD" },
        { id: 7, name: "CST LAB" },
        { id: 8, name: "CST Central" }
      ],
      grade_degrees: [
        { id: 9, name: "" },
        { id: 1, name: "X1" },
        { id: 2, name: "X2" },
        { id: 3, name: "X3" },
        { id: 4, name: "A5" },
        { id: 5, name: "B1" },
        { id: 6, name: "C1" },
        { id: 7, name: "C2" },
        { id: 8, name: "D1" }
      ],
      prizeType_degrees: [
        { id: 5, name: "" },
        { id: 1, name: "雏鹰之星" },
        { id: 2, name: "奖学金" },
        { id: 3, name: "CSR" },
        { id: 4, name: "Project Angel" }
      ],
      department_degrees: [
        { id: 5, name: "" },
        { id: 1, name: "East" },
        { id: 2, name: "North" },
        { id: 3, name: "South" },
        { id: 4, name: "RDC" }
      ],
      TerminationType_degrees: [
        { id: 4, name: "" },
        { id: 1, name: "Voluntary" },
        { id: 2, name: "Involuntary" },
        { id: 3, name: "Short-Term Intern" }
      ],
      TerminationReason_degrees: [
        { id: 11, name: "" },
        { id: 1, name: "Better Pay/Rewards" },
        { id: 2, name: "Career Opportunity/Development" },
        { id: 3, name: "Company Factors" },
        { id: 4, name: "Job Nature" },
        { id: 5, name: "Personal Reason" },
        { id: 6, name: "Work-Life Balance" },
        { id: 7, name: "Others" },
        { id: 8, name: "Company Factors" },
        { id: 9, name: "Unsatisfactory Performance" },
        { id: 10, name: "Misconduct" }
      ],
      tests1: [
        {
          empName: "王超",
          empNum: "3838438",
          account: "华斌",
          projectname: "劳斯劳斯",
          em: "陈宇雄",
          createUserName: "系统管理员",
          createTime: "2019-07-21",
          updateTime: "2019-07-22",
          updateUserName: "系统管理员"
        }
      ],
      tests2: [
        {
          empName: "王超",
          empNum: "3838438",
          growType: "培训",
          explain: "JTP培训",
          createUserName: "系统管理员",
          createTime: "2019-07-21",
          updateTime: "2019-07-22",
          updateUserName: "系统管理员"
        }
      ],
      tests3: [
        {
          empName: "王超",
          empNum: "3838438",
          communicationType: "训话",
          communicationContent: "你是猪",
          createUserName: "系统管理员",
          createTime: "2019-07-21",
          updateTime: "2019-07-22",
          updateUserName: "系统管理员"
        }
      ],
      tests4: [
        {
          empName: "王超",
          empNum: "3838438",
          prizeType: "阿里之星",
          prizeDate: "2019-08-11",
          createUserName: "系统管理员",
          createTime: "2019-07-21",
          updateTime: "2019-07-22",
          updateUserName: "系统管理员"
        }
      ],
      deps: [],
      defaultProps: {
        label: "name",
        isLeaf: "leaf",
        children: "children"
      },
      //获奖记录
      dialogVisible: false,
      dialogVisible_prize: false,
      dialogVisible_prize_form: false,
      tableLoading: false,
      advanceSearchViewVisible: false,
      showOrHidePop: false,
      showOrHidePop2: false,
      dep_prize: {
        id: "",
        chineseName: "",
        empID: "",
        prizeType: "",
        prizeDetail: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        createUserName: "",
        updateUserName: "",
        isDeleted: ""
      },
      //项目经历
      dialogVisible: false,
      dialogVisible_project: false,
      dialogVisible_project_form: false,
      tableLoading: false,
      advanceSearchViewVisible: false,
      showOrHidePop: false,
      showOrHidePop2: false,
      dep_project: {
        id: "",
        empName: "",
        empId: "",
        account: "",
        projectName: "",
        projectManager: "",
        joinTime: "",
        endTime: "",
        createUser: "",
        createTime: "",
        updateTime: "",
        updateUser: "",
        isDeleted: "",
        createUserName: "",
        updateUserName: ""
      },
      //成长经历
      dialogVisible: false,
      dialogVisible_learning: false,
      dialogVisible_learning_form: false,
      tableLoading: false,
      advanceSearchViewVisible: false,
      showOrHidePop: false,
      newteamsTrs: {
        effectiveDate: "",
        empId: 0,
        id: 0,
        newCst: "",
        newSubCst: "",
        nowCst: "",
        nowSubCst: "",
        operationUserId: 0
      },
      showOrHidePop2: false,
      dep_learning: {
        id: "",
        chineseName: "",
        empId: "",
        growingType: "",
        growingDetail: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        createUserName: "",
        updateUserName: "",
        isDeleted: ""
      },
      //校长访谈
      dialogVisible: false,
      dialogVisible_talking: false,
      dialogVisible_talking_form: false,
      tableLoading: false,
      advanceSearchViewVisible: false,
      showOrHidePop: false,
      showOrHidePop2: false,
      dep_talking: {
        id: "",
        chineseName: "",
        empID: "",
        headMaster: "",
        talkType: "",
        talkContent: "",
        createUser: "",
        createTime: "",
        createUserName: "",
        updateUserName: "",
        updateUser: "",
        updateTime: "",
        isDeleted: ""
      },
      //基本信息
      emp: {
        id: "",
        empId: "",
        chineseName: "",
        terminationType: "",
        terminationReason: "",
        joinTime: "",
        endTime: "",
        gender: "",
        grade: "",
        hireDate: "",
        department: "",
        legalEntity: "",
        workingLocation: "",
        teachingStartDate: "",
        phone: "",
        email: "",
        school: "",
        major: "",
        degree: "",
        graduationYear: "",
        cst: "",
        subCst: "",
        skillGroup: "",
        skillSets: "",
        leaveDate: "",
        exitComments: "",
        leavingAccount: "",
        formerId: "",
        birthday: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        isDeleted: ""
      },
      rules: {
        empId: [
          {
            required: false,
            message: "员工编号格式不正确",
            pattern: /^[0-9]*$/
          }
        ],
        formerId: [
          {
            required: false,
            message: "员工编号格式不正确",
            pattern: /^[0-9]*$/
          }
        ],
        chineseName: [
          {
            required: false,
            message: "必填:姓名",
            trigger: "blur"
          },
          {
            pattern: /^[\u4e00-\u9fa5]{2,4}$/,
            message: "请输入2-4个汉字",
            trigger: "blur"
          }
        ],
        gender: [{ required: false, message: "必填:性别", trigger: "blur" }],
        birthday: [
          { required: false, message: "必填:出生日期", trigger: "blur" }
        ],
        idCard: [
          {
            required: false,
            message: "必填:身份证号码",
            trigger: "blur"
          },
          {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: "身份证号码格式不正确",
            trigger: "blur"
          }
        ],
        wedlock: [
          { required: false, message: "必填:婚姻状况", trigger: "blur" }
        ],
        nationId: [
          { required: false, message: "必填:民族", trigger: "change" }
        ],
        nativePlace: [
          { required: false, message: "必填:籍贯", trigger: "blur" }
        ],
        politicId: [
          { required: false, message: "必填:政治面貌", trigger: "blur" }
        ],
        email: [
          { required: false, message: "必填:电子邮箱", trigger: "blur" },
          {
            type: "email",
            message: "邮箱格式不正确",
            trigger: "blur"
          }
        ],
        phone: [
          { required: false, message: "必填:电话号码", trigger: "blur" },
          {
            pattern: /(^1[3456789]\d{9}$)/,
            message: "手机号码格式不正确",
            trigger: "blur"
          }
        ],
        address: [
          { required: false, message: "必填:联系地址", trigger: "blur" }
        ],
        departmentId: [
          { required: false, message: "必填:部门", trigger: "change" }
        ],
        jobLevelId: [
          { required: false, message: "必填:职称", trigger: "change" }
        ],
        posId: [{ required: false, message: "必填:职位", trigger: "change" }],
        engageForm: [
          { required: false, message: "必填:聘用形式", trigger: "blur" }
        ],
        tiptopDegree: [
          { required: false, message: "必填:最高学历", trigger: "change" }
        ],
        grade: [{ required: false, message: "必填:级别", trigger: "change" }],
        cst: [{ required: false, message: "必填:所在CST", trigger: "change" }],
        subCst: [
          { required: false, message: "必填:所在SUB_CST", trigger: "change" }
        ],
        specialty: [{ required: false, message: "必填:专业", trigger: "blur" }],
        workId: [{ required: false, message: "必填:工号", trigger: "blur" }],
        school: [
          { required: false, message: "必填:毕业院校", trigger: "blur" }
        ],
        beginDate: [
          { required: false, message: "必填:入职日期", trigger: "blur" }
        ],
        conversionTime: [
          { required: false, message: "必填:转正日期", trigger: "blur" }
        ],
        beginContract: [
          { required: false, message: "必填:合同起始日期", trigger: "blur" }
        ],
        endContract: [
          { required: false, message: "必填:合同终止日期", trigger: "blur" }
        ],
        workAge: [{ required: false, message: "必填:工龄", trigger: "blur" }]
      },
      prizeRules: {
        empId: [{ required: true, message: "必填:工号", trigger: "blur" }],
        empName: [{ required: true, message: "必填:姓名", trigger: "blur" }],
        prizeName: [
          { required: true, message: "必填:奖项名称", trigger: "blur" }
        ],
        prizeDetail: [
          { required: true, message: "必填:获奖详情", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "必填:更新时间", trigger: "blur" }
        ]
      },
      fileUploadBtnText: "导入数据",
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
      dialogVisibleEditGG: false,
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
  },
  mounted: function() {
    this.initData();
    this.loadEmps();
    /* this.initPrizeData();
      this.loadEmpPrizes();
      this.initProjectData();
      this.loadEmpProjects();
      this.initLearningData();
      this.loadEmpLearnings();
      this.initTalkingData();
      this.loadEmpTalkings(); */
  },
  /*     mounted: function () {
      
    },  */
  methods: {
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
      this.subCst = "";
      this.emp.subCst = "";
    },
    getSubCst: function(subCst) {
      console.log(subCst);
      console.log(this.selectSubCst);
    },

    getTerminationType: function(terminationType) {
      let tempTerminationReason = [];
      this.terminationReasons = [];
      this.selectTerminationReason = "";
      let allTerminationReason = [
        {
          terminationType: "Voluntary",
          label: "Better Pay/Rewards"
        },
        {
          terminationType: "Voluntary",
          label: "Career Opportunity/Development"
        },
        {
          terminationType: "Voluntary",
          label: "Company Factors"
        },
        {
          terminationType: "Voluntary",
          label: "Job Nature"
        },
        {
          terminationType: "Voluntary",
          label: "Personal Reason"
        },
        {
          terminationType: "Voluntary",
          label: "Work-Life Balance"
        },
        {
          terminationType: "Voluntary",
          label: "Others"
        },
        {
          terminationType: "Involuntary",
          label: "Compony Factors"
        },
        {
          terminationType: "Involuntary",
          label: "Unsatisfactory Performance"
        },
        {
          terminationType: "Involuntary",
          label: "Misconduct"
        },
        {
          terminationType: "Short-Term Intern",
          label: ""
        }
      ];
      for (var val of allTerminationReason) {
        if (terminationType == val.terminationType) {
          console.log(val);
          tempTerminationReason.push({ label: val.label, value: val.label });
        }
      }
      this.terminationReasons = tempTerminationReason;
      this.terminationReason = "";
      this.emp.terminationReason = "";
    },
    getTerminationReason: function(terminationReason) {
      console.log(terminationReason);
      console.log(this.selectTerminationReason);
    },

    jump_to_detail_info() {},

    fileUploadSuccess(response, file, fileList) {
      console.log(response);
      if (response) {
        if (response.status == "200") {
          var responseType = "success";
        }
        this.$message({ type: responseType, message: response.obj });
      }
      this.loadEmps();
      this.fileUploadBtnText = "导入数据";
    },
    fileUploadError(err, file, fileList) {
      this.$message({ type: "error", message: "导入失败!" });
      this.fileUploadBtnText = "导入数据";
    },
    beforeFileUpload(file) {
      this.fileUploadBtnText = "正在导入";
    },
    exportEmps() {
      window.open("/employee/basic/exportEmp", "_parent");
      this.$message({ type: "success", message: "导出成功！" });
    },
    cancelSearch() {
      this.advanceSearchViewVisible = false;
      this.emptyEmpData();
      this.gender = "";
      this.cst = "";
      this.subCst = "";
      this.department = "";
      this.degree = "";
      this.terminationType = "";
      this.terminationReason = "";
      this.workingLocation = "";
      this.beginDateScope = "";
      this.hireDateScope = "";
      this.leaveDateScope = "";
      this.birthdayScope = "";
      this.endDateScope = "";
      this.loadEmps();
    },
    showAdvanceSearchView() {
      this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
      this.keywords = "";
      if (!this.advanceSearchViewVisible) {
        this.emptyEmpData();
        this.beginDateScope = "";
        this.hireDateScope = "";
        this.leaveDateScope = "";
        this.birthdayScope = "";
        this.endDateScope = "";
        this.gender = "";
        this.degree = "";
        this.department = "";
        this.cst = "";
        this.subCst = "";
        this.terminationType = "";
        this.workingLocation = "";
        this.loadEmps();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    deleteManyEmps() {
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
          var ids = "";
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doManyDelete(ids);
        })
        .catch(() => {});
    },
    doManyDelete(ids) {
      this.tableLoading = true;
      var _this = this;
      var datas = {
        deleteList: ids
      };
      this.postRequest("/employee/basic/deleteBatchEmp", datas).then(resp => {
        _this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          this.$message({ type: "success", message: "删除成功！" });
          _this.loadEmps();
        }
      });
    },
    deleteEmp(row) {
      this.$confirm(
        "此操作将永久删除[" + row.chineseName + "], 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.doDelete(row.id);
        })
        .catch(() => {});
    },
    doDelete(ids) {
      this.tableLoading = true;
      var _this = this;
      var datas = {
        id: ids,
        operationUserId: ""
      };
      this.postRequest("/employee/basic/deleteEmpById", datas).then(resp => {
        _this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          this.$message({ type: "success", message: "删除成功！" });
          _this.loadEmps();
        }
      });
    },
    keywordsChange(val) {
      if (val == "") {
        this.loadEmps();
      }
    },
    searchEmp() {
      this.currentPage = 1;
      this.loadEmps();
    },
    currentChange(currentChange) {
      this.currentPage = currentChange;
      this.loadEmps();
    },
    loadEmps() {
      var _this = this;
      var datas = {
        hireBeginDate: _this.hireDateScope[0],
        leaveBeginDate: _this.leaveDateScope[0],
        birthdayBeginDate: _this.birthdayScope[0],
        chineseName: _this.keywords,
        cst: _this.cst,
        subCst: _this.subCst,
        department: _this.department,
        degree: _this.degree,
        gender: _this.gender,
        terminationType: _this.terminationType,
        terminationReason: _this.terminationReason,
        workingLocation: _this.workingLocation,
        size: "10",
        start: (this.currentPage - 1) * 10,
        hireEndDate: _this.hireDateScope[1],
        leaveEndDate: _this.leaveDateScope[1],
        birthdayEndDate: _this.birthdayScope[1]
      };
      this.tableLoading = true;
      // this.postRequest("/employee/basic/listEmp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&politicId=" + this.emp.politicId + "&nationId=" + this.emp.nationId + "&posId=" + this.emp.posId + "&jobLevelId=" + this.emp.jobLevelId + "&engageForm=" + this.emp.engageForm + "&departmentId=" + this.emp.departmentId + "&beginDateScope=" + this.beginDateScope).then(resp=> {
      this.postRequest("/employee/basic/listEmp", datas).then(resp => {
        this.tableLoading = false;

        if (resp.data && resp.data.status == 200) {
          var data = resp.data.obj.employeeVos;
          _this.emps = data;
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
      });
    },
    addEmp(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.emp.id) {
            //更新
            this.tableLoading = true;
            this.postRequest("/employee/basic/updateEmpById", this.emp).then(
              resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyEmpData();
                  _this.loadEmps();
                }
              }
            );
          } else {
            //添加
            this.tableLoading = true;
            this.postRequest("/employee/basic/addEmp", this.emp).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible = false;
                _this.emptyEmpData();
                _this.loadEmps();
              }
            });
          }
        } else {
          return false;
        }
      });
    },
    cancelEidt() {
      this.dialogVisible = false;
      this.emptyEmpData();
    },
    showDepTree() {
      this.showOrHidePop = !this.showOrHidePop;
    },
    showDepTree2() {
      this.showOrHidePop2 = !this.showOrHidePop2;
    },
    handleNodeClick(data) {
      this.emp.departmentName = data.name;
      this.emp.departmentId = data.id;
      this.showOrHidePop = false;
      this.depTextColor = "#606266";
    },
    handleNodeClick2(data) {
      this.emp.departmentName = data.name;
      this.emp.departmentId = data.id;
      this.showOrHidePop2 = false;
      this.depTextColor = "#606266";
    },
    initData() {
      var _this = this;
      var datas = {
        chineseName: "",
        cst: "",
        degree: "",
        gender: "",
        hireBeginDate: "",
        hireEndDate: "",
        leaveBeginDate: "",
        leaveEndDate: "",
        size: 10,
        start: (this.currentPage - 1) * 10,
        subCst: "",
        terminationType: "",
        terminationReason: "",
        workingLocation: ""
      };
      this.postRequest("/employee/basic/listEmp", datas).then(resp => {
        if (resp.data && resp.data.status == 200) {
          var data = resp.data.obj.employeeVos;
          // _this.nations = data.nations;
          // _this.politics = data.politics;
          // _this.deps = data.deps;
          // _this.positions = data.positions;
          // _this.joblevels = data.joblevels;
          console.log("data.length", data.length);
          // _this.totalCount = data.length;
          _this.emps = data;
          _this.id = data.id;
          _this.emp.createUser = data.createUser;
          _this.emp.createTime = data.createTime;
          _this.emp.updateUser = data.updateUser;
          _this.emp.updateTime = data.updateTime;
          _this.emp.empId = data.empId;
          _this.emp.chineseName = data.chineseName;
          _this.emp.gender = data.gender;
          _this.emp.grade = data.grade;
          _this.emp.hireDate = data.hireDate;
          _this.emp.department = data.department;
          _this.emp.legalEntity = data.legalEntity;
          _this.emp.workingLocation = data.workingLocation;
          _this.emp.teachingStartDate = data.teachingStartDate;
          _this.emp.phone = data.phone;
          _this.emp.email = data.email;
          _this.emp.school = data.school;
          _this.emp.major = data.major;
          _this.emp.degree = data.degree;
          _this.emp.graduationYear = data.graduationYear;
          _this.emp.cst = data.cst;
          _this.emp.subCst = data.subCst;
          _this.emp.skillGroup = data.skillGroup;
          _this.emp.skillSets = data.skillSets;
          _this.emp.leaveDate = data.leaveDate;
          _this.emp.terminationType = data.terminationType;
          _this.emp.terminationReason = data.terminationReason;
          _this.emp.exitComments = data.exitComments;
          _this.emp.leavingAccount = data.leavingAccount;
          _this.emp.formerId = data.formerId;
          _this.emp.birthday = data.birthday;
        }
      });
    },
    showEditEmpView(row) {
      console.log(row);
      this.dialogTitle = "编辑员工";
      this.$refs["addEmpForm"].clearValidate(); // 重置验证
      this.emp = row;
      this.emp.birthday = this.formatDate(row.birthday);
      this.emp.conversionTime = this.formatDate(row.conversionTime);
      this.emp.beginContract = this.formatDate(row.beginContract);
      this.emp.endContract = this.formatDate(row.endContract);
      this.emp.beginDate = this.formatDate(row.beginDate);
      // this.emp.nationId = row.nation.id;
      // this.emp.politicId = row.politicsStatus.id;
      // this.emp.departmentId = row.department.id;
      // this.emp.departmentName = row.department.name;
      // this.emp.jobLevelId = row.jobLevel.id;
      // this.emp.posId = row.position.id;
      //        delete this.emp.department;
      //        delete this.emp.jobLevel;
      //        delete this.emp.position;
      //        delete this.emp.nation;
      //        delete this.emp.politicsStatus;
      delete this.emp.workAge;
      delete this.emp.notWorkDate;
      this.dialogVisible = true;
    },
    showAddEmpView() {
      this.dialogTitle = "添加员工";
      this.dialogVisible = true;
      this.$refs["addEmpForm"].clearValidate(); // 重置验证
      var _this = this;
      /* this.getRequest("/employee/basic/maxWorkID").then(resp=> {
           if (resp && resp.status == 200) {
             _this.emp.workId = resp.data;
           }
         }) */
    },
    emptyEmpData() {
      this.emp = {
        id: "",
        empId: "",
        chineseName: "",
        gender: "",
        grade: "",
        hireDate: "",
        department: "",
        legalEntity: "",
        workingLocation: "",
        teachingStartDate: "",
        phone: "",
        email: "",
        school: "",
        major: "",
        degree: "",
        graduationYear: "",
        cst: "",
        subCst: "",
        skillGroup: "",
        skillSets: "",
        leaveDate: "",
        terminationType: "",
        terminationReason: "",
        exitComments: "",
        leavingAccount: "",
        formerId: "",
        birthday: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        isDeleted: ""
      };
    },

    //    EmpPrize相关信息

    showEmpPrizeList(empId, name) {
      console.log("应该为0", this.newEmpPrizeEmpId);
      this.dialogTitle = "获奖记录信息";
      this.loadEmpPrizes(empId);
      this.initPrizeData(empId);
      this.newEmpPrizeEmpId = empId;
      this.newEmpPrizeEmpName = name;
      this.dialogVisible_prize = true;
    },
    loadEmpPrizes(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        prizeType: "",
        size: "10",
        start: (this.currentPage_prize - 1) * 10
      };
      this.tableLoading = true;
      // this.postRequest("/employee/basic/listEmp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&politicId=" + this.emp.politicId + "&nationId=" + this.emp.nationId + "&posId=" + this.emp.posId + "&jobLevelId=" + this.emp.jobLevelId + "&engageForm=" + this.emp.engageForm + "&departmentId=" + this.emp.departmentId + "&beginDateScope=" + this.beginDateScope).then(resp=> {
      this.postRequest("/employee/prize/listPrize", datas).then(resp => {
        this.tableLoading = false;
        console.log(resp);
        if (resp.data && resp.status == 200) {
          var data = resp.data.obj.empPrizeVoList;
          _this.deps = data;

          _this.totalCount_prize = resp.data.obj.listCount;
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
      });
    },
    initPrizeData(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        prizeType: "",
        size: "10",
        start: (this.currentPage_prize - 1) * 10
      };
      this.postRequest("/employee/prize/listPrize", datas).then(resp => {
        if (resp.data && resp.data.status == 200) {
          var data = resp.data.obj.empPrizeVoList;

          _this.deps = data;
          // _this.id = data.id;
          _this.dep.id = data.id;
          _this.dep.chineseName = data.chineseName;
          _this.dep.empId = data.empId;
          _this.dep.prizeType = data.prizeType;
          _this.dep.prizeDetail = data.prizeDetail;
          _this.dep.createUser = data.createUser;
          _this.dep.updateTime = data.createTime;
          _this.dep.createUser = data.updateUser;
          _this.dep.updateTime = data.updateTime;
          _this.dep.createUserName = data.updateUserName;
          _this.dep.updateUserName = data.updateUserName;
        }
      });
    },
    canclePrizeList() {
      this.dialogVisible_prize = false;
      this.newEmpPrizeEmpId = 0;
      this.newEmpPrizeEmpName = "";
    },
    cancelPrizeEidt(empId, name) {
      this.dialogVisible_prize_form = false;
      this.emptyPrizeEmpData();
      //this.showEmpPrizeList(empId,name);
    },
    emptyPrizeEmpData() {
      this.dep = {
        id: "",
        chineseName: "",
        empID: "",
        prizeType: "",
        prizeDetail: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        isDeleted: ""
      };
    },
    currentChange_prize(currentChange_prize) {
      this.currentPage_prize = currentChange_prize;
      this.loadEmpPrizes();
    },

    showPrizeEmpView(row) {
      console.log(row);
      this.dialogTitle = "编辑个人获奖记录";
      this.$refs["addEmpPrizeForm"].clearValidate(); // 重置验证
      this.dep_prize = row;
      this.dep_prize.id = row.id;
      this.dep_prize.empId = row.empId;
      this.dep_prize.chineseName = row.chineseName;
      this.dep_prize.prizeType = row.prizeType;
      this.dep_prize.prizeDetail = row.prizeDetail;
      delete this.dep_prize.workAge;
      delete this.dep_prize.notWorkDate;
      this.dialogVisible_prize_form = true;
    },
    addPrize(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.dep_prize.id) {
            //更新
            this.tableLoading = true;
            this.postRequest(
              "/employee/prize/updateEmpById",
              this.dep_prize
            ).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible_prize_form = false;
                _this.emptyPrizeEmpData();
                _this.loadEmpPrizes(this.newEmpPrizeEmpId);
              }
            });
          } else {
            //添加
            this.tableLoading = true;
            this.postRequest("/employee/prize/addPrize", this.dep_prize).then(
              resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible_prize_form = false;
                  _this.emptyPrizeEmpData();
                  _this.loadEmpPrizes(this.newEmpPrizeEmpId);
                }
              }
            );
          }
          _this.idIsDisabled = 0;
        } else {
          return false;
        }
      });
    },
    deleteEmpPrize(row) {
      this.$confirm(
        "此操作将永久删除[" +
          row.chineseName +
          "]的[" +
          row.prizeType +
          "]奖项, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.doDeletePrize(row.id, row.empId);
        })
        .catch(() => {});
    },
    doDeletePrize(ids, empId) {
      this.tableLoading = true;
      var _this = this;
      var datas = {
        id: ids,
        operationUserId: 1
      };
      this.postRequest("/employee/prize/deletePrizeById", datas).then(resp => {
        _this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          this.$message({ type: "success", message: "删除成功！" });
          _this.loadEmpPrizes(empId);
        }
      });
    },
    showAddEmpPrizeView() {
      this.idIsDisabled = 1;
      console.log(this.idIsDisabled);
      this.dialogTitle = "添加获奖记录";
      this.$refs["addEmpPrizeForm"].clearValidate(); // 重置验证
      (this.dep_prize.chineseName = this.newEmpPrizeEmpName),
        (this.dep_prize.empId = this.newEmpPrizeEmpId),
        (this.dep_prize.prizeType = "");
      this.dep_prize.prizeDetail = "";
      this.dialogVisible_prize_form = true;
    },

    //    EmpProject相关信息

    showEmpProjectList(empId, name) {
      console.log("应该为0", this.newEmpProjectEmpId);
      this.dialogTitle = "项目经历信息";
      this.loadEmpProjects(empId);
      this.initProjectData(empId);
      this.newEmpProjectEmpId = empId;
      this.newEmpProjectEmpName = name;
      this.dialogVisible_project = true;
    },
    loadEmpProjects(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        endBeginDate: "",
        search: "",
        size: "10",
        start: (this.currentPage_project - 1) * 10,
        startBeginDate: ""
      };
      this.tableLoading = true;
      // this.postRequest("/employee/basic/listEmp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&politicId=" + this.emp.politicId + "&nationId=" + this.emp.nationId + "&posId=" + this.emp.posId + "&jobLevelId=" + this.emp.jobLevelId + "&engageForm=" + this.emp.engageForm + "&departmentId=" + this.emp.departmentId + "&beginDateScope=" + this.beginDateScope).then(resp=> {
      this.postRequest("/employee/project/listProject", datas).then(resp => {
        this.tableLoading = false;
        console.log(resp);
        if (resp.data && resp.status == 200) {
          var data = resp.data.obj.projectExperienceVoList;
          _this.deps = data;

          _this.totalCount_project = resp.data.obj.listCount;
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
      });
    },
    initProjectData(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        endBeginDate: "",
        search: "",
        size: "10",
        start: (this.currentPage_project - 1) * 10,
        startBeginDate: ""
      };
      this.postRequest("/employee/project/listProject", datas).then(resp => {
        if (resp.data && resp.data.status == 200) {
          var data = resp.data.obj.projectExperienceVoList;

          _this.deps = data;
          // _this.id = data.id;
          _this.dep.id = data.id;
          _this.dep.account = data.account;
          _this.dep.empName = data.empName;
          _this.dep.empId = data.empId;
          _this.dep.projectName = data.projectName;
          _this.dep.projectManager = data.projectManager;
          _this.dep.joinTime = data.joinTime;
          _this.dep.endTime = data.endTime;
          _this.dep.updateUser = data.updateUser;
          _this.dep.createUser = data.createUser;
          _this.dep.createTime = data.createTime;
          _this.dep.updateTime = data.updateTime;
          _this.dep.createUserName = data.createUserName;
          _this.dep.updateUserName = data.updateUserName;
        }
      });
    },
    cancleProjectList() {
      this.dialogVisible_project = false;
      this.newEmpProjectEmpId = 0;
      this.newEmpProjectEmpName = "";
    },
    cancelProjectEidt(empId, name) {
      this.dialogVisible_project_form = false;
      this.emptyProjectEmpData();
    },
    emptyProjectEmpData() {
      this.dep = {
        id: "",
        chineseName: "",
        empId: "",
        account: "",
        projectName: "",
        projectManager: "",
        joinTime: "",
        endTime: "",
        createUser: "",
        createTime: "",
        updateTime: "",
        updateUser: "",
        isDeleted: "",
        createUserName: "",
        updateUserName: ""
      };
    },
    currentChange_project(currentChange_project) {
      this.currentPage_project = currentChange_project;
      this.loadEmpProjects();
    },

    showProjectEmpView(row) {
      console.log(row);
      this.dialogTitle = "编辑个人项目经历";
      this.$refs["addEmpProjectForm"].clearValidate(); // 重置验证
      this.dep_project = row;
      this.dep_project.empId = row.empId;
      this.dep_project.chineseName = row.chineseName;
      this.dep_project.account = row.account;
      this.dep_project.joinTime = row.joinTime;
      this.dep_project.endTime = row.endTime;
      this.dep_project.projectName = row.projectName;
      this.dep_project.projectManager = row.projectManager;
      delete this.dep_project.workAge;
      delete this.dep_project.notWorkDate;
      this.dialogVisible_project_form = true;
    },
    addProject(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.dep_project.id) {
            //更新
            this.tableLoading = true;
            this.postRequest(
              "/employee/project/updateProjectById",
              this.dep_project
            ).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible_project_form = false;
                _this.emptyProjectEmpData();
                _this.loadEmpProjects(this.newEmpProjectEmpId);
              }
            });
          } else {
            //添加
            this.tableLoading = true;
            this.postRequest(
              "/employee/project/addProject",
              this.dep_project
            ).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible_project_form = false;
                _this.emptyProjectEmpData();
                _this.loadEmpProjects(this.newEmpProjectEmpId);
              }
            });
          }
          _this.idIsDisabled = 0;
        } else {
          return false;
        }
      });
    },
    deleteEmpProject(row) {
      this.$confirm(
        "此操作将永久删除[" +
          row.chineseName +
          "]的[" +
          row.projectName +
          "]项目经历, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.doDeleteProject(row.id, row.empId);
        })
        .catch(() => {});
    },
    doDeleteProject(ids, empId) {
      this.tableLoading = true;
      var _this = this;
      var datas = {
        id: ids,
        operationUserId: 1
      };
      this.postRequest("/employee/project/deleteProjectById", datas).then(
        resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({ type: "success", message: "删除成功！" });
            _this.loadEmpProjects(empId);
          }
        }
      );
    },
    showAddEmpProjectView() {
      this.idIsDisabled = 1;
      console.log(this.idIsDisabled);
      this.dialogTitle = "添加项目经历";
      this.$refs["addEmpProjectForm"].clearValidate(); // 重置验证
      this.dep_project.chineseName = this.newEmpProjectEmpName;
      this.dep_project.empId = this.newEmpProjectEmpId;
      this.dep_project.id = "";
      this.dep_project.account = "";
      this.dep_project.joinTime = "";
      this.dep_project.endTime = "";
      this.dep_project.projectName = "";
      this.dep_project.projectManager = "";
      this.dialogVisible_project_form = true;
    },

    //    EmpLearning相关信息

    showEmpLearningList(empId, name) {
      console.log("应该为0", this.newEmpLearningEmpId);
      this.dialogTitle = "培训经历信息";
      this.loadEmpLearnings(empId);
      this.initLearningData(empId);
      this.newEmpLearningEmpId = empId;
      this.newEmpLearningEmpName = name;
      this.dialogVisible_learning = true;
    },
    loadEmpLearnings(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        growingType: "",
        size: "10",
        start: (this.currentPage_learning - 1) * 10
      };
      this.tableLoading = true;
      // this.postRequest("/employee/basic/listEmp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&politicId=" + this.emp.politicId + "&nationId=" + this.emp.nationId + "&posId=" + this.emp.posId + "&jobLevelId=" + this.emp.jobLevelId + "&engageForm=" + this.emp.engageForm + "&departmentId=" + this.emp.departmentId + "&beginDateScope=" + this.beginDateScope).then(resp=> {
      this.postRequest("/employee/experience/listExperience", datas).then(
        resp => {
          this.tableLoading = false;
          console.log(resp);
          if (resp.data && resp.status == 200) {
            var data = resp.data.obj.growingExperienceVoList;
            _this.deps = data;

            _this.totalCount_learning = resp.data.obj.listCount;
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
        }
      );
    },
    initLearningData(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        growingType: "",
        size: "10",
        start: (this.currentPage_learning - 1) * 10
      };
      this.postRequest("/employee/experience/listExperience", datas).then(
        resp => {
          if (resp.data && resp.data.status == 200) {
            var data = resp.data.obj.growingExperienceVoList;

            _this.deps = data;
            // _this.id = data.id;
            _this.dep.id = data.id;
            _this.dep.chineseName = data.chineseName;
            _this.dep.empId = data.empId;
            _this.dep.growingType = data.growingType;
            _this.dep.growingDetail = data.growingDetail;
            _this.dep.updateUser = data.updateUser;
            _this.dep.createUser = data.createUser;
            _this.dep.createTime = data.createTime;
            _this.dep.updateTime = data.updateTime;
            _this.dep.createUserName = data.createUserName;
            _this.dep.updateUserName = data.updateUserName;
          }
        }
      );
    },
    addLearning(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.dep_learning.id) {
            //更新
            this.tableLoading = true;
            this.postRequest(
              "/employee/experience/updateExperienceById",
              this.dep_learning
            ).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible_learning_form = false;
                _this.emptyLearningEmpData();
                _this.loadEmpLearnings(this.newEmpLearningEmpId);
              }
            });
          } else {
            //添加
            this.tableLoading = true;
            this.postRequest(
              "/employee/experience/addExperience",
              this.dep_learning
            ).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible_learning_form = false;
                _this.emptyLearningEmpData();
                _this.loadEmpLearnings(this.newEmpLearningEmpId);
              }
            });
          }
          _this.idIsDisabled = 0;
        } else {
          return false;
        }
      });
    },
    cancleLearningList() {
      this.dialogVisible_learning = false;
      this.newEmpLearningEmpId = 0;
      this.newEmpLearningEmpName = "";
    },
    cancelLearningEidt(empId, name) {
      this.dialogVisible_learning_form = false;
      this.emptyLearningEmpData();
    },
    showAddLearningEmpView(empId, empName) {
      console.log("应该不为0", this.newEmpLearningEmpId);
      this.dialogTitle = "添加记录";
      this.$refs["addEmpPrizeForm"].clearValidate(); // 重置验证
      this.dep.empId = empId;
      this.dep.empName = empName;
      this.dep.prizeDetail = "";
      this.dep.prizeType = "";
      this.dep.prizeName = "";
      this.dialogVisible_prize_form = true;
      // this.getRequest("/employee/basic/maxWorkID").then(resp=> {
      //   if (resp && resp.status == 200) {
      //     _this.emp.workId = resp.data;
      //   }
      // })
    },
    emptyLearningEmpData() {
      this.dep = {
        id: "",
        chineseName: "",
        empId: "",
        growingType: "",
        growingDetail: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: "",
        createUserName: "",
        updateUserName: "",
        isDeleted: ""
      };
    },
    currentChange_learning(currentChange_learning) {
      this.currentPage_learning = currentChange_learning;
      this.loadEmpLearnings();
    },

    showLearningEmpView(row) {
      console.log(row);
      this.dialogTitle = "编辑培训经历";
      this.$refs["addEmpPrizeForm"].clearValidate(); // 重置验证
      this.dep_learning = row;
      this.dep_learning.empId = row.empId;
      this.dep_learning.chineseName = row.chineseName;
      this.dep_learning.growingDetail = row.growingDetail;
      this.dep_learning.growingType = row.growingType;

      delete this.dep_learning.workAge;
      delete this.dep_learning.notWorkDate;
      this.dialogVisible_learning_form = true;
    },

    deleteEmpLearning(row) {
      this.$confirm(
        "此操作将永久删除[" +
          row.chineseName +
          "]的[" +
          row.growingType +
          "]培训经历, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.doDeleteLearning(row.id, row.empId);
        })
        .catch(() => {});
    },
    doDeleteLearning(ids, empId) {
      this.tableLoading = true;
      var _this = this;
      var datas = {
        id: ids,
        operationUserId: 1
      };
      this.postRequest("/employee/experience/deleteExperienceById", datas).then(
        resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.$message({ type: "success", message: "删除成功！" });
            _this.loadEmpLearnings(empId);
          }
        }
      );
    },
    showAddEmpLearningView() {
      this.idIsDisabled = 1;
      console.log(this.idIsDisabled);
      this.dialogTitle = "添加培训经历";
      this.$refs["addEmpLearningForm"].clearValidate(); // 重置验证
      this.dep_learning.id = "";
      this.dep_learning.chineseName = this.newEmpLearningEmpName;
      this.dep_learning.empId = this.newEmpLearningEmpId;
      this.dep_learning.growingType = "";
      this.dep_learning.growingDetail = "";
      this.dialogVisible_learning_form = true;
    },

    //    Teamchange相关信息

    //    EmpTalking相关信息

    showEmpTalkingList(empId, name) {
      console.log("应该为0", this.newEmpTalkingEmpId);
      this.dialogTitle = "校长访谈信息";
      this.loadEmpTalkings(empId);
      this.initTalkingData(empId);
      this.newEmpTalkingEmpId = empId;
      this.newEmpTalkingEmpName = name;
      this.dialogVisible_talking = true;
    },
    loadEmpTalkings(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        search: "",
        size: 10,
        start: (this.currentPage_talking - 1) * 10
      };
      this.tableLoading = true;
      // this.postRequest("/employee/basic/listEmp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&politicId=" + this.emp.politicId + "&nationId=" + this.emp.nationId + "&posId=" + this.emp.posId + "&jobLevelId=" + this.emp.jobLevelId + "&engageForm=" + this.emp.engageForm + "&departmentId=" + this.emp.departmentId + "&beginDateScope=" + this.beginDateScope).then(resp=> {
      this.postRequest("/employee/talk/listTalk", datas).then(resp => {
        this.tableLoading = false;
        console.log(resp);
        if (resp.data && resp.status == 200) {
          var data = resp.data.obj.headmasterTalkVoList;
          _this.deps = data;

          _this.totalCount_talking = resp.data.obj.listCount;
        }
      });
    },
    initTalkingData(empId) {
      var _this = this;
      var datas = {
        empId: empId,
        search: "",
        size: 10,
        start: (this.currentPage_talking - 1) * 10
      };
      this.postRequest("/employee/talk/listTalk", datas).then(resp => {
        if (resp.data && resp.data.status == 200) {
          var data = resp.data.obj.headmasterTalkVoList;

          _this.deps = data;
          // _this.id = data.id;
          _this.dep.id = data.id;
          _this.dep.chineseName = data.chineseName;
          _this.dep.empId = data.empId;
          _this.dep.headMaster = data.headMaster;
          _this.dep.talkType = data.talkType;
          _this.dep.talkContent = data.talkContent;
          _this.dep.updateUser = data.updateUser;
          _this.dep.createUser = data.createUser;
          _this.dep.createTime = data.createTime;
          _this.dep.updateTime = data.updateTime;
          _this.dep.createUserName = data.createUserName;
          _this.dep.updateUserName = data.updateUserName;
        }
      });
    },
    addTalking(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.dep_talking.id) {
            //更新
            this.tableLoading = true;
            this.postRequest(
              "/employee/talk/updateTalkById",
              this.dep_talking
            ).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible_talking_form = false;
                _this.emptyTalkingEmpData();
                _this.loadEmpTalkings(this.newEmpTalkingEmpId);
              }
            });
          } else {
            //添加
            this.tableLoading = true;
            this.postRequest("/employee/talk/addTalk", this.dep_talking).then(
              resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible_talking_form = false;
                  _this.emptyTalkingEmpData();
                  _this.loadEmpTalkings(this.newEmpTalkingEmpId);
                }
              }
            );
          }
          _this.idIsDisabled = 0;
        } else {
          return false;
        }
      });
    },
    cancleTalkingList() {
      this.dialogVisible_talking = false;
      this.newEmpTalkingEmpId = 0;
      this.newEmpTalkingEmpName = "";
    },
    cancelTalkingEidt(empId, name) {
      this.dialogVisible_talking_form = false;
      this.emptyTalkingEmpData();
    },
    emptyTalkingEmpData() {
      this.dep = {
        chineseName: "",
        empId: "",
        headMaster: "",
        talkType: "",
        talkContent: "",
        createUserName: "",
        createTime: "",
        updateTime: "",
        updateUserName: ""
      };
    },
    currentChange_talking(currentChange_talking) {
      this.currentPage_talking = currentChange_talking;
      this.loadEmpTalkings();
    },

    showTalkingEmpView(row) {
      console.log(row);
      this.dialogTitle = "编辑沟通详情";
      this.$refs["addEmpTalkingForm"].clearValidate(); // 重置验证
      this.dep_talking = row;
      this.dep_talking.chineseName = row.chineseName;
      this.dep_talking.empId = row.empId;
      this.dep_talking.headMaster = row.headMaster;
      this.dep_talking.talkType = row.talkType;
      this.dep_talking.talkContent = row.talkContent;
      this.dialogVisible_talking_form = true;
    },
    deleteEmpTalking(row) {
      this.$confirm(
        "此操作将永久删除[" +
          row.chineseName +
          "]的[" +
          row.talkType +
          "]访谈记录, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.doDeleteTalking(row.id, row.empId);
        })
        .catch(() => {});
    },
    doDeleteTalking(ids, empId) {
      this.tableLoading = true;
      var _this = this;
      var datas = {
        id: ids,
        operationUserId: 1
      };
      this.postRequest("/employee/talk/deleteTalkById", datas).then(resp => {
        _this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          this.$message({ type: "success", message: "删除成功！" });
          _this.loadEmpTalkings(empId);
        }
      });
    },
    showAddEmpTalkingView() {
      this.idIsDisabled = 1;
      console.log(this.idIsDisabled);
      this.dialogTitle = "添加沟通记录";
      this.$refs["addEmpTalkingForm"].clearValidate(); // 重置验证
      this.dep_talking.id = "";
      this.dep_talking.chineseName = this.newEmpTalkingEmpName;
      this.dep_talking.empId = this.newEmpTalkingEmpId;
      this.dep_talking.headMaster = "";
      this.dep_talking.talkContent = "";
      this.dep_talking.talkType = "";
      this.dialogVisible_talking_form = true;
    },
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
      this.empName = this.dialogVisibleEditGG = true;
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
              this.dialogVisibleEditGG = false;
              this.getList();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
      this.dialogVisibleEditGG = false;
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
            this.doManyDeleteTeamchangelog(this.multipleSelection[i].id);
          }
        })
        .catch(() => {});
    },
    doManyDeleteTeamchangelog(ids) {
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
          this.doManyDeleteTeamchangelog(row.id);
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
    showTeamchanges(row) {
      console.log(row);
      this.baseInfo = row;
      this.queryInfo.empId = row.empId;
      this.dialogVisible_teams = true;
      this.getList();
    },
    newTeamDialog() {
      this.dialogVisible_newTeams = true;
      // this.newteamsTrs

      this.newteamsTrs.empId = this.baseInfo.empId;
      // this.newteamsTrs.id = this.baseInfo.id;
      this.newteamsTrs.newCst = this.baseInfo.newCst;
      this.newteamsTrs.newSubCst = this.baseInfo.newSubCst;
      this.newteamsTrs.nowCst = this.baseInfo.cst;
      this.newteamsTrs.chineseName = this.baseInfo.chineseName;
      this.newteamsTrs.nowSubCst = this.baseInfo.subCst;
      // this.newteamsTrs.chineseName = row.chineseName;
      this.newteamsTrs.createUserName = this.baseInfo.createUserName;
    },
    newTeam(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          delete this.newteamsTrs.chineseName;
          delete this.newteamsTrs.createUserName;
          this.postRequest(
            "/employee/team_change/addTeamChangeLog",
            // "/employee/StudyDeclaration/updateStudyDeclarationById",
            this.newteamsTrs
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.dialogVisible_teams = false;
              this.getList();
              this.initData();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
      this.dialogVisible_newTeams = false;
    },
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
            "/employee/team_change/updateTeamChangeLog",
            this.changeInfo
          ).then(msg => {
            this.loading = false;
            if (msg && msg.status == 200) {
              console.log(msg);
              this.dialogVisible = false;
              this.getStudyList();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // handleClick(row) {
    //   console.log(row);
    //   this.changeInfo.empId = row.empId;
    //   this.changeInfo.id = row.id;
    //   this.changeInfo.newCst = row.newCst;
    //   this.changeInfo.newSubCst = row.newSubCst;
    //   this.changeInfo.nowCst = row.nowCst;
    //   this.changeInfo.nowSubCst = row.nowSubCst;
    //   this.changeInfo.chineseName = row.chineseName;
    //   this.changeInfo.createUserName = row.createUserName;

    //   this.empName = this.dialogVisibleEdit = true;dialogVisibleEdit
    // },
    teamEdit(formName) {
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
              this.dialogVisibleEdit = false;
              this.getStudyList();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
      this.dialogVisibleEditGG = false;
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
      }
      this.fileUploadBtnText = "导入数据";
    },
    fileUploadError(err, file, fileList) {
      this.$message({ type: "error", message: "导入失败!" });
      this.fileUploadBtnText = "导入数据";
    },
    beforeFileUpload(file) {
      this.fileUploadBtnText = "正在导入";
    },
    showStudyDialog(row) {
      this.dialogVisible_studyDecuration = true;
      this.queryStudyInfo.empId = row.empId;
      console.log(row);
      this.newDecorate.empId = row.empId;
      this.newDecorate.chineseName = row.chineseName;

      this.getStudyList();
    },
    newStudyDecoration() {
      this.dialogVisible_studyDecoration = true;

      (this.newDecorate.bczhours =""),
        (this.newDecorate.efcslevel = ""),
        (this.newDecorate.efhours = null),
        (this.newDecorate.efydlevel = ""),
        (this.newDecorate.efytdlevel = ""),
        (this.newDecorate.note = ""),
        (this.newDecorate.sharetimes = null),
        (this.newDecorate.tedtimes = null),
        (this.newDecorate.unqualified = null),
        (this.newDecorate.videohours = null);
    },
    handleClick_studyDecuration(row) {
      console.log(row);
      this.newDecorate = row;

      this.dialogVisible_editStudyDecoration = true;
    },
    newDecorateSure_studyDecoration(formName) {
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
              this.newDecorate = {
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
              };
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
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











<style>
.el-dialog__body {
  padding-top: 0px;
  padding-bottom: 0px;
}

.slide-fade-enter-active {
  transition: all 0.8s ease;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
}

.el-dropdown {
  vertical-align: top;
}
.el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
