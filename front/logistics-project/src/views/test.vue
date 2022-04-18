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
      this.$axios.get("http://localhost:8080/logisticsProject/test1.do")

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
