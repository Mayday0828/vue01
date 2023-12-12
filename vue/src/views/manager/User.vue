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

  <el-card>
    <div>
      <span>用户信息</span>
      <el-table :data="users"   style="width: 100%">
        <el-table-column prop="id" label="Id" width="180"></el-table-column>
        <el-table-column prop="username" label="用户名" width="180" ></el-table-column>
        <el-table-column prop="name" label="姓名" width="180"></el-table-column>
        <el-table-column prop="address" label="地址" width="200"></el-table-column>
        <el-table-column>
          <template slot-scope="scope">
            <el-button type="danger">编 辑</el-button>
            <el-button type="warning">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block" style="padding: 10px 0">
      <el-pagination
          :page-sizes="[5,10,15,20]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="100">
      </el-pagination>
    </div>
    <div>
      <el-table :data="users">
        <el-table-column label="ID" prop="id"></el-table-column>
        <el-table-column label="用户名" prop="username"></el-table-column>
        <el-table-column label="姓名" prop="name"></el-table-column>
        <el-table-column label="地址" prop="address"></el-table-column>
        <el-table-column label="操作" ></el-table-column>
      </el-table>
    </div>
  </el-card>




</template>

<script>

import request from "@/utils/request";

export default {
  name: "User",
  data(){
    return{
      users:[]
    }
  },
  mounted() {   // 页面加载完成之后触发
    request.get('/user/selectAll').then(res =>  {
      this.users=res.data
    })
  },
}
</script>

<style scoped>

</style>
