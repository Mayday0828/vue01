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
       <el-menu :collapse="isCollapse" :collapse-transition="false" router text-color="rgba(255,255,255,0.65)" active-text-color="#fff"
                background-color="#001529" style="border: none" :default-active="$route.path">
         <el-menu-item index="/index/home">
             <i class="el-icon-s-home"></i>
             <span slot="title">系统首页</span>
         </el-menu-item>
<!--         <el-menu-item index="/element">Element页面</el-menu-item>-->
          <!--   二级菜单-->
         <el-submenu index="info" v-if="user.role === '管理员'">
           <template slot="title">
             <i class="el-icon-menu"></i>
             <span>信息管理</span>
           </template>
           <el-menu-item index="/index/user">用户信息</el-menu-item>
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
             <span>{{ user.username}}
             <i class="el-icon-arrow-down el-icon--right"></i>
             </span>
             <el-dropdown-menu slot="dropdown">
               <el-dropdown-item><div @click="$router.push('/index/person')">个人信息</div></el-dropdown-item>
               <el-dropdown-item>修改密码</el-dropdown-item>
               <el-dropdown-item><div @click="$router.push('/')">退出登录</div></el-dropdown-item>
             </el-dropdown-menu>
           </el-dropdown>
         </div>
       </el-header>

       <el-main>
         <router-view />
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
  name: 'Manager',
  data() {
    return {
      isCollapse: false,  //不收缩
      asideWidth: '200px',
      collapseIcon: 'el-icon-s-fold',
      users: [],
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
      url: '',
      urls: []
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
