<template>
  <div>
    <el-card class="miancard" style="border-radius: 12px">
      <div slot="header" class="clearfix">
            <span><b>审核注册</b></span>
          </div>
      <div>
        <el-table 
          :data="tableData"
          height="520"
          stripe
          style="width: 100%"
          :header-row-style="{ height: '20px' }"
          :cell-style="{ padding: '5px' }"
        >
          <el-table-column prop="id" label="学工号">
          </el-table-column>

          <el-table-column prop="name" label="姓名">
          </el-table-column>

          <el-table-column label="类型">
            <template slot-scope="scope">{{(scope.row.isTeacher == 0) ? "学生" : "教师"}}</template>
          </el-table-column>
          
          <el-table-column label="操作">
            <template slot-scope="scope">
              <router-link
                :to="{
                  path: '/admin/verification-detail',
                  query: {
                    ID: scope.row.id,
                  },
                }"
                >
                <el-button type="text">审核</el-button>
              </router-link>
              
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.maintitle {
  font-size: 22px;
  text-align: left;
  font-weight: bold;
  padding: 5px;
  margin-bottom: 30px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.el-card {
  border-radius: 15px;
}
.clearfix:after {
  clear: both;
}
.clearfix {
  font-size: 18px;
}
.miancard{
  height: 100%;
}
</style>


<script>
import { GETUnactivatedAccounts } from "../../API/http";

export default {
  created() {
    GETUnactivatedAccounts()
      .then((data) => {
        this.tableData = data;
      })
      .catch((err) => {
        console.log(err);
        this.$message("数据请求错误");
      });
  },

  data() {
    return {
      toMatch: "",
      matchList: [],
      tableData: [],
    };
  },
};
</script>