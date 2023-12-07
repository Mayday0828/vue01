<template>
  <div>
<!--    <el-row>-->
<!--      <el-col :span="12">-->
<!--        <div style="width: 100%;height: 300px;background-color: lightslategrey"></div>-->
<!--      </el-col>-->
<!--      <el-col :span="12">-->
<!--        <div style="width: 100%;height: 300px;background-color: red"></div>-->
<!--      </el-col>-->
<!--    </el-row>-->

    <el-menu router style="border: none" :default-active="$route.path">
      <el-menu-item index="/">
        <template slot="title">
          <i class="el-icon-house"></i>
          <span>系统首页</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/element">Element页面</el-menu-item>
      <el-menu-item>系统首页</el-menu-item>
      <el-menu-item>系统首页</el-menu-item>
      <!--   二级菜单-->
      <el-submenu>
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>信息管理</span>
        </template>
        <el-menu-item>用户信息</el-menu-item>
      </el-submenu>
    </el-menu>


    <el-row>
      <el-col :span="6">
        <div style="padding: 10px;border: 1px solid #ccc;text-align: center">
          <img style="width: 50%" src="@/assets/logo.png">
          <div style="text-align: center">这是关于Element的描述</div>
          <div style="color: gold">价格 $99.00</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div style="padding: 10px;border: 1px solid #ccc;text-align: center">
          <img style="width: 50%" src="@/assets/logo.png">
          <div style="text-align: center">这是关于Element的描述</div>
          <div style="color: gold">价格 $99.00</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div style="padding: 10px;border: 1px solid #ccc;text-align: center">
          <img style="width: 50%" src="@/assets/logo.png">
          <div style="text-align: center">这是关于Element的描述</div>
          <div style="color: gold">价格 $99.00</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div style="padding: 10px;border: 1px solid #ccc;text-align: center">
          <img style="width: 50%" src="@/assets/logo.png">
          <div style="text-align: center">这是关于Element的描述</div>
          <div style="color: gold">价格 $99.00</div>
        </div>
      </el-col>
<!--      <el-col :span="6"><img style="width: 100%" src="@/assets/logo.png"></el-col>-->
<!--      <el-col :span="6"><img style="width: 100%" src="@/assets/logo.png"></el-col>-->
<!--      <el-col :span="6"><img style="width: 100%" src="@/assets/logo.png"></el-col>-->
    </el-row>

    <el-row style="margin-top: 30px">
      <el-col :span="24">
      <!--  默认按钮 primary success  info  warning danger -->
        <el-button>默认按钮</el-button>
        <el-button type="primary">主要按钮</el-button>
        <el-button type="success">成功按钮</el-button>
        <el-button type="info">信息按钮</el-button>
        <el-button type="warning">警告按钮</el-button>
        <el-button type="danger">危险按钮</el-button>
      <!--朴素按钮 plain-->
        <el-button type="success" plain>按钮</el-button>

      <!--   圆角按钮 round-->
        <el-button type="primary" round>主要按钮</el-button>
        <el-button type="success" round>成功按钮</el-button>
      <!--  图标      -->
        <el-button  icon="el-icon-edit" type="primary" circle></el-button>
        <el-button  icon="el-icon-check" type="warning" circle></el-button>

      </el-col>
    </el-row>

    <!--  表单 -->
    <!--  v-model  rows  textarea show-password   -->
    <!--  prefix-icon suffix-icon  -->
    <el-row style="margin-top: 30px">
      <el-col>
        <el-input style="width: 200px;margin-right: 30px" v-model="value" placeholder="请输入"></el-input>
        <el-input type="textarea" :rows="2" style="width: 200px" v-model="value" placeholder="请输入"></el-input>
        <el-input show-password style="width: 200px" v-model="password" placeholder="请输入"></el-input>
        <el-input style="width: 200px" v-model="value" placeholder="请输入" prefix-icon="el-icon-search"></el-input>
        <el-input clearable style="width: 200px" v-model="value" placeholder="请输入" suffix-icon="el-icon-user"></el-input>
        <el-button icon="el-icon-search" type="primary">搜索</el-button>
<!--        <textarea style="font-size:23px">描述：</textarea>-->
      </el-col>
    </el-row>

    <el-row style="margin-top: 30px">
      <el-autocomplete
          v-model="state2"
          :fetch-suggestions="querySearch"
          placeholder="请输入内容"
          :trigger-on-focus="false"

      ></el-autocomplete>
    </el-row>

    <!-- 下拉框-->
    <el-row>
      <el-select style="margin-top: 20px;margin-right: 20px" v-model="select" @change="changeSelect">
        <el-option value="苹果"></el-option>
        <el-option value="梨子"></el-option>
        <el-option value="柚子"></el-option>
      </el-select>

<!--      <el-select style="margin-top: 20px" v-model="select1" @change="changeSelect">-->
<!--        <el-option v-for="item in fruits" :key="item.id" :label="item.name" :value="item.name"></el-option>-->
<!--      </el-select>-->

<!--      <el-select style="margin-top: 20px" v-model="select2" @change="changeSelectUser">-->
<!--        <el-option v-for="item in user" :key="item.card" :label="item.name" :value="item.card"></el-option>-->
<!--      </el-select>-->
    </el-row>

  </div>
</template>


<script>
export default {
  name : 'Element',
  //函数function，return返回值，把对象内容return
  data() {
    return{
      value : '',
      password : '',
      state2 : '',
      coffees: [{ value: '1星巴克咖啡' },{ value: '2瑞幸咖啡' },{ value: '3库底咖啡' }],
      select : ''
      // select1 : '',
      // select2 : '',
      // fruits : [
      //   {name: '苹果',id:1},
      //   {name: '梨子',id:2},
      //   {name: '柚子',id:3}
      // ],
      // user : [
      //   {name: '张三', card :'123456'},
      //   {name: '张四', card :'125648'},
      //   {name: '张五', card :'125876'},
      // ],
    }
  },
  methods :{
    querySearch(query,cb) {
      // let restaurants = this.restaurants;
      let results = query ? this.coffees.filter(v => v.value.includes(query) ): this.coffees
      console.log(results)
      // 调用 callback 返回建议列表的数据
      cb(results);
    }
  },
  changeSelect() {
    console.log(this.select)
  },
  // changeSelectUser(){
  //   console.log(this.select2)
  // }
}
</script>