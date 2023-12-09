<template>
  <div>
   <el-container>
    <!--     侧边栏，导航条-->
    <!--  min-height: 100vh最小高度，vh   -->
<!-- 绑定动态属性 :width="asideWidth"   -->
     <el-aside  :width="asideWidth" style="min-height: 100vh;background-color: #001529">
       <div style="height: 60px;color: white;display: flex;align-items: center;justify-content: center">
         <img src="">logo
       </div>
       <el-menu :collapse="isCollapse" :collapse-transition="false" router text-color="rgba(255,255,255,0.65)" active-text-color="#fff" background-color="#001529" style="border: none" :default-active="$route.path">
         <el-menu-item index="/">
             <i class="el-icon-house"></i>
             <span slot="title">系统首页</span>
         </el-menu-item>
         <el-menu-item index="/1">
           <template slot="title">
             <i class="el-icon-house"></i>
             <span>系统首页</span>
           </template>
         </el-menu-item>
<!--         <el-menu-item index="/element">Element页面</el-menu-item>-->
          <!--   二级菜单-->
         <el-submenu>
           <template slot="title">
             <i class="el-icon-menu"></i>
             <span>信息管理</span>
           </template>
           <el-menu-item>用户信息</el-menu-item>
           <el-menu-item>用户管理</el-menu-item>
         </el-submenu>
       </el-menu>
     </el-aside>

     <el-container>
      <!--       头部-->
       <el-header>
         <i :class="collapseIcon" style="font-size: 26px" @click="handleCollapse"></i>
         <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-left: 20px">
           <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
           <el-breadcrumb-item :to="{ path: '/user' }">用户管理</el-breadcrumb-item>
         </el-breadcrumb>

         <div style="flex: 1;width: 0;display: flex;align-items: center;justify-content: flex-end">
           <el-dropdown placement="bottom">
             <span>用户
             <i class="el-icon-arrow-down el-icon--right"></i>
             </span>
             <el-dropdown-menu slot="dropdown">
               <el-dropdown-item>个人信息</el-dropdown-item>
               <el-dropdown-item>修改密码</el-dropdown-item>
               <el-dropdown-item>退出登录</el-dropdown-item>
             </el-dropdown-menu>
           </el-dropdown>
         </div>
       </el-header>

       <el-main>主体
         <div style="box-shadow: 0 0 10px rgba(0,0,0,0.1);padding: 10px 20px;border-radius: 10px;margin-bottom: 10px">
           越来越好，越来越幸运，所有考试都通过！！！！！！！！！！！！
         </div>
         <el-card style="width: 500px">
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
       </el-main>
     </el-container>
   </el-container>

  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    return {
      isCollapse: false,  //不收缩
      asideWidth: '200px',
      collapseIcon: 'el-icon-s-fold'
    }
  },
  mounted() {   // 页面加载完成之后触发
    request.get('/user/selectAll').then(res =>  {
      this.users=res.data
    })
  },
  methods :{
    handleCollapse(){
      this.isCollapse = !this.isCollapse
      this.asideWidth = this.isCollapse ? '64px' : '200px'
      this.collapseIcon = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
    }
  }
  // components: {
  //   HelloWorld
  // }
}
</script>

<style>
/*导航条的设置样式*/
.el-menu--inline {
  background-color: #000c17 !important;
}
.el-menu--inline .el-menu-item{
   background-color: #000c17 !important;
   padding-left: 49px !important;
 }
.el-menu-item:hover,.el-submenu__title:hover{
  color: #fff !important;
}
.el-submenu__title:hover i{
  color: #fff !important;
}
.el-menu-item.is-active{
  background-color: #40a9ff !important;
  border-radius: 5px !important;
  width: calc(100% - 8px);
  margin-left: 4px;
  /*margin: 4px !important;*/
}
.el-menu-item.is-active i, .el-menu-item.is-active .el-tooltip {
  margin-left: -4px;
}
.el-menu-item {
  /*margin: 4px !important;*/
  height: 40px !important;
  line-height: 40px !important;
}
.el-submenu__title{
  /*margin: 4px !important;*/
  height: 40px !important;
  line-height: 40px !important;
}

/*动画收缩+阴影*/
.el-aside{
  transition: width .3s;
  box-shadow: 2px 0 6px rgba(0,21,41,35);
}

/*头部样式,默认高度60*/
.el-header{
  box-shadow: 2px 0 6px rgba(0,21,41,35);
  display: flex;
  align-items: center;
}
</style>
