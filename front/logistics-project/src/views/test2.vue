<template>
  <h1>test2</h1>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "test2",
  data(){
    return{

    }
  },
  methods:{
    test:function (){
      var nowTimestamp = new Date().getTime();
      alert(nowTimestamp);
    }
  },
  created() {
    this.test();
  }
}
</script>

<style scoped>

</style>