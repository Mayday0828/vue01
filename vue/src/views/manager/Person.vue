<template>
  <div>
    <el-card style="width: 50%">
      <el-form :model="user" label-width="75px" style="padding-right: 25px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="用户名" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="user.phone" ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input type="textarea" v-model="user.address"></el-input>
        </el-form-item>
        <div style="text-align: center;margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('honey-user') || '{}')
    }
  },
  created() {
  },
  methods:{
      load() {
      },
    //保存当前用户信息到数据库，更新浏览器缓存用户
      update(){
        this.$request.put('/user/update',this.user).then(res =>{
          if (res.code === '200'){
            this.$message.success('保存成功')

            localStorage.setItem('honey-user',JSON.stringify(this.user))
          }else {
            this.$message.error(res.msg)
          }
        })
      }
  }
}
</script>

<style scoped>
/deep/.el-form-item__label{
  font-weight: bold;
}

</style>
