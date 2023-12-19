<template>
<div>
  <div style="padding: 10px 0">
    <el-input style="width: 210px" suffix-icon="el-icon-search" placeholder="请输入"></el-input><el-button style="margin-left: 5px" type="primary">搜索</el-button>
  </div>

  <div>
    <el-row>
      <el-col :span="8" style="padding-right: 10px">
        <el-card class="box-card">
          <div style="display: flex;align-items: center;">
            <img src="@/assets/img_4.png" style="margin-right: 40px;width: 150px;height: 150px;border-radius: 50%;">
            <div>
              <p style="font-size: 32px;margin-bottom: 10px;">{{ user.name}}</p>
              <p style="color: #999999;">{{ user.role }}</p>
            </div>
          </div>
        </el-card>
        <el-card style="margin-top: 20px;height: 400px">
          <div style="width: 100%">
            <div style="margin: 10px 0">
              <el-carousel height="400px">
                <el-carousel-item v-for="item in imgs" :key="item.src">
<!--                  <img :src="item" style="width: 100%">-->
                  <img :src="item" style="width: 100%">
                  <span style="font-size: 15px;color: lightslategrey">黑叶猴是灵长目猴科乌叶猴属哺乳动物,又称乌猿，乌叶猴，岩蛛猴，猿吊猴，黑蛛猴等。
                    体形纤瘦，四肢细长，头小尾巴长；体毛长而厚密，有光泽。
                    头顶有黑色直立的毛冠；两颊至耳基部有白毛；成体全身乌黑色；手、足具乌黑扁平指（趾）甲；尾尖端白色，无颊囊，臀部有两小块白色胼胝。</span>
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16" style="padding-left: 10px">
        <div style="display: flex;flex-wrap: wrap;justify-content: space-between">
          <el-card v-for="item in cuntData" :key="item.name" :body-style="{ display:'flex',padding:0 }" ref="card" @click="showDetail(item.name)" style="width: 32%;margin-bottom: 20px">
            <i class="icon" :class="`el-icon-${item.icon}`" :style="{ background: item.color }"></i>
            <div style="margin-left: 15px; display: flex;flex-direction: column;justify-content: center">
              <p style="font-size: 30px;margin-bottom: 10px">{{ item.name }}</p>

            </div>
          </el-card>
           <!--  折线图-->
          <el-card style="width: 100%">
            <div style="height: 350px" id="line">
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
<!--    <el-dialog :visible.sync="showDialog" title="详细信息">-->
<!--&lt;!&ndash;      <p>{{ selectedData.name }}</p>&ndash;&gt;-->
<!--      <p>{{ selectedData.name }}</p>-->
<!--      <p>kkkkkkk</p>-->
<!--&lt;!&ndash;      <p>{{ selectedData.value }}</p>&ndash;&gt;-->
<!--      &lt;!&ndash; 其他信息 &ndash;&gt;-->
<!--    </el-dialog>-->
  </div>
</div>
</template>

<script>
import * as echarts from 'echarts';

const option = {
  title: {
    text: '猴子数量'
  },
  tooltip: {
    trigger: 'axis'
  },
  xAxis: {
    type: 'category',
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [820, 932, 901, 934, 1290, 1330, 1320],
      type: 'line',
      smooth: true
    }
  ]
};

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
      ],
    }
  },
  // created() {
  // },
  //等待页面元素全部加载完成之后初始化
  mounted() {
    let lineDom = document.getElementById('line');
    let myChart = echarts.init(lineDom);
    myChart.setOption(option)
  },
  // methods() {
  //
  //   showDetail(item){
  //     this.selectedData = item;
  //     this.showDialog = true;
  //   }
  // }
  methods: {
    handleFileUpload(response,file,fileList){
      this.filelist=fileList
    },
    showCard(index) {
      this.$refs.card[index].$emit('click');
    },
    showDetail(eventName) {
      console.log('展示公告详情：', eventName);
    }
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
