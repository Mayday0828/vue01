<template>
  <div style="height: 100vh;display: flex; align-items: center;justify-content: center;background-color: cornflowerblue">
    <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
      <div style="flex: 1;">
        <img src="../assets/img_1.png" alt="" style="width:100%;height: 100%">
      </div>
      <div style="flex: 1;display: flex; align-items: center;justify-content: center">
        <el-form :model="user" style="width: 80%" :rules="rules" ref="registerRef">
          <div style="font-size: 20px;font-weight: bold;text-align: center; margin-bottom: 20px">欢迎注册</div>
          <el-form-item prop="username">
            <el-input  prefix-icon="el-icon-user" size="medium" placeholder="请输入账号：" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请输入密码：" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item prop="confirmPass">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请确认密码：" v-model="user.confirmPass"></el-input>
          </el-form-item>
          <el-form-item prop="role">
            <el-radio-group v-model="user.role">
              <el-radio label="用户"></el-radio>
              <el-radio label="管理员"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button type="info" style="width: 100%" @click="register">注 册</el-button>
          </el-form-item>
          <div style="display: flex">
            <div style="flex: 1">已有账号？请<span style="color: blue; cursor: pointer" @click="$router.push('/')">登录</span></div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>
<template>
  <el-card style="width: 500px;">
    <el-form label-width="80px" size="small">
      <el-upload
          class="avatar-uploader"
          action="http://localhost:9090/file/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
      >
        <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>

      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>



export default {
  name: 'RegisterView',
  data () {
    const validatePassword = (rule, confirmPass, callback) => {
      if(confirmPass === '') {
        callback(new Error('请确认密码'));
      } else if ( confirmPass !== this.user.password ) {
        callback(new Error('两次密码不一致'));
      } else {
        callback();
      }
    }
    // 验证码校验
    return {
      user: {
        confirmPass: '',
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur'},
        ],
        confirmPass: [
          { validator: validatePassword, trigger: 'blur' }
        ],
        role: [
          { required: true, message: '请选择 ', trigger: 'blur'},
        ],
      }
    }
  },
  created () {
  },
  methods: {
    register() {
      this.$refs['registerRef'].validate((valid) => {
        if (valid) {
          // 验证通过,valid是布尔值
          this.$request.post('/register', this.user).then(res => {
            if (res.code === '200') {
              this.$router.push('/')
              this.$message.success('注册成功')
            }else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>
<style scoped>

</style>
