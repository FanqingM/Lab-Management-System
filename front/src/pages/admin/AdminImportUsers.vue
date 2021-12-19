<template>
  <div style="height: 100%">
    <el-card style="height: 100%">
      <div slot="header" style="font-size: 18px; height: 30px">
        <span><b>导入用户列表</b></span>
      </div>
      <el-row style="height: calc(100% - 150px); padding: 10px">
        <el-col :span="10">
          <el-upload
            style="width: 100%; height: 100%; margin: 50px"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            accept=".xlsx"
            :on-change="upload"
            :limit="1"
            :on-exceed="handleExceed"
          >
            <!-- action="https://jsonplaceholder.typicode.com/posts/" -->
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">
              只能上传xlsx文件，且不超过500kb
            </div>
          </el-upload>
          <el-row style="width: 100%; height: 100px">
            <el-col :span="3" :offset="8">
              <el-button type="primary" @click="submit">提交</el-button>
            </el-col>
            <el-col :span="3" :offset="2">
              <router-link to="/admin/user-management">
                <el-button>取消</el-button>
              </router-link>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="10">
          <el-table
            v-if="userlist.length > 0"
            style="width: 100%; height: 100%; margin: 50px; overflow: auto"
            :data="userlist"
            stripe
            :header-row-style="{ height: '20px' }"
            :cell-style="{ padding: '5px' }"
          >
            <el-table-column label="学号" prop="id"> </el-table-column>
            <el-table-column label="姓名" prop="name"> </el-table-column>
            <el-table-column label="学院" prop="schoolName"> </el-table-column>
            <el-table-column label="班级" prop="classnum"> </el-table-column>
            <el-table-column label="邮箱" prop="email"> </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped>
.el-card {
  border-radius: 15px;
}
.info {
  text-align: left;
}
p {
  color: rgb(0, 0, 0);
  position: relative;
}
.fade-transform-enter-active {
  transition: all 0.3s;
}
.fade-transform-leave-active {
  transition: all 0.3s;
}
.image {
  margin-top: -0.5cm;
}

.modify {
  margin-top: 1cm;
  margin-left: 5cm;
}

.pic {
  height: 45%;
  width: 45%;
  margin-top: 1cm;
  margin-left: 2.2cm;
}
</style>

<script>
import XLSX from "xlsx";
import { POSTStudentList } from "../../API/http";

export default {
  data() {
    return {
      userlist: [],
    };
  },

  methods: {
    handleExceed() {
      this.$message({
        type: "warning",
        message: "超出最大上传文件数量的限制！",
      });
      return;
    },
    upload(file, fileList) {
      console.log("file", file);
      console.log("fileList", fileList);
      let files = { 0: file.raw };
      this.readExcel1(files);
    },
    readExcel1(files) {
      console.log(files);
      if (files.length <= 0) {
        //如果没有文件名
        return false;
      } else if (!/\.(xls|xlsx)$/.test(files[0].name.toLowerCase())) {
        this.$Message.error("上传格式不正确，请上传xls或者xlsx格式");
        return false;
      }

      const fileReader = new FileReader();
      fileReader.onload = (ev) => {
        try {
          const data = ev.target.result;
          const workbook = XLSX.read(data, {
            type: "binary",
          });
          const wsname = workbook.SheetNames[0]; //取第一张表
          const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]); //生成json表格内容
          console.log("ws", ws);
          this.userlist = [];
          for (let i = 0; i < ws.length; ++i) {
            this.userlist.push({
                id: String(ws[i].id),
                email: ws[i].email,
                name: ws[i].name,
                classnum: String(ws[i].class),
                schoolName: ws[i].school
            })
          }
          console.log('user',this.userlist);

          this.$refs.upload.value = "";
        } catch (e) {
          return false;
        }
      };
      fileReader.readAsBinaryString(files[0]);
    },
    submit() {
      POSTStudentList(this.userlist)
        .then(() => {
          this.$message("上传成功");
          this.$router.go(-1);
        })
        .catch((err) => {
          console.log(err);
          this.$message("上传失败");
        });
    },
  },
};
</script>