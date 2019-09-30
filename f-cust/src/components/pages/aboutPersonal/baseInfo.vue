<template>
  <div class="container">
    <table width="100%" >
      <tbody>
        <tr>
          <td rowspan="3">
            <img :src="user.userHead? user.userHead: require('../../../assets/imgs/user-header.png')" alt="">
          </td>
          <td>姓名： {{user.chineseName}}</td>
          <td>性别： {{user.gender}}</td>
          <td>学校： {{user.school}}</td>
        </tr>
        <tr>
          <td>学历： {{user.degree}}</td>
          <td>技术栈：{{user.skillSets}}</td>
          <td>专业： {{user.major}}</td>
        </tr>
        <tr>
          <td>部门：{{user.subCst}}</td>
          <td>员工号：{{user.empId}}</td>
          <td>等级：{{user.grade}}</td>
        </tr>
        <tr>
          <td colspan="2">邮件：{{user.email}}</td>
          <td>base地：{{user.workingLocation}}</td>
          <td>地区：{{user.department}}</td>
        </tr>
        <tr>
          <td>入职时间：{{user.hireDate}}</td>
          <td>电话： {{user.phone}}</td>
          <td colspan="2">公司： {{user.legalEntity}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "baseInfo",
  data() {
    return {
      user: {}
    };
  },
  created() {
    this.getRequest("/staff/getEmpBy/" + localStorage.getItem("id")).then(
      resp => {
        if (resp && resp.status == 200) {
          // _this.emp.workId = resp.data;
          console.log(resp);
          this.user = resp.data.obj;
          this.user.hireDate = this.user.hireDate.substring(0, 10);
        }
      }
    );
  },
  mounted() {},
  methods: {
    // 所有的方法封装
  }
};
</script>
<style scoped>
.container {
  width: 90%;
  margin: 5%;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 1px 1px 5px;
  font-size: 16px
}
table{
  width: 90%;
  margin: 5%
}
tr,td{
  height: 80px;
  line-height: 80px;
  text-align: left
}
</style>
