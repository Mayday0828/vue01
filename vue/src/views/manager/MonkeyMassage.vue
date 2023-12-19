<template>
  <div>用户信息
    <div style="padding: 10px 0">
      <el-input style="width: 210px" suffix-icon="el-icon-search" placeholder="请输入用户名" v-model="leader"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">查 询</el-button>
      <el-button style="margin-left: 5px" type="primary" @click="reset">重 置</el-button>
    </div>
    <div style="padding: 10px 0">
      <el-button type="primary" @click="atAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="danger">删除 <i class="el-icon-remove-outline"></i></el-button>
      <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-card>
      <div>
        <el-table :data="monkeys" style="width: 100%">
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column prop="id" label="序号" width="100"></el-table-column>
          <el-table-column prop="leader" label="负责人" width="150" ></el-table-column>
          <el-table-column prop="name" label="猴子" width="170"></el-table-column>
          <el-table-column prop="health" label="状况" width="150"></el-table-column>
          <el-table-column prop="weight" label="体重" width="150"></el-table-column>
          <el-table-column fixed="right" label="操作" width="150">
            <template slot-scope="scope">
              <el-button type="danger" @click="Edit(scope.row)" size="small" plain>编 辑</el-button>
              <el-button type="warning" @click="delet(scope.row.id)" size="small" plain>删 除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="block" style="padding: 10px 0">
        <el-pagination
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5,10,15,20]"
            :page-size="pageSize"
            layout="total,prev, pager, next"
            :total="total">
        </el-pagination>
        <!--      弹窗-->
      </div>
      <el-dialog title="新增信息" :visible.sync="dialogTableVisible">
        <el-form :model="form" label-width="75px" style="padding-right: 25px" :rules="rules" ref="formRef">
          <el-form-item label="负责人" prop="leader">
            <el-input v-model="form.leader" placeholder="负责人"></el-input>
          </el-form-item>
          <el-form-item label="猴子" prop="name">
            <el-input v-model="form.name" placeholder="猴子"></el-input>
          </el-form-item>
          <el-form-item label="状况" prop="health">
            <el-input v-model="form.health" placeholder="状况" ></el-input>
          </el-form-item>
          <el-form-item label="体重" prop="weight">
            <el-input v-model="form.weight" placeholder="体重"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
      <!--    <div>-->
      <!--      <el-table :data="users">-->
      <!--        <el-table-column label="ID" prop="id"></el-table-column>-->
      <!--        <el-table-column label="用户名" prop="username"></el-table-column>-->
      <!--        <el-table-column label="姓名" prop="name"></el-table-column>-->
      <!--        <el-table-column label="地址" prop="address"></el-table-column>-->
      <!--        <el-table-column label="操作" ></el-table-column>-->
      <!--      </el-table>-->
      <!--    </div>-->
    </el-card>

  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "MonkeyMassage",
  data(){
    return{
      monkeys:[],
      pageNum:1,   //当前页码
      pageSize:5,    //每一页的数量
      total: 0,
      leader: '',
      name: '',
      dialogTableVisible: false,
      form:{},
      monkey: JSON.parse(localStorage.getItem('honey-monkey') || '{}'),
      rules: {
        username: [
          { required: true,message: '请输入用户名', triangle: 'blue' }
        ]
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    delet(id){
      this.$confirm('确认删除','确认删除',{type: "warning"}).then(res =>{
        this.$request.delete('/monkey/delete/' + id).then(res =>{
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)                         //刷新页面数据
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() =>{})
    },
    Edit(row) {    //编辑方法
      this.form = JSON.parse(JSON.stringify(row))    //给form对象赋值，进行深拷贝
      this.dialogTableVisible = true          //打开弹窗
    },
    atAdd(){                     //新增方法
      this.form={}
      this.dialogTableVisible=true     //打开弹窗
    },
    save(){       //保存触发新增和更新
      this.$refs.formRef.validate((valid) =>{
        if(valid){
          this.$request({
            url: this.form.id ? '/monkey/update' : '/monkey/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then( res =>{
            if(res.code==='200'){
              this.$message.success('保存成功')
              this.load(1)
              this.dialogTableVisible = false  //关闭弹窗
            }else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    reset(){
      this.leader=''
      this.load()
    },
    load(){
      this.$request.get('/monkey/selectByPage',{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          leader: this.leader,
          name: this.name
        }
      }).then(res =>{
        this.monkeys = res.data.records
        this.total = res.data.total
      })
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    }
  }

}
</script>

<style scoped>

</style>

