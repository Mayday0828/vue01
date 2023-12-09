<template>
  <div style="height: 100vh;display: flex; align-items: center;justify-content: center;background-color: antiquewhite">
    <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
      <div style="flex: 1;">
        <img src="../assets/img.png" alt="" style="width:100%;height: 100%">
      </div>
      <div style="flex: 1;display: flex; align-items: center;justify-content: center">
        <el-form :model="user" style="width: 80%" :rules="rules" ref="loginRef">
          <div style="font-size: 20px;font-weight: bold;text-align: center; margin-bottom: 20px">欢迎登录</div>
          <el-form-item prop="username">
            <el-input  prefix-icon="el-icon-user" placeholder="请输入账号：" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" show-password placeholder="请输入密码：" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item prop="validCode">
            <div style="display: flex">
              <el-input prefix-icon="el-icon-circle-check" size="medium" style="flex: 1" v-model="user.code"></el-input>
              <div style="flex: 1;height: 40px">
                <valid-code @input="getCode"/>
              </div>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="login">登录</el-button>
          </el-form-item>
          <div style="display: flex">
            <div style="flex: 1">还没有账号？请<span style="color: blue; cursor: pointer">注册</span></div>
            <div style=" text-align: right"><span style="color: blue; cursor: pointer">忘记密码</span></div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import ValidCode from '@/components/ValidCode.vue'

export default {
  name: 'LoginView',
  components: {
    ValidCode
  },
  data () {
    return {
      code: '', // 验证码组件传过来的code
      user: {
        code: '', // 表单输入的验证码
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, messages: 'd12', trigger: 'blur'},
        ],
        password: [
          { required: true, messages: '请输入密码', trigger: 'blur'},
        ],
        ValidCode: [
          { required: true, messages: '请输入账号', trigger: 'blur'},
        ],
      }
    }
  },
  created () {
  },
  methods: {
    getCode (code) {
      console.log(code)
      this.code = code
    },
    login () {
      this.$request.post('/login', this.user).then(res =>{
        console.log(res)
      })
    }
  }
}
</script>
<style scoped>

</style>
