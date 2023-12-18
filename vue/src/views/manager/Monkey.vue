<template>
  <div class="product">
    <el-container>
      <el-row>
        <el-col :span="8" v-for="product in displayedProducts" :key="product.id">
          <el-card class="product-item" shadow="hover">
            <el-image
                style="width: 100%; height: 200px; object-fit: cover;"
                :src="require(`@/assets/${product.imageSrc}`)"
                fit="cover"
            />
            <div slot="header" class="clearfix">
              <h5 class="card-title" style="font-size: 14px">{{ product.name }}</h5>
            </div>
            <p class="card-text">{{ product.description }}</p>
            <div class="button-group">
              <el-button type="primary" herf="">前往</el-button>
              <el-button type="info" @click="">put cart</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-container>
  </div>



</template>

<script>
// import '@/assets/css/imgede.css'

export default {
  name: "Monkey",
  data() {
    return {
      products: [
        { id: 1, name: '猕 猴', description: 'Some quick example text to build on the card title and make up the bulk of the card\'s content.', imageSrc: 'img_11.png' },
        { id: 1, name: '懒 猴', description: 'Some quick example text to build on the card title and make up the bulk of the card\'s content.', imageSrc: 'img_6.png' },
        { id: 1, name: '蜘蛛猴', description: 'Some quick example text to build on the card title and make up the bulk of the card\'s content.', imageSrc: 'img_7.png' },
        { id: 1, name: '金丝猴', description: 'Some quick example text to build on the card title and make up the bulk of the card\'s content.', imageSrc: 'img_8.png' },
        { id: 1, name: '山 魈', description: 'Some quick example text to build on the card title and make up the bulk of the card\'s content.', imageSrc: 'img_9.png' },
        { id: 1, name: '黑叶猴', description: 'Some quick example text to build on the card title and make up the bulk of the card\'s content.', imageSrc: 'img_10.png' },
      ],
      currentPage: 1,
      pageSize: 9, // 每页显示数量
    };
  },
  computed: {
    // 计算属性，根据当前页码和每页显示数量计算显示的产品列表
    displayedProducts() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.products.slice(startIndex, endIndex);
    },
    // 计算总页数
    totalPages() {
      return Math.ceil(this.products.length / this.pageSize);
    },
  },

  methods: {
    // 上一页按钮点击事件
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    // 下一页按钮点击事件
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    // 跳转到指定页码
    gotoPage(page) {
      this.currentPage = page;
    },
  },


}
</script>

<style scoped>

.product-item {
  margin-bottom: 20px;
}

.button-group {
  margin-top: 10px;
  text-align: center;
}


</style>
