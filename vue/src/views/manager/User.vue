<template>
<!--<div>用户信息</div>-->
  <div style="padding: 10px 0">
    <el-input style="width: 210px" suffix-icon="el-icon-search" placeholder="请输入"></el-input><el-button style="margin-left: 5px" type="primary">搜索</el-button>
  </div>
  <div style="padding: 10px 0">
    <el-button type="primary">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    <el-button type="danger">删除 <i class="el-icon-remove-outline"></i></el-button>
    <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
    <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
  </div>


</template>

<script>
export default {
  name: "User"
}
</script>

<style scoped>

</style>
