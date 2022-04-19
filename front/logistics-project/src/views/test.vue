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
      this.$store.commit("REMOVE_INFO");
      alert(this.$store.getters.getToken);
    }
  },
  created() {
    this.testMethods()
  }
}
</script>

<style scoped>

</style>
