<template>
  <span>{{test}}</span>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "test",
  data() {
    return {
      test: ''
    }
  },
  methods:{
    testMethods(){
<<<<<<< HEAD
      this.$axios.get("http://localhost:8080/logisticsProject/test1.do")

=======
      this.$axios.get("http://localhost:8080/logisticsProject/login.do",
          {
            username:"mike",
            password:"123456"
          })
>>>>>>> 036616400b6140916778e298878fdf770acf83de
          .then(resp=>{
            this.test = resp.data;
          })
    }
  },
  created() {
    this.testMethods()
  }
}
</script>

<style scoped>

</style>
