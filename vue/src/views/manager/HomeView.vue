<template>
<div>
  <div style="padding: 10px 0">
    <el-input style="width: 210px" suffix-icon="el-icon-search" placeholder="请输入"></el-input><el-button style="margin-left: 5px" type="primary">搜索</el-button>
  </div>

  <div>
    <el-row>
      <el-col :span="8">
        <el-card class="box-card">
          <div style="display: flex;align-items: center;">
            <img src="@/assets/img_4.png" style="margin-right: 40px;width: 150px;height: 150px;border-radius: 50%;">
            <div>
              <p style="font-size: 32px;margin-bottom: 10px;">Admin</p>
              <p style="color: #999999;">管理员</p>
            </div>
          </div>
        </el-card>
        <el-card style="margin-top: 20px;height: 300px">
          <div style="width: 100%">
            <div style="margin: 10px 0">
              <el-carousel height="400px">
                <el-carousel-item v-for="item in imgs" :key="item.src">
<!--                  <img :src="item" style="width: 100%">-->
                  <img :src="item" style="width: 100%">
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16">
        <div style="display: flex;flex-wrap: wrap;justify-content: space-between">
          <el-card v-for="item in cuntData" :key="item.name" :body-style="{ display:'flex',padding:0 }" style="width: 32%;margin-bottom: 20px">
            <i class="icon" :class="`el-icon-${item.icon}`" :style="{ background: item.color }"></i>
            <div style="margin-left: 15px; display: flex;flex-direction: column;justify-content: center">
              <p style="font-size: 30px;margin-bottom: 10px">{{ item.name }}</p>
              <!--                      <p style="font-size: 14px;color: #999;text-align: center">{{ item.value }}</p>-->
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
      imgs: [
        require('@/assets/img_1.png'),
        require('@/assets/img_2.png'),
        require('@/assets/img_3.png')
      ],
      cuntData:[
        {
          name: "猕猴",
          value:123,
          icon:"success",
          color:"#2ec7c9"
        },
        {
          name: "懒猴",
          value:123,
          icon:"success",
          color:"#ffb980"
        },
        {
          name: "蜘蛛猴",
          value:123,
          icon:"success",
          color:"#5ab1ef"
        },
        {
          name: "金丝猴",
          value:123,
          icon:"success",
          color:"#2ec7c9"
        },
        {
          name: "山魈",
          value:123,
          icon:"success",
          color:"#2ec7c9"
        },
        {
          name: "黑叶猴",
          value:123,
          icon:"success",
          color:"#2ec7c9"
        }
      ]
    }
  },
  created() {
  },
  methods() {

  }
}
</script>

<style scoped>
.icon{
  width: 80px;
  height: 80px;
  font-size: 30px;
  text-align: center;
  line-height: 80px;
  color: #ffffff;
}


</style>
