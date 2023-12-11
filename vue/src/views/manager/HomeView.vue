<template>
<div>
  <div style="box-shadow: 0 0 10px rgba(0,0,0,0.1);padding: 10px 20px;border-radius: 10px;margin-bottom: 10px">
    {{ user.username }}越来越好，越来越幸运，所有考试都通过！！！！！！！！！！！！
  </div>
  <div style="display: flex">
    <el-card style="width: 100%">
      <div slot="header" class="clearfix">
        <span>javaWeb</span>
      </div>
      <div>
        <span>2023年12月</span>
        <div style="margin-top: 20px">
          <div style="margin: 10px 0"><strong>主题</strong></div>
          <el-button type="primary">按钮</el-button>
          <el-button type="success">按钮</el-button>
          <el-button type="info">按钮</el-button>
          <el-button type="warning">按钮</el-button>
          <el-button type="danger">按钮</el-button>
        </div>
      </div>
    </el-card>

    <el-card style="width: 50%;margin-right: 20px">
      <div>
        <span>渲染数据</span>
      </div>
      <div>
        <el-table :data="users">
          <el-table-column label="ID" prop="id"></el-table-column>
          <el-table-column label="用户名" prop="username"></el-table-column>
          <el-table-column label="姓名" prop="name"></el-table-column>
          <el-table-column label="地址" prop="address"></el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
    }
  },
  created() {
  },
  methods() {

  }
}
</script>

<style scoped>

</style>
