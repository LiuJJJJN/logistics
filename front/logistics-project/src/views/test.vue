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
      this.$axios.post("http://localhost:8080/logisticsProject/test2.do")
          .then(resp=>{
            alert(resp);
          }, err=>{
            alert(err);
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
