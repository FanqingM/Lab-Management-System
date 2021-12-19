<template>
  <el-card>
    <el-container style="height: calc(100% - 60px)">
      <el-aside style="width: auto">
        <el-menu
          class="el-menu-vertical-demo"
          default-active="/OrgFrame/Main"
          router
          active-text-color="#409EFF"
          :collapse="isCollapse"
          style="height: 100%"
        >
          <el-menu-item index="/student/course/">
            <span slot="title">实验</span>
          </el-menu-item>
          <el-menu-item index="/student/course/">
            <span slot="title">成绩</span>
          </el-menu-item>
          <el-menu-item index="/student/course/">
            <span slot="title">文件</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main
        style="height: 100%; overflow: auto; background: rgb(237, 241, 245)"
      >
        <keep-alive>
          <transition name="fade-transform" mode="out-in">
            <router-view style="height: 100%"></router-view>
          </transition>
        </keep-alive>
      </el-main>
    </el-container>
  </el-card>
</template>


<script scoped>

export default {
  created() {
    
  },
  data() {
    return {
        courseId: this.$route.params.courseId
    };
  },
  methods: {},
};
</script>
