<template>
  <div>
    <el-card>

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

      }
  }
}
</script>

<style scoped>

</style>
